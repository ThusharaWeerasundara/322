public class ClientApp extends ClientGui 		//Client app.this starts  client side interface
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) 
	{
			ClientGui frame = new ClientGui(); 		//new client gui
			frame.setVisible(true);
			
			while(true)   						//read inputs from server
			{		
					
					String newvalue = frame.getClient().getStockValue();
					
					try
					{
						double bid = Double.parseDouble(newvalue);  //get bid value only if a double value is received.else ignore
						if(bid > 0)
						{
							frame.setHighestBid(newvalue);
							frame.setHighestBidValue(bid);
						}
						
					}
					catch(Exception e)
					{
						//ignore this exception
					}
					
					System.out.print("");
												
			}
	}
} 