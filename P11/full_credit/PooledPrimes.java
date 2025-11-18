import qlogger.Qlogger;
import java.util.ArrayList;
import java.util.List;

public class PooledPrimes extends Primes {
    private final Object sliceMutex = new Object();

    private static final long SLICES = 50L;

    private long sliceSize;
    private long currentSlice;
    private long endSlice;

    public PooledPrimes() {
        super();
    }

    @Override
    public void search(long begin, long end, int numThreads) {
        if (numThreads <= 0) {
            super.search(begin, end, numThreads);
            return;
        }

        long total = Math.max(0L, end - begin);
        sliceSize = 1L + (total / SLICES); 
        currentSlice = begin - sliceSize; 
        endSlice = end;

        List<Thread> threads = new ArrayList<>(numThreads);
        for (int i = 0; i < numThreads; i++) {
            final int threadID = i + 1;
            Thread t = new Thread(() -> searchWorker(threadID));
            threads.add(t);
            t.start();
        }

        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
                Qlogger.log("PooledPrimes: interrupted while joining.");
            }
        }
    }

   
    private void searchWorker(int threadID) {
        while (true) {
            long sliceStart = nextSlice();
            if (sliceStart < 0L) {
                return;
            }
            long sliceEnd = Math.min(sliceStart + sliceSize, endSlice);
            findprimes(sliceStart, sliceEnd, threadID);
        }
    }
    private long nextSlice() {
        synchronized (sliceMutex) {
            long next = currentSlice + sliceSize;
            if (next >= endSlice) {
                return -1L;
            }
            currentSlice = next;
            Qlogger.log(String.format("nextSlice: providing slice [%d, %d)", next, Math.min(next + sliceSize, endSlice)));
            return next;
        }
    }
    @Override
    public void addPrime(long prime, int threadID) {
        synchronized (this) {
            super.addPrime(prime, threadID);
        }
    }
}
