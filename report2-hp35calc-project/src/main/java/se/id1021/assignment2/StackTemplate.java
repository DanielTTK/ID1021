package se.id1021.assignment2;

abstract class StackTemplate {
    int[] stack;
    int size;
    int top;

    public abstract void push(int val);

    public abstract int pop();
}
