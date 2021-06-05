import java.util.*;

class OXOModel {
    private final ArrayList<ArrayList<OXOPlayer>> cells;
    private final ArrayList<OXOPlayer> players;
    private OXOPlayer currentPlayer;
    private OXOPlayer winner;
    private boolean gameDrawn;
    private int winThreshold;

    public OXOModel(int numberOfRows, int numberOfColumns, int winThresh) {
        winThreshold = winThresh;
        cells = new ArrayList<ArrayList<OXOPlayer>>(numberOfRows);
        for (int i=0; i<numberOfRows; i++) {
            ArrayList<OXOPlayer> rows = new ArrayList<OXOPlayer>(numberOfColumns);
            for (int j=0; j<numberOfColumns; j++) {
                rows.add(null);
            }
            cells.add(rows);
        }
        players = new ArrayList<OXOPlayer>();
        //players.add(new OXOPlayer('A'));
    }

    public int getNumberOfPlayers() {
        return players.size();
    }

    public void addPlayer(OXOPlayer player) {
        players.add(player);
    }

    public OXOPlayer getPlayerByNumber(int number) {
        return players.get(number);
    }

    public OXOPlayer getWinner() {
        return winner;
    }

    public void setWinner(OXOPlayer player) {
        winner = player;
    }

    public OXOPlayer getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(OXOPlayer player) {

        currentPlayer = player;
    }

    public int getNumberOfRows() {
        return cells.size();
    }

    public int getNumberOfColumns() {
        return cells.get(0).size();
    }

    public OXOPlayer getCellOwner(int rowNumber, int colNumber) {
        return cells.get(rowNumber).get(colNumber);
    }

    public void setCellOwner(int rowNumber, int colNumber, OXOPlayer player) {
        cells.get(rowNumber).set(colNumber,player);
    }

    public void setWinThreshold(int winThresh) {
        winThreshold = winThresh;
    }

    public int getWinThreshold() {
        return winThreshold;
    }

    public void setGameDrawn() {
        gameDrawn = true;
    }

    public boolean isGameDrawn() {
        return gameDrawn;
    }
}
