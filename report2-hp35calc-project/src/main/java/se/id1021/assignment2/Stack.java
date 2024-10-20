package se.id1021.assignment2;

@SuppressWarnings("unchecked")
public class Stack<T> {
    T[] stack;
    int top = 0;

    public Stack(int size) {
        stack = (T[]) new Object[size];
        top = 0;
    }

    public void push(T val) {
        // System.out.println(stack.length + " <= " + top);
        if (stack.length <= (top)) {
            stack = incrArray(stack);
        } else if (top < 0) {
            System.out.print("Crash and burn");
        }
        stack[top++] = val;

    }

    public T pop() {
        if ((top - 1) < 0) {
            System.out.println("ERROR: Cannot pop, stack is empty.");
            return null;
        } else if ((top - 1) <= ((stack.length / 2) - 2)) {
            stack = decrArray(stack);
        }

        return stack[--top];
    }

    public T[] copyArray(T[] oldArray, T[] newArray, int mode) {
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

    public T[] incrArray(T[] stack2) {
        int newSize = stack2.length * 2;
        T[] newArray = (T[]) new Object[newSize];

        newArray = copyArray(stack2, newArray, 1);
        System.out.println("Stack has increased: " + newArray.length);
        return newArray;
    }

    public T[] decrArray(T[] oldArray) {
        int newSize = oldArray.length / 2;
        T[] newArray = (T[]) new Object[newSize];

        newArray = copyArray(oldArray, newArray, 2);
        System.out.println("Stack has decreased: " + newArray.length);
        return newArray;
    }

    int getStackLength() {
        return stack.length;
    }
}