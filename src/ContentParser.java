import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * ContentParser class
 * Connects to the url and get document
 * parse the document to get result 
 * 
 * @author Xiang Li <a href="mailto:bylixiang@ucla.edu">bylixiang@ucla.edu</a>
 * @version 1.0, 10 Nov 2013
 */

public class ContentParser {
	/**
	 * This method is used when there is only 
	 * one argument(keyword)
	 * 
	 * @param  url          url to connect to
	 * @return 				a string contains number of total results
	 */
	public String getTotal(URL url){
		Document doc = getURL(url.toString());
		String totalResult = getTotalNum(doc);
		return totalResult;
	}
	
	/**
	 * This method is used when there are two
	 * arguments(keyword and page number)
	 * 
	 * @param  url     url connect to 
	 * @return 		   pageResult object
	 */
	public PageResult getPage(URL url){
		Document doc = getURL(url.toString());
		PageResult result = getPageResult(doc);
		return result;
	}
	
	private Document getURL(String url){
		Document doc = null;
		try {
			doc = Jsoup.connect(url).userAgent("chrome").ignoreHttpErrors(true).timeout(0).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return doc;
	}

	private PageResult getPageResult(Document doc) {
		
		ArrayList<String> titles = new ArrayList<String>();
		ArrayList<String> prices = new ArrayList<String>();
		
		//titles are with classname "prodLink"
		Elements tElements = doc.getElementsByClass("prodLink");
		int size1 = tElements.size();
		for(int j = 0; j < size1; j=j+2){
			Element t = tElements.get(j);
			titles.add(t.attr("title").toString());
		}
		
		//prices are under divs with classname "PriceContent"
		Elements priceDisplay = doc.getElementsByClass("PriceContent");
		int size = priceDisplay.size();
		for(int i = 0; i < size; i=i+2){
			//spans with bigPriceText2 are numbers before decimal point
			Element bigPriceElement = priceDisplay.get(i).children().select("span.bigPriceText2").first();
			//spans with smallPriceText2 are number after decimal point
			Element smallPriceElement = priceDisplay.get(i).children().select("span.smallPriceText2").first();
			//
			Element boldElement = priceDisplay.get(i).children().select("div.PriceLBold").first();
			//
			Element textElement = priceDisplay.get(i).children().select("p.ShelfTextOOS").first();
			//check if this product has price
			if(bigPriceElement != null){
				prices.add(bigPriceElement.text() + smallPriceElement.text());
			}else if(boldElement != null){
				prices.add(boldElement.text());
			}else if(textElement != null){
				prices.add(textElement.text());
			}else{
				prices.add("No Prices");
			}
		}
		PageResult pageResult = new PageResult(titles, prices);	
		return pageResult;
	}

	private String getTotalNum(Document doc){
		
		Elements spans = doc.getElementsByClass("floatLeft");
		String totalResult = null;
		for(Element span : spans){
			String spanText = span.text();
			String[] eachLine = spanText.split("\n");
			for(int i=0; i < eachLine.length; i++){
				if(eachLine[i].indexOf("Results") != -1){
					totalResult = eachLine[i];
				}
			}
		}
		return totalResult;		
	}
}
