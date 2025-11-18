import qlogger.Qlogger;

public class ListPrimes {
    public static void main(String[] args) {

        Qlogger.enabled = true;

        if (args.length != 3) {
            System.out.println("usage: java ListPrimes <begin> <end> <#threads>");
            return;
        }

        long begin = Long.parseLong(args[0].replaceAll("_", ""));
        long end = Long.parseLong(args[1].replaceAll("_", ""));
        int numThreads = Integer.parseInt(args[2].replaceAll("_", ""));

        Primes primes;

        if (numThreads == 0) {
            primes = new Primes();
        } else if (numThreads > 0) {
            primes = new ThreadedPrimes();
        } else {
            System.out.println("ERROR: negative threads invalid in full credit version.");
            return;
        }

        primes.search(begin, end, numThreads);

        System.out.println("Found " + primes.size() + " primes.");
        System.out.println(primes);
    }
}
