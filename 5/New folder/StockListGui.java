import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import javax.swing.table.DefaultTableModel;

public class StockListGui extends ItemList
{
// Variable declaring
	private static final long serialVersionUID = 1L;
    private JLabel DisplayName = new JLabel();
	private JPanel NamePanel = new JPanel();
	private GroupLayout NameLayout = new GroupLayout(NamePanel);

// Declarering the JTable and make it's rows uneditable
	private JTable StockListTable = new JTable(){
		private static final long serialVersionUID = 1L;

		public boolean isCellEditable(int rowIndex, int collIndex){
			return false;
		}
	};

    private JScrollPane Scrollable_BidList = new JScrollPane();
    private JPanel BackgroundPanel = new JPanel();
    private GroupLayout BackgroundLayout = new GroupLayout(BackgroundPanel);

// Constructor method
    public StockListGui()
    {
    // Describing the NamePanel's properties
    	NamePanel.setBackground(new Color(23, 0,100));
    	DisplayName.setFont(new Font("Serif", Font.BOLD, 40)); // NOI18N
        DisplayName.setForeground(new Color(255, 255, 255));
        DisplayName.setText("Stock Item List of Auction Server");
        setVisible(true);

    // Setting up the layout for NamePanel
        NamePanel.setLayout(NameLayout);
        NameLayout.setHorizontalGroup(
            NameLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(NameLayout.createSequentialGroup()
                .addGap(97, 97, 97)
                .addComponent(DisplayName, GroupLayout.PREFERRED_SIZE, 579, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(141, Short.MAX_VALUE))
        );
        NameLayout.setVerticalGroup(
            NameLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(NameLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(DisplayName, GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE))
        );

    // Describing the StockListTable's properties
        StockListTable.setBackground(new java.awt.Color(240, 250, 250));
        // StockListTable.setForeground(new java.awt.Color(0, 51, 0));
        StockListTable.setFont(new Font("Serif", Font.BOLD, 16));
        StockListTable.setModel(new DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Symbol", "Name", "Price"
            }
        ));

        StockListTable.setRowHeight(33);
        Scrollable_BidList.setViewportView(StockListTable);
        if (StockListTable.getColumnModel().getColumnCount() > 0) {
            StockListTable.getColumnModel().getColumn(0).setPreferredWidth(150);
            StockListTable.getColumnModel().getColumn(0).setMaxWidth(150);
            StockListTable.getColumnModel().getColumn(2).setPreferredWidth(200);
            StockListTable.getColumnModel().getColumn(2).setMaxWidth(200);
        }

    // Setting up the layout for StockListTable
        BackgroundPanel.setLayout(BackgroundLayout);
        BackgroundLayout.setHorizontalGroup(
            BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(NamePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Scrollable_BidList)
        );
        BackgroundLayout.setVerticalGroup(
            BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BackgroundLayout.createSequentialGroup()
                .addComponent(NamePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(Scrollable_BidList, javax.swing.GroupLayout.PREFERRED_SIZE, 519, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

    // Setting up the background layout
        GroupLayout Layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(Layout);
        Layout.setHorizontalGroup(
            Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(BackgroundPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        Layout.setVerticalGroup(
            Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(BackgroundPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setTitle("CO225 - Project 2 - Group 19");
        setResizable(false);
        setLocationRelativeTo(null);
    }


// Getter method for getting StockListTable
    public JTable getTable()
    {
        return StockListTable;
    }

// This will both initialize and update stock list
    public void updateStockList()
    {   
        DefaultTableModel dtm = (DefaultTableModel)getTable().getModel();

        //Clear the tablel if any existing row found
        int count = dtm.getRowCount();
        if (count > 0) {
        	for (int i = count-1; i>=0; i--) {
        		dtm.removeRow(i);
        	}
        }

        for (StockItem checkedItem : getValues()) {
            Object[] newRow = {checkedItem.getSymbol(), checkedItem.getSecurityName(), checkedItem.getPrice()};
            dtm.addRow(newRow);
        }
    }

// When the button clicked this should call to display this frame
    

    // public static void main(String[] args) {
    // 	new StockListGui().init();
    // }

}