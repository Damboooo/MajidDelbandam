package ir.chocolategroup.majiddelbandam;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

	private GameManager mGameManager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		final ImageView IM = (ImageView) findViewById(R.id.imageView2);
		IM.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				createDialogPayment();
			}
		});		
		

		final ImageView level1 = (ImageView)findViewById(R.id.level1);
		
		
		mGameManager = (GameManager)getApplication();
		
		
		
		
		for (int id = 0; id < mGameManager.getMetaData().LevelCount; id++) {
			View levelLayout = findViewById(R.id.mainLayout);
			ImageView valueTV = new ImageView(MainActivity.this);
			valueTV.setImageDrawable(getResources().getDrawable(R.drawable.level_image));
	
			MarginLayoutParams marginParams = new MarginLayoutParams(
					level1.getLayoutParams());
			int[] position = findPosition(id);
	//		marginParams.setMargins(left_margin, top_margin, right_margin, bottom_margin);
			marginParams.setMargins(position[0],position[1],position[2],position[3]);
			RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
					marginParams);
			valueTV.setLayoutParams(layoutParams);
			
			((RelativeLayout) levelLayout).addView(valueTV);
			id++;
		}
		
		
		
		
		
		
		
		
		
		
		
//		        handler.postDelayed(runnable, 2000); //for initial delay..
		final Handler handler = new Handler();
		
		level1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				 // Load the ImageView that will host the animation and
				 // set its background to our AnimationDrawable XML resource.
//				 ImageView img = (ImageView)findViewById(R.id.imageView2);
//				 img.setBackgroundResource(R.drawable.spin_animation);
//
//				 // Get the background, which has been compiled to an AnimationDrawable object.
//				 AnimationDrawable frameAnimation = (AnimationDrawable) img.getBackground();

				 // Start the animation (looped playback by default).
//				 frameAnimation.start();
				
				
				handler.postDelayed(new Runnable() {
					@Override
					public void run() {
						level1.setImageResource(R.drawable.i1);
					}
				}, 0);

				handler.postDelayed(new Runnable() {
					@Override
					public void run() {
						level1.setImageResource(R.drawable.i2);
					}
				}, 100);
				handler.postDelayed(new Runnable() {
					@Override
					public void run() {
						level1.setImageResource(R.drawable.i3);
					}
				}, 200);
				handler.postDelayed(new Runnable() {
					@Override
					public void run() {
						level1.setImageResource(R.drawable.i4);
					}
				}, 300);

				handler.postDelayed(new Runnable() {
					@Override
					public void run() {
						level1.setImageResource(R.drawable.i5);
					}
				}, 400);
				handler.postDelayed(new Runnable() {
					@Override
					public void run() {
						level1.setImageResource(R.drawable.i6);
					}
				}, 500);
				handler.postDelayed(new Runnable() {
					@Override
					public void run() {
						level1.setImageResource(R.drawable.i7);
					}
				}, 600);

				handler.postDelayed(new Runnable() {
					@Override
					public void run() {
						level1.setImageResource(R.drawable.i6);
					}
				}, 700);
				handler.postDelayed(new Runnable() {
					@Override
					public void run() {
						level1.setImageResource(R.drawable.i5);
					}
				}, 800);
				handler.postDelayed(new Runnable() {
					@Override
					public void run() {
						level1.setImageResource(R.drawable.i4);
					}
				}, 900);

				handler.postDelayed(new Runnable() {
					@Override
					public void run() {
						level1.setImageResource(R.drawable.i3);
					}
				}, 1000);
				handler.postDelayed(new Runnable() {
					@Override
					public void run() {
						level1.setImageResource(R.drawable.i2);
					}
				}, 1100);
				handler.postDelayed(new Runnable() {
					@Override
					public void run() {
						Intent level = new Intent(MainActivity.this, LevelActivity.class);
						level.putExtra("levelnumber", 1); // 1 is the level number TO DO
						startActivity(level);
					}
				}, 1200);
			
				DrawView drawView = new DrawView(MainActivity.this);
				drawView.setBackgroundColor(Color.DKGRAY);
				setContentView(drawView);
			
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	private void createDialogPayment() {
		final Dialog dialog = new Dialog(MainActivity.this);
		dialog.setContentView(R.layout.payment_fragment);
		Button cancel = (Button) dialog.findViewById(R.id.cancel);
	
		
		cancel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TO DO payment
				dialog.dismiss();
			}
		});
		
		dialog.show();
	}
	int[] findPosition(int id) {
		int[] pos = new int[4];
		pos[0] = 50+(4-id%4)*30;
		pos[1] = 30+(id/4+1)*30;
		pos[2] = (id%4)*30;
		pos[3] = 20;
		return pos;
	}
}
