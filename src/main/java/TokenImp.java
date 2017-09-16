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
        //TODO: Do we need to annotate the constructor?
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public int getType() {
        if (isParenthesis(value)) {
            return PARENTHESIS_TYPE;
        } else if (isOperator(value)) {
            return OPERATOR_TYPE;
        } else {
            return NUMBER_TYPE;
        }
    }

    /**
     * @param value The value to check
     * @pre -
     * @post The given String value is checked as an operator,
     * where the result is a returned as a boolean.
     */
    private boolean isOperator(String value) {
        return OPERATOR_TOKENS.contains(value);
    }

    /**
     * @param value The value to check
     * @pre -
     * @post The given String value is checked as a parenthesis, where
     * the result is returned as a boolean.
     */
    private boolean isParenthesis(String value) {
        return PARENTHESIS_TOKENS.contains(value);
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
     * @pre The value of the token is an operator.
     * @post The precedence of the type of operator stored as the
     * Token's value is returned as an integer.
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
