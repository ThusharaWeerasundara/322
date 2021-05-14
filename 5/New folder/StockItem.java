public class StockItem {
	private String symbol;
	private String securityName;
	private String marketCategory;
	private String testIssue;
	private String financialStates;
	private double roundLotSize;
	private double price;
	// Possible constructor for this class
	StockItem(String symbol, String securityName, String marketCategory, String testIssue, String financialStates, double roundLotSize, double price) {
		this.symbol = symbol;
		this.securityName = securityName;
		this.marketCategory = marketCategory;
		this.testIssue = testIssue;
		this.financialStates = financialStates;
		this.roundLotSize = roundLotSize;
		this.price = price;
	}

// Getters
	public String getSymbol() {
		return this.symbol;
	}

	public String getSecurityName() {
		return this.securityName;
	}

	public double getPrice() {
		return this.price;
	}
	
	public String getMarketCategory() {
		return this.marketCategory;
	}

	public String getTestIssue() {
		return this.testIssue;
	}

	public String getFinancialStates() {
		return this.financialStates;
	}

	public double getRoundLotSize() {
		return this.roundLotSize;
	}


	public void setPrice(Double price) {
		
		if(price > this.price)
		{
			this.price = price;		
		}		
	}
}