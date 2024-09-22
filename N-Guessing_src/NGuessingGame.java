import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class NGuessingGame {
    private static ArrayList<Player> players = new ArrayList<Player>();
    private static LeaderBoard leaderBoard = new LeaderBoard();
    private static String max;
    private static Game game;
    private static boolean isSorted = true;

    private static JFrame frame;
    private static MainMenuPanel mainMenuPanel;
    private static GamePanel gamePanel;
    private static LeaderBoardPanel leaderBoardPanel;
    private static PlayerSearchPanel playerSearchPanel;
    private static PlayerAidPanel playerAidPanel;

    public static void main(String[] args){
        loadPlayer();
        loadLeaderBoard();

        frame = new JFrame();
        frame.setTitle("N-Guessing Game");
        frame.setSize(500,500);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(null);
        ImageIcon icon = new ImageIcon("icon_N.png");
        frame.setIconImage(icon.getImage());
        WindowAdapter closAdapter = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if(JOptionPane.showConfirmDialog(frame, "Are you sure you want to close the game?","Close",JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION){

                    savePlayer();
                    saveLeaderBoard();

                    frame.setVisible(false);
                    frame.dispose();
                }
            };
        };
        frame.addWindowListener(closAdapter);

        mainMenuPanel = new MainMenuPanel();
        mainMenuPanel.newGameButton.addActionListener(e -> newGame());
        mainMenuPanel.leaderBoardButton.addActionListener(e -> showLeaderBoard());
        mainMenuPanel.searchButton.addActionListener(e -> showPlayerSearch());
        mainMenuPanel.exitButton.addActionListener(e -> windowClose());
        mainMenuPanel.playerAidButton.addActionListener(e -> showPlayerAid());

        gamePanel = new GamePanel();
        gamePanel.restartButton.addActionListener(e -> newGame());
        gamePanel.returnButton.addActionListener(e -> returnToMainMenu());
        gamePanel.inputPanel.button.addActionListener(e -> checkGuess());

        leaderBoardPanel = new LeaderBoardPanel();
        leaderBoardPanel.returnButton.addActionListener(e -> returnToMainMenu());

        playerSearchPanel = new PlayerSearchPanel();
        playerSearchPanel.returnButton.addActionListener(e -> returnToMainMenu());
        playerSearchPanel.inputPanel.button.addActionListener(e -> playerSearch());

        playerAidPanel = new PlayerAidPanel();
        playerAidPanel.returnButton.addActionListener(e -> returnToMainMenu());

        frame.add(mainMenuPanel);
        frame.add(gamePanel);
        frame.add(leaderBoardPanel);
        frame.add(playerSearchPanel);
        frame.add(playerAidPanel);

        frame.setVisible(true);
    }

    private static void showPlayerAid() {
        playerAidPanel.setVisible(true);
        mainMenuPanel.setVisible(false);
    }

    private static void returnToMainMenu() {
        mainMenuPanel.setVisible(true);

        gamePanel.setVisible(false);
        gamePanel.reset();

        leaderBoardPanel.setVisible(false);
        leaderBoardPanel.reset();

        playerSearchPanel.setVisible(false);
        playerSearchPanel.reset();

        playerAidPanel.setVisible(false);
    }

    private static void windowClose(){
        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
    }

    private static void sortPlayer() {
        players.sort((x1, x2) -> x1.getName().compareTo(x2.getName()));
    }

    // for saving and loading records
    private static void loadLeaderBoard() {
        ObjectInputStream inputStream = null;
        String fileName = "leaderBoard.records";
        try {
            inputStream = new ObjectInputStream(new FileInputStream(fileName));

            leaderBoard = (LeaderBoard) inputStream.readObject();

            inputStream.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
        System.out.println("Leader Board loaded.");
    }

    private static void loadPlayer() {
        ObjectInputStream inputStream = null;
        String fileName = "players.records";
        try {
            inputStream = new ObjectInputStream(new FileInputStream(fileName));

            while (true) {
                players.add((Player) inputStream.readObject());
            }
        } catch (EOFException e) {
            System.out.println("Players' records loaded.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void saveLeaderBoard() {
        ObjectOutputStream outputStream = null;
        String fileName = "leaderBoard.records";

        try {
            outputStream = new ObjectOutputStream(new FileOutputStream(fileName));

            outputStream.writeObject(leaderBoard);
            
            outputStream.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
        System.out.println("Leader board is recorded.");
    }

    private static void savePlayer() {
        if(!isSorted){
            sortPlayer();
            isSorted = true;
        }

        ObjectOutputStream outputStream = null;
        String fileName = "players.records";

        try {
            outputStream = new ObjectOutputStream(new FileOutputStream(fileName));

            for (Player player : players) {
                outputStream.writeObject(player);
            }

            outputStream.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
        System.out.println("Players' records are recorded.");
    }

    // for player search
    private static Player searchPlayerHelper(String name){
        if(!isSorted){
            sortPlayer();
            isSorted = true;
        }
        
        int left = 0;
        int right = players.size() - 1;
        while(left <= right){
            int mid = left + (right - left) / 2;
            Player player = players.get(mid);

            if(player.getName().equals(name)){
                return player;
            }

            if(player.getName().compareTo(name) < 0){
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return null;
    }

    private static void playerSearch() {
        String name = "";
        Player player = null;
        try {
            name = playerSearchPanel.getSearchedText();
            name = name.trim();

            if(name == null || name.equals("")){
                throw new Exception("Empty input");
            }

            player = searchPlayerHelper(name);
            if(player == null){
                throw new Exception("No record of " + name + " is found.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(gamePanel, e.getMessage(),"Invalid Input!" , JOptionPane.WARNING_MESSAGE);
            return;
        }

        playerSearchPanel.reset();
        playerSearchPanel.addTitle(name);
        playerSearchPanel.showBoard();
        for (int i = 1; i <= player.getHighestSuccess(); i++) {
            if(player.getRecordOfDigit(i) != 0){
                playerSearchPanel.addRow(i,player.getRecordOfDigit(i));
            }
        }
    }

    private static void showPlayerSearch() {
        frame.getRootPane().setDefaultButton(playerSearchPanel.inputPanel.button);
        mainMenuPanel.setVisible(false);
        playerSearchPanel.setVisible(true);
    }

    // for leader board
    private static void showLeaderBoard() {
        try {
            for (int i = 1; i <= leaderBoard.getHighestSuccess(); i++) {
                ArrayList<String> playerNames = leaderBoard.getPlayerOfDigit(i);
                int bestScore = leaderBoard.getBestScore(i);

                if(bestScore == 0){
                    continue;
                }

                String players = "";
                for (String playerName : playerNames) {
                    players += playerName + " ";
                }
                leaderBoardPanel.addRow(i, bestScore, players);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Error reading Leader Board", 0);
            return;
        }

        mainMenuPanel.setVisible(false);
        leaderBoardPanel.setVisible(true);
    }

    // for new game
    private static void newGame() {
        int digit = 0;
        try {
            String input = JOptionPane.showInputDialog("How many digits of number you want to challenge?");
            if(input == null){
                return;
            }
            
            digit = Integer.parseInt(input);
            if(digit <= 0){
                throw new Exception("A number must have at least one digit.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, e.getMessage(),"Invalid Input!" , JOptionPane.WARNING_MESSAGE);
            return;
        }

        mainMenuPanel.setVisible(false);
        gamePanel.setVisible(true);

        game = new Game(digit);
        max = "9";
        for (int i = 1; i < digit; i++) {
            max += "9";
        }

        frame.getRootPane().setDefaultButton(gamePanel.inputPanel.button);
        gamePanel.reset();
        gamePanel.setHint("Please enter a number from 0 to " + max);
    }

    private static void checkGuess() {
        String guess = null;
        ArrayList<Integer> guessList = new ArrayList<Integer>();
        try {
            guess = gamePanel.getGuess();

            if(guess == null || guess.equals("")){
                throw new Exception("Guess can't be empty");
            }

            for (char c : guess.toCharArray()) {
                int num = Character.getNumericValue(c);
                if(num < 0 || num > 9){
                    throw new Exception("Digit only!");
                }
                guessList.add(num);
            }

            if(guessList.size() == 0 || guessList.size() > game.getDigit()){
                throw new Exception("Guessed number is not in the range");
            }
        } catch (Exception e) {
            gamePanel.inputPanel.clearInput();
            JOptionPane.showMessageDialog(gamePanel, e.getMessage(),"Invalid Input!" , JOptionPane.WARNING_MESSAGE);
            return;
        }

        game.checkAnswer(guessList);

        if(!game.isCorrect()){
            while(guess.length() < game.getDigit()){
                guess = "0" + guess;
            }

            gamePanel.printText("The number you guessed is " + guess + "\n");
            gamePanel.printText("Within the guess, there are: \n");
            gamePanel.printText(game.getPerfectGuess() + " perfect guesses (correct digit in correct place)\n");
            gamePanel.printText(game.getImperfectGuess() + " imperfect guesses (correct digit but in wrong place)\n");
            gamePanel.printText("\n");
        }else{
            gamePanel.printText("Congratulation on guessing the correct number " + guess + " with " + game.getAttempts() + " attempts!\n");
            
            String playerName = "Unknown";
            try {
                String input = JOptionPane.showInputDialog("Please enter your name to save the record");

                if(input != null && !input.equals("")){
                    playerName = input;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(frame, e.getMessage(),"Invalid Input!" , JOptionPane.WARNING_MESSAGE);
                return;
            }

            Player player = searchPlayerHelper(playerName);
            if(player == null){
                player = new Player(playerName);
                players.add(player);
                isSorted = false;
            }
            if(player.getRecordOfDigit(game.getDigit()) == 0 || player.getRecordOfDigit(game.getDigit()) > game.getAttempts()){
                player.updateRecord(game.getDigit(), game.getAttempts());

                if(leaderBoard.getBestScore(game.getDigit()) == 0 || leaderBoard.getBestScore(game.getDigit()) > game.getAttempts()){
                    leaderBoard.update(playerName, game.getDigit(), game.getAttempts());
                }
            }
            gamePanel.blockInput();
        }
    }
}
