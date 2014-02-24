package com.example.shopmobile.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
import com.example.shopmobile.Constants;
import com.example.shopmobile.model.Item;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * Search API for searching the catalog for items based on relevant keywords.
 * 
 * @author excelsior
 *
 */
public class CatalogSearchService {

	/**
	 * Returns a list of items corresponding to a category and a search keyword
	 * @param category	- Category of the item. e.g. Books
	 * @param keyword	- Keyword to search.	e.g. J.K.Rowling. Searched for all the books which
	 * 					have keyword "J.K.Rowling" somewhere in the attributes.
	 */
	public static List<Item> searchItemsInCatalog(String category, String keyword)
	{
		// TODO : Fetch JSON data using an apt url
		List<Item> items = Lists.newArrayList();
		
		// TODO : Hardcoded data as of now. Fix this !!
		Map<String, String> item1AttrMap = Maps.newHashMap();
		item1AttrMap.put(Constants.ITEM_URL, "http://highered.mcgraw-hill.com/sites/dl/free/0070131511/cover/cormen-lg_cover.jpg");
		items.add(new Item(1, item1AttrMap));
		
		Map<String, String> item2AttrMap = Maps.newHashMap();
		item2AttrMap.put(Constants.ITEM_URL, "http://pages.cs.wisc.edu/~dbbook/images/book3ed.jpg");
		items.add(new Item(2, item2AttrMap));
		
		return items;
	}
	
	// TODO
	/**
	 * Returns the fetch url based on the category and keyword parameters.
	 * @param category
	 * @param keyword
	 * @return
	 */
	private static String getCatalogSearchURL(String category, String keyword)
	{
		return null;
	}
	
	/**
	 * http://stackoverflow.com/questions/4308554/simplest-way-to-read-json-from-a-url-in-java
	 * @param url
	 * @return
	 * @throws IOException 
	 * @throws MalformedURLException 
	 * @throws JSONException 
	 */
	private static JSONObject getJSONFromURL(String url) throws MalformedURLException, IOException, JSONException
	{
	    InputStream is = new URL(url).openStream();
	    try {
	      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
	      String jsonText = readAll(rd);
	      JSONObject json = new JSONObject(jsonText);
	      return json;
	    } finally {
	      is.close();
	    }		
	}
	
	private static String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}
}
