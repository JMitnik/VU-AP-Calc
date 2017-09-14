public class Token implements TokenInterface {

    private String value;
    private static final String OPERATOR_TOKENS = "+-*/^";
    private static final String OPERATOR_PLUS_TOKEN = "+";
    private static final String OPERATOR_MINUS_TOKEN = "-";
    private static final String OPERATOR_MULTIPLY_TOKEN = "*";
    private static final String OPERATOR_DIVIDE_TOKEN = "/";
    private static final String OPERATOR_POWER_TOKEN = "^";
    private static final String PARANTHESIS_TOKENS = "()";

    /**
     * @pre -
     * @post The value associated with this token has been returned a String.
     */
    @Override
    public String getValue() {
        return value;
    }

    /**
     * @pre -
     * @post The type of this object, represented as an int, has been returned.
     */
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

    private boolean isOperator(String value) {
        //TODO: Can I use String.contains?
        return OPERATOR_TOKENS.contains(value);
    }

    private boolean isParenthesis(String value) {
        //TODO: Can I use String.contains?
        return PARANTHESIS_TOKENS.contains(value);
    }

    /**
     * @pre -
     * @post The precedence of the token, represented by an int,
     * has been returned. Higher int's signify a higher precedence.
     * If token type does not need a precedence,
     * the result of this method is -1.
     */
    @Override
    public int getPrecedence() {
        if (getType() == OPERATOR_TYPE) {
            return getOperatorPrecedence();
        } else if (getType() == PARENTHESIS_TYPE) {
            return 3;
        }

        return -1;
    }

    private int getOperatorPrecedence() {
        if (value.equals(OPERATOR_PLUS_TOKEN) || value.equals(OPERATOR_MINUS_TOKEN)) {
            return 0;
        } else if (value.equals(OPERATOR_MULTIPLY_TOKEN) || value.equals(OPERATOR_DIVIDE_TOKEN)) {
            return 1;
        } else {
            return 2;
        }
    }
}
