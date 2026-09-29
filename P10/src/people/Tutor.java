package people;

import java.io.PrintStream;
import java.util.Scanner;
import session.Course;

/**
 * A tutor who supports one academic course.
 *
 * SSNs are not needed by the tutoring planner and are never collected or stored.
 */
public class Tutor extends Person {
    private final String bio;
    private final Course course;

    public Tutor(String name, String email, String bio, Course course) {
        super(name, email);
        if (course == null) {
            throw new IllegalArgumentException("Course cannot be null");
        }
        this.bio = bio == null ? "" : bio;
        this.course = course;
    }

    /**
     * Compatibility constructor for older coursework callers. The legacy SSN
     * argument is deliberately ignored and is never stored.
     */
    @Deprecated
    public Tutor(String name, String email, int ignoredLegacySsn, String bio, Course course) {
        this(name, email, bio, course);
    }

    public Course getCourse() {
        return course;
    }

    public String getBio() {
        return bio;
    }

    @Override
    public void save(PrintStream out) {
        super.save(out);
        out.println(bio);
        course.save(out);
    }

    public Tutor(Scanner in) {
        super(in);
        String nextLine = in.nextLine().trim();
        // Read older coursework files that wrote an SSN before the bio,
        // without keeping or exposing that value in memory.
        if (nextLine.matches("\\d{8,9}")) {
            nextLine = in.nextLine().trim();
        }
        this.bio = nextLine;
        this.course = new Course(in);
    }
}
