package GenericStack;

import java.io.PrintStream;

class Demo {

    PrintStream out;

    Demo () {
        out = new PrintStream(System.out);
    }

    void printA (A a) {
        out.printf("%d", a.i);
    }

    void printStackA (Stack<A> arg) {
        Stack<A> stack = arg.copy();
        out.printf("[");
        for (int i = 0; i < arg.size()-1; i++) {
            printA(stack.pop());
            out.printf(", ");
        }
        printA(stack.pop());
        out.printf("]\n\n");
    }

    void start () {
        Stack<A> aStack = new Stack<A>();
        A a1 = new A(11),
          a2 = new A(12),
          a3 = new A(13);
        aStack.push(a1).push(a2).push(a3);
        printStackA(aStack);
        Stack<A> aStack2 = aStack.copy();
        aStack2.push(new A(14));
        printStackA(aStack2);
        Stack<A> aStack3 = aStack2.concat(aStack);
        printStackA(aStack3);
    }

    public static void main (String[] argv) {
        new Demo().start();
    }
}

