package se.id1021.assignment2;

import java.io.*;

public class HP35 {
    public static void main(String[] args) throws IOException {
        DynaStack stack = new DynaStack(4);
        System.out.println("HP-35 pocket calculator");
        boolean run = true;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t0 = 0;
        int t1 = 0;

        while (run) {
            System.out.print(" > ");
            String input = br.readLine();
            switch (input) {
                case "+":
                    t0 = stack.pop();
                    t1 = stack.pop();
                    stack.push(t0 + t1);
                    break;
                case "-":
                    t0 = stack.pop();
                    t1 = stack.pop();
                    stack.push(t1 - t0);
                    break;
                case "*":
                    t0 = stack.pop();
                    t1 = stack.pop();
                    stack.push(t0 * t1);
                    break;
                case "/":
                    t0 = stack.pop();
                    t1 = stack.pop();
                    stack.push(t0 / t1);
                    break;
                case "":
                    run = false;
                    break;
                default:
                    int nr = Integer.parseInt(input);
                    stack.push(nr);
                    break;
            }
        }
        System.out.printf("the result is: %d\n\n", stack.pop());
        // System.out.printf("I love reversed polish notation, don't you?\n");
    }
}
