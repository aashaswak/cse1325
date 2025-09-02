//bonus
import java.util.Scanner;


public class ToDo
{
    public static void main(String[] args)
    {
        int itemnum=(args.length>0)?(Integer.parseInt(args[0])):5;
        Item[] list =new Item[itemnum];
        Scanner scanner=new Scanner(System.in);
        String task;
        int priority;
        for(int i=0;i<itemnum;i++)
        {
            System.out.printf("Task #%d:",i);
            task=scanner.nextLine();
            System.out.printf("Priority from 1(highest) to 5(lowest)");
            priority=scanner.nextInt();
            scanner.nextLine();
            System.out.println();
            list[i]= new Item(task,priority);
        }
        for(int i=1;i<=5;i++)
        {
            for(int j=0;j<itemnum;j++)
            {
                if(list[j].isPriority(i))
                {
                    System.out.println(list[j]);
                }
            }
        }
    }

}