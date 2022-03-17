package example;
import java.io.PrintStream;

class E2 {

    PrintStream out;

    E2 () {
        out = new PrintStream(System.out);
    }

    void m3 () throws Exception {
        throw new Exception("ERROR");
    }

    void m2 () throws Exception {
        m3();
    }

    void m1 () throws Exception {
        m2();
    }

    void start () {
        try {
            m1();
        } catch (Exception e) {
            out.println(e);
        }
    }

    public static void main (String[] argv) {
        new E2().start();
    }
}
