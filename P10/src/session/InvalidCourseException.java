package session;

/**
 * Custom exception class thrown when a {@link session.Course}
 * is created with an invalid department abbreviation or course number.
 *
 * @author      Aashaswa Raj Khakurel
 * @version     1.0
 * @since       1.0
 * @license.agreement   ARK License 1.0
 */
public class InvalidCourseException extends IllegalArgumentException
{
    // public InvalidCourseException() { super(); }
    // public InvalidCourseException(String message) { super(message); }
    // public InvalidCourseException(Throwable cause) { super(cause); }
    // public InvalidCourseException(String message, Throwable cause) { super(message, cause); }

    /**
     * Creates an InvalidCourseException object.
     * Indicates that the provided department code does not meet the required format.
     *
     * @param dept the department abbreviation for the course
     * @since 1.0
     */
    public InvalidCourseException(String dept)
    {
        super("Invalid department in new Course: " + dept);
    }

    /**
     * Creates an InvalidCourseException object.
     * Indicates that the given course number is outside the accepted range.
     *
     * @param dept the department abbreviation of the course
     * @param number the numeric identifier of the course
     * @since 1.0
     */
    public InvalidCourseException(String dept, int number)
    {
        super("Invalid course number in new Course: " + dept + " " + number);
    }
}
