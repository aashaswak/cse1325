public class TestCard
{
    public static void main(String[] args)
    {
        Card objecct = new Card("Cat","Biralo");
        if (!(objecct.toString().equals("Biralo")))
        {
            System.err.println("FAIL: toString() doesnt give right answer");
        }
        if (!(objecct.attempt("Cat")))
        {
            System.err.println("FAIL: The method attempt didnt return whst it should have returned");
        }
        if (!((objecct.getTerm()).equals("Cat")))
        {
            System.err.println("FAIL: getTerm doesn't match ther required term");

        }
        
        if(!(objecct.attempt("CAT")))
        {
            System.err.println("FAIL: our attempt isnt case insensitive");

        }
    
        try
        {
            Card objecct2=new Card("", "RajuRastogi");
            System.err.println("FAIL: Empty Constructor String");
        }
        catch(Exception e)
        {
        }
        try
        {
            Card objecct3=new Card(null, "NarayanDai");
            System.err.println("FAIL: null constructor");
        }
        catch(Exception e)
        {
        }


    }
}