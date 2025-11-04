package mdi;

import java.util.Collections;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.File;
import java.io.PrintStream;
import java.util.Scanner;
import menu.Menu;
import menu.MenuItem;
import people.Student;
import people.Tutor;
import session.Session;
import session.Course;

public class MavTutor {
    private List<Course> courses = new ArrayList<>();
    private List<Student> students = new ArrayList<>();
    private List<Tutor> tutors = new ArrayList<>();
    private List<Session> sessions = new ArrayList<>();

    private Menu menu;
    private List<?> view;

    private File file = null;
    private Boolean dirty = false;

    public MavTutor() {
        this.menu = new Menu();
        this.view = courses;

        this.menu = new Menu();
        this.view = courses;

        menu.addMenuItem(new MenuItem("Quit", () -> quit()));
        menu.addMenuItem(new MenuItem("Create Course", () -> newCourse()));
        menu.addMenuItem(new MenuItem("View Courses", () -> selectView(courses)));
        menu.addMenuItem(new MenuItem("Create Student", () -> newStudent()));
        menu.addMenuItem(new MenuItem("View Students", () -> selectView(students)));
        menu.addMenuItem(new MenuItem("Create Tutor", () -> newTutor()));
        menu.addMenuItem(new MenuItem("View Tutors", () -> selectView(tutors)));
        menu.addMenuItem(new MenuItem("View Sessions", () -> selectView(sessions)));
        menu.addMenuItem(new MenuItem("Create Session", () -> newSession()));

        menu.addMenuItem(new MenuItem("New", () -> newz()));
        menu.addMenuItem(new MenuItem("Open", () -> open()));
        menu.addMenuItem(new MenuItem("Save", () -> save()));
        menu.addMenuItem(new MenuItem("Save As", () -> saveAs()));

        menu.run();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("");

        if (view == courses) {
            sb.append(menu.listToString("Courses:\n", courses, '•'));
        } else if (view == students) {
            sb.append(menu.listToString("Students:\n", students, '•'));
        } else if (view == tutors) {
            sb.append(menu.listToString("Tutors:\n", tutors, '•'));
        } else if (view == sessions) {
            sb.append(menu.listToString("Sessions:\n", sessions, '•'));
        }

        return sb.toString();
    }

    private static void showSplash() {
        String splash =
            " __  __           _____         _           \n" +
            "|  \\/  |         |  __ \\       | |          \n" +
            "| \\  / | __ _ ___| |  | |_   _ | |_ ___     \n" +
            "| |\\/| |/ _` / __| |  | | | | || __/ _ \\ \n" +
            "| |  | | (_| \\__ \\ |__| | |_| || ||  __/   \n" +
            "|_|  |_|\\__,_|___/_____/ \\__,_| \\__\\___| \n" +
            "         Welcome to MavTutor!              \n";
        System.out.println(splash);
        try {
            Thread.sleep(3000); 
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {
        boolean skipSplash = args.length > 0 && args[0].equalsIgnoreCase("nosplash");
        if (!skipSplash) {
            showSplash();
        }
        new MavTutor();
    }

    private void quit() {
        menu.result = null;
    }

    private void selectView(List<?> list) {
        this.view = list;
        showCurrentViewInResult();
    }

    private String buildCurrentViewText() {
        StringBuilder sb = new StringBuilder();

        if (view == courses) {
            sb.append("Courses:\n");
            for (Course c : courses) sb.append("• ").append(c).append("\n");
        } else if (view == students) {
            sb.append("Students:\n");
            for (Student s : students) sb.append("• ").append(s).append("\n");
        } else if (view == tutors) {
            sb.append("Tutors:\n");
            for (Tutor t : tutors) sb.append("• ").append(t).append("\n");
        } else if (view == sessions) {
            sb.append("Sessions:\n");
            for (Session sess : sessions) sb.append("• ").append(sess).append("\n");
        }

        return sb.toString();
    }

    private void showCurrentViewInResult() {
        menu.result.setLength(0);
        menu.result.append(buildCurrentViewText());
    }

    private void newz() {
        if (!safeToDiscardData()) {
            menu.result.append("New operation canceled.\n");
            return;
        }
        courses.clear();
        students.clear();
        tutors.clear();
        sessions.clear();
        file = null;
        dirty = false;
        menu.result.append("Cleared all data.\n");
    }

    private void saveAs() {
        file = null;
        save();
    }

    private void save() {
        if (file == null) {
            file = Menu.selectFile("Select the file to save", null, null);
        }
        try (PrintStream out = new PrintStream(file)) {
            out.println(courses.size());
            for (Course c : courses) c.save(out);
            out.println(students.size());
            for (Student s : students) s.save(out);
            out.println(tutors.size());
            for (Tutor t : tutors) t.save(out);
            out.println(sessions.size());
            for (Session sess : sessions) sess.save(out);

            menu.result.append("Saved successfully\n");
            dirty = false;
        } catch (Exception e) {
            menu.result.append("Error saving file: " + e.getMessage());
        }
    }

    private void open() {
        file = Menu.selectFile("Select the file to open", null, null);
        if (file != null) {
            try (Scanner in = new Scanner(file)) {
                courses.clear();
                students.clear();
                tutors.clear();
                sessions.clear();

                int courseCount = Integer.parseInt(in.nextLine().trim());
                for (int i = 0; i < courseCount; ++i) courses.add(new Course(in));

                int studentCount = Integer.parseInt(in.nextLine().trim());
                for (int i = 0; i < studentCount; ++i) students.add(new Student(in));

                int tutorCount = Integer.parseInt(in.nextLine().trim());
                for (int i = 0; i < tutorCount; ++i) tutors.add(new Tutor(in));

                int sessionCount = Integer.parseInt(in.nextLine().trim());
                for (int i = 0; i < sessionCount; ++i) sessions.add(new Session(in));

                menu.result.append("File loaded successfully\n");
                dirty = false;
            } catch (Exception e) {
                menu.result.append("Error loading file: " + e.getMessage() + "\n");
                newz();
            }
        }
    }

    private boolean safeToDiscardData() {
        if (!dirty) return true;
        while (true) {
            String ans = Menu.getString("You have unsaved changes. Select among (S)ave, (D)iscard, (A)bort? ");
            if (ans == null) return false;
            ans = ans.trim().toUpperCase();
            if (ans.startsWith("S")) {
                save();
                return !dirty;
            } else if (ans.startsWith("D")) {
                dirty = false;
                return true;
            } else if (ans.startsWith("A")) {
                return false;
            } else {
                menu.result.append("Invalid option. Enter S, D, or A.\n");
            }
        }
    }

    private void newCourse() {
        String dept = Menu.getString("Department: ");
        int number = Menu.getInt("Course number: ");
        Course course = new Course(dept, number);

        if (!courses.contains(course)) {
            courses.add(course);
            dirty = true;
            menu.result.append("Course added: ").append(course).append("\n");
        } else {
            menu.result.append("Course already exists: ").append(course).append("\n");
        }
    }

    private void newTutor() {
        String tutorName = Menu.getString("Tutor name: ");
        String tutorEmail = Menu.getString("Tutor email: ");
        int tutorSSN = Menu.getInt("Tutor SSN: ");

        System.out.println("\nAvailable Courses:");
        for (int i = 0; i < courses.size(); i++) {
            System.out.println(i + ". " + courses.get(i));
        }

        int courseIndex = Menu.getInt("Tutor's Course index: ");
        if (courseIndex < 0 || courseIndex >= courses.size()) {
            System.out.println("Invalid course index. Tutor not added.");
            return;
        }

        Course selectedCourse = courses.get(courseIndex);
        Tutor tutor = new Tutor(tutorName, tutorEmail, tutorSSN, "", selectedCourse);

        if (!tutors.contains(tutor)) {
            tutors.add(tutor);
            dirty = true;
            menu.result.append("Tutor added: ").append(tutor).append("\n");
        } else {
            menu.result.append("Tutor already exists: ").append(tutor).append("\n");
        }
    }

    private void newStudent() {
        String studentName = Menu.getString("Student name: ");
        String studentEmail = Menu.getString("Student email: ");
        Student newStudent = new Student(studentName, studentEmail);
        String userChoice = "";

        if (courses.isEmpty()) {
            menu.result.append("No courses available in the database.\n");
            return;
        }

        while (!userChoice.equalsIgnoreCase("Q")) {
            Integer selectedIndex = Menu.selectItemFromList("Select a course: ", courses);

            if (selectedIndex == null || selectedIndex < 0 || selectedIndex >= courses.size()) {
                System.out.println("Invalid course selection.\n");
                return;
            }

            Course selectedCourse = courses.get(selectedIndex);

            if (Arrays.asList(newStudent.getCourses()).contains(selectedCourse)) {
                System.out.println("Course already added for this student.\n");
            } else {
                newStudent.addCourse(selectedCourse);
                System.out.println("Added course: " + selectedCourse);
            }

            userChoice = Menu.getString("Add another course? (Enter 'Q' to quit): ");
        }

        if (!students.contains(newStudent)) {
            students.add(newStudent);
            dirty = true;
            menu.result.append("Student added: ").append(newStudent).append("\n");
        } else {
            menu.result.append("Student already exists: ").append(newStudent).append("\n");
        }
    }

    private void newSession() {
        menu.result.setLength(0);

        if (courses.isEmpty()) {
            menu.result.append("No courses available. Cannot create session.\n");
            return;
        }
        if (tutors.isEmpty()) {
            menu.result.append("No tutors available. Cannot create session.\n");
            return;
        }
        if (students.isEmpty()) {
            menu.result.append("No students available. Cannot create session.\n");
            return;
        }

        Integer courseIdx = Menu.selectItemFromList("Select course for session:", courses);
        if (courseIdx == null || courseIdx < 0 || courseIdx >= courses.size()) {
            menu.result.append("Invalid course selection. Session not created.\n");
            return;
        }
        Course selectedCourse = courses.get(courseIdx);

        Integer tutorIdx = Menu.selectItemFromList("Select tutor for session:", tutors);
        if (tutorIdx == null || tutorIdx < 0 || tutorIdx >= tutors.size()) {
            menu.result.append("Invalid tutor selection. Session not created.\n");
            return;
        }
        Tutor selectedTutor = tutors.get(tutorIdx);

        Session session = new Session(selectedCourse, selectedTutor);

        String date = Menu.getString("Enter session date (yyyymmdd): ");
        String time = Menu.getString("Enter session start time (hh:mm, 24hr): ");
        int duration = Menu.getInt("Enter session duration (minutes): ");
        session.setSchedule(date, time, duration);

        while (true) {
            Integer studentIdx = Menu.selectItemFromList("Add student to session (Cancel to finish):", students);
            if (studentIdx == null || studentIdx < 0 || studentIdx >= students.size()) break;
            Student selectedStudent = students.get(studentIdx);
            session.addStudent(selectedStudent);
            menu.result.append("Added student: ").append(selectedStudent).append("\n");
        }

        sessions.add(session);
        dirty = true;
        menu.result.append("Session created: ").append(session).append("\n");
    }
}
