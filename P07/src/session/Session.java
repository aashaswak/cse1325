package session;

import people.Tutor;
import people.Student;
import java.util.List;
import java.util.ArrayList;

/**
 * Represents a tutoring session that groups together a {@link session.Course}, a {@link people.Tutor},
 * and a {@link session.DateRange} indicating when it takes place, along with a flexible number of
 * {@link people.Student} participants who attend it.
 *
 * @author      Aashaswa Raj Khakurel
 * @version     1.0
 * @since       1.0
 * @license.agreement   ARK License 1.0
 */
public class Session
{
    /**
     * Builds a new Session instance.
     *
     * @param course the Course this session is associated with
     * @param tutor the Tutor responsible for conducting this session
     * @since 1.0
     */
    public Session(Course course, Tutor tutor)
    {
        this.course = course;
        this.tutor = tutor;
        this.students = new ArrayList<>();
    }

    /**
     * Initializes a {@link session.DateRange} for this session,
     * setting the specific day, start time, and overall length.
     *
     * @param date the calendar date when the session occurs
     * @param startTime the starting time of the session
     * @param duration the total length of the session in minutes
     * @since 1.0
     */
    public void setSchedule(String date, String startTime, long duration)
    {
        dates = new DateRange(date, startTime, duration);
    }

    /**
     * Registers a student to participate in this session.
     *
     * @param student the Student to be added to the list of attendees
     * @since 1.0
     */
    public void addStudent(Student student)
    {
        students.add(student);
    }

    /**
     * Returns a formatted string containing the course, schedule,
     * tutor, and all registered students for this session.
     *
     * @return a string summary of this Session
     * @since 1.0
     */
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder("Session on ");
        sb.append(course).append(" at ").append(dates).append("\n");
        sb.append("Tutor: ").append(tutor).append("\n");
        sb.append("Students: ");
        for (Student student : students)
        {
            sb.append("\n").append(student);
        }
        return sb.toString();
    }

    private Course course;
    private DateRange dates;
    private Tutor tutor;
    private List<Student> students;
}
