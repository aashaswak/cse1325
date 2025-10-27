package test;
import people.Person;
public class TestPerson
{
    public static void main (String args[])
    {
        Person person = new Person("Charlie", "cb@aol.com");
        if (!(person.toString().equals("Charlie (cb@aol.com)")))
        {
            System.err.println("FAIL: Should have printed out Charlie (cb@aol.com), but printed "+person);
        }
        if (!(person.getName().equals("Charlie")))
        {
            System.err.println("FAIL: Should have returned Charlie  but printed "+person.getName());
        }
        if (!(person.equals(person)))
        {
            System.err.println("FAIL: Same object should have returned true but it returned "+person.equals(person));
        }
        if ((person.equals(null)))
        {
            System.err.println("FAIL: Should have returned false, but returned "+person.equals(null));
        }
        if ((person.equals("String")))
        {
            System.err.println("FAIL: Should have returned false, but returned "+person.equals("String"));
        }
        Person person1 = new Person("Charlie", "cb@aol.com");
        if (!(person.equals(person1)))
        {
            System.err.println("FAIL: either name or email not identical");
        }


    }
}