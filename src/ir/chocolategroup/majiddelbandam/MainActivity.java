package ir.chocolategroup.majiddelbandam;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends Activity {
	private int id = 0;
	private GameManager mGameManager;
	TextView coins;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mGameManager = (GameManager) getApplication();

		coins = (TextView) findViewById(R.id.menuNumberOfCoins);
		coins.setText(mGameManager.getCoins() + "");
		final ImageView IM = (ImageView) findViewById(R.id.imageView2);
		IM.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				createDialogPayment();
			}
		});

		// final ImageView level1 = (ImageView) findViewById(R.id.level1);

		View levelLayout = findViewById(R.id.mainSpace);
		final ImageView[] levels = new ImageView[(int) mGameManager
				.getMetaData().LevelCount];
		for (id = 0; id < (int) mGameManager.getMetaData().LevelCount; id++) {
			levels[id] = new ImageView(MainActivity.this);
			// TODO An if for lock or unlock levels
			if (id + 1 <= mGameManager.getMetaData().userMove.length)
				levels[id].setImageDrawable(getResources().getDrawable(
						R.drawable.level_image));
			else
				levels[id].setImageDrawable(getResources().getDrawable(
						R.drawable.level_image_bw));

			MarginLayoutParams marginParams = new MarginLayoutParams(120, 120);
			// level1.getLayoutParams());
			int[] position = findPosition(id);
			// marginParams.setMargins(left_margin, top_margin, right_margin,
			// bottom_margin);
			
			 final DisplayMetrics displayMetrics=getResources().getDisplayMetrics();
			 final float
			 screenWidthInDp=displayMetrics.widthPixels/displayMetrics.density;
			 final float
			 screenHeightInDp=displayMetrics.heightPixels/displayMetrics.density;
			 final int screenWidth = Math.round(screenWidthInDp);
			 final int screenHeight = Math.round(screenHeightInDp);
			
			if (id != 0) {
//				if (id%6/3 == 1)
					marginParams.setMargins(screenWidth-50 - (id%3)*screenWidth/3, (id%3)*screenHeight/9+(id/3)*screenHeight/4,
							10, screenHeight-100-(id%3)*screenHeight/5);
//				else
//					marginParams.setMargins(screenWidth-50 + (id%3)*50, position[1],
//							position[2], position[3]);
//				if ((id % 6) / 3 == 1)
//					marginParams.setMargins(levels[id - 1].getLeft()-screenWidth/4, screenHeight-levels[id - 1].getTop()+10,
//							levels[id - 1].getRight()-screenWidth/4, screenHeight-levels[id - 1].getBottom()-10);
//				else
//					marginParams.setMargins(position[2], position[1],
//							(int)(levels[id - 1].getRight()+screenWidth/4), position[3]); // levels[id - 1].getLeft()

			} else
				marginParams.setMargins(screenWidth-50, 0, 0,
						screenHeight-100);
			RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
					marginParams);
			levels[id].setLayoutParams(layoutParams);
			((RelativeLayout) levelLayout).addView(levels[id]);

			// handler.postDelayed(runnable, 2000); //for initial delay..
			final Handler handler = new Handler();

			levels[id].setOnClickListener(new OnClickListener() {
				int id1 = id;

				@Override
				public void onClick(View v) {

					// Load the ImageView that will host the animation and
					// set its background to our AnimationDrawable XML resource.
					// ImageView img = (ImageView)findViewById(R.id.imageView2);
					// img.setBackgroundResource(R.drawable.spin_animation);
					//
					// // Get the background, which has been compiled to an
					// AnimationDrawable object.
					// AnimationDrawable frameAnimation = (AnimationDrawable)
					// img.getBackground();

					// Start the animation (looped playback by default).
					// frameAnimation.start();

					// handler.postDelayed(new Runnable() {
					// @Override
					// public void run() {
					// levels[id].setImageResource(R.drawable.i1);
					// }
					// }, 0);
					//
					// handler.postDelayed(new Runnable() {
					// @Override
					// public void run() {
					// levels[id].setImageResource(R.drawable.i2);
					// }
					// }, 100);
					// handler.postDelayed(new Runnable() {
					// @Override
					// public void run() {
					// levels[id].setImageResource(R.drawable.i3);
					// }
					// }, 200);
					// handler.postDelayed(new Runnable() {
					// @Override
					// public void run() {
					// levels[id].setImageResource(R.drawable.i4);
					// }
					// }, 300);
					//
					// handler.postDelayed(new Runnable() {
					// @Override
					// public void run() {
					// levels[id].setImageResource(R.drawable.i5);
					// }
					// }, 400);
					// handler.postDelayed(new Runnable() {
					// @Override
					// public void run() {
					// levels[id].setImageResource(R.drawable.i6);
					// }
					// }, 500);
					// handler.postDelayed(new Runnable() {
					// @Override
					// public void run() {
					// levels[id].setImageResource(R.drawable.i7);
					// }
					// }, 600);
					//
					// handler.postDelayed(new Runnable() {
					// @Override
					// public void run() {
					// levels[id].setImageResource(R.drawable.i6);
					// }
					// }, 700);
					// handler.postDelayed(new Runnable() {
					// @Override
					// public void run() {
					// levels[id].setImageResource(R.drawable.i5);
					// }
					// }, 800);
					// handler.postDelayed(new Runnable() {
					// @Override
					// public void run() {
					// levels[id].setImageResource(R.drawable.i4);
					// }
					// }, 900);
					//
					// handler.postDelayed(new Runnable() {
					// @Override
					// public void run() {
					// levels[id].setImageResource(R.drawable.i3);
					// }
					// }, 1000);
					// handler.postDelayed(new Runnable() {
					// @Override
					// public void run() {
					// levels[id].setImageResource(R.drawable.i2);
					// }
					// }, 1100);
					handler.postDelayed(new Runnable() {
						@Override
						public void run() {
							if (id1 + 1 <= mGameManager.getMetaData().userMove.length) {
								Intent level = new Intent(MainActivity.this,
										LevelActivity.class);
								level.putExtra("levelnumber", id1 + 1); // id is
																		// the
																		// level
																		// number
								startActivity(level);
							}

						}
					}, 1200);

					// DrawView drawView = new DrawView(MainActivity.this);
					// drawView.setBackgroundColor(Color.DKGRAY);
					// setContentView(drawView);

				}
			});
		}
	}

	@Override
	public void onBackPressed() {
		finish();
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
				// TODO payment
				dialog.dismiss();
			}
		});

		dialog.show();
	}

	// int[] findPosition(int id) {
	// int[] pos = new int[4];
	// int c = id % 4 + 1;
	// int c2 = id / 4;
	// switch (c) {
	// case 1:
	// pos[0] = 40;// left
	// pos[1] = 80 + c2 * 200;// top
	// pos[2] = 140;// right
	// break;
	// case 2:
	// pos[0] = 170; // left
	// pos[1] = 90 + c2 * 200;// top
	// pos[2] = 40;// right
	// break;
	// case 3:
	// pos[0] = 30;// left
	// pos[1] = 170 + c2 * 200;// top
	// pos[2] = 130;
	// break;
	// case 4:
	// pos[0] = 140;// left
	// pos[1] = 200 + c2 * 200;// top
	// pos[2] = 50;// right
	// break;
	// }
	// pos[3] = 20; // down
	// return pos;
	// }
	int[] findPosition(int id) {
		int[] pos = new int[4];
		pos[0] = 50 + (2 - id % 2) * 50;
		pos[1] = 30 + (id / 2 + 1) * 30;
		pos[2] = (id % 4) * 30;
		pos[3] = 20;
		return pos;
	}

}
