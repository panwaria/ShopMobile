package com.example.shopmobile;

import java.util.List;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import com.example.shopmobile.model.Item;
import com.example.shopmobile.service.CatalogSearchService;

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
//	    	loadImageForURL(url);
	    }
	}
	
	/**
	 * TODO : Loads image for an url in the grid.
	 * 
	 * Returns the bitmap representation of image to be set in the image view.
	 * @param url
	 */
//	private void loadImageForURL(String url) {
//		try {
//			ImageView imgView = (ImageView) findViewById(R.id.image);
//			Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(url).getContent());
//			imgView.setImageBitmap(bitmap);
//		} catch (MalformedURLException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
}
