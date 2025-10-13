public interface Rateable
{
    public void addRating(Rating rating);
    public double getAverageRating();
    public Rating[] getRatings();
}