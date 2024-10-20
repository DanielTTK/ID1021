package se.id1021.assignment2;

public class StaticStack {
    int[] stack;
    int size;
    int top = 1;

    public StaticStack(int size) {
        stack = new int[size];
        top = 1;
    }

    public void push(int val) {
        if (top <= stack.length) {
            stack[top++] = val;
        } else {
            System.out.println("error");
        }
    }

    public int pop() {
        if (stack.length <= (top - 1)) {
            System.out.print("meep");
            return -1;
        }
        return stack[--top];
    }

    public static void main(String[] args) {
        StaticStack s = new StaticStack(3);
        s.push(15);
        s.push(32);
        s.push(33);
        s.push(34);
        System.out.println("pop : " + s.pop());
        System.out.println("pop : " + s.pop());
        System.out.println("pop : " + s.pop());
        System.out.println("pop : " + s.pop());
    }
}
