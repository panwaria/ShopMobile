package com.example.shopmobile;

import java.util.List;

import com.example.shopmobile.model.Item;
import com.example.shopmobile.service.CatalogSearchService;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

/**
 * Renders the appropriate items in a grid format.
 * 
 * @author excelsior
 *
 */
public class ItemGridActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_itemgrid);
		
		// Retrieve the search parameters passed
		Bundle extras = getIntent().getExtras();
	    String category = extras.getString(Constants.SEARCH_CATEGORY);
	    String keyword = extras.getString(Constants.SEARCH_KEYWORD);
	    Log.i("ItemGridActivity", "Category : " + category + ", Keyword : " + keyword);
	    
	    List<Item> catalogItems = CatalogSearchService.searchItemsInCatalog(category, keyword);
	    Log.i("ItemGridActivity", "Found " + catalogItems.size() + " catalog items.");
	}
}
