public class MergeSort {
    public static void sort(int[] org) {
        if (org.length == 0)
            return;
        int[] aux = new int[org.length];
        sort(org, aux, 0, org.length - 1);
    }

    private static void sort(int[] org, int[] aux, int lo, int hi) {
        if (lo >= hi) {
            return;
        }

        int mid = lo + (hi - lo) / 2;

        // sort the items from lo to mid
        sort(org, aux, lo, mid);

        // sort the items from mid + 1 to hi
        sort(org, aux, mid + 1, hi);

        // merge the two sections using the auxiliary array
        merge(org, aux, lo, mid, hi);
    }

    private static void merge(int[] org, int[] aux, int lo, int mid, int hi) {
        // copy all items from lo to hi from org to aux
        for (int k = lo; k <= hi; k++) {
            aux[k] = org[k];
        }

        int i = lo; // the index in the first half
        int j = mid + 1; // the index in the second half

        // merge back to the org array
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                // all elements from left half have been merged, take from the right
                org[k] = aux[j++];
            } else if (j > hi) {
                // all elements from right half have been merged, take from the left
                org[k] = aux[i++];
            } else if (aux[i] <= aux[j]) {
                // current element of the left half is smaller, merge it
                org[k] = aux[i++];
            } else {
                // current element of the right half is smaller, merge it
                org[k] = aux[j++];
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = { 5, 4, 2, 6, 1, 8 };
        sort(arr);
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }
}
