package session;

import rating.Rateable;
import rating.Rating;
import people.Tutor;
import people.Student;
import java.util.List;
import java.util.Scanner;
import java.io.PrintStream;
import java.util.ArrayList;

public class Session implements Rateable  {
    private ArrayList<Rating> ratings = new ArrayList<>();

    private Course course;
    private DateRange dates;
    private Tutor tutor;
    private List<Student> students;

    public Session(Course course, Tutor tutor) {
        this.course = course;
        this.tutor = tutor;
        this.students = new ArrayList<>();
    }

    public void setSchedule(String date, String startTime, long duration) {
        dates = new DateRange(date, startTime, duration);
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Session on ");
        sb.append(course).append(" at ").append(dates).append("\n");
        sb.append("Tutor: ").append(tutor).append("\n");
        sb.append("Students: ");
        for (Student student : students) {
            sb.append("\n").append(student);
        }
        return sb.toString();
    }

    public void save(PrintStream out) {
        course.save(out);
        dates.save(out);
        tutor.save(out);
        out.println(students.size());
        for (Student student : students) {
            student.save(out);
        }
    }

    public Session(Scanner in) {
        this.course = new Course(in);
        this.dates = new DateRange(in);
        this.tutor = new Tutor(in);
        int studentCount = Integer.parseInt(in.nextLine().trim());
        this.students = new ArrayList<>();
        for (int i = 0; i < studentCount; i++) {
            this.students.add(new Student(in));
        }
    }

    @Override
    public void addRating(Rating rating) {
        ratings.add(rating);
    }

    @Override
public double getAverageRating() {
    if (ratings.isEmpty()) return Double.NaN;
    double total = 0;
for (Rating r : ratings) total += r.getStars(); 
    return total / ratings.size();
}


    @Override
    public Rating[] getRatings() {
        return ratings.toArray(new Rating[0]);
    }
}
