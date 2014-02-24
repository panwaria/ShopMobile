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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.example.shopmobile.Constants;
import com.example.shopmobile.model.Item;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * Search API for searching the catalog for items based on relevant keywords.
 * 
 * @author excelsior
 * 
 */
public class CatalogSearchService
{

	/**
	 * Returns a list of items corresponding to a category and a search keyword
	 * 
	 * @param category
	 *            - Category of the item. e.g. Books
	 * @param keyword
	 *            - Keyword to search. e.g. J.K.Rowling. Searched for all the
	 *            books which have keyword "J.K.Rowling" somewhere in the
	 *            attributes.
	 */
	public static List<Item> searchItemsInCatalog(String category, String keyword)
	{
		List<Item> items = Lists.newArrayList();
		String url = getCatalogSearchURL(category, keyword);
		JSONArray itemsObjects = null;
		try {
			itemsObjects = getJSONFromURL(url);
			for(int i=0; i < itemsObjects.length(); i++) {
				JSONObject obj = itemsObjects.getJSONObject(i);
				
				Map<String, String> attrValuesMap = Maps.newHashMap();
				attrValuesMap.put(Constants.ITEM_TITLE, obj.getString("title"));
				attrValuesMap.put(Constants.ITEM_URL, obj.getString("image_url"));
				attrValuesMap.put(Constants.ITEM_PRICE, obj.getString("price"));
				
				items.add(new Item(i, attrValuesMap));
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Log.i("TEST", "found " + items.size() + " items ..");
		return items;
	}

	// TODO
	/**
	 * Returns the fetch url based on the category and keyword parameters.
	 * 
	 * @param category
	 * @param keyword
	 * @return
	 */
	private static String getCatalogSearchURL(String category, String keyword)
	{
		String url = Constants.CATALOG_BASE_URL;
		url = url + "&category=";
		if(!Strings.isNullOrEmpty(category)) {
			url = url + category;
		}
		
		url = url + "&search=";
		if(!Strings.isNullOrEmpty(keyword)) {
			url = url + keyword;
		}
		
		Log.i("TEST", "URL : " + url);
		return url;
	}

	private static JSONArray getJSONFromURL(String url)
			throws MalformedURLException, IOException, JSONException {
		InputStream is = new URL(url).openStream();
		try {
			BufferedReader rd = new BufferedReader(new InputStreamReader(is,
					Charset.forName("UTF-8")));
			String jsonText = readAll(rd);
			JSONArray json = new JSONArray(jsonText);
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
