import java.util.Random;

public class AFirstTry {
    public static boolean unsorted_search(int[] array, int key) {
        for (int index = 0; index < array.length; index++) {
            if (array[index] == key) {
                return true;
            }
        }
        return false;
    }

    private static int[] sorted(int n) {
        Random rnd = new Random();
        int[] array = new int[n];
        int nxt = 0;
        for (int i = 0; i < n; i++) {
            nxt += rnd.nextInt(10) + 1;
            array[i] = nxt;
        }
        return array;
    }

    public static long bench(int n, int loop) {
        int[] array = sorted(n);

        int key = -1;

        long t0 = System.nanoTime();
        unsorted_search(array, key); // unsorted_search is a basic search
        long t1 = System.nanoTime(); // sorted(n) is a sorted array
        return (t1 - t0);
    }

    public static void main(String[] args) {
        int loop = 1000;
        int k = 20;

        int[] sizes = new int[k]; // Sizes is an array of n values.

        for (int i = 0; i < k; i++) {
            sizes[i] = (int) (Math.pow(2, i) * 100);
        }

        // JIT warmup
        bench(1000, 10000000);

        for (int n : sizes) {
            long min = Long.MAX_VALUE;
            for (int i = 0; i < k; i++) {
                long t = bench(n, loop);
                if (t < min)
                    min = t;
            }
            System.out.println("n: " + n + " time: " + min + " ratio: " + (float) n /
                    min);
            // System.out.println("(" + n + ", " + min + ")");
        }
    }

}
