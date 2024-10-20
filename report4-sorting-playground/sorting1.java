import java.util.Random;

public class sorting1 {
    private static int[] sorted(int n) {
        Random rnd = new Random();
        int[] array = new int[n];

        for (int i = 0; i < n; i++) {
            array[i] += rnd.nextInt(n) + 1;
        }
        return array;
    }

    // int[] array = { 5, 4, 2, 6, 1, 8 };
    public static void selecSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int tmp = 0;
            for (int j = i; j < array.length; j++) {
                if (array[i] > array[j]) {
                    tmp = array[i];
                    array[i] = array[j];
                    array[j] = tmp;
                }
            }
        }
    }

    // int[] array = { 5, 4, 2, 6, 1, 8 };
    public static void insSort(int array[]) {
        for (int i = 1; i < array.length; i++) {
            insertByOrder(array, i);
        }
    }

    public static void insertByOrder(int[] array, int i) {
        int key = array[i];
        int j = i - 1;
        while ((j > -1) && (array[j] < key)) {
            array[j + 1] = array[j];
            j--;
        }
        array[j + 1] = key;
    }

    // ------------- Merge Sort -------------
    public static void sort(int[] org) {
        if (org.length == 0)
            return;
        int[] aux = new int[org.length];
        sort(org, aux, 0, org.length - 1);
    }

    private static void sort(int[] org, int[] aux, int lo, int hi) {
        if (lo != hi) {
            int mid = lo + (hi - lo) / 2;

            // sort the items from lo to mid
            sort(org, aux, lo, mid);

            // sort the items from mid+1 to hi
            sort(org, aux, mid + 1, hi);

            // merge the two sections using the additional array
            merge(org, aux, lo, mid, hi);
        }
    }

    private static void merge(int[] org, int[] aux, int lo, int mid, int hi) {
        // copy all items from lo to hi from org to aux
        copyArray(org, aux, lo, hi);

        // let's do the merging
        int i = lo; // the index in the first part
        int j = mid + 1; // the index in the second part
        // for all indices from lo to hi
        for (int k = lo; k <= hi; k++) {
            // if i is greater than mid then
            // move the j'th item to the org array, update j
            if (i > mid) {
                org[k] = aux[j++];
            }

            // else if j is greate than hi then
            // move the i'th item to the org array, update i
            else if (j > hi) {
                org[k] = aux[i++];
            }

            // else if the i'th item is smaller or equal to the j'th item,
            // move the i'th item to the org array, update i
            else if (aux[i] <= aux[j]) {
                org[k] = aux[i++];
            }

            // else
            // move the j'th item to the org array, update j
            else {
                org[k] = aux[j++];
            }
        }
    }
    // ------------- Merge Sort -------------

    public static void copyArray(int[] org, int[] aux, int lo, int hi) {
        for (int k = lo; k <= hi; k++) {
            aux[k] = org[k];
        }
    }

    public static void swap(int[] org, int i, int j) {
        int tmp = 0;
        tmp = org[i];
        org[i] = org[j];
        org[j] = tmp;
    }

    public static void devide(int[] org, int lo, int hi) {
        int i = lo;
        for (int k = lo; k <= hi; k++) {

            if (org[i] < org[hi]) {
                swap(org, i, hi);
                hi--;
            } else if (org[i] > org[hi]) {
                swap(org, i, hi);
                i = hi;
            }
        }
    }

    public static void quickSort(int[] org, int[] aux, int lo, int mid, int hi) {
        copyArray(org, aux, lo, hi);

        sort(aux, org, lo, mid);
        sort(org, aux, mid + 1, hi);
        merge(org, aux, lo, mid, hi);
    }

    public static long bench(int n, int loop) {
        int[] array = sorted(n);
        // Random rnd = new Random();

        long t0 = System.nanoTime();
        sort(array);
        long t1 = System.nanoTime();
        return (t1 - t0);
    }

    public static long bench2(int n, int loop) {
        int[] array = sorted(n);

        long t0 = System.nanoTime();
        sort(array);
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

            System.out.println("(" + n + ", " + min1 + ")");

            // System.out.println("n: " + n + " time: " + min1 + " ratio: " + min1 / n);
        }

    }

}
