import javax.swing.JButton;
import javax.swing.JPanel;

public class MainMenuPanel extends JPanel{
    public JButton newGameButton;
    public JButton leaderBoardButton;
    public JButton searchButton;
    public JButton exitButton;
    public JButton playerAidButton;

    public MainMenuPanel(){
        this.setBounds(0,0,500,500);
        this.setVisible(true);
        this.setLayout(null);

        newGameButton = new JButton("New Game");
        newGameButton.setBounds(150,50,200,50);
        newGameButton.setFocusable(false);
        
        playerAidButton = new JButton("How to Play");
        playerAidButton.setBounds(150, 130, 200, 50);
        playerAidButton.setFocusable(false);
        
        leaderBoardButton = new JButton("Leader Board");
        leaderBoardButton.setBounds(150,210,200,50);
        leaderBoardButton.setFocusable(false);

        searchButton = new JButton("Player Search");
        searchButton.setBounds(150,290,200,50);
        searchButton.setFocusable(false);

        exitButton = new JButton("Exit Game");
        exitButton.setBounds(150,370,200,50);
        exitButton.setFocusable(false);

        this.add(newGameButton);
        this.add(leaderBoardButton);
        this.add(searchButton);
        this.add(exitButton);
        this.add(playerAidButton);
    }
}
