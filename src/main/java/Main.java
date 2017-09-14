import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main implements CalculatorInterface {


    public TokenList readTokens(String input) {
        // TODO: Implement this
        Scanner inputStream = new Scanner(input);
        return null;
    }


    public Double rpn(TokenList tokens) {
        // TODO: Implement this
        return null;
    }

    public TokenList shuntingYard(TokenList tokens) {
        // TODO: Implement this
        return null;
    }

    private void start() {
        // Create a scanner on System.in
        // While there is input, read line and parse it.
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            String test = in.next();
        }
        System.out.print("test");
    }

    public static void main(String[] argv) {
        new Main().start();
    }
}
