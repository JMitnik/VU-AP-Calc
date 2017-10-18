import javax.management.RuntimeErrorException;

public class TokenImp implements Token {
    private static final String OPERATOR_TOKENS = "+-*/^";
    private static final String OPERATOR_PLUS_TOKEN = "+";
    private static final String OPERATOR_MINUS_TOKEN = "-";
    private static final String OPERATOR_MULTIPLY_TOKEN = "*";
    private static final String OPERATOR_DIVIDE_TOKEN = "/";
    private static final String PARENTHESIS_TOKENS = "()";
    private static final int NUMBER_PRECEDENCE= -1;
    private static final int PLUS_MINUS_PRECEDENCE= 0;
    private static final int MULTI_DIVIDE_PRECEDENCE= 1;
    private static final int POWER_PRECEDENCE= 2;
    private static final int PARENTHESIS_PRECEDENCE = 3;

    private String value;

    TokenImp(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public int getType() throws RuntimeException {
        if (isParenthesis()) {
            return PARENTHESIS_TYPE;
        } else if (isOperator()) {
            return OPERATOR_TYPE;
        } else if (isNumber()) {
            return NUMBER_TYPE;
        } else {
            throw new RuntimeException("Token is not recognizable.");
        }
    }

    /**
     * Returns true if the given String 'value' is an operator, else false.
     * @return boolean
     */
    private boolean isOperator() {
        return OPERATOR_TOKENS.contains(value);
    }

    /**
     * Returns true if the given String 'value' is a parenthesis, else false.
     * @return boolean
     */
    private boolean isParenthesis() {
        return PARENTHESIS_TOKENS.contains(value);
    }

    /**
     * Checks if value is number
     * @return boolean.
     */
    private boolean isNumber() {
        try {
            double d = Double.parseDouble(value);
        } catch (NumberFormatException nfe) {
            return false;
        }

        return true;
    }

    @Override
    public int getPrecedence() {
        int tokenType = getType();

        if (tokenType == OPERATOR_TYPE) {
            return getOperatorPrecedence();
        } else if (tokenType == PARENTHESIS_TYPE) {
            return PARENTHESIS_PRECEDENCE;
        }

        return NUMBER_PRECEDENCE;
    }

    /**
     * Returns the precedence of the this token, which is an operator.
     * @return int
     */
    private int getOperatorPrecedence() {
        switch (value) {
            case OPERATOR_PLUS_TOKEN:
            case OPERATOR_MINUS_TOKEN:
                return PLUS_MINUS_PRECEDENCE;
            case OPERATOR_MULTIPLY_TOKEN:
            case OPERATOR_DIVIDE_TOKEN:
                return MULTI_DIVIDE_PRECEDENCE;
            default:
                return POWER_PRECEDENCE;
        }
    }
}
