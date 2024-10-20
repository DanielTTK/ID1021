import java.util.Random;

public class BinarySearch {
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

    public static boolean binary_search(int[] array, int key) {
        int first = 0;
        int last = array.length - 1;
        int index = 0;
        while (true) {
            index = first + ((last - first) / 2);

            if (array[index] == key) {
                return true;
            }
            if (array[index] < key && index < last) {
                first = index;
                continue;
            }
            if (array[index] > key && index > first) {
                last = index;
                continue;
            }
            return false;
        }
    }

    public static long bench(int n, int loop) {
        int[] array = sorted(n);
        Random rnd = new Random();
        int key = array[rnd.nextInt(n)];

        long t0 = System.nanoTime();
        binary_search(array, key);
        long t1 = System.nanoTime();
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
        bench(1000, 10000000);

        for (int n : sizes) {
            long min = Long.MAX_VALUE;
            for (int i = 0; i < k; i++) {
                long t = bench(n, loop);
                if (t < min)
                    min = t;
            }
            // System.out.println("n: " + n + " time: " + min + " ratio: " + n / min);
            System.out.println("(" + n + ", " + min + ")");
        }
    }
}
