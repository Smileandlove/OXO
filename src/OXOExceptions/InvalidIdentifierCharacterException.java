package OXOExceptions;

public class InvalidIdentifierCharacterException extends InvalidIdentifierException {
    private char invalidCharacter;
    private RowOrColumn invalidType;

    public InvalidIdentifierCharacterException()
    {
        super();
    }

    public InvalidIdentifierCharacterException(char character, RowOrColumn type) {
        invalidCharacter = character;
        invalidType = type;
    }

    public String toString() {
        return this.getClass().getName()+": Identifier "+ getInvalidCharacter() + " of "+ getInvalidType() +
                " is not valid";
    }

    protected char getInvalidCharacter()
    {
        return invalidCharacter;
    }

    protected RowOrColumn getInvalidType()
    {
        return invalidType;
    }
}
