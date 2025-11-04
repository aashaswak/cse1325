package people;
import java.io.PrintStream;
import java.util.Scanner;

import session.Course;
public class Tutor extends Person
{
    private final String bio;
    private final int ssn;
    private final Course course;

    public Tutor(String name, String email, int ssn, String bio, Course course)
    {
        super(name, email);

        int first = ssn / 1000000;
        int middle = (ssn / 10000) % 100;
        int last = ssn % 10000;

        if (first < 1 || first > 999 || middle < 1 || middle > 99 || last < 1 || last > 9999)
        {
            throw new IllegalArgumentException("Invalid SSN format");
        }

        this.ssn = ssn;
        this.bio = bio;
        this.course = course;
    }

    public int getSSN()
    {
        return ssn;
    }

    public Course getCourse()
    {
        return course;
    }

    public String getBio()
    {
        return bio;
    }

    public void save(PrintStream out){
        super.save(out);
        out.println(ssn);
        out.println(bio);
        course.save(out);
    }
    public Tutor(Scanner in){
        super(in);
        this.ssn = Integer.parseInt(in.nextLine().trim());
        this.bio = in.nextLine().trim();
        this.course = new Course(in);
    }   
}
