public class TokenStack implements TokenStackInterface {
    /**
     * @param token
     * @pre -
     * @post token Token is now at the top of the stack.
     */
    @Override
    public void push(Token token) {

    }

    /**
     * @pre The stack is not empty
     * @post The token at the top of the stack is returned and deleted.
     */
    @Override
    public Token pop() {
        return null;
    }

    /**
     * @pre The stack is not empty
     * @post The token at the top of the stack is returned.
     */
    @Override
    public Token top() {
        return null;
    }

    /**
     * @pre -
     * @post The number of elements on the stack is returned
     */
    @Override
    public int size() {
        return 0;
    }
}
