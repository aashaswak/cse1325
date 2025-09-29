import java.util.ArrayList;
import java.util.List;


public class Comment 
{
    private final String text;
    private final Person author;
    private final Comment inReplyTo;
    private final ArrayList<Comment> replies;


    public Comment(String text,Person author,Comment inReplyTo)
    {
        if(text==null||text.equals(""))
        {
            throw new IllegalArgumentException("Text cannot be null or empty");
        }
        if(author==null||author.equals(""))
        {
            throw new IllegalArgumentException("Author cannot be null or empty");
        }
        this.text=text;
        this.author=author;
        this.inReplyTo=inReplyTo;
        this.replies=new ArrayList<>();
    }

    public void addReply(String text,Person author)
    {
        if(text==null||text.equals(""))
        {
            throw new IllegalArgumentException("text must not be null or empty");   
        }
        if(author==null||author.equals(""))
        {
            throw new IllegalArgumentException("author must not be null or empty");
        }
        Comment reply=new Comment(text,author,this);
        replies.add(reply);
    }
    public int numReplies()
    {
        return replies.size();
    }
    public Comment getReply(int index)
    {
        return replies.get(index);
    }
    public Comment getInReplyTo()
    {
        return inReplyTo;
    }
    @Override

    public String toString()
    {
        String result="Comment by"+author;
        if(inReplyTo!=null)
        {
            result=result+"in reply to"+inReplyTo.author;
        }
        if(inReplyTo!="")
    }
}
