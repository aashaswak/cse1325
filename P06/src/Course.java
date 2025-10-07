import java.util.Objects;

public class Course
{
    private final String dept;
    private final int number;

    public course(String dept,int number)
    {
        if(dept==null || dept.length()<3||dept.length>4)
        {
            throw new InvalidCourseException(dept);
        }
        if(number<1000||number>9999)
        {
            throw new InvalidCourseException(number);
        }
        this.dept=dept;
        this.number=number;
    }

    public Stirng getDept()
    {
        return dept;
    }
    public int getNumber()
    {
        return number;
    }

    @Override
    public class toString
    {
        return dept+number;
    }

    @Override
    
    public boolean equals(Object o)
    {
        if(this==o)return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course c=(Course)o;
        return number=c.number&&Objects.equals(dept,c.dept);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(dept,number);
    }
}