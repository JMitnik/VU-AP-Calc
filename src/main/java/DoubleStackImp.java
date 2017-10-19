public class DoubleStackImp implements DoubleStack {
    private static final int INITIAL_STACK_LENGTH = 10;

    private Double[] doubleArray;
    private int numberOfElements;

     DoubleStackImp() {
        this.doubleArray = new Double[INITIAL_STACK_LENGTH];
        this.numberOfElements = 0;
    }

    @Override
    public void push(Double element) {
        if (numberOfElements == doubleArray.length) {
            doubleDoubleStack();
        }

        doubleArray[ numberOfElements++ ] = element;
    }

    /**
     * @pre doubleArray.length == numberOfElements
     * @post The current reference of 'doubleArray' points to a
     * new Double[] array with the elements copied from the previous
     * 'doubleArray', and with double the length of the previous tokenArray.
     */
    private void doubleDoubleStack() {
        Double[] copyArray = new Double[ doubleArray.length * 2 ];
        doubleArray = copyElements(doubleArray, copyArray);
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
            copyArray[i] = originalArray[i];
        }

        return copyArray;
    }

    @Override
    public Double pop() {
        Double result = top();
        // The "--" in front of a variable will execute the subtraction on
        // the variable first and then use the variable.
        doubleArray[ --numberOfElements ] = null;
        return result;
    }

    @Override
    public Double top() {
        return doubleArray[ numberOfElements - 1 ];
    }

    @Override
    public int size() {
        return numberOfElements;
    }
}
