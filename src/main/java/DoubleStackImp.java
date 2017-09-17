public class DoubleStackImp implements DoubleStack {
    private static final int INITAL_STACK_LENGTH = 10;

    private Double[] doubleArray;
    private int numberOfElements;

    public DoubleStackImp() {
        this.doubleArray = new Double[INITAL_STACK_LENGTH];
        this.numberOfElements = 0;
    }

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
        Double[] originalArray = doubleArray;
        Double[] copyArray = new Double[originalArray.length * 2];
        this.doubleArray = copyElements(originalArray, copyArray);
    }

    /**
     * @param originalArray The original Double[] whose elements will be copied.
     * @param copyArray The Double[] which will get the elements from originalArray.
     * @pre copyArray.length >= originalArray.length
     * @post The array 'copyArray' is pointing to, has the Doubles with the same
     * value as the ones in originalArray and is returned.
     */
    private Double[] copyElements(Double[] originalArray, Double[] copyArray) {
        for (int i = 0; i < originalArray.length; i++) {
            //TODO: Do I copy here or do I just point to same object?
            copyArray[i] = originalArray[i];
        }

        return copyArray;
    }

    @Override
    public Double pop() {
        Double top = top();
        this.numberOfElements -- ;
        return top;
    }

    @Override
    public Double top() {
        return doubleArray[Math.max(numberOfElements - 1, 0)];
    }

    @Override
    public int size() {
        return numberOfElements;
    }
}
