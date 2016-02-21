package ir.chocolategroup.majiddelbandam;

import java.util.Random;

import android.graphics.*;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;

public class LevelActivity extends Activity {

	TextView username;
	TextView password;
	Button save;
	ImageView r1Image;
	ImageView r2Image;
	ImageView r3Image;
	String usernameString, passwordString;

	TextView phoneNumber;
	TimePicker time;
	DatePicker date;
	Button call;
	Button BTNCanceltime;
	String phoneString, dateString, timeString;

	ImageView endImage;
	TextView end;
	TextView coins;
	TextView previous;

	int id = 0;

	private Level level;
	private GameManager mGameManager;
	Integer levelNumber;

	private Paint wordPaint;
	DisplayMetrics displayMetrics;
	float screenHeightInDp;
	float screenWidthInDp;
	int screenHeight;
	int screenWidth;
	Random random;
	int numberOfWords;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_level);
		initialize();

		// TODO read from level.getStartWord() & level.getEndWord()

		final ImageView IM = (ImageView) findViewById(R.id.imageView2);
		IM.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				createDialogHelp();
			}
		});
		final Button button = (Button) findViewById(R.id.submit);
//		previous = (TextView) findViewById(R.id.start);
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				// TODO Auto-generated method stub

				EditText input = (EditText) findViewById(R.id.current);
				// TODO if word is valid
				AddWordResult res = level.addWord(input.getText().toString());
				if (res.isFinish) {
					createDialogWin(res.prize);
				} else if (res.isValidWord) {
					addWordInGraphic(input.getText().toString(),7*(random.nextInt(20)-10)-21*numberOfWords,8*(random.nextInt(20)-10)+18*numberOfWords);
					input.setHint(input.getText().toString());
					input.setText("");
					numberOfWords++;
				} else
					// TO DO پیغام مناسب
					showToast("نه دیگه! باید فقط یه حرفش با حرف قبلی فرق بکنه.");
					input.setText("");
				// end.setText(isValid(start,input));
			}

		});
	}
	void initialize()
	{
		random = new Random();
		numberOfWords = 0;
		// screen size
		displayMetrics = getResources()
				.getDisplayMetrics();
		screenWidthInDp = displayMetrics.widthPixels
				/ displayMetrics.density;
		screenHeightInDp = displayMetrics.heightPixels
				/ displayMetrics.density;
		screenWidth = Math.round(screenWidthInDp);
		screenHeight = Math.round(screenHeightInDp);
		// get level from MainActivity
		id = 0;
		mGameManager = (GameManager) getApplication();
		levelNumber = (Integer) getIntent().getExtras().get("levelnumber");
		level = mGameManager.getLevel(levelNumber);
		level.reset();
		// word paint
		wordPaint = new Paint();
		wordPaint.setColor(Color.BLACK);
		wordPaint.setTextSize(80);
		Typeface font = Typeface.createFromAsset(getAssets(),
				"SOGAND.ttf");
		wordPaint.setTypeface(font);
		// level details
		coins = (TextView) findViewById(R.id.numberOfCoins);
		coins.setText(mGameManager.getCoins() + "");
		end = (TextView) findViewById(R.id.end);
		end.setText(level.getEndWord() + "");
//		endImage = (ImageView)findViewById(R.id.endImageView);
//		endImage.setImageDrawable(getResources().getDrawable(R.drawable.goldencoin));

		addWordInGraphic(level.getStartWord(), 0, 1);
//		addWordInGraphic(level.getEndWord(),1,0);

	}
	int[] findPosition(int id) {
		int[] pos = new int[4];
		pos[0] = 50 + (2 - id % 2) * 50;
		pos[1] = 30 + (id / 2 + 1) * 30;
		pos[2] = (id % 4) * 30;
		pos[3] = 20;
		return pos;
	}
	int[] findPosition2(int id) {
		int[] pos = new int[4];
		final DisplayMetrics displayMetrics = getResources()
				.getDisplayMetrics();
		pos[0] = (int)(displayMetrics.widthPixels	/ displayMetrics.density)-10-10*random.nextInt(10);
		pos[1] = 100+10*random.nextInt(10);
		pos[2] = 70;
		pos[3] = 70;
		return pos;
	}

	private void addWordInGraphic(String text , int width , int height){
		ImageView wordView = new ImageView(LevelActivity.this);
		Bitmap coin;
		if(numberOfWords <= level.getMinMove())
			coin = BitmapFactory.decodeResource(getResources(), R.drawable.goldencoin);
		else if(numberOfWords < 3*level.getMinMove()/2+2)
			coin = BitmapFactory.decodeResource(getResources(), R.drawable.redcoin);
		else
			coin = BitmapFactory.decodeResource(getResources(), R.drawable.graycoin);
		coin = getResizedBitmap(coin, 200 ,200);
		Bitmap resPic = Bitmap.createBitmap(displayMetrics.widthPixels, displayMetrics.heightPixels, Bitmap.Config.ARGB_8888);
		Canvas tempCanvas = new Canvas(resPic);

		Rect bounds = new Rect();
		wordPaint.getTextBounds(text, 0, text.length(), bounds);

		if(width == 0 && height == 1)
		{ // start
			tempCanvas.drawBitmap(coin, 4*resPic.getWidth() / 5+width, resPic.getHeight() / 100+height, null);
			tempCanvas.drawText(text, 4*resPic.getWidth() / 5+60+width, resPic.getHeight() / 100+120+height, wordPaint);
		}
//		else if(width == 1 && height == 0)
//		{ // end
//			tempCanvas.drawBitmap(coin, 3*resPic.getWidth() / 50+width, resPic.getHeight() / 10+height, null);
//			tempCanvas.drawText(text, 3*resPic.getWidth() / 50+60+width, resPic.getHeight() / 10+120+height, wordPaint);
//			wordView.setImageDrawable(new BitmapDrawable(getResources(), resPic));
//			View levelLayout = findViewById(R.id.levelLayout);
//			((LinearLayout)levelLayout).addView(wordView);
//			return;
//		}
		else { // others
			tempCanvas.drawBitmap(coin, 3 * resPic.getWidth() / 5 + width, resPic.getHeight() / 10 + height, null);
			tempCanvas.drawText(text, 3 * resPic.getWidth() / 5 + 60 + width, resPic.getHeight() / 10 + 120 + height, wordPaint);
		}

		wordView.setImageDrawable(new BitmapDrawable(getResources(), resPic));
		View linearLayout = findViewById(R.id.relativeLayout);
		((RelativeLayout)linearLayout).addView(wordView, numberOfWords);


	}




	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	private void createDialogHelp() {
		final Dialog dialog = new Dialog(LevelActivity.this);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.help_fragment);
		r1Image = (ImageView) dialog.findViewById(R.id.R1_image);
		r2Image = (ImageView) dialog.findViewById(R.id.R2_image);
		r3Image = (ImageView) dialog.findViewById(R.id.R3_image);

		r1Image.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				showToast(level.helpMidWord());
				dialog.dismiss();
			}
		});
		r2Image.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				createDialogNextPossibleWord(level.helpGetNextPossibleWords());
				dialog.dismiss();
			}
		});
		r3Image.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(level.helpGoToNextLevel())
				{
					Intent levelIntent = new Intent(LevelActivity.this,
							LevelActivity.class);
					levelIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					levelIntent.putExtra("levelnumber", level.getLevelNumber() + 1);
					startActivity(levelIntent);
					finish();
				}
				dialog.dismiss();
			}
		});

		dialog.show();
	}

	private void createDialogWin(int numberOfCoins) {
		final Dialog dialog = new Dialog(LevelActivity.this);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.win_fragment);
		Button menu = (Button) dialog.findViewById(R.id.menu);
		TextView prize = (TextView) dialog.findViewById(R.id.prize);
		prize.setText(numberOfCoins + "");
		menu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent main = new Intent(LevelActivity.this, MainActivity.class);
				main.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(main);
				finish();
			}
		});
		Button again = (Button) dialog.findViewById(R.id.again);
		again.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent levelIntent = new Intent(LevelActivity.this,
						LevelActivity.class);
				levelIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				levelIntent.putExtra("levelnumber", level.getLevelNumber());
				startActivity(levelIntent);
				finish();
			}
		});
		Button next = (Button) dialog.findViewById(R.id.next);
		next.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent levelIntent = new Intent(LevelActivity.this,
						LevelActivity.class);
				levelIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				levelIntent.putExtra("levelnumber", level.getLevelNumber() + 1);
				startActivity(levelIntent);
				finish();
			}
		});

		dialog.show();
	}

	private void createDialogNextPossibleWord(final String[] words) {
		if (words == null) {
			// TODO : پیام مناسب
			Toast.makeText(this, "سکه کافی ندارید!", Toast.LENGTH_LONG);

			// new
			// AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Exit")
			// .setMessage("Are you sure you want to exit?")
			// .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			// @Override
			// public void onClick(DialogInterface dialog, int which) {
			// finish();
			// }
			// }).setNegativeButton("No", null).show();

			return;
		}
		final Dialog dialog = new Dialog(LevelActivity.this);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.next_possible_words_fragment);

		ListView lvNextWords = (ListView) dialog.findViewById(R.id.lvNextWord);

		ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(this,
				R.layout.listview_row, words);

		lvNextWords.setAdapter(listAdapter);

		lvNextWords.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int pos,
					long arg3) {
				AddWordResult res = level.addWord(words[pos]);
				if (res.isFinish) {
					createDialogWin(res.prize);
				} else
					addWordInGraphic(words[pos],100,100);
				// TODO : add view
				// Toast.makeText(getApplicationContext(), pos,
				// Toast.LENGTH_SHORT).show();
				dialog.dismiss();

			}
		});

		dialog.show();
	}

	private void showToast(String message) {

		Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT)
				.show();

		// new AlertDialog.Builder(this)
		// .setIcon(android.R.drawable.ic_dialog_alert)
		// .setTitle("Exit")
		// .setMessage("Are you sure you want to exit?")
		// .setPositiveButton("Yes",
		// new DialogInterface.OnClickListener() {
		// @Override
		// public void onClick(DialogInterface dialog,
		// int which) {
		// finish();
		// }
		// }).setNegativeButton("No", null).show();
	}

	public Bitmap getResizedBitmap(Bitmap bm, int newWidth, int newHeight) {
		int width = bm.getWidth();
		int height = bm.getHeight();
		float scaleWidth = ((float) newWidth) / width;
		float scaleHeight = ((float) newHeight) / height;
		// CREATE A MATRIX FOR THE MANIPULATION
		Matrix matrix = new Matrix();
		// RESIZE THE BIT MAP
		matrix.postScale(scaleWidth, scaleHeight);

		// "RECREATE" THE NEW BITMAP
		Bitmap resizedBitmap = Bitmap.createBitmap(
				bm, 0, 0, width, height, matrix, false);
		bm.recycle();
		return resizedBitmap;
	}

}
