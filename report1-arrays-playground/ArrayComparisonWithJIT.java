import java.util.Random;

class ArrayComparisonWithJIT {
    public static void main(String[] args) {
        // int[] sizes = { 1, 10, 100, 1000 };

        // JIT warmup
        bench(1000, 10000000);

        // int amt = 10;
        int loop = 1000;
        int k = 100;

        int[] sizes = new int[k]; // Sizes is an array of n values.

        for (int i = 0; i < k; i++) {
            sizes[i] = (int) Math.pow(2, i);
        }

        for (int n : sizes) {
            long min = Long.MAX_VALUE;
            for (int i = 0; i < k; i++) {
                long t = bench(n, loop);
                if (t < min)
                    min = t;
            }
            System.out.println("(" + n + ", " + min + ")");
        }
    }

    public static int run(int[] array, int[] indx) {
        int sum = 0;
        for (int i = 0; i < indx.length; i++) {
            sum = sum + array[indx[i]];
        }
        return sum;
    }

    public static long bench(int n, int loop) {
        int[] array = new int[n];
        for (int i = 0; i < n; i++)
            array[i] = i;

        Random rnd = new Random();

        int[] indx = new int[loop];
        for (int i = 0; i < loop; i++)
            indx[i] = rnd.nextInt(n);

        int sum = 0;
        long t0 = System.nanoTime();
        run(array, indx);
        long t1 = System.nanoTime();
        return (t1 - t0);
    }

}