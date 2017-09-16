public class DoubleStack implements DoubleStackInterface {

    private static final int INITAL_LIST_LENGTH=10;
    private Double[] doubleArray;
    private int numberOfElements;

    DoubleStack(TokenList tokenList) {
        this.doubleArray = new Double[INITAL_LIST_LENGTH];
        this.numberOfElements = 0;
    }

    /**
     * @param element
     * @pre -
     * @post Double element is now at the top of the stack.
     */
    @Override
    public void push(Double element) {
        if (numberOfElements == doubleArray.length) {
            doubleTokenList(doubleArray);
        }

        this.doubleArray[this.numberOfElements] = element;
        this.numberOfElements ++;
    }

    private void doubleTokenList(Double[] originalList) {
        //TODO: Annotate this
        Double[] copyList = new Double[originalList.length * 2];
        this.doubleArray = copyElements(originalList, copyList);
    }

    private Double[] copyElements(Double[] originalList, Double[] copyList) {
        //TODO: Annotate this
        for (int i = 1; i < copyList.length; i++) {
            //TODO: Should I copy the value of the original token here?
            copyList[i] = new Double(originalList[i]);
        }
        return copyList;
    }

    /**
     * @pre The stack is not empty
     * @post The element at the top of the stack is returned and deleted.
     */
    @Override
    public Double pop() {
        return null;
    }

    /**
     * @pre The stack is not empty
     * @post The element at the top of the stack is returned.
     */
    @Override
    public Double top() {
        return null;
    }

    /**
     * @post The number of elements on the stack is returned
     */
    @Override
    public int size() {
        return 0;
    }
}
