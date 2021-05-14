//below imports are for make a gui 

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;

public class ClientGui extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField securityField;		//seecurity symbol field
	private JTextField nameField; 			//name of client
	private Client newUser = new Client();  //create a new client
	private JTextField bidAmount; 			//to enter current bid of client
	private JTextField initialPrice; 		//initial price of item
	private JTextField highestBid; 			//highets bid of otem
	private JTextField yourBid; 			//display last bid of client
	private double highestBidValue = 0;

	

	public ClientGui() 
	{	

		super("Stock Exchenge");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false); 
		
		yourBid = new JTextField();
		yourBid.setFont(new Font("Serif", Font.BOLD, 17));
		yourBid.setBounds(97, 308, 116, 22);
		contentPane.add(yourBid);
		yourBid.setColumns(10);

		highestBid = new JTextField();
		highestBid.setFont(new Font("Serif", Font.BOLD, 17));
		highestBid.setBounds(118, 351, 116, 22);
		contentPane.add(highestBid);
		highestBid.setColumns(10);
		highestBid.setVisible(false);

		JLabel lblName = new JLabel("Name:");
		lblName.setFont(new Font("Serif", Font.BOLD, 17));
        lblName.setForeground(new Color(50, 25, 120));
        lblName.setHorizontalAlignment(SwingConstants.LEFT);
		lblName.setBounds(12, 30, 63, 17);
		contentPane.add(lblName);
		
		JLabel lblSecuritySymbol = new JLabel("Security Symbol:");
		lblSecuritySymbol.setFont(new Font("Serif", Font.BOLD, 17));
        lblSecuritySymbol.setForeground(new Color(50, 25, 120));
        lblSecuritySymbol.setHorizontalAlignment(SwingConstants.LEFT);
		lblSecuritySymbol.setBounds(12, 59, 130, 19);
		contentPane.add(lblSecuritySymbol);
		
		securityField = new JTextField();
		securityField.setFont(new Font("Serif", Font.BOLD, 17));
		securityField.setBounds(136, 56, 116, 22);
		contentPane.add(securityField);
		securityField.setColumns(10);
		
		nameField = new JTextField();
		nameField.setFont(new Font("Serif", Font.BOLD, 17));
		nameField.setBounds(67, 27, 295, 24);
		contentPane.add(nameField);
		nameField.setColumns(10);

		JLabel item = new JLabel("Initial Price $:");
		item.setFont(new Font("Serif", Font.BOLD, 20));
        item.setForeground(new Color(50, 25, 120));
        item.setHorizontalAlignment(SwingConstants.LEFT);
		item.setBounds(12, 130, 150, 23);
		contentPane.add(item);
		//item.setText("ttttttttttttexxxt");
		initialPrice = new JTextField();
		initialPrice.setFont(new Font("Serif", Font.BOLD, 17));
		initialPrice.setBounds(140, 130, 116, 22);
		initialPrice.setFont(new Font("Serif", Font.BOLD, 20));
        initialPrice.setForeground(new Color(50, 25, 120));
        initialPrice.setHorizontalAlignment(SwingConstants.LEFT);
		contentPane.add(initialPrice);
		initialPrice.setColumns(10);
		
		//itemPrice.setBounds(121, 160, 56, 16);
		//contentPane.add(itemPrice);
		
		JButton nameConfirm = new JButton("Confirm");   //button to confirm client name 
		JButton securityConfirm = new JButton("Confirm");  //button to confirm client symbol 



		JLabel lblBid = new JLabel("Bid amount $:");
		lblBid.setFont(new Font("Serif", Font.BOLD, 17));
        lblBid.setForeground(new Color(50, 25, 120));
        lblBid.setHorizontalAlignment(SwingConstants.LEFT);
		lblBid.setBounds(12, 267, 115, 18);
		contentPane.add(lblBid);
		
		bidAmount = new JTextField();
		bidAmount.setFont(new Font("Serif", Font.BOLD, 17));
		bidAmount.setBounds(115, 264, 120, 22);
		contentPane.add(bidAmount);
		bidAmount.setColumns(10);
		
		initialPrice.setEditable(false);
		yourBid.setEditable(false);
		highestBid.setEditable(false);
		
		JButton btnBid = new JButton("Bid");  //button to keep bidding

		btnBid.setEnabled(false);
		bidAmount.setEditable(false);
		
		JButton startBid = new JButton("Start Bids");      //button to start bidding
		startBid.setFont(new Font("Serif", Font.BOLD, 18));
        startBid.setBackground(new Color(50, 25, 120));
        startBid.setForeground(new Color(255, 255, 255));
        startBid.setHorizontalAlignment(SwingConstants.CENTER);
		startBid.setEnabled(false);
		
		startBid.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent bidStart)  //to start bidding
			{
				btnBid.setEnabled(true);
				bidAmount.setEditable(true);
				
				Thread t = new Thread(newUser); 	//starts to listen inputs from server using a 2nd chanel
				t.start();
			}
		});
		startBid.setBounds(130, 195, 212, 40);
		contentPane.add(startBid);




		btnBid.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent bid) 	//to keep biding
			{
				String value =  bidAmount.getText();		//get inputed value 
				//double bigestBid = 0;
				double price = 0;
				
				try //if input is numeric this will run
				{	
					System.out.print("");
					price = Double.parseDouble(value);	
					//bigestBid = Double.parseDouble(highestBid.getText());
															
				}
				catch(Exception e)	//if not 
				{
					bidAmount.setText("") ;		
				}
					
				if( price > highestBidValue ) 	//if value is larger than highest bid
				{
					yourBid.setText("$"+value);
					newUser.sendToServer(value);					
					bidAmount.setText("") ;
				}
				else 	//else displya warning
				{
					JOptionPane.showMessageDialog(null,
					        "Please Enter a Valid Price!\n(numeric value which is higher than current highest bid)");
				}
			}
		});
		btnBid.setBounds(240, 263, 97, 25);
		contentPane.add(btnBid);


		nameConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent confirmName) 	//to confirm name
			{
				String userName =  nameField.getText();
				if(userName.equals(""))		//if field is empty
				{
					JOptionPane.showMessageDialog(null,
					        "Please Enter Your Name!");		
				}
				else
				{
					

					newUser.sendToServer(userName);		//send to server
					String user_response = newUser.receiveFromServer(); //get response from server
					System.out.println("response:" + user_response);
					if(user_response.equals("-2"))	//if invalid symbol
						{	
							System.out.println("Reenter name please.");
							JOptionPane.showMessageDialog(null,
							        "Please Enter Another Name. This Name is in Use!");
							nameField.setText("");
						}

					else
						{
							nameField.setEditable(false);		//after giving name you cant change
							nameConfirm.setEnabled(false);
							securityField.setEditable(true);	//you can edit symvols only after you give name
							securityConfirm.setEnabled(true);

						}

				}
			}
		});
		nameConfirm.setBounds(365, 26, 85, 25);
		contentPane.add(nameConfirm);
		
		
		securityConfirm.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent confirmItem) 	 //to confirm item
			{	
				String symbol = securityField.getText();	//get item input
				
								
				newUser.sendToServer(symbol);		//send to server
				
				String user_response = newUser.receiveFromServer(); //get response from server
				
				if(user_response.equals("-1"))	//if invalid symbol
				{
					JOptionPane.showMessageDialog(null,
					        "Please Enter Valid Symbol!");
					securityField.setText("");
				}
				else 	//else set bid price 
				{
					item.setText(symbol);
					
					initialPrice.setText(user_response);	
					highestBidValue = Double.parseDouble(user_response);
					securityField.setEditable(false);	//after this user cant change symbol
					securityConfirm.setEnabled(false);
					startBid.setEnabled(true);
					highestBid.setVisible(true);
					highestBid.setText(initialPrice.getText());		//set initial price
				}
				
				
			}
		});
		securityConfirm.setBounds(258, 55, 85, 25);
		contentPane.add(securityConfirm);
		
		
		
		
		
		nameConfirm.setEnabled(false);
		nameField.setEditable(false);
		
		securityField.setEditable(false);
		securityConfirm.setEnabled(false);
		
		
		JButton btnConnectToServer = new JButton("Connect to Server");
		btnConnectToServer.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent connect) 	//to connect to server
			{
				nameField.setEditable(true);
				nameConfirm.setEnabled(true);
				
				

				try
				{
					newUser.startClientServer();	//start client server
					newUser.setDataChanells();		//set data in/output channels
					btnConnectToServer.setEnabled(false);
				}
				catch(Exception e)	//fails if server is not ready
				{
					JOptionPane.showMessageDialog(null,
					        "Server is Offline!");
					System.exit(0);
				}
				
				
				
				
			}
		});
		btnConnectToServer.setBounds(250, 410, 230, 40);
		btnConnectToServer.setFont(new Font("Serif", Font.BOLD, 23));
        btnConnectToServer.setBackground(new Color(50, 25, 120));
        btnConnectToServer.setForeground(new Color(255, 255, 255));
        btnConnectToServer.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(btnConnectToServer);
		
		JLabel lblHighestBid = new JLabel("Highest Bid $:");
		lblHighestBid.setFont(new Font("Serif", Font.BOLD, 17));
        lblHighestBid.setForeground(new Color(50, 25, 120));
        lblHighestBid.setHorizontalAlignment(SwingConstants.LEFT);
		lblHighestBid.setBounds(12, 354, 120, 19);
		contentPane.add(lblHighestBid);
		
		
		
		JLabel lblYourBid = new JLabel("Your Bid $:");
		lblYourBid.setFont(new Font("Serif", Font.BOLD, 17));
        lblYourBid.setForeground(new Color(50, 25, 120));
        lblYourBid.setHorizontalAlignment(SwingConstants.LEFT);
		lblYourBid.setBounds(12, 311, 115, 16);
		contentPane.setBackground(new Color(220, 240, 240));
		contentPane.add(lblYourBid);
	}


	public void setHighestBid(String bid)	//to set hights bid for item
	{
		this.highestBid.setText(bid);
	}
	
	public Client getClient()		//to get client
	{
		return this.newUser;
	}
	
	public void setHighestBidValue(double value)
	{
		this.highestBidValue = value;
	}

}
