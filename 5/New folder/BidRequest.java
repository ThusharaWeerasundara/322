import java.util.Date;	// To get the real time

public class BidRequest 
{
// Initializing the private variables for use of this calss
	private String symbol;
	private String user;
	private Double price;
	private String time;	
	
// Setters for setting up the values		
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	
	public void setUser(String user) {
		this.user = user;
	}
	
	public void setPrice(Double price) {
		this.price = price;
	}
	
	public void setTime() {
		this.time = (new Date()).toString();
	}
// Setters finished

// Take the bid informaation of each users
	public String getBid()
	{
		return user + "," + symbol + "," + time + "," + price;
	}

// For the use of future versions

	// public String getTime() {
	// 	return time;
	// }

	// public Double getPrice() {
	// 	return price;
	// }

	// public String getUser() {
	// 	return user;
	// }

	// public String getSymbol() {
	// 	return symbol;
	// }
}
