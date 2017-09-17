import javax.management.RuntimeErrorException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main implements CalculatorInterface {

    //TODO: When do we instance a variable as implementation, and when as interface type?
    //TODO: Do Implementations know of each other?

    public TokenList readTokens(String input) {
        Scanner tokenScanner = new Scanner(input);
        TokenList tokenList = new TokenListImp();

        while (tokenScanner.hasNext()) {
            Token token = new TokenImp(tokenScanner.next());
            tokenList.add(token);
        }

        return tokenList;
    }


    public Double rpn(TokenList tokens) {
        DoubleStack doubleStack = new DoubleStackImp();

        for (int i = 0; i < tokens.size(); i++) {
            Token token = tokens.get(i);
            int tokenType = token.getType();

            if (tokenType == Token.NUMBER_TYPE ) {
                doubleStack.push(Double.parseDouble(token.getValue()));
            } else if (tokenType == Token.OPERATOR_TYPE) {
                Double lhs = doubleStack.pop();
                Double rhs = doubleStack.pop();
                Double eval = evaluateExpression(lhs, rhs, token);
            }
        }

        if (doubleStack.size() == 1) {
            return doubleStack.top();
        } else {
            throw new RuntimeException("Invalid Input, remaining tokens on stack.");
        }
    }

    private Double evaluateExpression(Double leftHandSide, Double righthandSide, Token operator) {
        switch (operator.getValue()) {
            case "+":
                return leftHandSide + righthandSide;
            case "-":
                return leftHandSide - righthandSide;
            case "*":
                return leftHandSide * righthandSide;
            case "/":
                return leftHandSide / righthandSide;
            case "^":
                return Math.pow(leftHandSide, righthandSide);
            default:
                throw new RuntimeException("Invalid Input, operator not recognized.");
        }
    }

    public TokenList shuntingYard(TokenList tokens) {
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

