package com.example.shopmobile;

import java.util.HashMap;
import java.util.Map;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import com.example.shopmobile.model.Item;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

public class MainActivity extends BaseActivity
{
	private Spinner categorySpinner = null;
	private EditText keywordText = null;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		setUIControls();
		initImageLoader(getApplicationContext());
	}

	public static void initImageLoader(Context context)
	{
		// This configuration tuning is custom. You can tune every option, you
		// may tune some of them,
		// or you can create default configuration by
		// ImageLoaderConfiguration.createDefault(this);
		// method.
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
				.threadPriority(Thread.NORM_PRIORITY - 2).denyCacheImageMultipleSizesInMemory()
				.discCacheFileNameGenerator(new Md5FileNameGenerator())
				.tasksProcessingOrder(QueueProcessingType.LIFO).writeDebugLogs() // Remove for release app
																					
				.build();
		// Initialize ImageLoader with configuration.
		ImageLoader.getInstance().init(config);
	}

	/**
	 * Method to initialize UI of MainAcivity
	 */
	private void setUIControls()
	{
		// Setting up Drop-down
		categorySpinner = (Spinner) findViewById(R.id.spinner_category);
		ArrayAdapter<CharSequence> categoryAdapter = ArrayAdapter.createFromResource(this,
				R.array.category_array, android.R.layout.simple_spinner_item);
		categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		categorySpinner.setAdapter(categoryAdapter);

		keywordText = (EditText) findViewById(R.id.keyword);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_menu, menu);
		return true;
	}

	/**
	 * Callback method for clicks on the buttons in Main Screen.
	 * 
	 * @param v
	 *            View clicked
	 */
	public void onButtonClick(View v)
	{
		Log.i("Main Activity", "Inside button event handler ..");
		switch (v.getId())
		{

		case R.id.btn_search:

			/**
			 * // For ItemGridActivity 
			 * String searchCategory = categorySpinner.getSelectedItem().toString(); 
			 * String searchKeyword = keywordText.getText().toString();
			 * 
			 * Intent showItemGrid = new Intent(MainActivity.this,
			 * ItemGridActivity.class);
			 * showItemGrid.putExtra(Constants.ITEM_CATEGORY, searchCategory);
			 * showItemGrid.putExtra(Constants.SEARCH_KEYWORD, searchKeyword);
			 * startActivity(showItemGrid); Log.i("Main Activity",
			 * "Search with category : " + searchCategory + ", keyword : " +
			 * searchKeyword);
			 **/

			String searchCategory = categorySpinner.getSelectedItem().toString();
			String searchKeyword = keywordText.getText().toString();
			
			Item[] itemArray = getDummyItemArray(searchCategory, searchKeyword);
			Intent intent = new Intent(this, ItemListActivity.class);
			intent.putExtra(Constants.Extra.ITEMS, itemArray);
			
			startActivity(intent);

			break;

		default:
		}
	}
	
	public Item[] getDummyItemArray(String searchCategory, String searchKeyword)
	{
		Item[] itemArray = new Item[35];
		
		Integer count = 0 ;
		for (String imageUrl : Constants.IMAGES)
		{
			Integer id = count + 100;
			Map<String, String> attrValuesMap = new HashMap<String, String>();
			
			attrValuesMap.put(Constants.ITEM_ID, id.toString());
			attrValuesMap.put(Constants.ITEM_URL, imageUrl);
			attrValuesMap.put(Constants.ITEM_TITLE, Constants.TITLES[count]);
			
			Double price = 10.0 + Math.random()*30;
			Integer priceInt = price.intValue();
			attrValuesMap.put(Constants.ITEM_PRICE, priceInt.toString());

			itemArray[count] = new Item(count+100, attrValuesMap);
			
			count++;
		}
		
		return itemArray;
	}

	@Override
	public void onBackPressed()
	{
		imageLoader.stop();
		super.onBackPressed();
	}
}
