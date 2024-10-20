package se.id1021.assignment2;

public class Test {
    public static void main(String[] args) {
        DynaStack stack = new DynaStack(16);
        for (Integer i = 0; i < 65; i++) {
            stack.push(i);
        }
        Integer j = stack.pop();
        while (j != -1) {
            System.out.printf(" pop: %d\n", j);
            j = stack.pop();
        }
    }
}