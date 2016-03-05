package ir.chocolategroup.majiddelbandam;

import android.graphics.*;
import android.graphics.drawable.BitmapDrawable;
import android.media.CamcorderProfile;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.*;
import android.view.View.OnClickListener;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.*;

public class MainActivity extends Activity {
	private static final int numberOfLevelInEachRow = 2;
	private static final int lockLevelColor = Color.RED;
	private static final int notDoneLevelColor = Color.BLUE;
	private static final int doneLevelColor = Color.GREEN;
//	private static final int done2LevelColor = Color.MAGENTA;
//	private static final int done3LevelColor = Color.YELLOW;

	private Bitmap ActiveBitmap;
	private Bitmap DeactiveBitmap;
	private Paint circlePaint;
	private Paint textPaint;

	private int id = 0;
	private GameManager mGameManager;
	TextView coins;

	private void initConst(){
		ActiveBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.level_image);
		DeactiveBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.level_image_bw);
		circlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		circlePaint.setStrokeWidth(20);
		circlePaint.setStyle(Paint.Style.STROKE);

		textPaint = new Paint();
		textPaint.setColor(Color.BLACK);
		textPaint.setTextSize(150);
		Typeface font = Typeface.createFromAsset(getAssets(),
				"swissko.ttf");
		textPaint.setTypeface(font);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
//		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


		setContentView(R.layout.activity_main);
		initConst();

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

		TableLayout levelLayout =(TableLayout) findViewById(R.id.mainSpace);
		View v = findViewById(R.id.scrollView1);
		final ImageView[] levels = new ImageView[(int) mGameManager
				.getLevelCount()];
		TableRow row=new TableRow(MainActivity.this);
		MetaData metaData = mGameManager.getMetaData();
		for (id = 0; id < (int)metaData.LevelCount ; id++) {

			levels[id] = new ImageView(MainActivity.this);
			// if for lock or unlock levels
			if (id + 1 <= metaData.getLastUnlockLevel()) {
				levels[id].setImageDrawable(new BitmapDrawable(getResources(), createPiture(true, doneLevelColor, id + 1, ((double) metaData.minMove[id]) / ((double) metaData.userMove[id]))));
			}
			else {
				levels[id].setImageDrawable(new BitmapDrawable(getResources(), createPiture(false,lockLevelColor,id+1,0)));
			}
			levels[id].setLayoutParams(new TableRow.LayoutParams(
					TableRow.LayoutParams.MATCH_PARENT,
					TableRow.LayoutParams.WRAP_CONTENT
			));
			if(id % numberOfLevelInEachRow == 0) {
//				if(id!= 0)
//					levelLayout.addView(row);
				row = new TableRow(MainActivity.this);
				row.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.FILL_PARENT, TableLayout.LayoutParams.WRAP_CONTENT, 1.0f));
				row.setGravity(Gravity.CENTER_HORIZONTAL);
				levelLayout.addView(row);

			}
			row.addView(levels[id]);

			// handler.postDelayed(runnable, 2000); //for initial delay..
			final Handler handler = new Handler();

			levels[id].setOnClickListener(new OnClickListener() {
				int id1 = id;

				@Override
				public void onClick(View v) {
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
				}
			});
		}
//		levelLayout.addView(row);
	}
//	private int getCircleColor(MetaData metaData ,int  levelId)
//	{
//		if(metaData.userMove[levelId] == Integer.MIN_VALUE)
//			return notDoneLevelColor;
//		int diff  =metaData.userMove[levelId] - metaData.minMove[levelId];
//		if(diff<= 0)
//			return done1LevelColor;
//		if(diff <= 2)
//			return done2LevelColor;
//		return done3LevelColor;
//	}
public View getTableWithAllRowsStretchedView() {
	LinearLayout linearLayout = new LinearLayout(this);
	linearLayout.setOrientation(LinearLayout.VERTICAL);
	linearLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

	TableLayout tableLayout = new TableLayout(this);
	tableLayout.setStretchAllColumns(true);
	tableLayout.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.FILL_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
	//tableLayout.setWeightSum(4);
	tableLayout.setStretchAllColumns(true);//.setColumnShrinkable(4,true);
	tableLayout.setShrinkAllColumns(true);


	for (int i = 0; i < 10; i++) {
		TableRow tableRow = new TableRow(this);
		tableRow.setGravity(Gravity.CENTER);
		tableRow.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.FILL_PARENT, TableLayout.LayoutParams.WRAP_CONTENT, 1.0f));

		for (int j = 0; j < 2; j++) {
//			Button button = new Button(this);
			ImageView im = new ImageView(MainActivity.this);
			final int buttonNumber = (j + i * 2);
			im.setImageDrawable(new BitmapDrawable(getResources(), createPiture(false, lockLevelColor, buttonNumber, 0)));

			im.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

			tableRow.addView(im);
		}
		tableLayout.addView(tableRow);
	}

	linearLayout.addView(tableLayout);
	return linearLayout;
}
	private Bitmap createPiture(boolean isActive,int circleColor,int levelNumber,double arc){
		Bitmap basePic;
		if(isActive)
			basePic = ActiveBitmap;
		else
			basePic = DeactiveBitmap;

		Bitmap resPic = Bitmap.createBitmap(basePic.getWidth()+40,basePic.getHeight()+40,Bitmap.Config.ARGB_8888);
		Canvas tempCanvas = new Canvas(resPic);

		String text = levelNumber + "";
		Rect bounds = new Rect();
		textPaint.getTextBounds(text, 0, text.length(), bounds);
		circlePaint.setColor(circleColor);

		if(isActive)
		{
			RectF rec = new RectF(20,20, resPic.getWidth()-20,resPic.getHeight()-20);
			int deg =(int)(arc * 360);
			tempCanvas.drawArc(rec, 270, deg, false, circlePaint);
			circlePaint.setColor(Color.RED);
			tempCanvas.drawArc(rec, (270+deg)%360, 360-deg, false, circlePaint);
		}
		else{
			tempCanvas.drawCircle(resPic.getWidth() / 2, resPic.getHeight() / 2, resPic.getWidth() / 2 - 20, circlePaint);
		}



		tempCanvas.drawBitmap(basePic, 20, 20, null);
		tempCanvas.drawText(text, resPic.getWidth() / 2 - bounds.width() / 2, resPic.getHeight() / 2 + bounds.height() / 2, textPaint);
		return resPic;
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
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.payment_fragment);


		//dialog.setOnDismissListener();


//		Button cancel = (Button) dialog.findViewById(R.id.cancel);

//		cancel.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				// TODO payment
//				dialog.dismiss();
//			}
//		});

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
