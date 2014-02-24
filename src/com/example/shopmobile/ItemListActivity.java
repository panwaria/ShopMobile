package com.example.shopmobile;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.shopmobile.model.Item;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;
import com.nostra13.universalimageloader.core.assist.SimpleImageLoadingListener;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

public class ItemListActivity extends AbsListViewBaseActivity
{
	DisplayImageOptions options;

//	String[] imageUrls;
	Item[] items;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_itemlist);

		Intent i = getIntent();
		Object[] objArray = (Object[]) i.getSerializableExtra(Constants.Extra.ITEMS);
		items = new Item[objArray.length];

		int count = 0;
		for (Object obj : objArray)
			items[count++] = (Item) obj;
		
//		Bundle bundle = getIntent().getExtras();
//		imageUrls = bundle.getStringArray(Extra.IMAGES);

		options = new DisplayImageOptions.Builder().showImageOnLoading(R.drawable.ic_stub)
				.showImageForEmptyUri(R.drawable.ic_empty).showImageOnFail(R.drawable.ic_error)
				.cacheInMemory(true).cacheOnDisc(true).considerExifParams(true)
				.displayer(new RoundedBitmapDisplayer(20)).build();

		listView = (ListView) findViewById(android.R.id.list);
		((ListView) listView).setAdapter(new ItemAdapter());
		listView.setOnItemClickListener(new OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id)
			{
				Toast.makeText(ItemListActivity.this, "Need to send an intent to Stripe Activity", Toast.LENGTH_SHORT).show();
				// Send intent to Stripe Activity giving details of the item clicked.
			}
		});
	}

	@Override
	public void onBackPressed()
	{
		AnimateFirstDisplayListener.displayedImages.clear();
		super.onBackPressed();
	}

//	private void startImagePagerActivity(int position)
//	{
//		Intent intent = new Intent(this, ImagePagerActivity.class);
//		intent.putExtra(Extra.IMAGES, imageUrls);
//		intent.putExtra(Extra.IMAGE_POSITION, position);
//		startActivity(intent);
//	}

	class ItemAdapter extends BaseAdapter
	{

		private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();

		private class ViewHolder
		{
			public TextView text;
			public ImageView image;
		}

		@Override
		public int getCount()
		{
			return items.length;
//			return imageUrls.length;
		}

		@Override
		public Object getItem(int position)
		{
			return position;
		}

		@Override
		public long getItemId(int position)
		{
			return items[position].getId();
//			return position;
		}

		@Override
		public View getView(final int position, View convertView, ViewGroup parent)
		{
			View view = convertView;
			final ViewHolder holder;
			if (convertView == null)
			{
				view = getLayoutInflater().inflate(R.layout.item_list_image, parent, false);
				holder = new ViewHolder();
				holder.text = (TextView) view.findViewById(R.id.text);
				holder.image = (ImageView) view.findViewById(R.id.image);
				view.setTag(holder);
			} 
			else
			{
				holder = (ViewHolder) view.getTag();
			}

			if(items[position] == null)
				return view;
			
			Log.i("Prakhar", "ID: " + items[position].getId() + " URL: " + items[position].getAttrValue(Constants.ITEM_URL));
			holder.text.setText("ID: " + items[position].getId());//(position + 1));

			imageLoader.displayImage(items[position].getAttrValue(Constants.ITEM_URL).toString() /*imageUrls[position]*/, 
					holder.image, options, animateFirstListener);

			return view;
		}
	}

	private static class AnimateFirstDisplayListener extends SimpleImageLoadingListener
	{

		static final List<String> displayedImages = Collections
				.synchronizedList(new LinkedList<String>());

		@Override
		public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage)
		{
			if (loadedImage != null)
			{
				ImageView imageView = (ImageView) view;
				boolean firstDisplay = !displayedImages.contains(imageUri);
				if (firstDisplay)
				{
					FadeInBitmapDisplayer.animate(imageView, 500);
					displayedImages.add(imageUri);
				}
			}
		}
	}

}
