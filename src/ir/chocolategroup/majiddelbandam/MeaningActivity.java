package ir.chocolategroup.majiddelbandam;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by majid on 07/05/2016.
 */
public class MeaningActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meaning);
        String mean = (String) getIntent().getExtras().get("mean");
        TextView meanView = (TextView) findViewById(R.id.meanView);
        meanView.setText(mean);
    }
}