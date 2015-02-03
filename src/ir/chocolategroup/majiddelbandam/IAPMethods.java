
/*package ir.chocolategroup.majiddelbandam;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Toast;
import ir.chocolategroup.billing.IabHelper;
import ir.chocolategroup.billing.IabResult;
import ir.chocolategroup.billing.Inventory;
import ir.chocolategroup.billing.MarketName;
import ir.chocolategroup.billing.Purchase;

public class IAPMethods {
	public static final MarketName mMarket = MarketName.CAFFE_BAZZAR;
	
	protected static final String SKU_GEM_1 = "gem1";
	protected static final String SKU_GEM_5 = "gem5";
	protected static final String SKU_GEM_10 = "gem10";
	protected static final String SKU_GEM_50 = "gem50";
	protected static final String SKU_GEM_100 = "gem100";
	protected static final String SKU_GEM_10_new = "gem10-new";
	protected static final String SKU_GEM_50_new = "gem50-new";
	protected static final String SKU_GEM_100_new = "gem100-new";
	private static final String p = "alsdkfjlasdjfpsou09adsfu";
	private static final int RC_REQUEST = 0x111;
	
	protected IabHelper mIap;
	
	private Activity mContext;
	
	private SharedPreferences mSharedPreferences;
	private SharedPreferences.Editor mPrefsEditor;

	
	public IAPMethods(Activity context) {
		mContext = context;
		mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
		mPrefsEditor = mSharedPreferences.edit();
	}
	
	public void setup() {
		try {
			//
			mIap = new IabHelper(mContext, getKey(), mMarket);
			// mWaitingDialog.show();
			mIap.startSetup(new IabHelper.OnIabSetupFinishedListener() {
				public void onIabSetupFinished(IabResult result) {
					if (!result.isSuccess()) {
						if (result.getResponse() == IabHelper.BILLING_RESPONSE_RESULT_BILLING_UNAVAILABLE) {
							// complain("نسخه‌ی بازار/مایکت شما از پرداخت درون برنامه‌ای پشتیبانی نمی‌کند :(");
							mContext.runOnUiThread(new Runnable() {
								@Override
								public void run() {
									//TOOD: start prgressbar
//									MainMenu.getInstance().prgProgress.setVisibility(View.GONE);
//									MainMenu.getInstance().btnBuy.setVisibility(View.GONE);
//									MainMenu.getInstance().mWaitingDialog.dismiss();
								}
							});
						} else {
							//TODO: error
							//MainMenu.getInstance().complain("Problem setting up in-app billing: " + result);
						}
						return;
					}
					mContext.runOnUiThread(new Runnable() {
						@Override
						public void run() {
=======
//package ir.chocolategroup.majiddelbandam;
//
//import java.io.PrintWriter;
//import java.io.StringWriter;
//import java.io.Writer;
//
//import android.app.Activity;
//import android.app.AlertDialog;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.net.Uri;
//import android.os.Handler;
//import android.preference.PreferenceManager;
//import android.view.View;
//import android.widget.Toast;
//import ir.chocolategroup.billing.IabHelper;
//import ir.chocolategroup.billing.IabResult;
//import ir.chocolategroup.billing.Inventory;
//import ir.chocolategroup.billing.MarketName;
//import ir.chocolategroup.billing.Purchase;
//
//public class IAPMethods {
//	public static final MarketName mMarket = MarketName.CAFFE_BAZZAR;
//	
//	protected static final String SKU_GEM_1 = "gem1";
//	protected static final String SKU_GEM_5 = "gem5";
//	protected static final String SKU_GEM_10 = "gem10";
//	protected static final String SKU_GEM_50 = "gem50";
//	protected static final String SKU_GEM_100 = "gem100";
//	protected static final String SKU_GEM_10_new = "gem10-new";
//	protected static final String SKU_GEM_50_new = "gem50-new";
//	protected static final String SKU_GEM_100_new = "gem100-new";
//	private static final String p = ye string e random;
//	private static final int RC_REQUEST = 0x111;
//	
//	protected IabHelper mIap;
//	
//	private Activity mContext;
//	
//	private SharedPreferences mSharedPreferences;
//	private SharedPreferences.Editor mPrefsEditor;
//
//	
//	public IAPMethods(Activity context) {
//		mContext = context;
//		mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
//		mPrefsEditor = mSharedPreferences.edit();
//	}
//	
//	public void setup() {
//		try {
//			//
//			mIap = new IabHelper(mContext, getKey(), mMarket);
//			// mWaitingDialog.show();
//			mIap.startSetup(new IabHelper.OnIabSetupFinishedListener() {
//				public void onIabSetupFinished(IabResult result) {
//					if (!result.isSuccess()) {
//						if (result.getResponse() == IabHelper.BILLING_RESPONSE_RESULT_BILLING_UNAVAILABLE) {
//							// complain("نسخه‌ی بازار/مایکت شما از پرداخت درون برنامه‌ای پشتیبانی نمی‌کند :(");
//							mContext.runOnUiThread(new Runnable() {
//								@Override
//								public void run() {
//									MainMenu.getInstance().prgProgress.setVisibility(View.GONE);
//									MainMenu.getInstance().btnBuy.setVisibility(View.GONE);
//									MainMenu.getInstance().mWaitingDialog.dismiss();
//								}
//							});
//						} else {
//							MainMenu.getInstance().complain("Problem setting up in-app billing: " + result);
//						}
//						return;
//					}
//					mContext.runOnUiThread(new Runnable() {
//						@Override
//						public void run() {
>>>>>>> 388cc9c08e53ab040a4143b00ef92965cda0be15
//							if (mIap == null) {
//								MainMenu.getInstance().mWaitingDialog.dismiss();
//								return;
//							}
<<<<<<< HEAD
						}
					});
					mIap.queryInventoryAsync(mGotInventoryListener);
				}
			});
		} catch (Exception e) {
			final Exception exception = e;
			showBugReportDialog(exception);
		}
	}
	

	private void showBugReportDialog(final Exception exception) {
		final AlertDialog alert = new AlertDialog.Builder(mContext)
				.setMessage(mContext.getResources().getString(R.string.bug_report))
				.setPositiveButton(R.string.yes_send_report, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Writer writer = new StringWriter();
						PrintWriter printWriter = new PrintWriter(writer);
						exception.printStackTrace(printWriter);
						String s = writer.toString();
						
						Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
					            "mailto","bugreport@chocolategroup.ir", null));
						intent.putExtra(Intent.EXTRA_EMAIL, "bugreport@chocolategroup.ir");
						intent.putExtra(Intent.EXTRA_SUBJECT, "کدومه - bugreport");
						intent.putExtra(Intent.EXTRA_TEXT, s);

						mContext.startActivity(Intent.createChooser(intent, "باگ :'("));

					}
				}).setNeutralButton(R.string.later_report, new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
					}
				}).setNegativeButton(R.string.never_report, new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						 mPrefsEditor.putBoolean("show_bug_reports", false);
						 mPrefsEditor.commit();
					}
				}).create();

		if (mSharedPreferences.getBoolean("show_bug_reports", true)) {
			new Handler().postDelayed(new Runnable() {
				@Override
				public void run() {
					alert.show();
				}
			}, 2000);
		}
	}

	private final IabHelper.QueryInventoryFinishedListener mGotInventoryListener = new IabHelper.QueryInventoryFinishedListener() {
		@Override
		public void onQueryInventoryFinished(IabResult result, Inventory inv) {
			if (mIap == null) {
//				MainMenu.getInstance().mWaitingDialog.dismiss();
				return;
			}

//			MainMenu.getInstance().btnBuy.setEnabled(true);
//			MainMenu.getInstance().prgProgress.setVisibility(View.GONE);

			if (result.isFailure()) {
				//MainMenu.getInstance().mWaitingDialog.dismiss();
//				MainMenu.getInstance().complain("برای هماهنگ شدن خرید‌های درون برنامه‌ای با سرور اتصال اینترنت خودرا چک کنید.");
				return;
			}

			handlePurchase(inv, SKU_GEM_1);
			handlePurchase(inv, SKU_GEM_5);
			handlePurchase(inv, SKU_GEM_10);
			handlePurchase(inv, SKU_GEM_50);
			handlePurchase(inv, SKU_GEM_100);
			handlePurchase(inv, SKU_GEM_10_new);
			handlePurchase(inv, SKU_GEM_50_new);
			handlePurchase(inv, SKU_GEM_100_new);

		 	//MainMenu.getInstance().mWaitingDialog.dismiss();
		}

		private void handlePurchase(Inventory inv, String p) {//delete
			Purchase gemPurchase = inv.getPurchase(p);
			if (gemPurchase != null && verifyDeveloperPayload(gemPurchase)) {
				mIap.consumeAsync(gemPurchase, mConsumeFinishedListener);
				return;
			}
		}
	};
	

	private final IabHelper.OnConsumeFinishedListener mConsumeFinishedListener = new IabHelper.OnConsumeFinishedListener() {
		public void onConsumeFinished(Purchase purchase, IabResult result) {
			try {
				if (mIap == null) {
					MainMenu.getInstance().mWaitingDialog.dismiss();
					return;
				}

				if (result.isSuccess()) {
					int increase = 0;
					if (SKU_GEM_1.equalsIgnoreCase(purchase.getSku())) {
						increase = 1;
					} else if (SKU_GEM_5.equalsIgnoreCase(purchase.getSku())) {
						increase = 5;
					} else if (SKU_GEM_10.equalsIgnoreCase(purchase.getSku()) || SKU_GEM_10_new.equalsIgnoreCase(purchase.getSku())) {
						increase = 10;
					} else if (SKU_GEM_50.equalsIgnoreCase(purchase.getSku()) || SKU_GEM_50_new.equalsIgnoreCase(purchase.getSku())) {
						increase = 50;
					} else if (SKU_GEM_100.equalsIgnoreCase(purchase.getSku()) || SKU_GEM_100_new.equalsIgnoreCase(purchase.getSku())) {
						increase = 100;
					}
					MainMenu.getInstance().addGems(increase);
				} else {
					MainMenu.getInstance().complain("Error while consuming: " + result);
					MainMenu.getInstance().mWaitingDialog.dismiss();
				}
			} finally {
				MainMenu.getInstance().mWaitingDialog.dismiss();
			}
		}
	};

	private final IabHelper.OnIabPurchaseFinishedListener mPurchaseFinishedListener = new IabHelper.OnIabPurchaseFinishedListener() {

		@Override
		public void onIabPurchaseFinished(IabResult result, Purchase purchase) {
			if (mIap == null)
				return;

			if (result.isFailure()) {
				MainMenu.getInstance().complain("Error purchasing: " + result);
				MainMenu.getInstance().mWaitingDialog.dismiss();
				return;
			}
			if (!verifyDeveloperPayload(purchase)) {
				MainMenu.getInstance().complain("Error purchasing. Authenticity verification failed.");
				MainMenu.getInstance().mWaitingDialog.dismiss();
				return;
			}

			if (purchase.getSku().startsWith("gem")) {
				Toast.makeText(mContext, R.string.bought, Toast.LENGTH_SHORT).show();
				mIap.consumeAsync(purchase, mConsumeFinishedListener);
			}
		}
	};

	private boolean verifyDeveloperPayload(String payload) {
		return p.contains(payload);
	}

	private boolean verifyDeveloperPayload(Purchase purchase) {
		String returned = purchase.getDeveloperPayload();
		return verifyDeveloperPayload(returned);
	}

	private String getPayload() {
		int max = p.length() - 1;
		int start = (int) (max * Math.random() / 2);
		int length = (int) (Math.random() * (max - start - 2)) + 2;
		return p.substring(start, start + length);
	}
	

	private String getKey() {
		if (mMarket == MarketName.CAFFE_BAZZAR)
			return az site e cafe bazar;
		else if (mMarket == MarketName.MYKET)
			return az site e myket;
		else
			return "";
	}

	public void startBuy(String type) {
		try {
			mIap.launchPurchaseFlow(mContext, type, RC_REQUEST, mPurchaseFinishedListener, getPayload());
		} catch (Exception e) {
		    MainMenu.getInstance().complain(mContext.getResources().getString(R.string.doing_some_shit));
		}
	}
}
*/
=======
//						}
//					});
//					mIap.queryInventoryAsync(mGotInventoryListener);
//				}
//			});
//		} catch (Exception e) {
//			final Exception exception = e;
//			showBugReportDialog(exception);
//		}
//	}
//	
//
//	private void showBugReportDialog(final Exception exception) {
//		final AlertDialog alert = new AlertDialog.Builder(mContext)
//				.setMessage(mContext.getResources().getString(R.string.bug_report))
//				.setPositiveButton(R.string.yes_send_report, new DialogInterface.OnClickListener() {
//					@Override
//					public void onClick(DialogInterface dialog, int which) {
//						Writer writer = new StringWriter();
//						PrintWriter printWriter = new PrintWriter(writer);
//						exception.printStackTrace(printWriter);
//						String s = writer.toString();
//						
//						Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
//					            "mailto","bugreport@chocolategroup.ir", null));
//						intent.putExtra(Intent.EXTRA_EMAIL, "bugreport@chocolategroup.ir");
//						intent.putExtra(Intent.EXTRA_SUBJECT, "کدومه - bugreport");
//						intent.putExtra(Intent.EXTRA_TEXT, s);
//
//						mContext.startActivity(Intent.createChooser(intent, "باگ :'("));
//
//					}
//				}).setNeutralButton(R.string.later_report, new DialogInterface.OnClickListener() {
//
//					@Override
//					public void onClick(DialogInterface dialog, int which) {
//					}
//				}).setNegativeButton(R.string.never_report, new DialogInterface.OnClickListener() {
//
//					@Override
//					public void onClick(DialogInterface dialog, int which) {
//						 mPrefsEditor.putBoolean("show_bug_reports", false);
//						 mPrefsEditor.commit();
//					}
//				}).create();
//
//		if (mSharedPreferences.getBoolean("show_bug_reports", true)) {
//			new Handler().postDelayed(new Runnable() {
//				@Override
//				public void run() {
//					alert.show();
//				}
//			}, 2000);
//		}
//	}
//
//	private final IabHelper.QueryInventoryFinishedListener mGotInventoryListener = new IabHelper.QueryInventoryFinishedListener() {
//		@Override
//		public void onQueryInventoryFinished(IabResult result, Inventory inv) {
//			if (mIap == null) {
//				MainMenu.getInstance().mWaitingDialog.dismiss();
//				return;
//			}
//
//			MainMenu.getInstance().btnBuy.setEnabled(true);
//			MainMenu.getInstance().prgProgress.setVisibility(View.GONE);
//
//			if (result.isFailure()) {
//				MainMenu.getInstance().mWaitingDialog.dismiss();
////				MainMenu.getInstance().complain("برای هماهنگ شدن خرید‌های درون برنامه‌ای با سرور اتصال اینترنت خودرا چک کنید.");
//				return;
//			}
//
//			handlePurchase(inv, SKU_GEM_1);
//			handlePurchase(inv, SKU_GEM_5);
//			handlePurchase(inv, SKU_GEM_10);
//			handlePurchase(inv, SKU_GEM_50);
//			handlePurchase(inv, SKU_GEM_100);
//			handlePurchase(inv, SKU_GEM_10_new);
//			handlePurchase(inv, SKU_GEM_50_new);
//			handlePurchase(inv, SKU_GEM_100_new);
//
//		 	MainMenu.getInstance().mWaitingDialog.dismiss();
//		}
//
//		private void handlePurchase(Inventory inv, String p) {
//			Purchase gemPurchase = inv.getPurchase(p);
//			if (gemPurchase != null && verifyDeveloperPayload(gemPurchase)) {
//				mIap.consumeAsync(gemPurchase, mConsumeFinishedListener);
//				return;
//			}
//		}
//	};
//	
//
//	private final IabHelper.OnConsumeFinishedListener mConsumeFinishedListener = new IabHelper.OnConsumeFinishedListener() {
//		public void onConsumeFinished(Purchase purchase, IabResult result) {
//			try {
//				if (mIap == null) {
//					MainMenu.getInstance().mWaitingDialog.dismiss();
//					return;
//				}
//
//				if (result.isSuccess()) {
//					int increase = 0;
//					if (SKU_GEM_1.equalsIgnoreCase(purchase.getSku())) {
//						increase = 1;
//					} else if (SKU_GEM_5.equalsIgnoreCase(purchase.getSku())) {
//						increase = 5;
//					} else if (SKU_GEM_10.equalsIgnoreCase(purchase.getSku()) || SKU_GEM_10_new.equalsIgnoreCase(purchase.getSku())) {
//						increase = 10;
//					} else if (SKU_GEM_50.equalsIgnoreCase(purchase.getSku()) || SKU_GEM_50_new.equalsIgnoreCase(purchase.getSku())) {
//						increase = 50;
//					} else if (SKU_GEM_100.equalsIgnoreCase(purchase.getSku()) || SKU_GEM_100_new.equalsIgnoreCase(purchase.getSku())) {
//						increase = 100;
//					}
//					MainMenu.getInstance().addGems(increase);
//				} else {
//					MainMenu.getInstance().complain("Error while consuming: " + result);
//					MainMenu.getInstance().mWaitingDialog.dismiss();
//				}
//			} finally {
//				MainMenu.getInstance().mWaitingDialog.dismiss();
//			}
//		}
//	};
//
//	private final IabHelper.OnIabPurchaseFinishedListener mPurchaseFinishedListener = new IabHelper.OnIabPurchaseFinishedListener() {
//
//		@Override
//		public void onIabPurchaseFinished(IabResult result, Purchase purchase) {
//			if (mIap == null)
//				return;
//
//			if (result.isFailure()) {
//				MainMenu.getInstance().complain("Error purchasing: " + result);
//				MainMenu.getInstance().mWaitingDialog.dismiss();
//				return;
//			}
//			if (!verifyDeveloperPayload(purchase)) {
//				MainMenu.getInstance().complain("Error purchasing. Authenticity verification failed.");
//				MainMenu.getInstance().mWaitingDialog.dismiss();
//				return;
//			}
//
//			if (purchase.getSku().startsWith("gem")) {
//				Toast.makeText(mContext, R.string.bought, Toast.LENGTH_SHORT).show();
//				mIap.consumeAsync(purchase, mConsumeFinishedListener);
//			}
//		}
//	};
//
//	private boolean verifyDeveloperPayload(String payload) {
//		return p.contains(payload);
//	}
//
//	private boolean verifyDeveloperPayload(Purchase purchase) {
//		String returned = purchase.getDeveloperPayload();
//		return verifyDeveloperPayload(returned);
//	}
//
//	private String getPayload() {
//		int max = p.length() - 1;
//		int start = (int) (max * Math.random() / 2);
//		int length = (int) (Math.random() * (max - start - 2)) + 2;
//		return p.substring(start, start + length);
//	}
//	
//
//	private String getKey() {
//		if (mMarket == MarketName.CAFFE_BAZZAR)
//			return az site e cafe bazar;
//		else if (mMarket == MarketName.MYKET)
//			return az site e myket;
//		else
//			return "";
//	}
//
//	public void startBuy(String type) {
//		try {
//			mIap.launchPurchaseFlow(mContext, type, RC_REQUEST, mPurchaseFinishedListener, getPayload());
//		} catch (Exception e) {
//		    MainMenu.getInstance().complain(mContext.getResources().getString(R.string.doing_some_shit));
//		}
//	}
//}

