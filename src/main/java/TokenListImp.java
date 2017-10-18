public class TokenListImp implements TokenList {
    private static final int INITIAL_LIST_LENGTH=10;

    private Token[] tokenArray;
    private int numberOfElements;

    TokenListImp() {
        this.tokenArray = new Token[INITIAL_LIST_LENGTH];
        this.numberOfElements = 0;
    }

    @Override
    public void add(Token token) {
        if (numberOfElements == tokenArray.length) {
            doubleTokenList();
        }

        this.tokenArray[numberOfElements] = token;
        this.numberOfElements ++;
    }

    /**
     * Sets the reference of tokenArray to a new Token[] array
     * with the elements copied from the previous
     * 'tokenArray', and with double the length of the previous tokenArray.
     */
    private void doubleTokenList() {
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
    public void remove(int index) {
        for (int i = index + 1; i < numberOfElements; i++) {
            this.tokenArray[i-1] = tokenArray[i];
        }

        this.tokenArray[numberOfElements - 1] = null;
        this.numberOfElements --;
    }

    @Override
    public void set(int index, Token token) {
        this.tokenArray[index] = token;
    }

    @Override
    public Token get(int index) {
        return tokenArray[index];
    }

    @Override
    public int size() {
        return numberOfElements;
    }
}
