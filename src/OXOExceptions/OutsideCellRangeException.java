package OXOExceptions;

public class OutsideCellRangeException extends CellDoesNotExistException
{
    private int excPosition;
    private RowOrColumn excType;

    public OutsideCellRangeException() {
        super();
    }

    public OutsideCellRangeException(int position, RowOrColumn type) {
        excPosition=position;
        excType=type;
    }

    public String toString() {
        return this.getClass().getName()+": Identifier "+ getExcPosition() + " of "+ getExcType() + " is out of range";
    }

    public int getExcPosition() {
        return excPosition;
    }

    public RowOrColumn getExcType() {
        return excType;
    }
}
