public class TokenStackImp implements TokenStack {
    private static final int INITIAL_STACK_SIZE = 10;

    private Token[] tokenArray;
    private int numberOfElements;

    TokenStackImp() {
        this.tokenArray = new Token[INITIAL_STACK_SIZE];
        this.numberOfElements = 0;
    }

    @Override
    public void push(Token token) {
        if (numberOfElements == tokenArray.length) {
            doubleTokenStack();
        }

        this.tokenArray[this.numberOfElements++] = token;
    }

    /**
     * @pre tokenArray.length == numberOfElements
     * @post The current reference of 'tokenArray' points to a
     * new Token[] array with the elements copied from the previous
     * 'tokenArray', and with double the length of the previous tokenArray.
     */
    private void doubleTokenStack() {
        Token[] originalArray = tokenArray;
        Token[] copyArray = new Token[originalArray.length * 2];
        this.tokenArray = copyElements(originalArray, copyArray);
    }

    /**
     * @param originalArray The original Token[] whose elements will be copied.
     * @param copyArray The Token[] which will get the elements from originalArray.
     * @pre copyArray.length >= originalArray.length
     * @post The array 'copyArray' is pointing to, has the Tokens with the same
     * value as the ones in originalArray and is returned.
     */
    private Token[] copyElements(Token[] originalArray, Token[] copyArray) {
        for (int i = 0; i < originalArray.length; i++) {
            copyArray[i] = originalArray[i];
        }

        return copyArray;
    }

    @Override
    public Token pop() {
        Token top = top();
        tokenArray[ --numberOfElements ] = null;
        return top;
    }

    @Override
    public Token top() {
        return tokenArray[ numberOfElements - 1 ];
    }

    @Override
    public int size() {
        return numberOfElements;
    }
}
