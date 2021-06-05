package OXOExceptions;

public class CellDoesNotExistException extends OXOMoveException {
    public CellDoesNotExistException() {
        super();
    }

    public CellDoesNotExistException(int row, int column) {
        super(row,column);
    }

    public String toString() {
        return this.getClass().getName()+": Cell ["+ getRow() + "," + getColumn() + "] does not exist.";
    }
}
