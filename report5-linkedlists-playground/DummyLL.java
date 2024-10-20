import java.util.Random;

class DummyLL {
    private static int[] sorted(int n) {
        Random rnd = new Random();
        int[] array = new int[n];

        for (int i = 0; i < n; i++) {
            array[i] += rnd.nextInt(n) + 1;
        }
        return array;
    }

    public static long bench(int n, int loop) {
        int[] array = sorted(n);
        // Random rnd = new Random();

        long t0 = System.nanoTime();
        // sort(array);
        long t1 = System.nanoTime();
        return (t1 - t0);
    }

    public static long bench2(int n, int loop) {
        int[] array = sorted(n);

        long t0 = System.nanoTime();
        // sort(array);
        long t1 = System.nanoTime();
        return (t1 - t0);
    }

    public static void main(String[] args) {
        int loop = 1000;
        int k = 10;

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
            // System.out.println("n: " + n + " time1: " + min1 + " time2: " + min2 +
            // " diff: " + (min2 - min1));

            // System.out.println("(" + n + ", " + min1 + ")");

            // System.out.println("n: " + n + " time: " + min1 + " ratio: " + min1 / n);
        }
    }
}
