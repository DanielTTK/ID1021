public class Queue {
    Node head;
    Node tail;

    private class Node {
        Integer item;
        Node next;

        private Node(Integer item, Node list) {
            this.item = item;
            this.next = list;
        }
    }

    public void QueueGen(int n) {
        Node last = null;
        last = new Node(0, last);
        tail = last;
        for (int i = 0; i < n; i++) {
            last = new Node(i, last);
        }
        head = last;
    }

    public void enqueue(Integer item) {
        if (this.head == null) {
            this.head = new Node(item, null);
            tail = this.head;
        }
        Node nxt = tail;
        nxt.next = new Node(item, null);
        tail = nxt.next;
    }

    public Integer dequene() {
        if (head == null) {
            return null;
        }
        Integer value = head.item;
        head = head.next;

        return value;
    }

    public void printLinkedList() {
        Node nxt = head;

        while (nxt != null) {
            System.out.print(nxt.item + " ");
            nxt = nxt.next;
        }
        System.out.println();
    }

    Integer huh = null;

    public static long bench(int n, Queue ll) {
        ll.QueueGen(n);
        long t0 = System.nanoTime();
        Integer huh = ll.dequene();
        long t1 = System.nanoTime();
        return (t1 - t0);
    }

    public static void main(String[] args) {
        Queue ll = new Queue();

        // Create the first nodes (head of the linked lists)
        ll.head = null; // We will use this as the head of the list

        // Benchmarking
        int k = 10;

        int[] sizes = new int[k]; // Sizes is an array of n values.

        for (int i = 0; i < k; i++) {
            sizes[i] = (int) (Math.pow(2, i) * 100);
        }

        // JIT warmup
        bench(1000, ll);

        for (int n : sizes) {
            long min1 = Long.MAX_VALUE;
            // long min2 = Long.MAX_VALUE;
            for (int i = 0; i < k; i++) {
                long t1 = bench(n, ll);
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
        // System.out.print(ll.huh);
    }

}
