import java.util.Random;

public class BinaryTree {
    private class Node {
        private Integer value;
        private Node left, right;

        private Node(Integer value) {
            this.value = value;
            this.left = this.right = null;
        }

        @SuppressWarnings("unused")
        public void print() {
            if (left != null)
                left.print();
            System.out.println(value);
            if (right != null)
                right.print();
        }

        public void add(Integer value) {
            if (this.value == null) {
                root = new Node(value);
            } else if (value == this.value) {
                return;
            } else if (value < this.value) {
                if (this.left == null) {
                    this.left = new Node(value);
                } else {
                    this.left.add(value); // iterate through left branch. (try to add)
                }
            } else if (value > this.value) {
                if (this.right == null) {
                    this.right = new Node(value);
                } else {
                    this.right.add(value); // iterate through right branch. (try to add)
                }
            }
        }

        public boolean lookup(Integer value) {
            if (this.value == null) {
                return false;
            } else if (value == this.value) {
                return true;
            } else if (value < this.value) {
                if (this.left == null) {
                    return false;
                } else {
                    this.left.lookup(value); // iterate
                }
            } else if (value > this.value) {
                if (this.right == null) {
                    return false;
                } else {
                    this.right.lookup(value);
                }
            }

            return false;
        }
    }

    Node root;

    public BinaryTree() {
        root = null;
    }

    public void createRoot(int v) {
        root = new Node(v);
    }

    // The reason it prioritizes going left is because the
    // "smaller" values are there. As soon as we "pop" back,
    // we print that value and then traverse the left subtree.
    // If even the right subtree is empty, we pop back again,
    // as the left subtree is empty!
    public void stackPrint() {
        Stack<Node> stk = new Stack<Node>(5);
        Node cur = this.root;
        while (cur != null || stk.getStackLength() > 0) {
            // move to leftmost and save prev. positions
            while (cur != null) {
                stk.push(cur);
                cur = cur.left;
            }

            cur = stk.pop(); // move one step back
            if (cur != null) {
                System.out.print(cur.value + " "); // print
            } else {
                break;
            }

            // Go right once, then iterate left for this subtree.
            if (cur.right != null) {
                cur = cur.right;

                while (cur != null) {
                    stk.push(cur);
                    cur = cur.left;
                }
            } else {
                cur = null;
            }
        }
    }

    public void queuePrint() {
        QueueGeneric<Node> q = new QueueGeneric<Node>();
        Node cur = this.root;

        if (cur == null)
            return;

        q.enqueue(cur);
        while (!q.isEmpty()) {
            cur = q.dequeue();
            System.out.print(cur.value + " ");
            // iteration of q, traversal
            if (cur.left != null) {
                q.enqueue(cur.left);
            }
            if (cur.right != null) {
                q.enqueue(cur.right);
            }
        }
    }

    // Method to return a sequence object
    public Sequence sequence() { // had difficulty understanding purpose of this tbh
        return new Sequence(root);
    }

    public class Sequence {
        private QueueGeneric<Node> q;

        public Sequence(Node root) { // add root here as we cannot access root
            this.q = new QueueGeneric<Node>();

            if (root != null) { // count root into the list
                q.enqueue(root);
            }
        }

        Integer next() {
            if (q.isEmpty()) {
                return null; // r null if q is empty
            }

            Node cur = q.dequeue();
            // System.out.print(cur.value + " "); // debug?
            // iteration of q, traversal
            if (cur.left != null) {
                q.enqueue(cur.left);
            }
            if (cur.right != null) {
                q.enqueue(cur.right);
            }
            return cur.value;
        }

        public boolean hasNext() {
            return !q.isEmpty();
        }
    }

    public void TreeGen(int n) {
        root = new Node(0);

        for (int i = 0; i < n; i++) {
            Random rnd = new Random();
            Integer rand = rnd.nextInt(n) + 1;
            root.add(rand);
        }
    }

    public long bench(int n) {
        Random rnd = new Random();
        long t0 = 0, t1 = 0, t2 = 0;

        int lookups = n;
        TreeGen(n);
        for (int i = 0; i < lookups; i++) {
            Integer rand = rnd.nextInt(n) + 1;
            t0 = System.nanoTime();
            root.lookup(rand);
            t1 = System.nanoTime();
            t2 += (t1 - t0);
        }
        return t2;
    }

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

        l.root = l.new Node(12);
        l.root.add(6);
        l.root.add(2);
        l.root.add(7);
        l.root.add(15);
        l.root.add(18);
        l.root.add(16);

        System.out.println("Using queue: ");
        l.queuePrint();
        System.out.println();

        System.out.println("Using stack: ");
        l.stackPrint();

        System.out.println("Using sequence: ");
        Sequence s = l.sequence();

        // print three val. and pause
        for (int i = 0; i < 3 && s.hasNext(); i++) {
            System.out.println(s.next());
        }

        l.root.add(20); // add to the thingy

        // get and print 2 more
        for (int i = 0; i < 2 && s.hasNext(); i++) {
            System.out.println(s.next());
        }

        // get and print 3 more
        for (int i = 0; i < 3 && s.hasNext(); i++) {
            System.out.println(s.next());
        }

        for (int n : sizes) {
            long min1 = Long.MAX_VALUE;
            // long min2 = Long.MAX_VALUE;
            for (int i = 0; i < k; i++) {
                long t1 = l.bench(n);
                if (t1 < min1)
                    min1 = t1;
                // if (t2 < min2)
                // min2 = t2;
            }
            // System.out.println("n: " + n + " time1: " + min1 + " time2: " + min2 +
            // " diff: " + (min2 - min1));

            // System.out.println("(" + n + ", " + min1 + ")");

            // System.out.println("n: " + n + " time: " + min1 + " ratio: " + min1 / n);
        }

    }
}
