import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class PlayerSearchPanel extends JPanel{

    public ReturnButton returnButton;
    public InputPanel inputPanel;
    private JTable boardTable;
    private DefaultTableModel tableModel;
    private JScrollPane board;

    public PlayerSearchPanel() {
        this.setBounds(0,0,500,500);
        this.setVisible(false);
        this.setLayout(null);

        returnButton = new ReturnButton();

        inputPanel = new InputPanel();

        JLabel label = new JLabel("Please enter the player name");
        label.setBounds(75,25,200,25);

        tableModel = new DefaultTableModel(){
            
            @Override
            public boolean isCellEditable(int row, int col){
                return false;
            }
        };
        tableModel.addColumn("Digits");
        tableModel.addColumn("BestAttempts");
        
        boardTable = new JTable(tableModel);

        board = new JScrollPane();
        board.setViewportView(boardTable);
        board.setBounds(50, 100, 400, 350);
        board.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        board.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder()," ",TitledBorder.LEFT,TitledBorder.TOP));
        board.setVisible(false);

        this.add(returnButton);
        this.add(inputPanel);
        this.add(board);
        this.add(label);
    }

    public void addTitle(String s){
        board.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),s,TitledBorder.LEFT,TitledBorder.TOP));
    }

    public void addRow(int digit, int attempt){
        tableModel.addRow(new Object[]{digit,attempt});
    }

    public String getSearchedText(){
        return inputPanel.getInputText();
    }

    public void reset() {
        board.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder()," ",TitledBorder.LEFT,TitledBorder.TOP));
        tableModel.setRowCount(0);
        inputPanel.clearInput();
        board.setVisible(false);
    }

    public void showBoard() {
        board.setVisible(true);
    }
}
