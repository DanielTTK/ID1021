public class ArrayQueue<T> {
    private T[] array; // The array that holds queue elements
    private int front; // Index for the front element
    private int rear; // Index for the next free spot
    private int size; // Current number of elements in the queue
    private int capacity; // Max capacity of the array

    public static long bench(int n, ArrayQueue<Integer> queue) {
        long t0 = 0;
        long t1 = 0;
        t0 = System.nanoTime();
        queue.enqueue(5);
        t1 = System.nanoTime();

        return (t1 - t0);
    }

    public static void main(String[] args) {
        ArrayQueue<Integer> queue = new ArrayQueue<>(5);

        // Benchmarking
        int k = 20;

        int[] sizes = new int[k]; // Sizes is an array of n values.

        for (int i = 0; i < k; i++) {
            sizes[i] = (int) (Math.pow(2, i) * 100);
        }

        // JIT warmup
        // bench(1000, queue);

        for (int n : sizes) {
            long min1 = Long.MAX_VALUE;
            // long min2 = Long.MAX_VALUE;
            for (int i = 0; i < k; i++) {
                long t1 = bench(n, queue);
                if (t1 < min1)
                    min1 = t1;
                // if (t2 < min2)
                // min2 = t2;
            }
            // System.out.println("n: " + n + " time1: " + min1 + " time2: " + min2 +
            // " diff: " + (min2 - min1));

            System.out.println("(" + n + ", " + min1 + ")");

            // System.out.println("n: " + n + " time: " + min1 + " ratio: " + min1 / n);
        }
        // System.out.print(ll.huh);
    }

    @SuppressWarnings("unchecked")
    public ArrayQueue(int capacity) {
        this.capacity = capacity;
        array = (T[]) new Object[capacity];
        front = 0;
        rear = 0;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }

    public void enqueue(T item) {
        if (isFull()) {
            resizeArray();
        }
        array[rear] = item;
        rear = (rear + 1) % capacity; // Wrap around
        size++;
    }

    // Resize array if full
    @SuppressWarnings("unchecked")
    private void resizeArray() {
        int newCapacity = capacity * 2; // Double the capacity
        T[] newArray = (T[]) new Object[newCapacity];

        // Copy elements in the correct order from front to rear
        for (int i = 0; i < size; i++) {
            newArray[i] = array[(front + i) % capacity];
        }

        array = newArray;
        front = 0;
        rear = size;
        capacity = newCapacity;
    }

    public T dequeue() {
        if (isEmpty()) {
            return null;
        }

        T item = array[front];
        array[front] = null; // Remove reference for garbage collection
        front = (front + 1) % capacity; // Wrap around
        size--;
        return item;
    }

    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }
        return array[front];
    }

    public int size() {
        return size;
    }

}
