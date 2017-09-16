import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main implements CalculatorInterface {


    public TokenList readTokens(String input) {
        Scanner tokenScanner = new Scanner(input);
        TokenList tokenList = new TokenList();

        while (tokenScanner.hasNext()) {
            Token token = new Token(tokenScanner.next());
            tokenList.add(token);
        }

        return tokenList;
    }


    public Double rpn(TokenList tokens) {
        DoubleStack doubleStack = new DoubleStack(tokens);
        return null;
    }

    public TokenList shuntingYard(TokenList tokens) {
        // TODO: Implement this
        return null;
    }

    private void start() {
        // TODO: Read input from file instead?
        Scanner in = new Scanner(System.in);
        String tokenLine = in.nextLine();
        //TODO: Parse multiple lines with loop
        TokenList tokenList = readTokens(tokenLine);
    }

    public static void main(String[] argv) {
        new Main().start();
    }
}
