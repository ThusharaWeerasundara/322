import javax.swing.JOptionPane; 				//for display pop ups
import java.io.BufferedReader;					//to read from server
import java.io.InputStreamReader;				//to read input stream through socket
import java.io.PrintWriter;						//to write to server through socket
import java.net.Socket;							//to use sockets

public class Client implements Runnable 		//implements runable because need another thread for 2 way communication
{
	private PrintWriter send;					//use to send messages
	private BufferedReader fromServer;			//use to receive messages
	private Socket socket; 						
	protected String stockValue;				//current stock price for selected item
	final static int PORT = 20000;

	public void startClientServer() 			//method to start client 
	{		
		try
		{
			this.socket = new Socket("localhost",PORT); 	//ip address is local and port is as given
		}
		 
		 	catch(Exception e) 								//if server is offline this action fails and show error
		{
		 		JOptionPane.showMessageDialog(null,
				        "Server is not Online!");
		 		System.exit(0);
		}
	}


	public void run() 			//this method runs on another thread and keep listening to server
	{	
					
		while(true)
		{	
			try
			{	
				 BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

				 try
				 {
				 	String message[] = in.readLine().split(":"); //get the 2 parts fron incoming message
				 	stockValue = message[1]; 	//store price
				 }
				 catch(Exception e)  	//if above split fails that means invalid message
				 {	
					System.out.println(in.readLine());
				 	System.out.println("Invalid Entry!");
				 }
	

			}
			catch(Exception e)   //above listen operation fails if server stops therfore client should quit
			{
				JOptionPane.showMessageDialog(null,
				        "Server is not Online!");
				
		 		System.exit(0);
			}
			
		}
	}	

	
	public void setDataChanells()	//this method sets data chanels ipnput and output
		{	
			try
			{
				send = new PrintWriter(this.socket.getOutputStream(), true);
				fromServer = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
			}
			catch(Exception e) 	 //fails if server is offline
			{
				JOptionPane.showMessageDialog(null,
				        "Server is not Online!");
		 		System.exit(0);
			}
		}


	public void sendToServer(String details)  //send messages to server
		{
			send.println(details);
		}

	public String getStockValue()  //return stock value as a string
	{
		return stockValue;
	}

	public String receiveFromServer()  //receive messages from server
		{	

			String incoming; 
			try
				{
					incoming = fromServer.readLine(); 
					if(incoming.indexOf(":") != -1)
						{
							try
								{
									String parts[] = incoming.split(":"); //store 2 parts in message
									System.out.println(incoming);
									return parts[1];						
								}
							catch(Exception e) //if above operation fails
								{
									return null;
								}
						}
			
					else
						return incoming;
				}
			catch(Exception e)
				{
					return "";
				}
		
			
			
		}
}
