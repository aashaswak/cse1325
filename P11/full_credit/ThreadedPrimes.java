import java.util.ArrayList;
import java.util.List;
public class ThreadedPrimes extends Primes
{
    private static final Object mutex = new Object();
    @Override
    public void search(long begin, long end, int numThreads)
    {

        List<Thread> threads = new ArrayList<>();
        long delta = (end-begin+1)/numThreads;
        for(int i = 0; i<numThreads; i++)
        {
            final int threadID = i;
            final long tbegin = begin;
            final long tend = (i != numThreads - 1) ? begin + delta - 1 : end;
            Thread thread = new Thread(() -> findprimes(tbegin, tend, threadID));
            thread.start();
            begin+=delta;
            threads.add(thread);
        }
        for(Thread t: threads)
        {
            try
            {
                t.join();
            }
            catch(InterruptedException e)
            {
                System.err.println("EXIT: " + e);
            }
        }
    }
    @Override
    protected void addPrime(long prime, int threadID)
    {
        synchronized(mutex)
        {
            super.addPrime(prime, threadID);
        }

    }

}