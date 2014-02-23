package com.example.shopmobile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import com.example.shopmobile.model.Item;
import com.example.shopmobile.service.CatalogSearchService;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;
import android.widget.ImageView;

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
	    
	    for(Item item : catalogItems)
	    {
	    	String url = item.getAttrValuesMap().get(Constants.ITEM_IMG_URL);
	    	loadImageForURL(url);
	    }
	}
	
	private void loadImageForURL(String url) {
		try {
			ImageView imgView = (ImageView) findViewById(R.id.image);
			Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(url).getContent());
			imgView.setImageBitmap(bitmap);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
