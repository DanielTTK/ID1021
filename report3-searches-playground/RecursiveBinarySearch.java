import java.util.Random;

public class RecursiveBinarySearch {
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

    private static boolean recursive(int[] arr, int key, int min, int max) {
        int mid = min + ((max - min) / 2);
        if (arr[mid] == key) {
            return true;
        }
        if ((arr[mid] > key) && (min < mid)) {
            recursive(arr, key, mid, max);
        }
        if ((arr[mid] < key) && (mid < max)) {
            recursive(arr, key, min, mid);
        }
        return false;
    }

    public static long bench(int n, int loop) {
        int[] array = sorted(n);
        Random rnd = new Random();
        int key = array[rnd.nextInt(n)];

        long t0 = System.nanoTime();
        recursive(array, key, 0, array.length);
        long t1 = System.nanoTime();
        return (t1 - t0);
    }

    public static void main(String[] args) {
        int loop = 1000;
        int k = 40;

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
            System.out.println("n: " + n + " time: " + min + " ratio: " + (float) n / min);
            // System.out.println("(" + n + ", " + min + ")");
        }
    }
}
