import java.util.Random;

public class ItemDupes {
    public static void main(String[] args) {
        // --------------------------------------------------------------
        // duplicates(1000, 10000000);
        // runSetArray();
        runlogs();
    }

    // --------------------------------------------------------------

    private static void runSetArray() {
        int[] sizes = { 1, 1, 2, 4, 6, 8, 10, 12, 14, 16, 32, 64, 128, 256, 512 };

        int loop = 1000;
        int k = 9;

        for (int n : sizes) {
            duplicates(1, loop);
            duplicates(1, loop);
            long min = Long.MAX_VALUE;
            for (int i = 0; i < k; i++) {
                long t = duplicates(n, loop);
                if (t < min)
                    min = t;
            }
            System.out.println("(" + n + ", " + min + ")");
        }
    }

    private static void runlogs() {
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
                long t = duplicates(n, loop);
                if (t < min)
                    min = t;
            }
            System.out.println("(" + n + ", " + min + ")");
        }
    }

    private static long duplicates(int n, int loop) {
        Random rnd = new Random();
        int[] array_a = new int[n];
        for (int i = 0; i < n; i++) {
            array_a[i] = rnd.nextInt(n * 2);
        }
        int[] array_b = new int[n];
        for (int i = 0; i < n; i++) {
            array_b[i] = rnd.nextInt(n * 2);
        }
        int sum = 0;
        long t0 = System.nanoTime();
        for (int k = 0; k < loop; k++) {
            for (int i = 0; i < n; i++) {
                int key = array_a[i];
                for (int j = 0; j < n; j++) {
                    if (key == array_b[j]) {
                        sum++;
                        break;
                    }
                }
            }
        }
        long t1 = System.nanoTime();
        // System.out.print(sum);
        return t1 - t0;
    }
}
