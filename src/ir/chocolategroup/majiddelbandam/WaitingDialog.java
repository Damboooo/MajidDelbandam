package ir.chocolategroup.majiddelbandam;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;

/**
 * Created by mohammad hosein on 14/03/2016.
 */
public class WaitingDialog {
    private static WaitingDialog instance;
//    public static WaitingDialog getInstance(Context context)
//    {
//        if(instance == null)
//            instance = new WaitingDialog(context);
//        return instance;
//    }
    private Dialog dialog;
    Context context;
    public WaitingDialog(Context context)
    {
        this.context = context;
        dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.waiting_fragment);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
    }
    public void show()
    {
        dialog.show();
    }
    public void dismiss()
    {
        dialog.dismiss();
        dialog = null;
    }
}
