package ir.chocolategroup.majiddelbandam;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.Toast;
import io.nivad.iab.BillingProcessor;
import io.nivad.iab.TransactionDetails;
import android.graphics.drawable.ColorDrawable;
/**
 * Created by mohammad hosein on 20/03/2016.
 */

public class PaymentActivity extends Activity implements BillingProcessor.IBillingHandler {
    private BillingProcessor mNivadBilling;
    private WaitingDialog waitingDialog;
    private GameManager gameManager;

    private final static String SKU_100_Coins = "100";
    private final static String SKU_200_Coins = "200";
    private final static String SKU_500_Coins = "500";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.payment_fragment);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        waitingDialog = new WaitingDialog(this);
        waitingDialog.show();

        mNivadBilling = new BillingProcessor(this, "Bazaar RSA Key", "Nivad Application ID", "Nivad Application Secret", this);

        LinearLayout buy_100 = (LinearLayout)findViewById(R.id.buy_100);
        LinearLayout buy_200 = (LinearLayout)findViewById(R.id.buy_200);
        LinearLayout buy_500 = (LinearLayout)findViewById(R.id.buy_500);

        View.OnClickListener onClickListener =  new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(v.getId())
                {
                    case R.id.buy_100:
                        purchase(SKU_100_Coins);
                        break;
                    case R.id.buy_200:
                        purchase(SKU_200_Coins);
                        break;
                    case R.id.buy_500:
                        purchase(SKU_500_Coins);
                        break;


                }
            }
        };

        buy_100.setOnClickListener(onClickListener);
        buy_200.setOnClickListener(onClickListener);
        buy_500.setOnClickListener(onClickListener);
    }

    private void processPurchase(String productId)
    {
        int addedCoins = 0;
        switch (productId)
        {
            case SKU_100_Coins:
                addedCoins = 100;
                break;
            case SKU_200_Coins:
                addedCoins = 200;
                break;
            case SKU_500_Coins:
                addedCoins = 500;
                break;

        }

        gameManager.addCoins(addedCoins);
    }

    @Override
    public void onProductPurchased(String productId, TransactionDetails transactionDetails) {
        if(mNivadBilling.consumePurchase(productId))
        {
            processPurchase(productId);
        }
        waitingDialog.dismiss();
    }

    @Override
    public void onPurchaseHistoryRestored() {
        for(String productId : mNivadBilling.listOwnedProducts()) {
            if(mNivadBilling.consumePurchase(productId))
            {
                processPurchase(productId);
            }
        }
    }

    @Override
    public void onBillingError(int code, Throwable throwable) {
        if(waitingDialog != null)
            waitingDialog.dismiss();
        String message = "ERROR";
        //TODO : ???? ????? ????? ???

        Toast.makeText(getApplicationContext(),message , Toast.LENGTH_LONG)
                .show();
        finish();
    }

    @Override
    public void onBillingInitialized() {
        waitingDialog.dismiss();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!mNivadBilling.handleActivityResult(requestCode, resultCode, data))
            super.onActivityResult(requestCode, resultCode, data);
	}
    @Override
    public void onDestroy() {
        if (mNivadBilling != null)
            mNivadBilling.release();
        super.onDestroy();
    }

    private void purchase(String SKU)
    {
        if(waitingDialog != null)
            waitingDialog.dismiss();
        waitingDialog = new WaitingDialog(this);
        waitingDialog.show();
        mNivadBilling.purchase(this,SKU);
    }
}
