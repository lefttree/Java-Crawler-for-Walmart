import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Generates url to connect
 * Has two createURL() methods for 
 * 1 keyword and page number
 * 2 only keyword
 * 
 * @author Xiang Li <a href="mailto:bylixiang@ucla.edu">bylixiang@ucla.edu</a>
 * @version 1.0, 10 Nov 2013
 */
public class URLCreator {
    /**
     * Returns an url to connect according to
	 * given keyword and page number
	 *
     * @param query    the keyword to search for
     * @param pageNum  the page number to search for
     * @return 		   an url string
     */
    public URL createURL(String query, String pageNum){
    	URL url = null;
    	String firstString= "http://www.walmart.com/search/search-ng.do?tab_value=all&search_query=";		
    	String secondString= "&search_constraint=0&Find=Find&pref_store=2960&ss=false&ic=16_";
    	String thirdString= "&_mm=";
    	int Num = Integer.parseInt(pageNum);
    	if(query.equals("") || query == null){
    		System.err.println("Cannot convert " + query + " to URL");
    	}
    	if(Num < 1){
    		System.err.println("Page number must be larger than 0");
    	}
    	String charset = "UTF-8";
    	try {
			query = URLEncoder.encode(query, charset);
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
    	Num = (Num-1) * 16;
    	String queryString = firstString + query + secondString + Num + thirdString;
    	try {
			url = new URL(queryString);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
    	
		return url;
    }
	/**
	 *Returns an url to connect according to
	 *given keyword
	 * 
	 *@param query    the keyword to search for
	 *@return     an url string 
	 */
 
    public URL createURL(String query){
    	URL url = null;
    	String firstString= "http://www.walmart.com/search/search-ng.do?tab_value=all&search_query=";		
    	String secondString= "&search_constraint=0&Find=Find&pref_store=2960&ss=false&ic=16_";
    	String thirdString= "&_mm=";
    	if(query.equals("") || query == null){
    		System.err.println("Cannot convert " + query + " to URL");
    		return url;
    	}
    	String charset = "UTF-8";
    	try {
			query = URLEncoder.encode(query, charset);
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
    	String queryString = firstString + query + secondString + "0" + thirdString;
    	try {
			url = new URL(queryString);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return url ;
    }
}
