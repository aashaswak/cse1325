import java.util.ArrayList;
import java.util.List;

public class ThreadedPrimes extends Primes {
    private final Object mutex = new Object();

    public ThreadedPrimes() {
        super();
    }

    @Override
    public void search(long begin, long end, int numThreads) {
        if (numThreads <= 0) {
            super.search(begin, end, numThreads);
            return;
        }

        long range = end - begin;
        long delta = (range / numThreads);
        if (delta <= 0) delta = 1;

        List<Thread> threads = new ArrayList<>(numThreads);

        long curBegin = begin;
        for (int i = 0; i < numThreads; i++) {

            final int threadID = i + 1;
            final long sliceBegin = curBegin;
            final long sliceEnd;

            if (i == numThreads - 1) {
                sliceEnd = end; // last thread takes the remainder
            } else {
                sliceEnd = Math.min(end, sliceBegin + delta);
            }

            Thread t = new Thread(() -> findprimes(sliceBegin, sliceEnd, threadID));
            threads.add(t);
            t.start();

            curBegin = sliceEnd;
            if (curBegin >= end) break;
        }

        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
                Qlogger.log("ThreadedPrimes: interrupted while joining threads.");
            }
        }
    }

    @Override
    public void addPrime(long prime, int threadID) {
        synchronized (mutex) {
            super.addPrime(prime, threadID);
        }
    }
}
