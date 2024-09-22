import java.util.ArrayList;

public class Game {
    private int digit;
    private int attempts;
    private int[] answer;
    private int imperfectGuess;
    private int perfectGuess;
    private boolean correct;

    public Game() {
        this(4, 0, generateAnswer(4));
    }

    public Game(int digit){
        this(digit, 0, generateAnswer(digit));
    }

    public Game(int digit, int attempts, int[] answer) {
        this.digit = digit;
        this.attempts = attempts;
        this.answer = answer;
        imperfectGuess = 0;
        perfectGuess = 0;
        correct = false;
    }

    public void checkAnswer(ArrayList<Integer> guessList){
        attempts++;

        // guess shuold not have more digits than the number guessing
        if(guessList.size() > answer.length){
            return;
        }

        while(guessList.size() < answer.length){
            guessList.add(0,0);
        }

        // check for perfect guesses
        perfectGuess = 0;
        int[] tempAns = answer.clone();
        for (int i = 0; i < digit; i++) {
            if(tempAns[i] == guessList.get(i)){
                perfectGuess++;
                tempAns[i] = -1;
                guessList.set(i, -1);
            }
        }

        if(perfectGuess == digit){
            correct = true;
            return;
        }

        // check for inperfect guesses
        imperfectGuess = 0;
        for (int guessI = 0; guessI < digit; guessI++) {
            if(guessList.get(guessI) == -1){
                continue;
            }

            for (int ansI = 0; ansI < digit; ansI++) {
                if(tempAns[ansI] == -1){
                    continue;
                }

                if(guessList.get(guessI) == tempAns[ansI]){
                    imperfectGuess++;
                    tempAns[ansI] = -1;
                    guessList.set(guessI, -1);
                    break;
                }
            }
        }
    }

    private static int[] generateAnswer(int digit) {
        int[] newAnswer = new int[digit];
        for (int i = 0; i < digit; i++) {
            // generate random number from 0 to 9
            newAnswer[i] = (int) (Math.random() * 10);
        }
        return newAnswer;
    }

    public boolean isCorrect() {
        return correct;
    }

    public int[] getAnswer() {
        return answer;
    }

    public int getAttempts() {
        return attempts;
    }

    public int getDigit() {
        return digit;
    }

    public int getImperfectGuess() {
        return imperfectGuess;
    }

    public int getPerfectGuess() {
        return perfectGuess;
    }

    public void setAnswer(int[] answer) {
        this.answer = answer;
    }

    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }

    public void setDigit(int digit) {
        this.digit = digit;
    }
}
