package rating;
import people.Person;
import java.util.ArrayList;
public class Comment 
{
    private final String text;
    private final Person author;
    private final Comment inReplyTo;
    private final ArrayList<Comment> replies;

  