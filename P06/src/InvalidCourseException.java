public class InvalidCourseException extends IllegalArguementException
{
    public class InvalidCourseException(String dept)
    {
        super("Invalid dept in new course:"+dept);
    }
    public class InvalidCourseException(String dept,int number)
    {
        super("Invalid course number in new course:"++ dept + " " + number);
    }
}