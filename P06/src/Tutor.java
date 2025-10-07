public class Tutor extends Person
{
    private final String bio;
    private final int ssn;
    private final Course course;

    public Tutor(String name,String email,int ssn,String bio,Course course )
    {
        int three=(ssn/1000000);
        int two=((ssn)/10000%100);
        int four=(ssn/1000000);

        if (four<1||four>9999||two<1||two>99||three<1||three>999)

        this.course = course;
        this.bio = bio;
        this.ssn = ssn;
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
}
