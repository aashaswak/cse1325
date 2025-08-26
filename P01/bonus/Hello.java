import java.util.Scanner;

public class Hello{
    public Hello(){
    }

        public static void main(String[] var0){
            Scanner var1 = new Scanner(System.in);
            System.out.println("Please enter your name:");
            String var2 = var1.nextLine();
            System.out.println("hello,"+var2);
        }
}