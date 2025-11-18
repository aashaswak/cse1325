import qlogger.Qlogger;
public class ListPrimes{
    public static void main(String []args)
    {
        Qlogger.enabled=true;

        if(args.length!=3)
        {
            System.out.println("usage: java ListPrimes <begin> <end> <#threads>");
            return;
        }

        try
        {
            long begin=Long.parseLong(args[0].replaceAll("_", ""));
            long end=Long.parseLong(args[1].replaceAll("_", ""));
            int numThreads=Integer.parseInt(args[2].replaceAll("_", ""));

            Primes primes;
            if(numThreads==0)
            {
                primes=new Primes();
            }
            else if(numThreads>0)
            {
                    primes = new ThreadedPrimes();
            } else 
            {
                primes = new PooledPrimes();
            }


            primes.search(begin, end, numThreads);

            String summary = String.format("Found %d primes in [%d, %d).", primes.size(), begin, end);
            
            Qlogger.log(summary);

            System.out.println(summary);
            System.out.println(primes.toString());

        } catch (NumberFormatException nfe) {
            System.out.println("Invalid numeric argument. Usage: java ListPrimes <begin> <end> <#threads>");
        }


        }
    }
