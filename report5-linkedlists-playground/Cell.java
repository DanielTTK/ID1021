public class Cell {
    int head;
    Cell tail;

    Cell(int val, Cell tl) {
        head = val;
        tail = tl;
    }

    Cell first;

    void add(int item) { // add smth to top of list
        Cell tmp = new Cell(item, first); // Make a new cell, then make first "point" to it
        first = tmp;
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

    public void append(DummyLL b) {
        Cell nxt = this.first;
        // Cell prv = null;
        while (nxt.tail != null) {
            // prv = nxt;
            nxt = nxt.tail;
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

    public static void main(String[] args) {
        // Create the first node (head of the linked list)
        Cell first = new Cell(0, null); // We will use this as the head of the list

        // Adding elements to the linked list
        first.add(1);
        first.add(2); // Mention that you relearnt breakpoints and maybe also time of print variables
        first.add(3);

        // Print the linked list
        System.out.println("Original List:");
        first.printLinkedList();

        // Find length
        System.out.println("\n\nList Length:");
        System.out.println(first.length());

        // Removing an element
        System.out.println("\n*Removing 2*");
        first.remove(2);

        // Find an element
        System.out.println("\nFinding 2:");
        boolean found = first.find(2);
        System.out.println("Found 2: " + found);

        // Find an element
        System.out.println("\nFinding 1:");
        boolean found2 = first.find(1);
        System.out.println("Found 1: " + found2);

        // Print the modified linked list
        System.out.println("\nModified List:");
        first.printLinkedList();

        // Find length
        System.out.println("\n\nList Length:");
        System.out.println(first.length());
    }
}
