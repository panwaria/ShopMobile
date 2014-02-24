package com.example.shopmobile;


public final class Constants 
{
	// List of attribute names possible in the item
	public static final String ITEM_ID	= "id";
	public static final String ITEM_NAME = "name";
	public static final String ITEM_IMG_URL = "imageURL";
	public static final String ITEM_CATEGORY = "category";
	public static final String ITEM_PRICE = "price";
	
	// Search parameters
	public static final String SEARCH_CATEGORY = "search_category";
	public static final String SEARCH_KEYWORD = "search_keyword";
	
	// Stripe parameters
	public static final String STRIPE_KEY = "sk_test_PpWVbVOturcmtfKqhUdtBOB8";
	
	public static final String CHARGE_AMOUNT = "amount";
	public static final String CHARGE_CURRENCY = "currency";
	public static final String CHARGE_CARD = "card";
	
	public static final String CARD_NUMBER = "number";
	public static final String CARD_EXP_MONTH = "exp_month";
	public static final String CARD_EXP_YEAR = "exp_year";
	public static final String CARD_CVC = "cvc";
	
	public static final Long TEST_CARD_NUMBER = 4242424242424242l;
	public static final int TEST_CARD_EXP_MONTH = 12;
	public static final int TEST_CARD_EXP_YEAR = 2015;
	public static final String TEST_CARD_CVC = "123";
	
	public static final String DEFAULT_CURRENCY = "usd";

	public static class Extra
	{
		public static final String IMAGES = "com.example.shopmobile.IMAGES";
		public static final String IMAGE_POSITION = "ccom.example.shopmobile.IMAGE_POSITION";
	}
	
	public static final String[] IMAGES = new String[] 
	{
		// Light images
		"http://tabletpcssource.com/wp-content/uploads/2011/05/android-logo.png",
		"http://simpozia.com/pages/images/stories/windows-icon.png",
		"http://radiotray.sourceforge.net/radio.png",
		"http://www.bandwidthblog.com/wp-content/uploads/2011/11/twitter-logo.png",
		"http://weloveicons.s3.amazonaws.com/icons/100907_itunes1.png",
		"http://weloveicons.s3.amazonaws.com/icons/100929_applications.png",
		"http://www.idyllicmusic.com/index_files/get_apple-iphone.png",
		"http://www.frenchrevolutionfood.com/wp-content/uploads/2009/04/Twitter-Bird.png",
		"http://3.bp.blogspot.com/-ka5MiRGJ_S4/TdD9OoF6bmI/AAAAAAAAE8k/7ydKtptUtSg/s1600/Google_Sky%2BMaps_Android.png",
		"http://www.desiredsoft.com/images/icon_webhosting.png",
		"http://goodereader.com/apps/wp-content/uploads/downloads/thumbnails/2012/01/hi-256-0-99dda8c730196ab93c67f0659d5b8489abdeb977.png",
		"http://1.bp.blogspot.com/-mlaJ4p_3rBU/TdD9OWxN8II/AAAAAAAAE8U/xyynWwr3_4Q/s1600/antivitus_free.png",
		"http://cdn3.iconfinder.com/data/icons/transformers/computer.png",
		"http://cdn.geekwire.com/wp-content/uploads/2011/04/firefox.png?7794fe",
		"https://ssl.gstatic.com/android/market/com.rovio.angrybirdsseasons/hi-256-9-347dae230614238a639d21508ae492302340b2ba",
		"http://androidblaze.com/wp-content/uploads/2011/12/tablet-pc-256x256.jpg",
		"http://www.theblaze.com/wp-content/uploads/2011/08/Apple.png",
		"http://1.bp.blogspot.com/-y-HQwQ4Kuu0/TdD9_iKIY7I/AAAAAAAAE88/3G4xiclDZD0/s1600/Twitter_Android.png",
		"http://3.bp.blogspot.com/-nAf4IMJGpc8/TdD9OGNUHHI/AAAAAAAAE8E/VM9yU_lIgZ4/s1600/Adobe%2BReader_Android.png",
		"http://cdn.geekwire.com/wp-content/uploads/2011/05/oovoo-android.png?7794fe",
		"http://icons.iconarchive.com/icons/kocco/ndroid/128/android-market-2-icon.png",
		"http://thecustomizewindows.com/wp-content/uploads/2011/11/Nicest-Android-Live-Wallpapers.png",
		"http://c.wrzuta.pl/wm16596/a32f1a47002ab3a949afeb4f",
		"http://macprovid.vo.llnwd.net/o43/hub/media/1090/6882/01_headline_Muse.jpg",
		// Special cases
		"http://cdn.urbanislandz.com/wp-content/uploads/2011/10/MMSposter-large.jpg", // Very large image
		"http://4.bp.blogspot.com/-LEvwF87bbyU/Uicaskm-g6I/AAAAAAAAZ2c/V-WZZAvFg5I/s800/Pesto+Guacamole+500w+0268.jpg", // Image with "Mark has been invalidated" problem
		"file:///sdcard/Universal Image Loader @#&=+-_.,!()~'%20.png", // Image from SD card with encoded symbols
		"assets://Living Things @#&=+-_.,!()~'%20.jpg", // Image from assets
		"drawable://" + R.drawable.ic_launcher, // Image from drawables
		"http://upload.wikimedia.org/wikipedia/ru/b/b6/Как_кот_с_мышами_воевал.png", // Link with UTF-8
		"https://www.eff.org/sites/default/files/chrome150_0.jpg", // Image from HTTPS
		"http://bit.ly/soBiXr", // Redirect link
		"http://img001.us.expono.com/100001/100001-1bc30-2d736f_m.jpg", // EXIF
		"", // Empty link
		"http://wrong.site.com/corruptedLink", // Wrong link
	};

}
