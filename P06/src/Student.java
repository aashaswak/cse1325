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
}
