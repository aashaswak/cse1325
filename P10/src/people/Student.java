package people;
import session.Course;
import java.util.Arrays;
import java.util.Scanner;
import java.io.PrintStream;
import java.util.ArrayList;

public class Student extends Person {
    private static int nextStudentID = 0;

    private final int studentID;
    private final ArrayList<Course> courses;

    public Student(String name, String email) {
        super(name, email);               
        this.studentID = nextStudentID++; 
        this.courses = new ArrayList<>();
    }

    public int getStudentID() { return studentID; }

    public void addCourse(Course course) {
        if (course == null) throw new IllegalArgumentException("Course cannot be null");
        courses.add(course);
    }

    public Course[] getCourses() {
        return courses.toArray(new Course[0]);
    }

    @Override
    public String toString() {
        return super.toString().replace(")", ", #" + studentID + ")");
    }

    public void save(PrintStream out){
        super.save(out);
        out.println(nextStudentID);
        out.println(studentID);
        out.println(courses.size());
        for (Course course : courses) {
            course.save(out);
        }
    }
    public Student(Scanner in){
        super(in);
        nextStudentID = Integer.parseInt(in.nextLine().trim());
        this.studentID = Integer.parseInt(in.nextLine().trim());
        int count = Integer.parseInt(in.nextLine().trim());
        this.courses = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            this.courses.add(new Course(in));
        }
    }
}

