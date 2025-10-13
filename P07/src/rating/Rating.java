public class Rating {
    private final int stars;
    private final Comment review;

    public Rating(int stars,Comment review)
    {
        if(stars<1||stars>5)
        {
            throw new IllegalArgumentException("Not in range between 1 and 5");
        }
        this.stars=stars;
        this.review=review;
    }
    public int getStars()
    {
        return stars;
    }

    public Comment getReview()
    {
        return review;
    }

    @Override

    public String toString()
    {
        String starString="";
        for(int i=0;i<stars;i++)
        {
            starString+="\u2605";
        }
         for (int i = stars; i < 5; i++) 
        {
             starString += "\u2606";
        }
    return starString;
}
}
