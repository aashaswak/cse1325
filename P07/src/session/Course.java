import java.util.Objects;

public class Course {
    private final String dept;
    private final int number;

    public Course(String dept, int number) {
        if (dept == null || dept.length() < 3 || dept.length() > 4) {
            throw new InvalidCourseException(dept);
        }
        if (number < 1000 || number > 9999) {
            throw new InvalidCourseException(dept, number);
        }
        this.dept = dept;
        this.number = number;
    }

    public String getDept() { return dept; }
    public int getNumber() { return number; }

    @Override
    public String toString() { 
        return dept + number; 
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Course)) return false;
        Course c = (Course) o;
        return number == c.number && Objects.equals(dept, c.dept);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dept, number);
    }
}
