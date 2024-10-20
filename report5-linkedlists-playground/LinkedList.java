class LinkedList {
    Cell first;

    public static void main(String[] args) {
        // Create the first node (head of the linked list)
        LinkedList ll = new LinkedList();
        LinkedList b = new LinkedList();
        ll.first = ll.new Cell(0, null); // We will use this as the head of the list
        b.first = b.new Cell(0, null);

        // Adding elements to the linked list
        ll.first.add(1);
        ll.first.add(2); // Mention that you relearnt breakpoints and maybe also time of print variables
        ll.first.add(3);

        b.first.add(1);
        b.first.add(2);
        b.first.add(3);

        // Print the linked list
        System.out.println("Original List:");
        ll.first.printLinkedList();

        // Removing an element
        System.out.println("\n\n*Removing 2*");
        ll.first.remove(2);

        // Find an element
        System.out.println("\nFinding 2:");
        boolean found = ll.first.find(2);
        System.out.println("Found 2: " + found);

        // Find an element
        System.out.println("\nFinding 1:");
        boolean found2 = ll.first.find(1);
        System.out.println("Found 1: " + found2);

        // Print the modified linked list
        System.out.println("\nModified List:");
        ll.first.printLinkedList();

        System.out.println("\nScnd Linked List");
        b.first.printLinkedList();

        ll.append(b);

        System.out.println("\nAppended List:");
        ll.first.printLinkedList();
    }

    public void append(LinkedList b) {
        Cell nxt = this.first;
        while (nxt.tail != null) {
            nxt = nxt.tail;
        }
        nxt.tail = b.first;
    }

    public void LinkedListGenerator(int n) {
        Cell last = null;
        for (int i = 0; i < n; i++) {
            last = new Cell(i, last);
        }
        first = last;
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
        }
    }
}
