public class DoubleStackImp implements DoubleStack {
    private static final int INITAL_STACK_LENGTH=10;

    private Double[] doubleArray;
    private int numberOfElements;

    @Override
    public void push(Double element) {
        if (numberOfElements == doubleArray.length) {
            doubleDoubleStack();
        }

        this.doubleArray[this.numberOfElements] = element;
        this.numberOfElements ++;
    }

    /**
     * @pre doubleArray.length == numberOfElements
     * @post The current reference of 'doubleArray' points to a
     * new Double[] array with the elements copied from the previous
     * 'doubleArray', and with double the length of the previous tokenArray.
     */
    private void doubleDoubleStack() {
        Double[] originalList = doubleArray;
        Double[] copyList = new Double[originalList.length * 2];
        this.doubleArray = copyElements(originalList, copyList);
    }

    /**
     * @param originalList The original Double[] whose elements will be copied.
     * @param copyList The Double[] which will get the elements from originalList.
     * @pre copyList.length >= originalList.length
     * @post The array 'copyList' is pointing to, has the Doubles with the same
     * value as the ones in originalList and is returned.
     */
    private Double[] copyElements(Double[] originalList, Double[] copyList) {
        for (int i = 0; i < originalList.length; i++) {
            //TODO: Do I copy here or do I just point to same object?
            copyList[i] = originalList[i];
        }

        return copyList;
    }

    @Override
    public Double pop() {
        Double top = top();
        this.numberOfElements -- ;
        return top;
    }

    @Override
    public Double top() {
        return doubleArray[numberOfElements - 1];
    }

    @Override
    public int size() {
        return numberOfElements;
    }
}
