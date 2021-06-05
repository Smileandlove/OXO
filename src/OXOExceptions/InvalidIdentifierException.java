package OXOExceptions;

public class InvalidIdentifierException extends CellDoesNotExistException {
    public InvalidIdentifierException() {
        super();
    }

    public InvalidIdentifierException(int row, int column)
    {
        super(row,column);
    }

    public String toString() {
        return this.getClass().getName()+": Cell ["+ getRow() + "," + getColumn() + "] is invalid.";
    }
}
