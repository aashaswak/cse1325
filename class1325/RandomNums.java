public class RandomNums{
    public static void main(String[] args){
        // int numr=(args.length==1)? Integer.parseInt(args(0)):24;
        int numr=24;
        if(args.length==1) numr=Integer.parseInt(args[0]);

        double min=Double.MAX_VALUE;
        double sum=0;
        double max=Double.MIN_VALUE;

        for(int i=0;i<numr;i++){
            double d=Math.random();
            if(d<min) min=d;
            sum +=d;
            if(d>max) max=d;
            System.out.println(d);
        }
    }
}