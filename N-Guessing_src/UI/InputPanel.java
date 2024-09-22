import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class InputPanel extends JPanel{

    public JButton button;
    private JTextField textField;

    public InputPanel() {
        this.setBounds(0,50,500,25);
        this.setVisible(true);
        this.setLayout(null);

        button = new JButton("Enter");
        button.setBounds(300,0,100,25);
        button.setFocusable(false);
        
        textField = new JTextField();
        textField.setBounds(75,0,200,25);

        this.add(button);
        this.add(textField);
        
    }

    public void clearInput(){
        textField.setText("");
    }

    public String getInputText(){
        return textField.getText();
    }

    public void enableInput(){
        textField.setEditable(true);
        button.setEnabled(true);
    }

    public void disableInput(){
        textField.setEditable(false);
        button.setEnabled(false);
    }
}
