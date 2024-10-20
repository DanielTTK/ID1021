package se.id1021.assignment2;

public class DynaStack {
    int[] stack;
    int size;
    int top = 0;

    public DynaStack(int size) {
        stack = new int[size];
        top = 0;
    }

    public void push(int val) {
        // System.out.println(stack.length + " <= " + top);
        if (stack.length <= (top)) {
            stack = incrArray(stack);
        } else if (top < 0) {
            System.out.print("Crash and burn");
        }
        stack[top++] = val;

    }

    public int pop() {
        if ((top - 1) < 0) {
            System.out.println("ERROR: Cannot pop, stack is empty.");
            return -1;
        } else if ((top - 1) <= ((stack.length / 2) - 2)) {
            stack = decrArray(stack);
        }

        return stack[--top];
    }

    public static void main(String[] args) {
        DynaStack s = new DynaStack(4);
        s.pop();
        s.push(15);
        s.push(32);
        s.push(33);
        s.push(15);
        s.push(32);
        s.push(33);

        System.out.println("pop: " + s.pop());
        System.out.println("pop: " + s.pop());
        System.out.println("pop: " + s.pop());
        System.out.println("pop: " + s.pop());
        System.out.println("pop: " + s.pop());
        System.out.println("pop: " + s.pop());

        System.out.println("Stack length was " + s.getStackLength());
    }

    public int[] copyArray(int[] oldArray, int[] newArray, int mode) {
        int size = 0;
        if (mode == 1) {
            size = oldArray.length;
        } else if (mode == 2) {
            size = newArray.length;
        }

        for (int i = 0; i < size; i++) {
            newArray[i] = oldArray[i];
        }
        return newArray;
    }

    public int[] incrArray(int[] oldArray) {
        int newSize = oldArray.length * 2;
        int[] newArray = new int[newSize];

        newArray = copyArray(oldArray, newArray, 1);
        System.out.println("Stack has increased: " + newArray.length);
        return newArray;
    }

    public int[] decrArray(int[] oldArray) {
        int newSize = oldArray.length / 2;
        int[] newArray = new int[newSize];

        newArray = copyArray(oldArray, newArray, 2);
        System.out.println("Stack has decreased: " + newArray.length);
        return newArray;
    }

    int getStackLength() {
        return stack.length;
    }
}