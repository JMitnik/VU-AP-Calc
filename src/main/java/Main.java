import java.io.PrintStream;
import java.util.Objects;
import java.util.Scanner;

public class Main implements CalculatorInterface {

    private void start() {
        Scanner in = new Scanner(System.in);

        while(in.hasNext()) {
            try {
                String inputLine = in.nextLine();
                TokenList tokenLine = readTokens(inputLine);

                Double result = rpn(shuntingYard(tokenLine));
                System.out.printf("%.6f\n", result);
            } catch (RuntimeException error) {
                System.out.println(error);
            }
        }

        in.close();
    }

    /**
     * Reads and interprets the input string as a tokenList.
     * @param input - String input to parse for tokens.
     * @return TokenList
     */
    public TokenList readTokens(String input) {
        TokenList tokenList = new TokenListImp();
        Scanner tokenIn = new Scanner(input);

        while (tokenIn.hasNext()) {
            Token token = new TokenImp(tokenIn.next());
            validateToken(token);
            tokenList.add(token);
        }

        tokenIn.close();
        return tokenList;
    }

    /**
     * Checks if the given token 'token' can be identified as one of the three possible token-types.
     * @param token - Token to check
     */
    private void validateToken(Token token) {
        try {
            token.getType();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Converts the given TokenList 'tokens' to a prefix-notation.
     * @param tokens - Given TokenList to convert
     * @return TokenList - TokenList sorted in a prefix-notation
     */
    public TokenList shuntingYard(TokenList tokens) {
        TokenStack tokenStack = new TokenStackImp();
        TokenList output = new TokenListImp();

        for (int i = 0; i < tokens.size(); i++) {
            sortTokenInPrefix(tokens.get(i), tokenStack, output);
        }

        while (tokenStack.size() > 0) {
            if (tokenStack.top().getType() == Token.OPERATOR_TYPE) {
                output.add(tokenStack.pop());
            } else {
                tokenStack.pop();
            }
        }

        return output;
    }

    /**
     * Sorts a token based on its type in-place.
     * @param token
     * @param tokenStack
     * @param output
     */
    private void sortTokenInPrefix(Token token, TokenStack tokenStack, TokenList output) {
        int tokenType = token.getType();

        if (tokenType == Token.NUMBER_TYPE ) {
            output.add(token);
        } else if (tokenType == Token.OPERATOR_TYPE) {
            sortOperatorTokenInPrefix(token, tokenStack, output);
        } else {
            sortParenthesisTokenInPrefix (token, tokenStack, output);
        }
    }

    /**
     * Sorts a parenthesis-token either into a temporary position in the tokenStack,
     * or added to the 'output' in-place based on its value.
     * @param token
     * @param tokenStack
     * @param output
     */
    private void sortParenthesisTokenInPrefix(Token token, TokenStack tokenStack, TokenList output) {
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

    /**
     * Sorts an operator-token either into a temporary position in the tokenStack,
     * or added to the 'output' in-place based on its precedence and type.
     * @param token
     * @param tokenStack
     * @param output
     */
    private void sortOperatorTokenInPrefix(Token token, TokenStack tokenStack, TokenList output) {
        while (tokenStack.size() > 0) {
            if (tokenStack.top().getType()==Token.OPERATOR_TYPE && tokenStack.top().getPrecedence() >= token.getPrecedence()) {
                output.add(tokenStack.pop());
            } else {
                break;
            }
        }

        tokenStack.push(token);
    }


    /**
     * Performs the calculations on the given TokenList and returns the evaluation
     * of the input-expression.
     * @param tokens - TokenList in prefix-notation
     * @return Double
     */
    public Double rpn(TokenList tokens) {
        DoubleStack doubleStack = calculateTokenList(tokens);

        if (doubleStack.size() == 1) {
            return doubleStack.top();
        } else {
            throw new RuntimeException("Invalid Input.");
        }
    }

    /**
     * Perform the operations to its operands and reduces the given TokenList
     * to the smallest possible number of Doubles.
     * @param tokens - TokenList to reduce.
     * @return DoubleStack
     */
    private DoubleStack calculateTokenList(TokenList tokens) {
        DoubleStack result = new DoubleStackImp();

        for (int i = 0; i < tokens.size(); i++) {
            Token token = tokens.get(i);
            int tokenType = token.getType();

            if (tokenType == Token.NUMBER_TYPE ) {
                result.push(Double.parseDouble(token.getValue()));
            } else if (tokenType == Token.OPERATOR_TYPE) {
                if (result.size() >= 2) {
                    Double rhs = result.pop();
                    Double lhs = result.pop();
                    Double eval = evaluateExpression(lhs, rhs, token);

                    result.push(eval);
                } else {
                    throw new RuntimeException("Missing operand; too many operators.");
                }
            }
        }

        return result;
    }

    /**
     * Returns the evaluation of the two operands with the given operator has been returned as
     * Double.
     * @param leftHandSide left-hand side of expressions to be evaluated
     * @param rightHandSide right-hand side of expressions to be evaluated
     * @param operator operation to perform on the given Doubles
     * @return Double
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

    public static void main(String[] argv) {
        new Main().start();
    }
}

