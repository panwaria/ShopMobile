package com.example.shopmobile;

public class Constants {
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
}
