package web;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import people.Person;
import people.Student;
import people.Tutor;
import rating.Comment;
import rating.Rateable;
import rating.Rating;
import session.Course;
import session.Session;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Small local browser interface for the CSE 1325 MavTutor model.
 * The existing Java model classes remain responsible for courses, people,
 * tutoring sessions, and ratings. This class only provides HTTP and HTML.
 */
public final class MavTutorWeb {
    private final List<Course> courses = new ArrayList<>();
    private final List<Student> students = new ArrayList<>();
    private final List<Tutor> tutors = new ArrayList<>();
    private final List<Session> sessions = new ArrayList<>();
    private final Path dataFile = Path.of(System.getProperty("user.home"), ".mavtutor-web-data.txt");

    public static void main(String[] args) throws Exception {
        int port = Integer.parseInt(System.getenv().getOrDefault("PORT", "8080"));
        MavTutorWeb app = new MavTutorWeb();
        app.load();
        HttpServer server = HttpServer.create(new InetSocketAddress("127.0.0.1", port), 0);
        server.createContext("/", app::handle);
        server.start();
        System.out.println("MavTutor is running at http://127.0.0.1:" + port);
        System.out.println("Data file: " + app.dataFile);
    }

    private void handle(HttpExchange exchange) throws IOException {
        try {
            String path = exchange.getRequestURI().getPath();
            String method = exchange.getRequestMethod();
            if (method.equals("GET") && path.equals("/styles.css")) {
                send(exchange, 200, "text/css; charset=utf-8", Files.readString(Path.of("web", "styles.css")));
            } else if (method.equals("GET") && path.equals("/backup")) {
                send(exchange, 200, "text/plain; charset=utf-8", dataText(),
                        "attachment; filename=mavtutor-backup.txt");
            } else if (method.equals("GET") && path.equals("/")) {
                Map<String, String> query = parse(exchange.getRequestURI().getRawQuery());
                send(exchange, 200, "text/html; charset=utf-8", page(query.getOrDefault("page", "overview"), query.get("message")));
            } else if (method.equals("POST")) {
                Map<String, String> form = parse(new String(exchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8));
                handlePost(exchange, path, form);
            } else {
                send(exchange, 404, "text/plain; charset=utf-8", "Not found");
            }
        } catch (IllegalArgumentException | IndexOutOfBoundsException ex) {
            redirect(exchange, "overview", "Please check the form: " + ex.getMessage());
        } catch (Exception ex) {
            ex.printStackTrace();
            redirect(exchange, "overview", "Could not complete that action. " + ex.getMessage());
        }
    }

    private void handlePost(HttpExchange exchange, String path, Map<String, String> form) throws IOException {
        switch (path) {
            case "/course" -> {
                Course course = new Course(required(form, "dept").toUpperCase(), Integer.parseInt(required(form, "number")));
                if (courses.contains(course)) throw new IllegalArgumentException("That course already exists.");
                courses.add(course);
                save();
                redirect(exchange, "courses", "Course added.");
            }
            case "/student" -> {
                Student student = new Student(required(form, "name"), required(form, "email"));
                for (String value : form.getOrDefault("courses", "").split(",")) {
                    if (!value.isBlank()) student.addCourse(courses.get(Integer.parseInt(value)));
                }
                students.add(student);
                save();
                redirect(exchange, "students", "Student added.");
            }
            case "/tutor" -> {
                Course course = courses.get(Integer.parseInt(required(form, "course")));
                Tutor tutor = new Tutor(required(form, "name"), required(form, "email"), form.getOrDefault("bio", ""), course);
                tutors.add(tutor);
                save();
                redirect(exchange, "tutors", "Tutor added.");
            }
            case "/session" -> {
                Course course = courses.get(Integer.parseInt(required(form, "course")));
                Tutor tutor = tutors.get(Integer.parseInt(required(form, "tutor")));
                Session session = new Session(course, tutor);
                session.setSchedule(required(form, "date"), required(form, "time"), Long.parseLong(required(form, "duration")));
                for (String value : form.getOrDefault("students", "").split(",")) {
                    if (!value.isBlank()) session.addStudent(students.get(Integer.parseInt(value)));
                }
                sessions.add(session);
                save();
                redirect(exchange, "sessions", "Session scheduled.");
            }
            case "/review" -> {
                Rateable target = target(required(form, "type"), Integer.parseInt(required(form, "target")));
                Person author = person(required(form, "authorType"), Integer.parseInt(required(form, "author")));
                Comment comment = new Comment(required(form, "comment"), author, null);
                target.addRating(new Rating(Integer.parseInt(required(form, "stars")), comment));
                save();
                redirect(exchange, "reviews", "Review added.");
            }
            case "/save" -> {
                save();
                redirect(exchange, "overview", "Saved to " + dataFile.getFileName() + ".");
            }
            case "/load" -> {
                load();
                redirect(exchange, "overview", "Opened " + dataFile.getFileName() + ".");
            }
            case "/new" -> {
                courses.clear(); students.clear(); tutors.clear(); sessions.clear();
                save();
                redirect(exchange, "overview", "Started a new data set.");
            }
            default -> send(exchange, 404, "text/plain; charset=utf-8", "Not found");
        }
    }

    private String page(String section, String message) throws IOException {
        String content = switch (section) {
            case "courses" -> coursesPage();
            case "students" -> studentsPage();
            case "tutors" -> tutorsPage();
            case "sessions" -> sessionsPage();
            case "reviews" -> reviewsPage();
            default -> overviewPage();
        };
        String template = Files.readString(Path.of("web", "index.html"));
        String title = section.substring(0, 1).toUpperCase() + section.substring(1);
        String notice = message == null ? "" : "<p class=\"notice\">" + esc(message) + "</p>";
        return template.replace("{{TITLE}}", esc(title)).replace("{{MESSAGE}}", notice).replace("{{CONTENT}}", content);
    }

    private String overviewPage() {
        return "<section class=\"stats\"><article><small>Courses</small><b>" + courses.size()
                + "</b></article><article><small>Students</small><b>" + students.size()
                + "</b></article><article><small>Tutors</small><b>" + tutors.size()
                + "</b></article><article><small>Sessions</small><b>" + sessions.size()
                + "</b></article></section><section class=\"panel\"><h2>MavTutor</h2>"
                + "<p>Manage tutoring courses, students, tutors, sessions, and reviews with the Java classes from P10.</p>"
                + "<p>This local web interface uses the existing <code>Course</code>, <code>Student</code>, <code>Tutor</code>, "
                + "<code>Session</code>, <code>Rating</code>, and <code>Comment</code> classes.</p>"
                + "<div class=\"actions\"><a class=\"button\" href=\"/?page=courses\">Set up courses</a>"
                + "<a class=\"button\" href=\"/?page=sessions\">View sessions</a></div></section>"
                + "<section class=\"panel\"><h2>Local data</h2><p>Data is saved on this computer in <code>"
                + esc(dataFile.toString()) + "</code>.</p><div class=\"actions\">"
                + "<form method=\"post\" action=\"/save\"><button>Save now</button></form>"
                + "<form method=\"post\" action=\"/load\"><button>Open saved data</button></form>"
                + "<a class=\"button\" href=\"/backup\">Download backup</a>"
                + "<form method=\"post\" action=\"/new\" onsubmit=\"return confirm('Start a new data set?')\"><button class=\"danger\">New data set</button></form>"
                + "</div></section>";
    }

    private String coursesPage() {
        StringBuilder html = new StringBuilder("<section class=\"panel\"><h2>Add a course</h2><form method=\"post\" action=\"/course\" class=\"form\">"
                + "<label>Department code<input name=\"dept\" minlength=\"3\" maxlength=\"4\" required></label>"
                + "<label>Course number<input name=\"number\" type=\"number\" min=\"1000\" max=\"9999\" required></label>"
                + "<button>Add course</button></form></section><section class=\"panel\"><h2>Courses</h2><ol>");
        for (Course course : courses) html.append("<li>").append(esc(course.toString())).append("</li>");
        if (courses.isEmpty()) html.append("<li class=\"muted\">No courses yet.</li>");
        return html.append("</ol></section>").toString();
    }

    private String studentsPage() {
        StringBuilder html = new StringBuilder("<section class=\"panel\"><h2>Add a student</h2>");
        if (courses.isEmpty()) return html.append("<p>Add a course before creating students.</p></section>").toString();
        html.append("<form method=\"post\" action=\"/student\" class=\"form\"><label>Name<input name=\"name\" required></label>"
                + "<label>Email<input name=\"email\" type=\"email\" required></label><label>Courses<select name=\"courses\" multiple required>");
        for (int i=0;i<courses.size();i++) html.append(option(i, courses.get(i).toString()));
        html.append("</select><small>Use Ctrl or Command to choose more than one.</small></label><button>Add student</button></form></section><section class=\"panel\"><h2>Students</h2><ol>");
        for (Student student : students) html.append("<li>").append(esc(student.toString())).append("</li>");
        if (students.isEmpty()) html.append("<li class=\"muted\">No students yet.</li>");
        return html.append("</ol></section>").toString();
    }

    private String tutorsPage() {
        StringBuilder html = new StringBuilder("<section class=\"panel\"><h2>Add a tutor</h2>");
        if (courses.isEmpty()) return html.append("<p>Add a course before creating tutors.</p></section>").toString();
        html.append("<form method=\"post\" action=\"/tutor\" class=\"form\"><label>Name<input name=\"name\" required></label>"
                + "<label>Email<input name=\"email\" type=\"email\" required></label><label>Course<select name=\"course\" required>");
        for (int i=0;i<courses.size();i++) html.append(option(i, courses.get(i).toString()));
        html.append("</select></label><label>Short bio<textarea name=\"bio\" rows=\"3\"></textarea></label>"
                + "<button>Add tutor</button></form></section><section class=\"panel\"><h2>Tutors</h2><ol>");
        for (Tutor tutor : tutors) html.append("<li>").append(esc(tutor.toString())).append(" — ").append(esc(tutor.getCourse().toString())).append("</li>");
        if (tutors.isEmpty()) html.append("<li class=\"muted\">No tutors yet.</li>");
        return html.append("</ol></section>").toString();
    }

    private String sessionsPage() {
        if (courses.isEmpty() || tutors.isEmpty() || students.isEmpty())
            return "<section class=\"panel\"><h2>Sessions</h2><p>Add at least one course, tutor, and student before scheduling a session.</p></section>";
        StringBuilder html = new StringBuilder("<section class=\"panel\"><h2>Schedule a session</h2><form method=\"post\" action=\"/session\" class=\"form\">"
                + "<label>Course<select name=\"course\" required>");
        for (int i=0;i<courses.size();i++) html.append(option(i, courses.get(i).toString()));
        html.append("</select></label><label>Tutor<select name=\"tutor\" required>");
        for (int i=0;i<tutors.size();i++) html.append(option(i, tutors.get(i).toString()));
        html.append("</select></label><label>Date<input name=\"date\" type=\"date\" required></label>"
                + "<label>Start time<input name=\"time\" type=\"time\" required></label>"
                + "<label>Duration in minutes<input name=\"duration\" type=\"number\" min=\"1\" max=\"720\" value=\"60\" required></label>"
                + "<label>Students<select name=\"students\" multiple>");
        for (int i=0;i<students.size();i++) html.append(option(i, students.get(i).toString()));
        html.append("</select><small>Use Ctrl or Command to choose more than one.</small></label><button>Schedule session</button></form></section>"
                + "<section class=\"panel\"><h2>Sessions</h2><ol>");
        for (Session session : sessions) html.append("<li class=\"pre\">").append(esc(session.toString())).append("</li>");
        if (sessions.isEmpty()) html.append("<li class=\"muted\">No sessions scheduled.</li>");
        return html.append("</ol></section>").toString();
    }

    private String reviewsPage() {
        StringBuilder html = new StringBuilder("<section class=\"panel\"><h2>Add a review</h2>");
        if (students.isEmpty() && tutors.isEmpty()) return html.append("<p>Add a student or tutor before reviewing an item.</p></section>").toString();
        html.append("<form method=\"post\" action=\"/review\" class=\"form\"><label>Review type<select name=\"type\">"
                + "<option value=\"student\">Student</option><option value=\"tutor\">Tutor</option><option value=\"session\">Session</option></select></label>"
                + "<label>Item number<input name=\"target\" type=\"number\" min=\"0\" value=\"0\" required></label>"
                + "<label>Reviewer type<select name=\"authorType\"><option value=\"student\">Student</option><option value=\"tutor\">Tutor</option></select></label>"
                + "<label>Reviewer number<input name=\"author\" type=\"number\" min=\"0\" value=\"0\" required></label>"
                + "<label>Stars<select name=\"stars\"><option>5</option><option>4</option><option>3</option><option>2</option><option>1</option></select></label>"
                + "<label>Comment<textarea name=\"comment\" required></textarea></label><button>Add review</button></form></section>"
                + "<section class=\"panel\"><h2>Existing reviews</h2>");
        appendReviews(html, "Students", students);
        appendReviews(html, "Tutors", tutors);
        appendReviews(html, "Sessions", sessions);
        return html.append("</section>").toString();
    }

    private void appendReviews(StringBuilder html, String heading, List<? extends Rateable> items) {
        html.append("<h3>").append(heading).append("</h3><ul>");
        for (Rateable item : items) {
            html.append("<li>").append(esc(item.toString())).append(" — average ")
                    .append(item.getAverageRating() == 0 ? "No ratings" : String.format("%.1f / 5", item.getAverageRating()));
            for (Rating rating : item.getRatings()) html.append("<p>").append(esc(rating.toString())).append(" ")
                    .append(esc(rating.getReview().toString())).append("</p>");
            html.append("</li>");
        }
        if (items.isEmpty()) html.append("<li class=\"muted\">No items yet.</li>");
        html.append("</ul>");
    }

    private Rateable target(String type, int index) {
        return switch (type) {
            case "student" -> students.get(index);
            case "tutor" -> tutors.get(index);
            case "session" -> sessions.get(index);
            default -> throw new IllegalArgumentException("Unknown review type.");
        };
    }

    private Person person(String type, int index) {
        return switch (type) {
            case "student" -> students.get(index);
            case "tutor" -> tutors.get(index);
            default -> throw new IllegalArgumentException("Unknown reviewer type.");
        };
    }

    private void save() throws IOException {
        Files.writeString(dataFile, dataText(), StandardCharsets.UTF_8);
    }

    private String dataText() {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        try (PrintStream out = new PrintStream(bytes, true, StandardCharsets.UTF_8)) {
            out.println(courses.size()); for (Course c : courses) c.save(out);
            out.println(students.size()); for (Student s : students) s.save(out);
            out.println(tutors.size()); for (Tutor t : tutors) t.save(out);
            out.println(sessions.size()); for (Session s : sessions) s.save(out);
        }
        return bytes.toString(StandardCharsets.UTF_8);
    }

    private void load() throws IOException {
        courses.clear(); students.clear(); tutors.clear(); sessions.clear();
        if (!Files.exists(dataFile)) return;
        try (Scanner in = new Scanner(dataFile, StandardCharsets.UTF_8)) {
            int count = Integer.parseInt(in.nextLine().trim()); for (int i=0;i<count;i++) courses.add(new Course(in));
            count = Integer.parseInt(in.nextLine().trim()); for (int i=0;i<count;i++) students.add(new Student(in));
            count = Integer.parseInt(in.nextLine().trim()); for (int i=0;i<count;i++) tutors.add(new Tutor(in));
            count = Integer.parseInt(in.nextLine().trim()); for (int i=0;i<count;i++) sessions.add(new Session(in));
        }
    }

    private static String option(int value, String label) {
        return "<option value=\"" + value + "\">" + esc(label) + "</option>";
    }

    private static String required(Map<String, String> form, String key) {
        String value = form.get(key);
        if (value == null || value.isBlank()) throw new IllegalArgumentException("Missing " + key + ".");
        return value.trim();
    }

    private static Map<String, String> parse(String encoded) {
        Map<String, String> values = new HashMap<>();
        if (encoded == null || encoded.isBlank()) return values;
        for (String pair : encoded.split("&")) {
            String[] parts = pair.split("=", 2);
            String key = URLDecoder.decode(parts[0], StandardCharsets.UTF_8);
            String value = parts.length == 1 ? "" : URLDecoder.decode(parts[1], StandardCharsets.UTF_8);
            values.merge(key, value, (a, b) -> a + "," + b);
        }
        return values;
    }

    private static String esc(String value) {
        return value == null ? "" : value.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;")
                .replace("\"", "&quot;").replace("'", "&#39;");
    }

    private static void redirect(HttpExchange exchange, String page, String message) throws IOException {
        exchange.getResponseHeaders().set("Location", "/?page=" + page + "&message="
                + java.net.URLEncoder.encode(message, StandardCharsets.UTF_8));
        exchange.sendResponseHeaders(303, -1);
        exchange.close();
    }

    private static void send(HttpExchange exchange, int status, String type, String body) throws IOException {
        send(exchange, status, type, body, null);
    }

    private static void send(HttpExchange exchange, int status, String type, String body, String disposition) throws IOException {
        byte[] bytes = body.getBytes(StandardCharsets.UTF_8);
        exchange.getResponseHeaders().set("Content-Type", type);
        if (disposition != null) exchange.getResponseHeaders().set("Content-Disposition", disposition);
        exchange.sendResponseHeaders(status, bytes.length);
        exchange.getResponseBody().write(bytes);
        exchange.close();
    }
}
