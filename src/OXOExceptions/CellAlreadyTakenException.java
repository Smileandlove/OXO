package OXOExceptions;

public class CellAlreadyTakenException extends OXOMoveException {

    public CellAlreadyTakenException() {
        super();
    }

    public CellAlreadyTakenException(int row, int column) {
        super(row,column);
    }

    public String toString() {
        return this.getClass().getName()+": Cell ["+ getRow() + "," + getColumn() + "] has been already taken.";
    }

}
