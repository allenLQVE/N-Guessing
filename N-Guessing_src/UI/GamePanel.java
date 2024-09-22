import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GamePanel extends JPanel {

    public JButton restartButton;
    public ReturnButton returnButton;
    private Display display;
    private JLabel label;
    public InputPanel inputPanel;

    public GamePanel() {
        this.setBounds(0,0,500,500);
        this.setVisible(false);
        this.setLayout(null);

        inputPanel = new InputPanel();

        restartButton = new JButton("Restart");
        restartButton.setBounds(399,0,100,25);
        restartButton.setFocusable(false);

        returnButton = new ReturnButton();

        display = new Display();

        label = new JLabel();
        label.setBounds(75,25,400,25);

        this.add(returnButton);
        this.add(restartButton);
        this.add(display);
        this.add(inputPanel);
        this.add(label);
    }

    public void reset(){
        display.clear();
        inputPanel.clearInput();
        inputPanel.enableInput();
        label.setText(null);
    }

    public void setHint(String s){
        label.setText(s);
    }

    public String getGuess() {
        String text = inputPanel.getInputText();
        inputPanel.clearInput();
        return text;
    }

    public void printText(String s){
        display.printText(s);
    }

    public void blockInput() {
        inputPanel.disableInput();
    }
}
