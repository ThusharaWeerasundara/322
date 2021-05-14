import java.net.ServerSocket;		//to use serversocket
import java.net.Socket; 			//to use socket
import java.util.ArrayList; 		// import the ArrayList class
import javax.swing.JOptionPane;     //to pop up mesages

public class Server extends ItemList //this is the server class and can use methods in itemlist

{		

	private static final long serialVersionUID = 1L;
	private ArrayList<ServerThread> clients = new ArrayList<>(); 	//to store threads of each client
	private static ServerGui serverFrame;  							//only 1 gui for server therefore it is static
	final static int PORT = 20000;									//port as given

	public static void main(String[] args) 
		{	
			Server stock_exchange = new Server();					//start server 
			
			serverFrame = new ServerGui(); 							//start server gui
			stock_exchange.startAuction();							//start auction
		}

	@SuppressWarnings("resource")
	private void startAuction()    
	{
		
		try
		{
			ItemList.makeItemList();								//make item list to store initial data of stocks
			serverFrame.setFavPrices();								//set favourite 8 prices

			ServerSocket server = new ServerSocket(PORT); 			//initialzi server socket
			System.out.println("Server is up....");


			
			while(true) 											//use while loop to accept clients
			{
				Socket client = server.accept(); 					//blocking method to accept clients one by one
				
				ServerThread connection = new ServerThread(client, clients, serverFrame); 	//make threads for each client to run them parallaly
				Thread t = new Thread(connection);
				clients.add(connection); 				//store client connections for further use
				t.start(); 					
			}
		}
		catch(Exception e) 			//server will not start if the socket designated is busy
		{
			JOptionPane.showMessageDialog(null,
			        "Theese Server Resources are in Use!");
			System.exit(0);
		}
		
	}

	

}
