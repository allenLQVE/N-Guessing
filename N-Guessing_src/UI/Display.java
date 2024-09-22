import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class Display extends JScrollPane{

    private JTextArea textArea;

    public Display() {
        textArea = new JTextArea();
        textArea.setEditable(false);

        this.setViewportView(textArea);
        this.setBounds(50, 100, 400, 350);
        this.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    }
    
    public void printText(String s){
        textArea.append(s);
    }

    public void clear(){
        textArea.setText(null);
    }
}
