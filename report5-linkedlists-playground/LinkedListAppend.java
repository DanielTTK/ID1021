class LinkedListAppend {
    Cell first;

    public static long bench(int n, LinkedListAppend ll, LinkedListAppend b) {
        ll.LinkedListGenerator(n);
        b.LinkedListGenerator(5);
        long t0 = System.nanoTime();
        ll.append(b);
        long t1 = System.nanoTime();
        return (t1 - t0);
    }

    public static long bench2(int n, LinkedListAppend ll, LinkedListAppend b) {
        ll.LinkedListGenerator(5);
        b.LinkedListGenerator(n);
        long t0 = System.nanoTime();
        ll.append(b);
        long t1 = System.nanoTime();
        return (t1 - t0);
    }

    public static void main(String[] args) {
        LinkedListAppend ll = new LinkedListAppend();
        LinkedListAppend b = new LinkedListAppend();

        // Create the first nodes (head of the linked lists)
        ll.first = ll.new Cell(0, null); // We will use this as the head of the list
        b.first = b.new Cell(0, null);

        // Benchmarking
        int k = 10;

        int[] sizes = new int[k]; // Sizes is an array of n values.

        for (int i = 0; i < k; i++) {
            sizes[i] = (int) (Math.pow(2, i) * 100);
        }

        // JIT warmup
        bench(1000, ll, b);
        bench2(1000, ll, b);

        for (int n : sizes) {
            long min1 = Long.MAX_VALUE;
            long min2 = Long.MAX_VALUE;
            for (int i = 0; i < k; i++) {
                long t1 = bench(n, ll, b);
                long t2 = bench2(n, ll, b);
                if (t1 < min1)
                    min1 = t1;
                if (t2 < min2)
                    min2 = t2;
            }
            // System.out.println("n: " + n + " time1: " + min1 + " time2: " + min2 +
            // " diff: " + (min2 - min1));

            // System.out.println("(" + n + ", " + min1 + ")");

            System.out.println("n: " + n + " time: " + min2 + " ratio: " + min2 / n);
        }
    }

    public void LinkedListGenerator(int n) {
        Cell last = null;
        for (int i = 0; i < n; i++) {
            last = new Cell(i, last);
        }
        first = last;
    }

    public void append(LinkedListAppend b) {
        Cell nxt = this.first;
        while (nxt.tail != null) {
            nxt = nxt.tail;
        }
        nxt.tail = b.first;
    }

    public class Cell {
        int head;
        Cell tail;

        Cell(int val, Cell tl) {
            head = val;
            tail = tl;
        }

        void add(int item) { // add smth to top of list
            if (first == null) {
                first = new Cell(item, null);
            } else {
                first = new Cell(item, first); // Make a new cell, then make first "point" to it
            }
        }

        int length() {
            Cell nxt = first;
            int total = 0;
            while (nxt != null) {
                total++;
                nxt = nxt.tail;
            }
            return total;
        }

        boolean find(int item) {
            Cell nxt = first;
            while (nxt != null) {
                if (nxt.head == item) {
                    return true;
                }
                nxt = nxt.tail; // Makes sure we iterate through the list
            }
            return false;
        }

        void remove(int item) {
            if (first == null)
                return;

            Cell prev = first;
            Cell nxt = first.tail;
            if (first.head == item) {
                first = tail;
            }

            while (nxt != null) {
                if (nxt.head == item) {
                    prev.tail = nxt.tail;
                    return;
                }
                prev = nxt;
                nxt = nxt.tail; // Makes sure we iterate through the list
            }
        }

        public void printLinkedList() {
            // Cell prv = first;
            Cell nxt = first;

            while (nxt != null) {
                System.out.print(nxt.head + " ");
                // Makes sure we iterate through the list
                // prv = nxt;
                nxt = nxt.tail;
            }
            System.out.println();
        }
    }
}
