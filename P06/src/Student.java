import java.util.ArrayList;

import Course.toString;

public class Student extends Person
{
    private static int nextStudentID=0;

    private final int studentID;

    private final ArrayList <course> courses;

    public student (String name,String email)
    {
        super(name,email);
        this.studentID = nextStudentID++;
        this.courses=new Arraylist<>();
    }

    public void addCourse(Course c)
    {
        courses.add(c);
    }

    public Course[] getCourses()
    {
        return courses.toArray(new Course[0]);
    }
    @Override
    public String toString()
    {
        return super.toString() + "\b, #"+studentID+")";
    }

}