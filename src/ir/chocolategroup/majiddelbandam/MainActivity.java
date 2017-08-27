package ir.chocolategroup.majiddelbandam;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.*;

public class MainActivity extends FragmentActivity {
	private final int numberOfLevelInOnePage = 6;

	private GameManager mGameManager;
	TextView coins;



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
//		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_main);

		mGameManager = (GameManager) getApplication();

		ScreenLevelPageFragment.init(this, mGameManager.getMetaData());
		ViewPager levelPager = (ViewPager)findViewById(R.id.level_pager);
		LevelPagerAdapter adapter = new LevelPagerAdapter(getSupportFragmentManager(),mGameManager.getMetaData());
		levelPager.setAdapter(adapter);

//		int selecetedPage = (mGameManager.getMetaData().getLastUnlockLevel()-1)/numberOfLevelInOnePage;
		levelPager.setCurrentItem((mGameManager.getMetaData().getLastUnlockLevel()-1)/numberOfLevelInOnePage);

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

//		TableLayout levelLayout = (TableLayout) findViewById(R.id.mainSpace);
//		final ImageView[] levels = new ImageView[(int) mGameManager
//				.getLevelCount()];
//		TableRow row = new TableRow(MainActivity.this);
//		MetaData metaData = mGameManager.getMetaData();
//		for (id = 0; id < (int) metaData.LevelCount; id++) {
//
//			levels[id] = new ImageView(MainActivity.this);
//			// if for lock or unlock levels
//			if (id + 1 <= metaData.getLastUnlockLevel()) {
//				levels[id].setImageDrawable(new BitmapDrawable(getResources(), createPiture(true, doneLevelColor, id + 1, ((double) metaData.minMove[id]) / ((double) metaData.userMove[id]))));
//			} else {
//				levels[id].setImageDrawable(new BitmapDrawable(getResources(), createPiture(false, lockLevelColor, id + 1, 0)));
//			}
//			levels[id].setLayoutParams(new TableRow.LayoutParams(
//					TableRow.LayoutParams.MATCH_PARENT,
//					TableRow.LayoutParams.WRAP_CONTENT
//			));
//			if (id % numberOfLevelInEachRow == 0) {
////				if(id!= 0)
////					levelLayout.addView(row);
//				row = new TableRow(MainActivity.this);
//				row.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.FILL_PARENT, TableLayout.LayoutParams.WRAP_CONTENT, 1.0f));
//				row.setGravity(Gravity.CENTER_HORIZONTAL);
//				levelLayout.addView(row);
//
//			}
//			row.addView(levels[id]);
//
//			// handler.postDelayed(runnable, 2000); //for initial delay..
//			final Handler handler = new Handler();
//
//			levels[id].setOnClickListener(new OnClickListener() {
//				int id1 = id;
//
//				@Override
//				public void onClick(View v) {
//					handler.postDelayed(new Runnable() {
//						@Override
//						public void run() {
//							if (id1 + 1 <= mGameManager.getMetaData().userMove.length) {
//								Intent level = new Intent(MainActivity.this,
//										LevelActivity.class);
//								level.putExtra("levelnumber", id1 + 1); // id is
//								// the
//								// level
//								// number
//								startActivity(level);
//							}
//
//						}
//					}, 1200);
//				}
//			});
//		}
//		levelLayout.addView(row);
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
		Intent payment = new Intent(MainActivity.this,
				PaymentActivity.class);
		startActivity(payment);
	}

	private class LevelPagerAdapter extends FragmentStatePagerAdapter {
		private MetaData metaData;

		public LevelPagerAdapter(FragmentManager fm , MetaData metaData) {
			super(fm);
			this.metaData = metaData;
		}

		@Override
		public Fragment getItem(int i) {
			return ScreenLevelPageFragment.create(i*numberOfLevelInOnePage,(int)Math.min((i+1)*numberOfLevelInOnePage,metaData.LevelCount),4,2);
//			return ScreenLevelPageFragment.create(i*numberOfLevelInOnePage,(int)Math.min((i+1)*numberOfLevelInOnePage,100),3,2);
		}

		@Override
		public int getCount() {
			return (int)Math.ceil(((double)metaData.LevelCount)/ numberOfLevelInOnePage);
//			return (int)Math.ceil(100.0/ numberOfLevelInOnePage);
		}
	}
}
