package com.example.shopmobile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		setUIControls();
	}
	
	/**
	 * Method to initialize UI of MainAcivity
	 */
	private void setUIControls()
	{
		// Setting up Drop-down
		Spinner categorySpinner = (Spinner) findViewById(R.id.spinner_category);
		ArrayAdapter<CharSequence> categoryAdapter = ArrayAdapter.createFromResource(this, R.array.category_array, 
														android.R.layout.simple_spinner_item);
		categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		categorySpinner.setAdapter(categoryAdapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/**
	 * Callback method for clicks on the buttons in Main Screen.
	 * @param v		View clicked
	 */
	public void onButtonClick(View v)
	{
		Log.i("Main Activity", "Inside button event handler ..");
		switch(v.getId())
		{
 
		case R.id.btn_search:
 
			Intent showItemGrid = new Intent(MainActivity.this, ItemGridActivity.class);
			startActivity(showItemGrid);
			Log.i("Main Activity", "Inside button search ..");
 
			break;
 
		default:
		}
	}
}
