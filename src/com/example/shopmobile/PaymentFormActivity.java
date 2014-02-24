package com.example.shopmobile;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.shopmobile.model.Item;
import com.example.shopmobile.service.StripePaymentService;

/**
 * Renders the payment form for the purchase of the chosen object.
 * 
 * @author excelsior
 *
 */
public class PaymentFormActivity extends Activity
{
	private Item itemToCheckout = null;
	
	private EditText cardNumber = null;
	private EditText cardExpirationYear = null;
	private EditText cardExpirationMonth = null;	
	private EditText cardCVC = null;
	private EditText userMailingAddress = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_paymentform);
		
		// Retrieve the search parameters passed
		Bundle extras = getIntent().getExtras();
//		itemToCheckout = (Item)extras.get(Constants.ITEM_TO_CHECKOUT);
		itemToCheckout = (Item) getIntent().getSerializableExtra(Constants.ITEM_TO_CHECKOUT);
		
		setUIControls();
	}
	
	private void setUIControls()
	{
		cardNumber = (EditText)findViewById(R.id.card_number_value);
		cardExpirationYear = (EditText)findViewById(R.id.card_expiry_date_year_value);
		cardExpirationMonth = (EditText)findViewById(R.id.card_expiry_date_month_value);		
		cardCVC = (EditText)findViewById(R.id.card_cvc_value);
		userMailingAddress = (EditText)findViewById(R.id.user_address_value);
	}
	
	/**
	 * Event handler for "Checkout" button.
	 * @param v
	 */
	public void onButtonClick(View v)
	{
		Log.i("PaymentForm Activity", "Inside payment form event handler ..");
		switch (v.getId())
		{
			case R.id.btn_checkout:
				String name = itemToCheckout.getAttrValue(Constants.ITEM_TITLE).toString();
				int price = Integer.parseInt(itemToCheckout.getAttrValue(Constants.ITEM_PRICE).toString());
				
				long cardNo = Long.parseLong(cardNumber.getText().toString());
				int cardExpYear = Integer.parseInt(cardExpirationYear.getText().toString());
				int cardExpMonth = Integer.parseInt(cardExpirationMonth.getText().toString());
				String cvc = cardCVC.getText().toString();
				
				String address = userMailingAddress.getText().toString();
				
				boolean isDebitSuccess = 
						StripePaymentService.debit(price, Constants.DEFAULT_CURRENCY, cardNo, 
								cardExpMonth, cardExpYear, cvc
						);
				Log.i("PaymentFormActivity", "Debit status : " + isDebitSuccess + " for item " + name);
				if(isDebitSuccess) {
					Log.i("PaymentFormActivity", "Item " + name + " shipped to " + address);
				}
			break;
		}		
	}
}
