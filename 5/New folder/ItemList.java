// This is a simple collection of items given in the stocks.csv file
import java.io.*; 

import java.util.HashMap;

import javax.swing.JFrame;

import java.util.Collection;

public class ItemList extends JFrame
{
	private static final long serialVersionUID = 1L;
	protected static HashMap<String,StockItem> StockItemMap;

	public static void  makeItemList() 
	{
		try {

			int tokenLength;
			Double roundLotSize, price;
			String [] token;
		// Read and get values from stocks.csv file
			File file = new File("stocks.csv");
			BufferedReader br = new BufferedReader(new FileReader(file));
			StockItemMap =new HashMap<String,StockItem>();
	  
		// To neglect the first line of the StockItem.csv file
			br.readLine();

			String str, securityName;

		// read and split data into their fields and making hash map  
			while ((str = br.readLine()) != null) 
			{
				token = str.split(",");
				tokenLength = token.length;

			// Handling incorrect ordering data in the stocks.csv file
				if (tokenLength != 6) {
					securityName = "";
					for (int i=1 ; i<tokenLength-4 ; i++ ) {
						securityName += token[i];
						if (i != tokenLength-5) {
							securityName += ",";
						}
					}
				} else {
					securityName = token[1];
				}

				roundLotSize = Double.parseDouble(token[tokenLength-1]);
			// Generate random price between 0 and 10 and round off the price to two decimsl points
				price = Math.round(Math.random()*1000)/100.0;

			// Generate a Item 
				StockItem item = new StockItem(token[0], securityName, token[tokenLength-4], token[tokenLength-3], token[tokenLength-2], roundLotSize, price);
				
			// Put the generated Item into HashMap
				StockItemMap.put(token[0],item);
			}

			br.close();

	// Exeption Handling
		} catch (IOException ex) {
			System.out.println("Error: " +ex);
		} catch (NullPointerException ex) {
			System.out.println("Error: " +ex);
		}
	}

	// Check whether the interested value is there 
	public boolean isExist(String symbol){
        return StockItemMap.containsKey(symbol);
    }
    // Get the information of the given item
    public static StockItem getStockItem(String symbol){
        return StockItemMap.get(symbol);
    }

    // update the item price and put it in the hash map again
    public  void updatePrice(String symbol,double price){
       StockItem item = StockItemMap.get(symbol);
       item.setPrice(price);
       StockItemMap.put(symbol, item);
    }

// Takes the item list
    public static Collection<StockItem> getValues(){
        return StockItemMap.values();
    }
    
    public static HashMap<String, StockItem> getDatabase()
    {
    	return ItemList.StockItemMap;
    }
}