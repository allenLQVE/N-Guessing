import java.io.Serializable;
import java.util.ArrayList;

public class LeaderBoard implements Serializable{
    private int highestSuccess;
    private Object[] playerNames;
    private int[] scores;

    public LeaderBoard(){
        this(0, new Object[10], new int[10]);
    }

    public LeaderBoard(int highestSuccess, Object[] playerNames, int[] scores) {
        this.highestSuccess = highestSuccess;
        this.playerNames = playerNames;
        this.scores = scores;
    }

    @SuppressWarnings("unchecked")
    public ArrayList<String> getPlayerOfDigit(int digit){
        if(digit <= highestSuccess){
            return (ArrayList<String>) playerNames[digit];
        }
        return null;
    }

    public int getBestScore(int digit){
        if(digit <= highestSuccess){
            return scores[digit];
        }
        return 0;
    }

    @SuppressWarnings("unchecked")
    public void update(String playerName, int digit, int attempts){
        if(digit >= playerNames.length){
            ArrayList<String>[] newPlayerNames = (ArrayList<String>[]) new Object[digit * 2];
            System.arraycopy(playerNames, 0, newPlayerNames, 0, playerNames.length);
            playerNames = newPlayerNames;

            int[] newScore = new int[digit * 2];
            System.arraycopy(scores, 0, newScore, 0, scores.length);
            scores = newScore;
        }

        
        if(attempts <= scores[digit] || scores[digit] == 0){
            // A new record on a new level or better record on a recorded level
            if(attempts != scores[digit]){
                playerNames[digit] = new ArrayList<String>();
                scores[digit] = attempts;
            }
            ArrayList<String> namesArrayList = (ArrayList<String>) playerNames[digit];

            if(!namesArrayList.contains(playerName)){
                namesArrayList.add(playerName);
            }
        }

        if(digit > highestSuccess){
            highestSuccess = digit;
        }
    }

    public int getHighestSuccess() {
        return highestSuccess;
    }

    @SuppressWarnings("unchecked")
    public ArrayList<String>[] getPlayerNames() {
        return (ArrayList<String>[]) playerNames;
    }

    public int[] getScores() {
        return scores;
    }

    public void setHighestSuccess(int highestSuccess) {
        this.highestSuccess = highestSuccess;
    }

    public void setPlayerNames(ArrayList<String>[] playerNames) {
        this.playerNames = playerNames;
    }

    public void setScores(int[] scores) {
        this.scores = scores;
    }
}
