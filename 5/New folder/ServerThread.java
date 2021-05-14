import java.io.BufferedReader;				//to use BufferedReader
import java.io.IOException;
import java.io.InputStreamReader;			//to use InputStreamReader
import java.net.Socket;						// to use Socket
import java.io.PrintWriter;					//to use PrintWriter
import java.util.ArrayList; 				// import the ArrayList class
import java.util.*;							//to use HashMap and Set
import javax.swing.JOptionPane;				//to use option pane


public class ServerThread extends Server implements Runnable  //this class makes threads in server side to work with each client
{

	private static final long serialVersionUID = 1L;
	private	Socket socket;      //socket for user
	private BufferedReader in;  //to read from user
	private PrintWriter send;   //to send to a user
	private ServerGui serverFrame; //server gui
	
	private static HashMap <String, ArrayList<Socket>> bids = new HashMap<String, ArrayList<Socket>>();  	//to store all items and sockets of bidders.only a common one is needed therefore this is static
	private static HashMap <String, ArrayList<BidRequest>> bidRequests = new HashMap<String, ArrayList<BidRequest>>(); //to store all bids. this too needs only one threfore this is static
	private static Set<String> userNames = new HashSet<String>(); //to store distinct user names

	ServerThread(Socket socket, ArrayList<ServerThread> clients, ServerGui serverFrame)  	//constructor of class
	{
		this.socket = socket;				//store values
		this.serverFrame = serverFrame;
		
		try {
			this.send = new PrintWriter(socket.getOutputStream(), true);
			this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
			}
	
	public void run()   
	{	
		String name = "";		//to store user name
		String item = "";		//to store user item
		try 
		{
			String message = null;
										
			while(true) 	//run until gets a valid symbol
			{
				name = in.readLine();	//get user input

				if(checkName(name))		//if enterd name is distinct
				{	
					send.println("Name accepted.");
					break;
				}
				else
					send.println("-2");  //else send -2 
								
			}
			
			
			
			
			while(true) 	//run until gets a valid symbol
			{
				 item = in.readLine();		//get user item
				
				if(isExist(item))
				{	
					send.println("Highest bid in $ for " + item + " is:" +getStockItem(item).getPrice());  //if item exists send current price
					insertBid(item); //store that request
					break;
				}
				else
				{
					send.println("-1");  //else send -1 as given
				}				
			}
			
			while(true)		//listen and send replies to users until server or client exits
			{					
					message = in.readLine();  //client bid
					try
					{
						if(Double.parseDouble(message) > getStockItem(item).getPrice())  //if new bid is grater than highest bid
							{
								BidRequest new_bid = new BidRequest();		//create new object to store bid details
								new_bid.setSymbol(item);
								new_bid.setUser(name);
								new_bid.setPrice(Double.parseDouble(message));
								new_bid.setTime();
								
								String list = new_bid.getBid();    
								Object [] store = list.split(",");  //store to update log entries

								serverFrame.setLog(store); 		//update log

								recordBid(item, new_bid);		//record this bid
								updatePrice(item, Double.parseDouble(message));	 //update price in data base

								try
								{
									broadcast(item);  //broadcast a message indicating new item price send a message to user(visible in nc)								
								}
								catch(Exception e)  //this fails if clients are offline
								{	
									removeUser(name, socket, item, in, send);
									JOptionPane.showMessageDialog(null,
									        "Client is Offline!");
								}
							}
							else  //else send a message to user(visible in nc)
							{
									send.println("Enter higher bid!");
							}

					}
					catch(Exception e)  //if user rnters invalid value send a message to user(visible in nc)
					{
						send.println("Enter valid entry!");
					}
					
					
					serverFrame.setFavPrices();		//update favourite 8 prices
			}
		}
		catch(Exception e)
		{
			removeUser(name, socket, item, in, send);
		}
	}

	public static void broadcast(String item)		//this methods send a message to send a message to user(visible in nc)
	{	
		ArrayList<Socket> updateClients = bids.get(item);	//store sockets of required users
		
		PrintWriter go; //to send output

		for(Socket client : updateClients)  //go threough each socket
		{	
			try
			{
				go = new PrintWriter(client.getOutputStream(), true);
				go.println("Highest bid in $ for " + item + " is:" +getStockItem(item).getPrice());  //message
			}
			catch(Exception e) //if fails client is offline
			{
				JOptionPane.showMessageDialog(null,
				        "Client is Offline!");
			}
			
		}
	}

	public void recordBid(String item, BidRequest bid)		//method to record bids
	{
		if(bidRequests.containsKey(item))					//if item contains update its bids list
		{
			ArrayList<BidRequest> bidsForItem = bidRequests.get(item);
			bidsForItem.add(bid);
			bidRequests.put(item, bidsForItem);
		}
		
		else 		//else make a new entry
		{	
			ArrayList<BidRequest> bidsForItem = new ArrayList<BidRequest>();
			bidsForItem.add(bid);
			bidRequests.put(item,bidsForItem);
		}
	}

	public void insertBid(String item) //to record user sockets
	{	
		if(bids.containsKey(item)) //if item contains update its user socket list
		{
			ArrayList<Socket> bidders = bids.get(item);
			bidders.add(socket);
			bids.put(item, bidders);
		}
		
		else //else make a new entry
		{	
			ArrayList<Socket> bidders = new ArrayList<Socket>();
			bidders.add(socket);
			bids.put(item,bidders);
		}
			
	}

	public boolean checkName(String name)
	{
		if(userNames.contains(name))
		{
			return false;
		}
		else
		{
			userNames.add(name);
			return true;
		}
	}

	public void removeUser(String name, Socket socket,String item, BufferedReader in, PrintWriter out) 
	{
		userNames.remove(name);
			
		try
		{	
			bids.get(item).remove(socket);
			socket.close();
			in.close();
			out.close();
		}
		catch(Exception e)
		{
			System.out.println("Close operation failed.");
		}
				
	}

}
