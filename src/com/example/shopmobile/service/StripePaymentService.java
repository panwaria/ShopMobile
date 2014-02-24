package com.example.shopmobile.service;

import java.util.Map;

import android.util.Log;

import com.example.shopmobile.Constants;
import com.google.common.collect.Maps;
import com.stripe.Stripe;
import com.stripe.exception.APIConnectionException;
import com.stripe.exception.APIException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.model.Charge;

/**
 * Provides functionality to accept payements using Stripe API.
 * 
 * @author excelsior
 *
 */
public class StripePaymentService {
	/**
	 * Debit the amount from the credit card using Stripe API.
	 * 
	 * @return
	 */
	public static final boolean debit(double amount, String currency, long cardNumber, int expMonth, int expYear, String cvc)
	{
		Stripe.apiKey = Constants.STRIPE_KEY;
		
		Map<String, Object> cardParams = Maps.newHashMap();
		cardParams.put(Constants.CARD_NUMBER, cardNumber);
		cardParams.put(Constants.CARD_EXP_MONTH, expMonth);
		cardParams.put(Constants.CARD_EXP_YEAR, expYear);
		cardParams.put(Constants.CARD_CVC, cvc);
		
		Map<String, Object> chargeParams = Maps.newHashMap();
		chargeParams.put(Constants.CHARGE_AMOUNT, amount);
		chargeParams.put(Constants.CHARGE_CURRENCY, currency);
		chargeParams.put(Constants.CHARGE_CARD, cardParams);
		
		boolean isDebitSuccess = true;
		Exception err = null;
		Charge charge = null;
		try {
			charge = Charge.create(chargeParams);
		} catch (AuthenticationException e) {
			isDebitSuccess = false;
			err = e;
		} catch (InvalidRequestException e) {
			isDebitSuccess = false;
			err = e;
		} catch (APIConnectionException e) {
			isDebitSuccess = false;
			err = e;
		} catch (CardException e) {
			isDebitSuccess = false;
			err = e;
		} catch (APIException e) {
			isDebitSuccess = false;
			err = e;
		}
		
		if(isDebitSuccess) {
			Log.i("StripePaymentService", "Credit card debited successfully for " + amount + " " + currency + 
				  "Charge : " + charge.toString());
		}
		else {
			Log.e("StripePaymentService", "Failed to debit credit card for " + amount + " " + currency + 
				  ". Reason : " + err.getStackTrace());
		}
		
		return isDebitSuccess;
	}
	
}
