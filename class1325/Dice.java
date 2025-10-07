import java.util.Random;
import java.util.Arrays;


public class Dice{
    public static void main(String[] args){
        Random random=new Random();
        if(args.length!=2){
            System.err.println("usage: Java Dice <#Dice> <#slides");
            System.exit(-1);
        }
        int numDice= Integer.parseInt(args[0]);
        int numSide= Integer.parseInt(args[1]);

        int[]dice=new int[numDice];

        int sum=0;
        for(int i=0;i<numDice;++i){
            dice[i]=random.nextInt(numSide);
            sum+=dice[i];
        }
        Arrays.sort(dice);
        for(var d:dice) System.out.print(" "+d);
        System.out.println
    }
}