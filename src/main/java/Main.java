import java.io.PrintStream;
import java.util.Objects;
import java.util.Scanner;

public class Main implements CalculatorInterface {

    public TokenList readTokens(String input) {
        TokenList tokenList = new TokenListImp();

        // Replaces and removes whitespace from the input

        String[] inputArray = removeDuplicateOperators(input)
                .replace(" ", "")
                .split( "(?=[-+*^\\/()])|(?<=[*^\\/()])|(?<=[^-+*^\\/(][+-])" );

        validateStringInput(input);

        for (String token: inputArray) {
            if (validateToken(token)) {
                tokenList.add(new TokenImp(token));
            };
        }

        return tokenList;
    }

    private boolean validateToken(String token) {
        final String OPERATORS = "+/-*^";
        final String PARENTHESES = "()";

        if (OPERATORS.contains(token) || token.matches("[+-]?\\d*[\\.,]?\\d*") || PARENTHESES.contains(token)) {
            return true;
        } else {
            throw new RuntimeException("Cannot calculate! Contains an unknown character.");
        }
    }

    private void validateStringInput(String input) {
        validateInputExists(input);
        validateOperatorInExpressions(input);
    }

    private void validateInputExists(String input) {
        if (!(input.matches(".*\\d.*"))) {
            throw new RuntimeException("The input is empty or contains non-valid characters.");
        }
    }

    private void validateOperatorInExpressions(String input) {
        if (input.matches( ".*\\d\\s+\\d.*" ) ) {
            throw new RuntimeException("An operator is needed to perform on numbers.");
        }
    }


    /*
     * PRE -
     * POST All subsequent operators in the input which could be replaced by a
     * single operator (+&-) are removed and replaced by a single operator
     */
    private String removeDuplicateOperators( String in ) {
        while (in.matches( ".*[\\+-]{2,}.*" ) ) {
            in = in.replaceAll( "\\+{2,}|(--)+", "\\+" );
            in  = in.replaceAll( "(\\+-)+|(-\\+)+", "-" );
        }

        return in;
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
        printIntro();

        while(in.hasNext()) {
            try {
                String inputLine = in.nextLine();
                TokenList tokenLine = readTokens(inputLine);

                Double result = rpn(shuntingYard(tokenLine));
                System.out.printf("%.6f\n", result);
            } catch (RuntimeException error) {
                System.out.println(error);
            }
            new PrintStream(System.out).printf("> ");
        }

        in.close();
    }

    private void printIntro() {
        int VERSION = 4;

        System.out.printf("Welcome to Calculator v%d Enter input in infix notation. Type help for more information.\n", VERSION);
        System.out.print("Input:\n> ");
    }

    public static void main(String[] argv) {
        new Main().start();
    }
}

