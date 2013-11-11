import java.util.ArrayList;

/**
 * PageResult class
 * has two ArraysList members
 * 
 * @author Xiang Li <a href="mailto:bylixiang@ucla.edu">bylixiang@ucla.edu</a>
 * @version 1.0, 10 Nov 2013
 */
public class PageResult {
	
	
	private ArrayList<String> titles;
	private ArrayList<String> prices;
	
	/**
	 * class constructor
	 * 
	 * @param titles  ArrayList contains titles of one page
	 * @param prices  ArrayList contains prices of one page
	 */
	public PageResult(ArrayList<String> titles, ArrayList<String> prices){
		this.titles = titles;
		this.prices = prices;
	}
	
	/**
	 *Prints the page results to screen
	 */
	public void printResult(){
		for(int i = 0;i<titles.size();i++){
			System.out.println("Title: " + titles.get(i));
			System.out.println("Prices: " + prices.get(i) + "\n");
		}
	}
}
