import java.util.Objects;
import java.util.Scanner;

public class Main implements CalculatorInterface {

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
                Double rhs = doubleStack.pop();
                Double lhs = doubleStack.pop();
                Double eval = evaluateExpression(lhs, rhs, token);

                doubleStack.push(eval);
            }
        }

        if (doubleStack.size() == 1) {
            return doubleStack.top();
        } else {
            throw new RuntimeException("Invalid Input, remaining tokens on stack.");
        }
    }

    /**
     * @param leftHandSide left-hand side of expressions to be evaluated
     * @param rightHandSide right-hand side of expressions to be evaluated
     * @param operator operation to perform on the given Doubles
     * @pre -
     * @post The evaluation of the two operands with the given operator has been returned as
     * Double.
     */
    private Double evaluateExpression(Double leftHandSide, Double rightHandSide, Token operator) {
        switch (operator.getValue()) {
            case "+":
                return leftHandSide + rightHandSide;
            case "-":
                return leftHandSide - rightHandSide;
            case "*":
                return leftHandSide * rightHandSide;
            case "/":
                return leftHandSide / rightHandSide;
            case "^":
                return Math.pow(leftHandSide, rightHandSide);
            default:
                throw new RuntimeException("Invalid Input, operator not recognized.");
        }
    }

    public TokenList shuntingYard(TokenList tokens) {
        TokenStack tokenStack = new TokenStackImp();
        TokenList output = new TokenListImp();

        for (int i = 0; i < tokens.size(); i++) {
            Token token = tokens.get(i);
            int tokenType = token.getType();

            if (tokenType == Token.NUMBER_TYPE ) {
                output.add(token);
            } else if (tokenType == Token.OPERATOR_TYPE) {
                while (tokenStack.size() > 0) {
                    if (tokenStack.top().getType()==Token.OPERATOR_TYPE && tokenStack.top().getPrecedence() >= token.getPrecedence()) {
                        // If the top of the 'tokenStack' is an operator and is more or equal important to the
                        // current token,
                        output.add(tokenStack.pop());
                    } else {
                        break;
                    }
                }

                tokenStack.push(token);
            }
            if (Objects.equals(token.getValue(), "(")) {
                tokenStack.push(token);
            } else if (Objects.equals(token.getValue(), ")")) {
                if (tokenStack.size() > 0) {
                    while (!tokenStack.top().getValue().equals("(")) {
                        output.add(tokenStack.pop());
                    }

                    tokenStack.pop();
                }
            }
        }

        while(tokenStack.size() > 0) {
            if (tokenStack.top().getType() == Token.OPERATOR_TYPE) {
                output.add(tokenStack.pop());
            } else {
                tokenStack.pop();
            }
        }

        return output;
    }

    private void start() {
        Scanner in = new Scanner(System.in);

        while(in.hasNext()) {
            String inputLine = in.nextLine();
            TokenList tokenLine = readTokens(inputLine);
            parseInputLine(tokenLine);

            Double result = rpn(shuntingYard(tokenLine));
            System.out.printf("%.6f\n", result);
        }
    }

    /**
     * @param tokenList list of Tokens to parse
     * @pre -
     * @post The input has been parsed and no errors have been found, or the program notifies the
     * user that a possible problem in the input has been detected.
     */
    private void parseInputLine(TokenList tokenList) {
        if (tokenList.size() > 0 ) {
            for (int i = 1; i < tokenList.size(); i ++) {
                // Checks for duplicate input of the same type subsequently.
                if (tokenList.get(i).getType() == tokenList.get(i - 1).getType() && ! (tokenList.get(i).getType()==Token.PARENTHESIS_TYPE)) {
                    System.out.println("WARNING: Input contains duplicate subsequent elements.");
                }
            }
        }

    }


    public static void main(String[] argv) {
        new Main().start();
    }
}

