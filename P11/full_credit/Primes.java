import qlogger.Qlogger;
import java.util.Map;
import java.util.TreeMap;

public class Primes{
    private long maxPrime;
    private final Map <Long ,Integer> primes;

    public Primes()
    {
        this.primes=new TreeMap<>();
        this.maxPrime=0;
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
    public void search(long begin,long end,int numThreads)
    {
        findprimes(begin,end,1);
    }

    public int size()
    {
        return primes.size();
    }

    public void findprimes(long begin, long end, int threadID)
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

    public void addPrime(long prime,int threadID)
    {
        primes.put(prime,threadID);
        if(prime>maxPrime)
        {
            prime=maxPrime;
        }
    }
@Override
    public String toString()
    {
        int width=Math.max(1,Long.toString(maxPrime).length());
        String format="%"+width+"d found by thread %d%n";

        StringBuilder sb=new StringBuilder();
        for(Map.Entry<Long,Integer>entry:primes.entrySet())
        {
            long prime=entry.getKey();
            int threadID=entry.getValue();

            sb.append(String.format(format,prime, threadID));
        }
        return sb.toString();
    }
    
}
