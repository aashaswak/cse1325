import java.util.Objects;
import java.util.Arrays;
import java.util.ArrayList;
public class Person implements Rateable
{
    protected String name;
    protected String email;
    private ArrayList<Rating> ratings=new ArrayList<>();

    public Person(String name,String email)
    {
        if(name==null||name.equals(""))
        {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        if(email==null||email.equals(""))
        {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }

        this.name=name;
        this.email=email;
    }
    public String getName()
    {
        return name;
    }
    @Override
    public boolean equals(Object o)
    {
        if(this==o)return true;
        if(o==null||getClass()!=o.getClass())return false;
        Person p=(Person)o;
        return name.equals(p.name) && email.equals(p.email);
    }
    @Override
    public int hashCode()
    {
        return Objects.hash(name,email);
    }
    @Override
    public String toString()
    {
        return name+"("+email+")";
    }
     @Override
    public void addRating(Rating rating)
    {
        ratings.add(rating);
    }
    @Override
    public double getAverageRating()
    {
        int sum=0; 
        double avg=0;
        for (int i=0; i<ratings.size();i++)
        {
            sum+=ratings.get(i).getStars();
        }
        average = sum/ratings.size();
        return average;
    }
    @Override
    public Rating[] getRatings()
    {
        Rating[] ratingarray = ratings.toArray(new Rating[0]);
        return ratingarray;
    }

}
    

