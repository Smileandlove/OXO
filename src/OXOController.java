import OXOExceptions.*;

class OXOController {
    OXOModel gameModel;

    public OXOController(OXOModel model) {
        //Set a maximum grid size of 9x9.
        if (model.getNumberOfRows()>9||model.getNumberOfColumns()>9) {
            System.out.println("Invalid size of cells.");
            System.exit(0);
        }
        //Check the number of players.
        if (model.getNumberOfPlayers()<1) {
            System.out.println("Nobody is playing OXO Games.");
            System.exit(0);
        }
        gameModel = model;
        //Set the initial player, otherwise the game will not start.
        gameModel.setCurrentPlayer(gameModel.getPlayerByNumber(0));
    }

    public void handleIncomingCommand(String command) throws OXOMoveException {
        //Check whether the command is valid.
        validCommand(command);
        //Get the row number from the first character of the command.
        int row = command.toLowerCase().charAt(0)-'a';
        //Get the column number from the second character of the command.
        int column = command.charAt(1) - '1';
        //Check if the input is out of range.
        outsideRange(row,column);
        //Check if the cell is already taken.
        isCellTaken(row,column);

        gameModel.setCellOwner(row,column, gameModel.getCurrentPlayer());
        if (isWin(gameModel)) {
            gameModel.setWinner(gameModel.getCurrentPlayer());
        }
        else if (isDrawn(gameModel)) {
            gameModel.setGameDrawn();
        }
        else {
            setNextPlayer();
        }
    }
    //Check if a winner has been achieved.
    //In fact, I wanted to use only a set of loops, but found it too difficult...
    private boolean isWin(OXOModel model) {
        //Create a counter array to count the number of times the current player appears.
        int[] sum = new int[4];
        int size = model.getNumberOfRows();
        //Count the number on the two diagonals.
        for (int i=0; i<model.getNumberOfRows(); i++) {
            if (model.getNumberOfRows()==model.getNumberOfColumns()) {
                if (model.getCellOwner(i,i)==model.getCurrentPlayer()) {
                    sum[0]++;
                }
                if (model.getCellOwner(i,size-i-1)==model.getCurrentPlayer()) {
                    sum[1]++;
                }
            }
        }
        //Count the number on the rows.
        for (int i=0; i<model.getNumberOfRows(); i++) {
            if (sum[2]==model.getWinThreshold()) {
                return true;
            }
            sum[2]=0;
            for (int j=0; j<model.getNumberOfColumns(); j++) {
                if (model.getCellOwner(i,j)==model.getCurrentPlayer()) {
                    sum [2]++;
                }
            }
        }
        //Count the number on the columns.
        for (int i=0; i<model.getNumberOfColumns(); i++) {
            if (sum[3]==model.getWinThreshold()) {
                return true;
            }
            sum[3]=0;
            for (int j=0; j<model.getNumberOfRows(); j++) {
                if (model.getCellOwner(j,i)==model.getCurrentPlayer()) {
                    sum[3]++;
                }
            }
        }
        //Finally, check the counter again.
        for (int i=0; i<4; i++) {
            if (sum[i]== model.getWinThreshold()) {
                return true;
            }
        }
        return false;
    }
    //Check if the game has been drawn.
    private boolean isDrawn(OXOModel model) {
        for (int i=0; i<model.getNumberOfRows(); i++) {
            for (int j=0; j<model.getNumberOfColumns(); j++) {
                if (model.getCellOwner(i,j)==null) {
                    return false;
                }
            }
        }
        return true;
    }
    //Set the next player, like the name of method.
    private void setNextPlayer() {
        int number = 0;
        for (int i=0; i<gameModel.getNumberOfPlayers(); i++) {
            if (gameModel.getPlayerByNumber(i)==gameModel.getCurrentPlayer()) {
                number=i;
                break;
            }
        }
        //If it is the last player, start again with the first player.
        if (number==gameModel.getNumberOfPlayers()-1) {
            gameModel.setCurrentPlayer(gameModel.getPlayerByNumber(0));
        }
        else {
            gameModel.setCurrentPlayer(gameModel.getPlayerByNumber(number+1));
        }
    }
    private void validCommand(String input) throws InvalidIdentifierException {
        //Check if the length of command is 2.
        if (input.length()!=2) {
            throw new InvalidIdentifierLengthException(input.length());
        }
        //Check if the first character of command is English letter.
        //In fact, I wanted to use Character.isLetter, but Chinese characters passed the test...
        if (!Character.toString(input.charAt(0)).matches("[a-zA-Z]")) {
            throw new InvalidIdentifierCharacterException(input.charAt(0),RowOrColumn.ROW);
        }
        //Check if the second character of command is number.
        if (!Character.isDigit(input.charAt(1))) {
            throw new InvalidIdentifierCharacterException(input.charAt(1),RowOrColumn.COLUMN);
        }
    }
    private void outsideRange(int rowNumber,int columnNumber) throws OutsideCellRangeException {
        if (rowNumber < 0 || rowNumber >= gameModel.getNumberOfRows()) {
            throw new OutsideCellRangeException(rowNumber,RowOrColumn.ROW);
        }
        if (columnNumber < 0 || columnNumber >= gameModel.getNumberOfColumns()) {
            throw new OutsideCellRangeException(columnNumber,RowOrColumn.COLUMN);
        }
    }
    private void isCellTaken(int rowNumber,int columnNumber) throws CellAlreadyTakenException {
        if (gameModel.getCellOwner(rowNumber,columnNumber)!=null) {
            throw new CellAlreadyTakenException(rowNumber,columnNumber);
        }
    }
}
