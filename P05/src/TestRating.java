public class TestRating
{
    public static void main(String args[])
    {
        //Test Vector 1:
        for(int i = 1; i <= 5; i++)
        {
            Rating ratingObj = new Rating(i, null);
            if (ratingObj.getStars() != i)
            {
                System.err.println("FAIL: getStars() expected " + i + " but got " + ratingObj.getStars());
            }
            //for toString()
            String stars = "";
            for (int j = 0; j < i; j++)
            { 
                stars += new String(Character.toChars(0x2605));
            }
            for (int j = i; j < 5; j++) 
            {
                stars += new String(Character.toChars(0x2606));
            }
            if (!ratingObj.toString().equals(stars)) 
            {
                System.err.println("FAIL: toString() should be " + stars + " but was " + ratingObj.toString());
            }
        }
        
        //Test Vector 2:
        Person reviewer = new Person("Raul", "raul@aol.com");
        Comment remark = new Comment("Bad review", reviewer, null);
        Rating ratingCheck = new Rating(3, remark);
        if (ratingCheck.getReview() != remark)
        {
            System.err.println("FAIL: getReview() did not return the same Comment instance");
        }
    }
}
