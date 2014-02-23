package com.example.shopmobile;

import android.app.Activity;
import android.os.Bundle;

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
	}
}
