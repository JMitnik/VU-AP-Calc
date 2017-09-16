public class TokenList implements TokenListInterface {

    private static final int INITAL_LIST_LENGTH=10;
    private Token[] tokenArray;
    private int numberOfElements;

    TokenList() {
        this.tokenArray = new Token[INITAL_LIST_LENGTH];
        this.numberOfElements = 0;
    }

    /**
     * @param token
     * @pre -
     * @post The token 'token' has been added at the end of the TokenList,
     * preserving the previous order.
     */
    @Override
    public void add(Token token) {
        //TODO: Find consistency in using 'this' and not
        if (numberOfElements == tokenArray.length) {
            doubleTokenList(tokenArray);
        }

        this.tokenArray[this.numberOfElements] = token;
        this.numberOfElements ++;
    }

    private void doubleTokenList(Token[] originalList) {
        //TODO: Annotate this
        Token[] copyList = new Token[originalList.length * 2];
        this.tokenArray = copyElements(originalList, copyList);
    }

    private Token[] copyElements(Token[] originalList, Token[] copyList) {
        //TODO: Annotate this
        for (int i = 1; i < copyList.length; i++) {
            //TODO: Should I copy the value of the original token here?
            copyList[i] = new Token(originalList[i].getValue());
        }

        return copyList;
    }

    /**
     * @param index
     * @pre index < size() and index >= 0
     * @post The element at location 'index' has been removed, preserving the previous order. The size of the TokenList has been reduced by 1.
     */
    @Override
    public void remove(int index) {
        //TODO: Annotate this
        //TODO: Implement the remove method
    }

    /**
     * @param index The index to be set
     * @param token The value to set the element at location index to.
     * @pre index < size();
     * @post The element at location 'index' has the value 'token', preserving the previous order.
     */
    @Override
    public void set(int index, Token token) {
        tokenArray[index] = token;
    }

    /**
     * @param index The index of the element to be returned.
     * @return The element and index index.
     * @pre index < size();
     * @post The element at index 'index' has been returned.
     */
    @Override
    public Token get(int index) {
        return tokenArray[index];
    }

    /**
     * @pre -
     * @post The number of elements in the list has been returned.
     */
    @Override
    public int size() {
        return tokenArray.length;
    }
}
