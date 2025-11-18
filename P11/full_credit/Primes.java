import qlogger.Qlogger.log;
import java.util.Map;
import java.util.TreeMap;

public class Primes{
    private long maxPrime;
    private final Map <Long ,Integer> primes;

    public Primes()
    {
        this.primes=new Treemap<>();
        this.maxprime=0;
    }
    public boolean isPrime(long number)
    {
        if(number<2L) return false;
        for(long i=2L;i<=(long)Math.sqrt(number);i++)
        {
            if(number%i==0L) return false;
        }
        return true;
    }
    public search(long begin,long end,int numThreads)
    {
        findPrimes
    }

    public findprimes(long begin, long end, int threadID)
    {
        Qlogger.log(String.format("findprimes: thread %d searching [%d,%d)",threadID,begin,end));
        for(long i=begin;i<end;i++)
        {
            if(isPrime(i))
            {
                addPrime(i,threadID);
            }
        }
    }

    public addPrime(long prime,int threadID)
    {
        primes.put(prime,threadID);
        if(prime>maxPrime)
        {
            prime=maxPrime;
        }
    }

    
    
}
