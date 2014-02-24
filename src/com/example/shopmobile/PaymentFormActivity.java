package com.example.shopmobile;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
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
//	private EditText cardExpirationYear = null;
//	private EditText cardExpirationMonth = null;	
	private Spinner cardExpirationYear = null;
	private Spinner cardExpirationMonth = null;
	private EditText cardCVC = null;
	private EditText userMailingAddress = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_paymentform);
		
		// Hack : To fix the NetworkOnMainThreadException error.
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	    StrictMode.setThreadPolicy(policy);
	    
		// Retrieve the search parameters passed
		Bundle extras = getIntent().getExtras();
//		itemToCheckout = (Item)extras.get(Constants.ITEM_TO_CHECKOUT);
		itemToCheckout = (Item) getIntent().getSerializableExtra(Constants.ITEM_TO_CHECKOUT);
		
		setUIControls();
	}
	
	private void setUIControls()
	{
		cardNumber = (EditText)findViewById(R.id.card_number_value);
//		cardExpirationYear = (EditText)findViewById(R.id.card_expiry_date_year_value);
//		cardExpirationMonth = (EditText)findViewById(R.id.card_expiry_date_month_value);	
		cardExpirationYear = (Spinner)findViewById(R.id.card_expiry_date_year_value);
		cardExpirationMonth = (Spinner)findViewById(R.id.card_expiry_date_month_value);
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
				int price = (int)Double.parseDouble((itemToCheckout.getAttrValue(Constants.ITEM_PRICE).toString()));
				
				long cardNo = Long.parseLong(cardNumber.getText().toString());
//				int cardExpYear = Integer.parseInt(cardExpirationYear.getText().toString());
//				int cardExpMonth = Integer.parseInt(cardExpirationMonth.getText().toString());
				int cardExpYear = Integer.parseInt(cardExpirationYear.getSelectedItem().toString());
				int cardExpMonth = Integer.parseInt(cardExpirationMonth.getSelectedItem().toString());
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
				
				if(isDebitSuccess)
				{
					AlertDialog.Builder builder = new AlertDialog.Builder(PaymentFormActivity.this);
					builder.setMessage("Transaction Successful! Your item \"" + name + "\" will be shipped to \""
		       				+ address + "\"")
				       		.setTitle("Congratulations! And, thanks for your purchase!")
							.setNegativeButton("Ok", new DialogInterface.OnClickListener() 
							{
							   public void onClick(DialogInterface dialog, int id) 
							   {
									finish();
							   }
					       })
			 				.create()
		 					.show();	
				}
				else
				{
					AlertDialog.Builder builder = new AlertDialog.Builder(this /* Activity Context */);
					builder.setMessage("Please retry with valid information.")
							.setCancelable(true)
				       		.setTitle("Transaction Failed!")
							.setNegativeButton("Ok", new DialogInterface.OnClickListener() 
							{
							   public void onClick(DialogInterface dialog, int id) 
							   {
									dialog.cancel();
							   }
					       })
			 				.create()
		 					.show();	
				}
				
//				Toast.makeText(getApplicationContext(), "Debit Status : " + isDebitSuccess, Toast.LENGTH_LONG).show();
			break;
		}		
	}
}
