package ir.chocolategroup.majiddelbandam;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {

	private GameManager mGameManager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mGameManager = (GameManager)getApplication();
		
		final ImageView level1 = (ImageView)findViewById(R.id.level1);
//		        handler.postDelayed(runnable, 2000); //for initial delay..
		final Handler handler = new Handler();
		
		level1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				handler.postDelayed(new Runnable() {
					@Override
					public void run() {
						level1.setImageResource(R.drawable.level_image_bw);
					}
				}, 0);

				handler.postDelayed(new Runnable() {
					@Override
					public void run() {
						level1.setImageResource(R.drawable.level_image_bw2);
					}
				}, 100);
				handler.postDelayed(new Runnable() {
					@Override
					public void run() {
						level1.setImageResource(R.drawable.level_image_bw3);
					}
				}, 200);
				handler.postDelayed(new Runnable() {
					@Override
					public void run() {
						Intent level = new Intent(MainActivity.this, LevelActivity.class);
						startActivity(level);
					}
				}, 300);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
