package com.example.shopmobile;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AbsListView;
import com.nostra13.universalimageloader.core.assist.PauseOnScrollListener;

/**
 * 
 * 
 * @author Sergey Tarasevich (nostra13[at]gmail[dot]com)
 */
public class AbsListViewBaseActivity extends BaseActivity
{

	protected static final String STATE_PAUSE_ON_SCROLL = "STATE_PAUSE_ON_SCROLL";
	protected static final String STATE_PAUSE_ON_FLING = "STATE_PAUSE_ON_FLING";

	protected AbsListView listView;

	protected boolean pauseOnScroll = false;
	protected boolean pauseOnFling = true;

	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState)
	{
		pauseOnScroll = savedInstanceState.getBoolean(STATE_PAUSE_ON_SCROLL, false);
		pauseOnFling = savedInstanceState.getBoolean(STATE_PAUSE_ON_FLING, true);
	}

	@Override
	public void onResume()
	{
		super.onResume();
		applyScrollListener();
	}

	private void applyScrollListener()
	{
		listView.setOnScrollListener(new PauseOnScrollListener(imageLoader, pauseOnScroll,
				pauseOnFling));
	}

	@Override
	public void onSaveInstanceState(Bundle outState)
	{
		outState.putBoolean(STATE_PAUSE_ON_SCROLL, pauseOnScroll);
		outState.putBoolean(STATE_PAUSE_ON_FLING, pauseOnFling);
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu)
	{
		MenuItem pauseOnScrollItem = menu.findItem(R.id.item_pause_on_scroll);
		pauseOnScrollItem.setVisible(true);
		pauseOnScrollItem.setChecked(pauseOnScroll);

		MenuItem pauseOnFlingItem = menu.findItem(R.id.item_pause_on_fling);
		pauseOnFlingItem.setVisible(true);
		pauseOnFlingItem.setChecked(pauseOnFling);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{
		case R.id.item_pause_on_scroll:
			pauseOnScroll = !pauseOnScroll;
			item.setChecked(pauseOnScroll);
			applyScrollListener();
			return true;
		case R.id.item_pause_on_fling:
			pauseOnFling = !pauseOnFling;
			item.setChecked(pauseOnFling);
			applyScrollListener();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
}
