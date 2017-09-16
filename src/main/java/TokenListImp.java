public class TokenListImp implements TokenList {
    private static final int INITAL_LIST_LENGTH=10;

    private Token[] tokenArray;
    private int numberOfElements;

    TokenListImp() {
        this.tokenArray = new TokenImp[INITAL_LIST_LENGTH];
        this.numberOfElements = 0;
    }

    //TODO: Copy constructor!

    @Override
    public void add(Token token) {
        if (numberOfElements == tokenArray.length) {
            doubleTokenList();
        }

        this.tokenArray[this.numberOfElements] = token;
        this.numberOfElements ++;
    }

    /**
     * @pre tokenArray.length == numberOfElements
     * @post The current reference of 'tokenArray' points to a
     * new Token[] array with the elements copied from the previous
     * 'tokenArray', and with double the length of the previous tokenArray.
     */
    private void doubleTokenList() {
        Token[] originalList = tokenArray;
        Token[] copyList = new TokenImp[originalList.length * 2];
        this.tokenArray = copyElements(originalList, copyList);
    }

    /**
     * @param originalList The original Token[] whose elements will be copied.
     * @param copyList The Token[] which will get the elements from originalList.
     * @pre copyList.length >= originalList.length
     * @post The array 'copyList' is pointing to, has the Tokens with the same
     * value as the ones in originalList and is returned.
     */
    private Token[] copyElements(Token[] originalList, Token[] copyList) {
        for (int i = 0; i < originalList.length; i++) {
            //TODO: Do I copy here or do I just point to same object?
            copyList[i] = new TokenImp(originalList[i].getValue());
        }

        return copyList;
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
        return tokenArray.length;
    }
}
