import java.net.URL;

/**
 * Gets command line arguments 
 * Start screen scraping process
 * 
 * @author Xiang Li <a href="mailto:bylixiang@ucla.edu">bylixiang@ucla.edu</a>
 * @version 1.0, 10 Nov 2013
 */
public class URLConnectionReader {
	/**
	 * Main function 
	 * get command line arguments
	 * and start screen scrape
	 * 
	 * @param args[0] query
	 * @param args[1] page_num
	 */
    public static void main(String[] args) throws Exception {
    	//create objects
    	URLCreator urlCreator = new URLCreator();
		ContentParser content = new ContentParser();
		
		if(args.length < 1){
			System.out.println("Not enough arguments");
		}else if(args.length > 2){
			System.out.println("You can only pass 2 arguments");
		}else if(args.length == 1){
			URL url = urlCreator.createURL(args[0]);
			String totalResult = content.getTotal(url);
			System.out.println(" Total result: " + totalResult);
		}else if(args.length == 2){
			URL url = urlCreator.createURL(args[0], args[1]);
			PageResult pageResult = content.getPage(url);
			pageResult.printResult();
		}
    }
}