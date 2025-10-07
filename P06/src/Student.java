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
        if(c==null)throw new IllegalArgumentException("Course cannot be null");
        courses.add(c);
    }

    public Course[] getCourses()
    {
        return courses.toArray(new Course[0]);
    }
    @Override
    public toString()
    {
        String rep=super.toString();
        return rep.replace(")",",#"+studentID+")");
    }

}