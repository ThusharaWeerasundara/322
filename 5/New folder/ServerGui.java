import java.awt.GridLayout;
import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ServerGui extends ItemList
{
	private static final long serialVersionUID = 1L;
	private JLabel ProjectName = new JLabel();
	private JPanel FavListPanel = new JPanel();

	private JLabel FBSymbol = new JLabel();
	private JLabel FBName = new JLabel();
	private JLabel FBPrice = new JLabel();
	private JPanel FBPanel = new JPanel();
	private GroupLayout FBLayout = new GroupLayout(FBPanel);

	private JLabel VRTUSymbol = new JLabel();
	private JLabel VRTUName = new JLabel();
	private JLabel VRTUPrice = new JLabel();
	private JPanel VRTUPanel = new JPanel();
	private GroupLayout VRTULayout = new GroupLayout(VRTUPanel);

	private JLabel MSFTSymbol = new JLabel();
    private JLabel MSFTName = new JLabel();
    private JLabel MSFTPrice = new JLabel();
    private JPanel MSFTPanel = new JPanel();
    private GroupLayout MSFTLayout = new GroupLayout(MSFTPanel);

    private JLabel GOOGLSymbol = new JLabel();
    private JLabel GOOGLName = new JLabel();
    private JLabel GOOGLPrice = new JLabel();
    private JPanel GOOGLPanel = new JPanel();
    private GroupLayout GOOGLLayout = new GroupLayout(GOOGLPanel);

    private JLabel YHOOSymbol = new JLabel();
    private JLabel YHOOName = new JLabel();
    private JLabel YHOOPrice = new JLabel();
    private JPanel YHOOPanel = new JPanel();
    private GroupLayout YHOOLayout = new GroupLayout(YHOOPanel);

    private JLabel XLNXSymbol = new JLabel();
    private JLabel XLNXName = new JLabel();
    private JLabel XLNXPrice = new JLabel();
    private JPanel XLNXPanel = new JPanel();
    private GroupLayout XLNXLayout = new GroupLayout(XLNXPanel);

    private JLabel TSLASymbol = new JLabel();
    private JLabel TSLAName = new JLabel();
    private JLabel TSLAPrice = new JLabel();
    private JPanel TSLAPanel = new JPanel();
    private GroupLayout TSLALayout = new GroupLayout(TSLAPanel);

    private JLabel TXNSymbol = new JLabel();
    private JLabel TXNName = new JLabel();
    private JLabel TXNPrice = new JLabel();
    private JPanel TXNPanel = new JPanel();
    private GroupLayout TXNLayout = new GroupLayout(TXNPanel);

// Set the JTable uneditable
    private JTable BiddingList = new JTable(){
		private static final long serialVersionUID = 1L;

		public boolean isCellEditable(int rowIndex, int collIndex){
            return false;
        }
    };
    private JScrollPane Scrollable_BidList = new JScrollPane();
    private JButton StockListButton = new JButton();

    private JPanel BackgroundPanel = new JPanel();
    private GroupLayout BackgroundLayout = new GroupLayout(BackgroundPanel);

// Constructor (initializing gui properties will be done here)    
	public ServerGui()
	{

		ProjectName.setFont(new Font("Serif", Font.BOLD, 40)); // NOI18N
        ProjectName.setForeground(new Color(255, 255, 255));
        ProjectName.setText("Auction Server");

        FavListPanel.setBackground(new Color(0, 0, 0));
        FavListPanel.setLayout(new GridLayout(8, 0));
//
        FBSymbol.setFont(new Font("Serif", Font.BOLD, 24)); // NOI18N
        FBSymbol.setForeground(new Color(130, 51, 250));
        FBSymbol.setHorizontalAlignment(SwingConstants.CENTER);
        FBSymbol.setText("FB");

        FBName.setFont(new Font("Serif", Font.BOLD, 16)); // NOI18N
        FBName.setForeground(new Color(130, 51, 250));
        FBName.setText("Facebook Inc. - Class A Common Stock");

        FBPrice.setFont(new Font("Serif", Font.BOLD, 24)); // NOI18N
        FBPrice.setForeground(new Color(130, 51, 250));
        FBPrice.setHorizontalAlignment(SwingConstants.CENTER);
        FBPrice.setText("");

        FBPanel.setLayout(FBLayout);
        FBLayout.setHorizontalGroup( FBLayout
        	.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(FBLayout.createSequentialGroup()
            .addComponent(FBSymbol, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
            .addGap(19, 19, 19)
            .addComponent(FBName, GroupLayout.PREFERRED_SIZE, 320, GroupLayout.PREFERRED_SIZE)
            .addGap(7, 7, 7)
            .addComponent(FBPrice, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))
        );
        FBLayout.setVerticalGroup( FBLayout
        	.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(FBSymbol, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
            .addComponent(FBName, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
            .addComponent(FBPrice, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
        );
//
        VRTUSymbol.setFont(new Font("Serif", Font.BOLD, 24)); // NOI18N
        VRTUSymbol.setForeground(new Color(130, 51, 250));
        VRTUSymbol.setHorizontalAlignment(SwingConstants.CENTER);
        VRTUSymbol.setText("VRTU");

        VRTUName.setFont(new Font("Serif", Font.BOLD, 16)); // NOI18N
        VRTUName.setForeground(new Color(130, 51, 250));
        VRTUName.setText("Virtusa Corporation - common stock");

        VRTUPrice.setFont(new Font("Serif", Font.BOLD, 24)); // NOI18N
        VRTUPrice.setForeground(new Color(130, 51, 250));
        VRTUPrice.setHorizontalAlignment(SwingConstants.CENTER);
        VRTUPrice.setText("");

        VRTUPanel.setLayout(VRTULayout);
        VRTULayout.setHorizontalGroup( VRTULayout
        	.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(VRTULayout.createSequentialGroup()
            .addComponent(VRTUSymbol, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
            .addGap(19, 19, 19)
            .addComponent(VRTUName, GroupLayout.PREFERRED_SIZE, 320, GroupLayout.PREFERRED_SIZE)
            .addGap(7, 7, 7)
            .addComponent(VRTUPrice, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))
        );
        VRTULayout.setVerticalGroup( VRTULayout
        	.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(VRTUSymbol, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
            .addComponent(VRTUName, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
            .addComponent(VRTUPrice, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
        );
//
        MSFTSymbol.setFont(new Font("Serif", Font.BOLD, 24)); // NOI18N
        MSFTSymbol.setForeground(new Color(130, 51, 250));
        MSFTSymbol.setHorizontalAlignment(SwingConstants.CENTER);
        MSFTSymbol.setText("MSFT");

        MSFTName.setFont(new Font("Serif", Font.BOLD, 16)); // NOI18N
        MSFTName.setForeground(new Color(130, 51, 250));
        MSFTName.setText("Microsoft Corporation - Common Stock");

        MSFTPrice.setFont(new Font("Serif", Font.BOLD, 24)); // NOI18N
        MSFTPrice.setForeground(new Color(130, 51, 250));
        MSFTPrice.setHorizontalAlignment(SwingConstants.CENTER);
        MSFTPrice.setText("");

        MSFTPanel.setLayout(MSFTLayout);
        MSFTLayout.setHorizontalGroup( MSFTLayout
        	.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(MSFTLayout.createSequentialGroup()
            .addComponent(MSFTSymbol, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
            .addGap(19, 19, 19)
            .addComponent(MSFTName, GroupLayout.PREFERRED_SIZE, 320, GroupLayout.PREFERRED_SIZE)
            .addGap(7, 7, 7)
            .addComponent(MSFTPrice, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))
        );
        MSFTLayout.setVerticalGroup( MSFTLayout
        	.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(MSFTSymbol, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
            .addComponent(MSFTName, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
            .addComponent(MSFTPrice, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
        );
//
        GOOGLSymbol.setFont(new Font("Serif", Font.BOLD, 24)); // NOI18N
        GOOGLSymbol.setForeground(new Color(130, 51, 250));
        GOOGLSymbol.setHorizontalAlignment(SwingConstants.CENTER);
        GOOGLSymbol.setText("GOOGL");

        GOOGLName.setFont(new Font("Serif", Font.BOLD, 16)); // NOI18N
        GOOGLName.setForeground(new Color(130, 51, 250));
        GOOGLName.setText("Google Inc. - Class A Common Stock");

        GOOGLPrice.setFont(new Font("Serif", Font.BOLD, 24)); // NOI18N
        GOOGLPrice.setForeground(new Color(130, 51, 250));
        GOOGLPrice.setHorizontalAlignment(SwingConstants.CENTER);
        GOOGLPrice.setText("");

        GOOGLPanel.setLayout(GOOGLLayout);
        GOOGLLayout.setHorizontalGroup( GOOGLLayout
        	.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GOOGLLayout.createSequentialGroup()
            .addComponent(GOOGLSymbol, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
            .addGap(19, 19, 19)
            .addComponent(GOOGLName, GroupLayout.PREFERRED_SIZE, 320, GroupLayout.PREFERRED_SIZE)
            .addGap(7, 7, 7)
            .addComponent(GOOGLPrice, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))
        );
        GOOGLLayout.setVerticalGroup( GOOGLLayout
        	.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(GOOGLSymbol, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
            .addComponent(GOOGLName, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
            .addComponent(GOOGLPrice, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
        );
//
        YHOOSymbol.setFont(new Font("Serif", Font.BOLD, 24)); // NOI18N
        YHOOSymbol.setForeground(new Color(130, 51, 250));
        YHOOSymbol.setHorizontalAlignment(SwingConstants.CENTER);
        YHOOSymbol.setText("YHOO");

        YHOOName.setFont(new Font("Serif", Font.BOLD, 16)); // NOI18N
        YHOOName.setForeground(new Color(130, 51, 250));
        YHOOName.setText("Yahoo! Inc. - Common Stock");

        YHOOPrice.setFont(new Font("Serif", Font.BOLD, 24)); // NOI18N
        YHOOPrice.setForeground(new Color(130, 51, 250));
        YHOOPrice.setHorizontalAlignment(SwingConstants.CENTER);
        YHOOPrice.setText("");

        YHOOPanel.setLayout(YHOOLayout);
        YHOOLayout.setHorizontalGroup( YHOOLayout
        	.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(YHOOLayout.createSequentialGroup()
            .addComponent(YHOOSymbol, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
            .addGap(19, 19, 19)
            .addComponent(YHOOName, GroupLayout.PREFERRED_SIZE, 320, GroupLayout.PREFERRED_SIZE)
            .addGap(7, 7, 7)
            .addComponent(YHOOPrice, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))
        );
        YHOOLayout.setVerticalGroup( YHOOLayout
        	.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(YHOOSymbol, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
            .addComponent(YHOOName, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
            .addComponent(YHOOPrice, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
        );
//
        XLNXSymbol.setFont(new Font("Serif", Font.BOLD, 24)); // NOI18N
        XLNXSymbol.setForeground(new Color(130, 51, 250));
        XLNXSymbol.setHorizontalAlignment(SwingConstants.CENTER);
        XLNXSymbol.setText("XLNX");

        XLNXName.setFont(new Font("Serif", Font.BOLD, 16)); // NOI18N
        XLNXName.setForeground(new Color(130, 51, 250));
        XLNXName.setText("Xilinx Inc. - Common Stock");

        XLNXPrice.setFont(new Font("Serif", Font.BOLD, 24)); // NOI18N
        XLNXPrice.setForeground(new Color(130, 51, 250));
        XLNXPrice.setHorizontalAlignment(SwingConstants.CENTER);
        XLNXPrice.setText("");

        XLNXPanel.setLayout(XLNXLayout);
        XLNXLayout.setHorizontalGroup( XLNXLayout
        	.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(XLNXLayout.createSequentialGroup()
            .addComponent(XLNXSymbol, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
            .addGap(19, 19, 19)
            .addComponent(XLNXName, GroupLayout.PREFERRED_SIZE, 320, GroupLayout.PREFERRED_SIZE)
            .addGap(7, 7, 7)
            .addComponent(XLNXPrice, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))
        );
        XLNXLayout.setVerticalGroup( XLNXLayout
        	.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(XLNXSymbol, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
            .addComponent(XLNXName, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
            .addComponent(XLNXPrice, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
        );
//
        TSLASymbol.setFont(new Font("Serif", Font.BOLD, 24)); // NOI18N
        TSLASymbol.setForeground(new Color(130, 51, 250));
        TSLASymbol.setHorizontalAlignment(SwingConstants.CENTER);
        TSLASymbol.setText("TSLA");

        TSLAName.setFont(new Font("Serif", Font.BOLD, 16)); // NOI18N
        TSLAName.setForeground(new Color(130, 51, 250));
        TSLAName.setText("Tesla Motors, Inc. - Common Stock");

        TSLAPrice.setFont(new Font("Serif", Font.BOLD, 24)); // NOI18N
        TSLAPrice.setForeground(new Color(130, 51, 250));
        TSLAPrice.setHorizontalAlignment(SwingConstants.CENTER);
        TSLAPrice.setText("");

        TSLAPanel.setLayout(TSLALayout);
        TSLALayout.setHorizontalGroup( TSLALayout
        	.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(TSLALayout.createSequentialGroup()
            .addComponent(TSLASymbol, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
            .addGap(19, 19, 19)
            .addComponent(TSLAName, GroupLayout.PREFERRED_SIZE, 320, GroupLayout.PREFERRED_SIZE)
            .addGap(7, 7, 7)
            .addComponent(TSLAPrice, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))
        );
        TSLALayout.setVerticalGroup( TSLALayout
        	.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(TSLASymbol, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
            .addComponent(TSLAName, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
            .addComponent(TSLAPrice, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
        );
//
        TXNSymbol.setFont(new Font("Serif", Font.BOLD, 24)); // NOI18N
        TXNSymbol.setForeground(new Color(130, 51, 250));
        TXNSymbol.setHorizontalAlignment(SwingConstants.CENTER);
        TXNSymbol.setText("TXN");

        TXNName.setFont(new Font("Serif", Font.BOLD, 16)); // NOI18N
        TXNName.setForeground(new Color(130, 51, 250));
        TXNName.setText("Texas Instruments Incorporated - Common Stock");

        TXNPrice.setFont(new Font("Serif", Font.BOLD, 24)); // NOI18N
        TXNPrice.setForeground(new Color(130, 51, 250));
        TXNPrice.setHorizontalAlignment(SwingConstants.CENTER);
        TXNPrice.setText("");

        TXNPanel.setLayout(TXNLayout);
        TXNLayout.setHorizontalGroup( TXNLayout
        	.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(TXNLayout.createSequentialGroup()
            .addComponent(TXNSymbol, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
            .addGap(19, 19, 19)
            .addComponent(TXNName, GroupLayout.PREFERRED_SIZE, 320, GroupLayout.PREFERRED_SIZE)
            .addGap(7, 7, 7)
            .addComponent(TXNPrice, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))
        );
        TXNLayout.setVerticalGroup( TXNLayout
        	.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(TXNSymbol, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
            .addComponent(TXNName, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
            .addComponent(TXNPrice, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
        );

// Adding the favourite to a pannel
        FavListPanel.add(FBPanel);
        FavListPanel.add(VRTUPanel);
        FavListPanel.add(MSFTPanel);
        FavListPanel.add(GOOGLPanel);
        FavListPanel.add(YHOOPanel);
        FavListPanel.add(XLNXPanel);
        FavListPanel.add(TSLAPanel);
        FavListPanel.add(TXNPanel);

// Bidding list scrollable table
        BiddingList.setFont(new Font("Serif", Font.BOLD, 16));
        BiddingList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Client Name", "Symbol", "Time", "Bid Price"
            }
        ));

        Scrollable_BidList.setViewportView(BiddingList);
        if (BiddingList.getColumnModel().getColumnCount() > 0) {
            BiddingList.getColumnModel().getColumn(0).setResizable(false);
            BiddingList.getColumnModel().getColumn(1).setPreferredWidth(50);
        }

        StockListButton.setBackground(new Color(10,201, 30));
        StockListButton.setFont(new Font("Serif", Font.BOLD, 28)); // NOI18N
        StockListButton.setForeground(new Color(255, 255, 255));
        StockListButton.setText("Stock Items List");

        StockListButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent btnclick) 
            {
                StockListGui listGui = new StockListGui();
                //listGui.init();
                listGui.updateStockList();
               // listGui.showDisplay();
            }
        });

// Setting out the background panel and layout
        BackgroundPanel.setBackground(new Color(23, 0,100));
        BackgroundPanel.setLayout(BackgroundLayout);
        BackgroundLayout.setHorizontalGroup(
            BackgroundLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, BackgroundLayout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(ProjectName, GroupLayout.PREFERRED_SIZE, 500, GroupLayout.PREFERRED_SIZE)
                .addGap(309, 309, 309))
            .addGroup(BackgroundLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(BackgroundLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addGroup(BackgroundLayout.createSequentialGroup()
                        .addComponent(StockListButton, GroupLayout.PREFERRED_SIZE, 669, GroupLayout.PREFERRED_SIZE))
                    .addComponent(FavListPanel, GroupLayout.PREFERRED_SIZE, 669, GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48)
                .addComponent(Scrollable_BidList, GroupLayout.PREFERRED_SIZE, 531, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(48, Short.MAX_VALUE))
        );
        BackgroundLayout.setVerticalGroup(
            BackgroundLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(BackgroundLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(ProjectName, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(BackgroundLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(BackgroundLayout.createSequentialGroup()
                        .addComponent(FavListPanel, GroupLayout.PREFERRED_SIZE, 476, GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(StockListButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(Scrollable_BidList, GroupLayout.PREFERRED_SIZE, 576, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(48, Short.MAX_VALUE))
        );
        GroupLayout Layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(Layout);
        Layout.setHorizontalGroup(
            Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(BackgroundPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        Layout.setVerticalGroup(
            Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(Layout.createSequentialGroup()
                .addComponent(BackgroundPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
        setTitle("CO225 - Project 2 - Group 19");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
	}
    // GUI layout initializing is over

// Some getters for getting perticular items
    public JButton getStockListButton() {
        return StockListButton;
    }
    public JTable getTable() {
        return BiddingList;
    }
// Getters end

// For changing Favourite stock items current value
    public void setFavPrices()
    {           
        this.FBPrice.setText("$" + String.valueOf(getStockItem("FB").getPrice()));
        this.VRTUPrice.setText("$" + String.valueOf(getStockItem("VRTU").getPrice()));
        this.MSFTPrice.setText("$" + String.valueOf(getStockItem("MSFT").getPrice()));
        this.GOOGLPrice.setText("$" + String.valueOf(getStockItem("GOOGL").getPrice()));
        this.YHOOPrice.setText("$" + String.valueOf(getStockItem("YHOO").getPrice()));
        this.XLNXPrice.setText("$" + String.valueOf(getStockItem("XLNX").getPrice()));
        this.TSLAPrice.setText("$" + String.valueOf(getStockItem("TSLA").getPrice()));
        this.TXNPrice.setText("$" + String.valueOf(getStockItem("TXN").getPrice()));       
    }

// all valid bids will be monitored
    public void setLog(Object [] record)
    {       

        try {
            // thread to sleep for 500 milliseconds
            Thread.sleep(500);
         } catch (Exception e) {
            System.out.println(e);
         }
        javax.swing.table.DefaultTableModel dtm = (javax.swing.table.DefaultTableModel)this.getTable().getModel();
        dtm.addRow(record);
    }

}