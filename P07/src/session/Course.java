package session;

import java.util.Objects;

/**
 * Defines an academic course recognized by its department abbreviation and numeric identifier.
 *
 * @author      Aashaswa Raj Khakurel
 * @version     1.0
 * @since       1.0
 * @license.agreement   ARK License 1.0
 */
public class Course
{
    private String dept;
    private int number;

    /**
     * Builds a Course object using the given department code and course number.
     * Validates that both inputs follow the proper formatting conventions.
     *
     * @param dept the abbreviated department name assigned to this course
     * @param number the four-digit number representing this course
     * @since 1.0
     */
    public Course(String dept, int number)
    {
        // The department code must have a length of 3–4 characters.
        // The course number must be between 1000 and 9999, inclusive.
        if ((dept.length() < 3) || (dept.length() > 4))
        {
            throw new InvalidCourseException(dept);
        }
        if (number < 1000 || number > 9999)
        {
            throw new InvalidCourseException(dept, number);
        }
        this.dept = dept;
        this.number = number;
    }

    /**
     * Compares this course to another object for equality.
     * Returns true if both have identical department codes and course numbers.
     *
     * @param o the object being compared to this course
     * @return true if both represent the same course; false otherwise
     * @since 1.0
     */
    @Override
    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course p = (Course) o;
        return (p.dept.equals(dept) && (p.number == number));
    }

    /**
     * Computes a hash code for this Course instance.
     *
     * @return an integer hash code derived from the department and course number
     * @since 1.0
     */
    @Override
    public int hashCode()
    {
        return Objects.hash(dept, number);
    }

    /**
     * Returns a string that represents this Course in "DEPT####" format.
     *
     * @return a string representation of the Course object
     * @since 1.0
     */
    @Override
    public String toString()
    {
        return dept + number;
    }
}
