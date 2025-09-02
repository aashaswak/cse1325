public class Item
{
    private String task;
    private int priority;
    public Item(String task,int priority)
    {
        this.task=task;
        this.priority=priority;
        if(priority<1 || priority>5) throw new IllegalArgumentException("Priority must be between 1 and 5");
        
    }
    public boolean isPriority(int priority)
    {
        return (this.priority==priority)?true:false;
    
@Override
public String toString()
{
    return priority+" "+task;
}
}
}
