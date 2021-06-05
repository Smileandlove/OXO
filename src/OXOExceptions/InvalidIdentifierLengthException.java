package OXOExceptions;

public class InvalidIdentifierLengthException extends InvalidIdentifierException {
    private int invalidLength;

    public InvalidIdentifierLengthException()
    {
        super();
    }

    public InvalidIdentifierLengthException(int Length) {
        invalidLength = Length;
    }

    public String toString() {
        return this.getClass().getName()+" : The identifier consists of "+ getInvalidLength()+
                " characters, which is more than two characters";
    }

    protected int getInvalidLength() {
        return invalidLength;
    }
}
