package com.example.shopmobile.stripe;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import com.example.shopmobile.R;
import com.stripe.android.Stripe;
import com.stripe.android.TokenCallback;
import com.stripe.android.model.Card;
import com.stripe.android.model.Token;

public class PaymentActivity extends FragmentActivity
{

	/*
	 * You need to set this to your stripe test publishable key.
	 * 
	 * For more info, see https://stripe.com/docs/stripe.js
	 * 
	 * E.g.
	 * 
	 * private static final String publishableKey = "pk_something123456789";
	 */
	public static final String PUBLISHABLE_KEY = "pk_test_UzmMdKbR7x2JnRb9BVApLJnW";

	private ProgressDialogFragment progressFragment;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.stripe_payment_activity);

		progressFragment = ProgressDialogFragment.newInstance(R.string.progressMessage);
	}

	public void saveCreditCard(PaymentForm form)
	{

		Card card = new Card(form.getCardNumber(), form.getExpMonth(), form.getExpYear(),
				form.getCvc());

		boolean validation = card.validateCard();
		if (validation)
		{
			startProgress();
			new Stripe().createToken(card, PUBLISHABLE_KEY, new TokenCallback()
			{
				public void onSuccess(Token token)
				{
					getTokenList().addToList(token);
					finishProgress();
				}

				public void onError(Exception error)
				{
					handleError(error.getLocalizedMessage());
					finishProgress();
				}
			});
		} else
		{
			handleError("You did not enter a valid card");
		}
	}

	private void startProgress()
	{
		progressFragment.show(getSupportFragmentManager(), "progress");
	}

	private void finishProgress()
	{
		progressFragment.dismiss();
	}

	private void handleError(String error)
	{
		DialogFragment fragment = ErrorDialogFragment.newInstance(R.string.validationErrors, error);
		fragment.show(getSupportFragmentManager(), "error");
	}

	private TokenList getTokenList()
	{
		return (TokenList) (getSupportFragmentManager().findFragmentById(R.id.token_list));
	}
}
