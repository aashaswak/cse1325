//bonus
import java.util.Scanner;


public class ToDo
{
    public static void main(String[] args)
    {
        int itemnum=(args.length>0)?(Integer.parseInt(args[0])):5;
        Item[] list =new Item[itemnum];
        Scanner scanner=new Scanner(System.in);
        for(int i=0;i<itemnum;i++)
        {
            list[i]= new Item();
            System.out.printf("Task #%d:",i);
            list[i].task=scanner.nextLine();
            System.out.printf("Priority from 1(highest) to 5(lowest)");
            list[i].priority=scanner.nextInt();
            scanner.nextLine();
            System.out.println();
        }
        for(int i=1;i<=5;i++)
        {
            for(int j=0;j<itemnum;j++)
            {
                if(list[j].priority==i)
                {
                    System.out.println(list[j].priority+" "+list[j].task);
                }
            }
        }
    }

}