import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

public class LeaderBoardPanel extends JPanel{
    
    public ReturnButton returnButton;
    private JTable boardTable;
    private DefaultTableModel tableModel;

    public LeaderBoardPanel() {
        this.setBounds(0,0,500,500);
        this.setVisible(false);
        this.setLayout(null);

        JLabel title = new JLabel("Leader Board");
        title.setBounds(175,25,300,50);
        title.setFont(new Font("",Font.PLAIN,20 ));

        returnButton = new ReturnButton();

        tableModel = new DefaultTableModel(){
            
            @Override
            public boolean isCellEditable(int row, int col){
                return false;
            }
        };
        tableModel.addColumn("Digits");
        tableModel.addColumn("BestAttempts");
        tableModel.addColumn("Players");
        
        boardTable = new JTable(tableModel);
        boardTable.getColumnModel().getColumn(0).setPreferredWidth(100);
        boardTable.getColumnModel().getColumn(1).setPreferredWidth(100);
        boardTable.getColumnModel().getColumn(2).setPreferredWidth(200);

        JScrollPane board = new JScrollPane();
        board.setViewportView(boardTable);
        board.setBounds(50, 100, 400, 350);
        board.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        this.add(title);
        this.add(returnButton);
        this.add(board);
    }

    public void addRow(int digit, int attempt, String players){
        tableModel.addRow(new Object[]{digit,attempt,players});
    }

    public void reset() {
        tableModel.setRowCount(0);
    }
}
