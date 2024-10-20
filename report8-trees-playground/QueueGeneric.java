public class QueueGeneric<T> {
    Node head;
    Node tail;

    private class Node {
        T item;
        Node next;

        private Node(T item, Node list) {
            this.item = item;
            this.next = list;
        }
    }

    public void enqueue(T item) {
        if (this.head == null) {
            this.head = new Node(item, null);
            this.tail = this.head;
        } else {
            Node nxt = tail;
            nxt.next = new Node(item, null);
            tail = nxt.next;
        }

    }

    public T dequeue() {
        if (head == null) {
            return null;
        }
        T value = head.item;
        head = head.next;

        return value;
    }

    public boolean hasNext() {
        return (head.next != null);
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void printLinkedList() {
        Node nxt = head;

        while (nxt != null) {
            System.out.print(nxt.item + " ");
            nxt = nxt.next;
        }
        System.out.println();
    }
}
