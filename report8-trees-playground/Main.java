
public class Main {
    public static void main(String[] args) {
        BinaryTree l = new BinaryTree();

        // Benchmarking
        int k = 50;

        int[] sizes = new int[k]; // Sizes is an array of n values.

        for (int i = 0; i < k; i++) {
            sizes[i] = (int) (Math.pow(2, i) * 100);
        }
        // JIT warmup
        // l.bench(1000);

        l.TreeGen(10);
        l.stackPrint();
        for (int n : sizes) {
            long min1 = Long.MAX_VALUE;
            // long min2 = Long.MAX_VALUE;
            for (int i = 0; i < k; i++) {
                long t1 = 0;
                if (t1 < min1)
                    min1 = t1;
                // if (t2 < min2)
                // min2 = t2;
            }
            // System.out.println("n: " + n + " time1: " + min1 + " time2: " + min2 +
            // " diff: " + (min2 - min1));

            // System.out.println("(" + n + ", " + min1 + ")");

            System.out.println("n: " + n + " time: " + min1 + " ratio: " + min1 / n);
        }
    }
}
