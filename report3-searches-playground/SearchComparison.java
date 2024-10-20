import java.util.Random;

public class SearchComparison {
    private static int[] sorted(int n) {
        Random rnd = new Random();
        int[] array = new int[n];
        int nxt = 0;
        for (int i = 0; i < n; i++) {
            nxt += rnd.nextInt(n) + 1;
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

    public static boolean unsorted_search(int[] array, int key) {
        for (int index = 0; index < array.length; index++) {
            if (array[index] == key) {
                return true;
            }
        }
        return false;
    }

    public static long bench(int n, int loop) {
        int[] array = sorted(n);
        Random rnd = new Random();
        int key = rnd.nextInt(10) + 1;

        long t0 = System.nanoTime();
        recursive(array, key, 0, array.length);
        long t1 = System.nanoTime();
        return (t1 - t0);
    }

    public static long bench2(int n, int loop) {
        int[] array = sorted(n);
        Random rnd = new Random();
        int key = rnd.nextInt(10) + 1;

        long t0 = System.nanoTime();
        binary_search(array, key);// sorted(n) is a sorted array
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
            long min1 = Long.MAX_VALUE;
            long min2 = Long.MAX_VALUE;
            for (int i = 0; i < k; i++) {
                long t1 = bench(n, loop);
                long t2 = bench2(n, loop);
                if (t1 < min1)
                    min1 = t1;
                if (t2 < min2) {
                    min2 = t2;
                }
            }
            System.out.println("n: " + n + " time1: " + min1 + " time2: " + min2 + " diff: " + (min2 - min1));
            // System.out.println("(" + n + ", " + min + ")");
        }
    }

}
