import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class PlayerAidPanel extends JPanel{

    public ReturnButton returnButton;
    private final String TEXT = "This game is played by guessing an x-digit number until \n" +
        "the player guesses the correct number.\n\n" +
        "Player will receive feedback on the number they guessed. \n" +
        "For the correct digit in the correct place, \n" +
        "they will recieve a perfect guess, \n" +
        "and for the correct digit that is not in the correct place, \n" +
        "they will receive an imperfect guess.\n\n" +
        "Example:\n" +
        "If the correct answer is 1998, \n" +
        "a guess of 0123 will have \n" +
        "0 perfect guess and 1 imperfect guess; \n" + 
        "a guess of 1111 will have \n" +
        "1 perfect guess and 0 imperfect guess; \n" +
        "a guess of 9988 \n" +
        "will have 2 perfect guesses and 1 imperfect guess.";

    public PlayerAidPanel() {
        this.setBounds(0,0,500,500);
        this.setVisible(false);
        this.setLayout(null);

        JLabel title = new JLabel("How to Play");
        title.setBounds(175,25,300,50);
        title.setFont(new Font("",Font.PLAIN,20 ));

        JTextArea text = new JTextArea(TEXT);
        text.setBounds(50, 80, 400, 420);
        text.setBackground(null);
        text.setEditable(false);
        text.setFont(new Font("",Font.PLAIN, 16));

        returnButton = new ReturnButton();

        this.add(returnButton);
        this.add(title);
        this.add(text);
    }
}
