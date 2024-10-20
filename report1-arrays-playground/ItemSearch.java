import java.util.Random;

public class ItemSearch {
    public static void main(String[] args) {
        // --------------------------------------------------------------
        int n = 1200;
        int loop = 1000;
        int k = 10;
        long min = Long.MAX_VALUE;
        long max = 0;
        long total = 0;

        search(n, 1000000);

        for (int i = 0; i < k; i++) {
            long t = search(n, loop);
            if (t > max)
                max = t;
            if (t < min)
                min = t;
            total += t;
        }

        System.out.println(" min: " + ((double) min) / loop + " ns");
        System.out.println(" max: " + ((double) max) / loop + " ns");
        System.out.println(" avg: " + ((double) total) / loop / k + " ns");
        // --------------------------------------------------------------
    }

    private static long search(int n, int loop) {
        Random rnd = new Random();

        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = rnd.nextInt(n * 2);
        }

        int[] keys = new int[loop];
        for (int k = 0; k < loop; k++) {
            keys[k] = rnd.nextInt(n * 2);
        }

        int sum = 0;
        long t0 = System.nanoTime();
        for (int i = 0; i < loop; i++) {
            int key = keys[i];
            for (int j = 0; j < n; j++) {
                if (key == array[j]) {
                    sum++;
                    break;
                }
            }
        }
        long t1 = System.nanoTime();
        return (t1 - t0);
    }

}
