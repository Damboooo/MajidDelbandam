package ir.chocolategroup.majiddelbandam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import android.content.Context;
import android.graphics.*;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.*;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.*;

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
	//	int gray;
	int prevGray;
	Drawable prevDrawable;
	ImageView[] chars;
	ImageView[] keysView;
	int[] keys;
	int charI;
	int keyI;
	//	HashMap<Drawable,Character> charMap;
	HashMap<Integer, Character> charMap;
	ArrayList<String> current;
	View linearLayout;
	ImageView guideView;

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
/*		final Button button = (Button) findViewById(R.id.submit);
//		previous = (TextView) findViewById(R.id.start);
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				// TODO Auto-generated method stub

				EditText input = (EditText) findViewById(R.id.current);
				// TODO if word is valid
				AddWordResult res = level.addWord(input.getText().toString());
				if (res.isFinish) {
					createDialogWin(res.prize, res.userMove, res.minMove);
				} else if (res.isValidWord) {
					addWordInGraphic(input.getText().toString(), 7 * (random.nextInt(20) - 10) - 21 * numberOfWords, 8 * (random.nextInt(20) - 10) + 18 * numberOfWords);
					input.setHint(input.getText().toString());
					input.setText("");
					numberOfWords++;
				} else {
					// TO DO پیغام مناسب
					showToast("نه دیگه! باید فقط یه حرفش با حرف قبلی فرق بکنه.");
					input.setText("");
				}
				// end.setText(isValid(start,input));
			}

		});
*/
		//TODO : vibrate sample code
//		Vibrator v = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
//		v.vibrate(50);
	}

	void initialize() {
//		charMap = new HashMap<Drawable,Character>();
//		charMap.put(getResources().getDrawable(R.drawable.a1),'ا');
//		charMap.put(getResources().getDrawable(R.drawable.a2),'ب');
//		charMap.put(getResources().getDrawable(R.drawable.a3),'پ');
//		charMap.put(getResources().getDrawable(R.drawable.a4),'ت');
//		charMap.put(getResources().getDrawable(R.drawable.a5),'ث');
//		charMap.put(getResources().getDrawable(R.drawable.a6),'ج');
//		charMap.put(getResources().getDrawable(R.drawable.a7),'چ');
//		charMap.put(getResources().getDrawable(R.drawable.a8),'ح');
//		charMap.put(getResources().getDrawable(R.drawable.a9),'خ');
//		charMap.put(getResources().getDrawable(R.drawable.a10),'د');
//		charMap.put(getResources().getDrawable(R.drawable.a11),'ذ');
//		charMap.put(getResources().getDrawable(R.drawable.a12),'ر');
//		charMap.put(getResources().getDrawable(R.drawable.a13),'ز');
//		charMap.put(getResources().getDrawable(R.drawable.a14),'ژ');
//		charMap.put(getResources().getDrawable(R.drawable.a15),'س');
//		charMap.put(getResources().getDrawable(R.drawable.a16),'ش');
//		charMap.put(getResources().getDrawable(R.drawable.a17),'ص');
//		charMap.put(getResources().getDrawable(R.drawable.a18),'ض');
//		charMap.put(getResources().getDrawable(R.drawable.a19),'ط');
//		charMap.put(getResources().getDrawable(R.drawable.a20),'ظ');
//		charMap.put(getResources().getDrawable(R.drawable.a21),'ع');
//		charMap.put(getResources().getDrawable(R.drawable.a22),'غ');
//		charMap.put(getResources().getDrawable(R.drawable.a23),'ف');
//		charMap.put(getResources().getDrawable(R.drawable.a24),'ق');
//		charMap.put(getResources().getDrawable(R.drawable.a25),'ک');
//		charMap.put(getResources().getDrawable(R.drawable.a26),'گ');
//		charMap.put(getResources().getDrawable(R.drawable.a27),'ل');
//		charMap.put(getResources().getDrawable(R.drawable.a28),'م');
//		charMap.put(getResources().getDrawable(R.drawable.a29),'ن');
//		charMap.put(getResources().getDrawable(R.drawable.a30),'و');
//		charMap.put(getResources().getDrawable(R.drawable.a31),'ه');
//		charMap.put(getResources().getDrawable(R.drawable.a32),'ی');
		charMap = new HashMap<Integer, Character>();
		charMap.put(1, 'ا');
		charMap.put(2, 'ب');
		charMap.put(3, 'پ');
		charMap.put(4, 'ت');
		charMap.put(5, 'ث');
		charMap.put(6, 'ج');
		charMap.put(7, 'چ');
		charMap.put(8, 'ح');
		charMap.put(9, 'خ');
		charMap.put(10, 'د');
		charMap.put(11, 'ذ');
		charMap.put(12, 'ر');
		charMap.put(13, 'ز');
		charMap.put(14, 'ژ');
		charMap.put(15, 'س');
		charMap.put(16, 'ش');
		charMap.put(17, 'ص');
		charMap.put(18, 'ض');
		charMap.put(19, 'ط');
		charMap.put(20, 'ظ');
		charMap.put(21, 'ع');
		charMap.put(22, 'غ');
		charMap.put(23, 'ف');
		charMap.put(24, 'ق');
		charMap.put(25, 'ک');
		charMap.put(26, 'گ');
		charMap.put(27, 'ل');
		charMap.put(28, 'م');
		charMap.put(29, 'ن');
		charMap.put(30, 'و');
		charMap.put(31, 'ه');
		charMap.put(32, 'ی');
		// keys
		keys = new int[]{
				R.drawable.a1,
				R.drawable.a2,
				R.drawable.a3,
				R.drawable.a4,
				R.drawable.a5,
				R.drawable.a6,
				R.drawable.a7,
				R.drawable.a8,
				R.drawable.a9,
				R.drawable.a10,
				R.drawable.a11,
				R.drawable.a12,
				R.drawable.a13,
				R.drawable.a14,
				R.drawable.a15,
				R.drawable.a16,
				R.drawable.a17,
				R.drawable.a18,
				R.drawable.a19,
				R.drawable.a20,
				R.drawable.a21,
				R.drawable.a22,
				R.drawable.a23,
				R.drawable.a24,
				R.drawable.a25,
				R.drawable.a26,
				R.drawable.a27,
				R.drawable.a28,
				R.drawable.a29,
				R.drawable.a30,
				R.drawable.a31,
				R.drawable.a32
		};
		//

		prevGray = -1;
		prevDrawable = null;
		random = new Random();
		numberOfWords = 0;
		id = 0;
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
		String start = level.getStartWord();
		addWordInGraphic(start, 0, 1);
		addWordInGraphic(level.getEndWord() + "", 1, 0);

		// characters
		chars = new ImageView[start.length()];
		for (charI = 0; charI < start.length(); charI++) {
			Log.e("char " + charI, "-" + start.charAt(charI) + "-");
			Drawable d = getCharImage(start.charAt(start.length() - 1 - charI));

			chars[charI] = new ImageView(LevelActivity.this);
			chars[charI].setImageBitmap(drawableToBitmap(d));

			LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(100, 100);
			chars[charI].setLayoutParams(layoutParams);

			View v = findViewById(R.id.footer);
			((LinearLayout) v).addView(chars[charI], charI);

			chars[charI].setOnClickListener(new OnClickListener() {
				int charI1 = charI;

				@Override
				public void onClick(View view) {
					Log.e("gray", charI1 + "");
					Log.e("prevGray", prevGray + "");
					graying(charI1);
				}
			});
		}
		// keyboard
		current = new ArrayList<String>();
		current.add(level.getStartWord());
		keysView = new ImageView[32];
		keyI = 0;
		for (keyI = 0; keyI < keysView.length; keyI++)
		{
		keysView[keyI] = new ImageView(LevelActivity.this);
		keysView[keyI].setImageDrawable(getResources().getDrawable(keys[keyI]));

		LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(100, 100);
		keysView[keyI].setLayoutParams(layoutParams);
		if(keyI < 8) {
			View v = findViewById(R.id.keyboard1);
			((LinearLayout) v).addView(keysView[keyI], keyI);
		}
		if(keyI >= 8 && keyI < 16) {
			View v = findViewById(R.id.keyboard2);
			((LinearLayout) v).addView(keysView[keyI], keyI-8);
		}
		if(keyI >= 16 && keyI < 24) {
			View v = findViewById(R.id.keyboard3);
			((LinearLayout) v).addView(keysView[keyI], keyI-16);
		}
		if(keyI >= 24 && keyI < 32) {
			View v = findViewById(R.id.keyboard4);
			((LinearLayout) v).addView(keysView[keyI], keyI-24);
		}
		// Animation
		moveAnimation(keysView[keyI], keyI);


		keysView[keyI].setOnClickListener(new OnClickListener() {
			int keyI1 = keyI;

			@Override
			public void onClick(View view) {
				if (prevGray != -1) {
					// word make
					int pprev = current.get(current.size() - 1).length() - prevGray - 1;
					Log.e("char is", "" + charMap.get(keyI1));
					Log.e("word0 is ", current.get(current.size() - 1));
//					Log.e("1 ", current.get(current.size() - 1).substring(0, prevGray));
//					Log.e("2 ", charMap.get(keyI1 + 1)+"");
//					Log.e("3 ", current.get(current.size() - 1).substring(prevGray + 1, current.get(current.size() - 1).length()));
					current.add(current.get(current.size() - 1).substring(0, pprev) + charMap.get(keyI1 + 1) + current.get(current.size() - 1).substring(pprev + 1, current.get(current.size() - 1).length()));

					Log.e("word1 is ", current.get(current.size() - 1));
					boolean added = addWord(current.get(current.size() - 1));
					if(added)
						chars[prevGray].setImageDrawable(keysView[keyI1].getDrawable());
					else
						chars[prevGray].setImageDrawable(prevDrawable);
					prevGray = -1;
					prevDrawable = null;
				}
			}
		});
 	}

	}

	private void moveAnimation(View view,int i) {
		Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate);
		keysView[i].startAnimation(animation);
	}

	boolean addWord(String word){
		// TODO if word is valid
		AddWordResult res = level.addWord(word);
		if (res.isFinish) {
			createDialogWin(res.prize,res.userMove,res.minMove);
		} else if (res.isValidWord) {
			addWordInGraphic(word, 7 * (random.nextInt(20) - 10) - 21 * numberOfWords, 8 * (random.nextInt(20) - 10) + 18*numberOfWords);
			numberOfWords++;
		} else {
			// TO DO پیغام مناسب
			showToast("نه دیگه! باید فقط یه حرفش با حرف قبلی فرق بکنه.");
			return false;
		}
		return true;
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

	private Drawable getCharImage(char c) {
		Drawable d;
		Log.e("In Switch", "-" + (int) c + "-");
		switch (c) {
			case 'ا':
				d = getResources().getDrawable(R.drawable.a1);
				break;
			case 'ب':
				d = getResources().getDrawable(R.drawable.a2);
				break;
			case 'پ':
				d = getResources().getDrawable(R.drawable.a3);
				break;
			case 'ت':
				d = getResources().getDrawable(R.drawable.a4);
				break;
			case 'ث':
				d = getResources().getDrawable(R.drawable.a5);
				break;
			case 'ج':
				d = getResources().getDrawable(R.drawable.a6);
				break;
			case 'چ':
				d = getResources().getDrawable(R.drawable.a7);
				break;
			case 'ح':
				d = getResources().getDrawable(R.drawable.a8);
				break;
			case 'خ':
				d = getResources().getDrawable(R.drawable.a9);
				break;
			case 'د':
				d = getResources().getDrawable(R.drawable.a10);
				break;
			case 'ذ':
				d = getResources().getDrawable(R.drawable.a11);
				break;
			case 'ر':
				d = getResources().getDrawable(R.drawable.a12);
				break;
			case 'ز':
				d = getResources().getDrawable(R.drawable.a13);
				break;
			case 'ژ':
				d = getResources().getDrawable(R.drawable.a14);
				break;
			case 'س':
				d = getResources().getDrawable(R.drawable.a15);
				break;
			case 'ش':
				d = getResources().getDrawable(R.drawable.a16);
				break;
			case 'ص':
				d = getResources().getDrawable(R.drawable.a17);
				break;
			case 'ض':
				d = getResources().getDrawable(R.drawable.a18);
				break;
			case 'ط':
				d = getResources().getDrawable(R.drawable.a19);
				break;
			case 'ظ':
				d = getResources().getDrawable(R.drawable.a20);
				break;
			case 'ع':
				d = getResources().getDrawable(R.drawable.a21);
				break;
			case 'غ':
				d = getResources().getDrawable(R.drawable.a22);
				break;
			case 'ف':
				d = getResources().getDrawable(R.drawable.a23);
				break;
			case 'ق':
				d = getResources().getDrawable(R.drawable.a24);
				break;
			case 'ک':
				d = getResources().getDrawable(R.drawable.a25);
				break;
			case 'گ':
				d = getResources().getDrawable(R.drawable.a26);
				break;
			case 'ل':
				d = getResources().getDrawable(R.drawable.a27);
				break;
			case 'م':
				d = getResources().getDrawable(R.drawable.a28);
				break;
			case 'ن':
				d = getResources().getDrawable(R.drawable.a29);
				break;
			case 'و':
				d = getResources().getDrawable(R.drawable.a30);
				break;
			case 'ه':
				d = getResources().getDrawable(R.drawable.a31);
				break;
			case 'ی':
				d = getResources().getDrawable(R.drawable.a32);
				break;
			default:
				d = getResources().getDrawable(R.drawable.a1);
		}
		return d;
	}
	private void addWordInGraphic(String text , int width , int height){
		ImageView wordView = new ImageView(LevelActivity.this);
		Bitmap coin;
		if(numberOfWords <= level.getMinMove())
			coin = BitmapFactory.decodeResource(getResources(), R.drawable.golden_coin);
		else if(numberOfWords < 3*level.getMinMove()/2+2)
			coin = BitmapFactory.decodeResource(getResources(), R.drawable.red_coin);
		else
			coin = BitmapFactory.decodeResource(getResources(), R.drawable.gray_coin);
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
		else if(width == 1 && height == 0)
		{ // end
			Log.e("END", text);

			tempCanvas.drawBitmap(coin, 1 * resPic.getWidth() / 5 + width, resPic.getHeight() / 10 + height, null);
			tempCanvas.drawText(text, 1 * resPic.getWidth() / 5 + 60 + width, resPic.getHeight() / 10 + 120 + height, wordPaint);
			wordView.setImageDrawable(new BitmapDrawable(getResources(), resPic));
//			View levelLayout = findViewById(R.id.relative_scroll);
//			((RelativeLayout)levelLayout).addView(wordView);
//			return;
		}
		else { // others
			tempCanvas.drawBitmap(coin, 3 * resPic.getWidth() / 5 + width, resPic.getHeight() / 10 + height, null);
			tempCanvas.drawText(text, 3 * resPic.getWidth() / 5 + 60 + width, resPic.getHeight() / 10 + 120 + height, wordPaint);
		}

		wordView.setImageDrawable(new BitmapDrawable(getResources(), resPic));
		linearLayout = findViewById(R.id.relativeLayout);
		((RelativeLayout)linearLayout).addView(wordView, numberOfWords);
		if(!(width == 0 && height == 1)) {
			wordView.setOnClickListener(new View.OnClickListener() {
				ImageView wordView1 = wordView;

				@Override
				public void onClick(View view) {
					((RelativeLayout) linearLayout).removeView(wordView1);
					level.delete(current.get(current.size() - 1));
					current.remove(current.size() - 1);
					for (int i = 0; i < current.get(current.size() - 1).length(); i++) {
						Log.e("char in for " + i, "-" + current.get(current.size() - 1).charAt(i) + "-");
						Drawable d = getCharImage(current.get(current.size() - 1).charAt(current.get(current.size() - 1).length() - 1 - i));
						//chars[i] = new ImageView(LevelActivity.this);
						chars[i].setImageBitmap(drawableToBitmap(d));
					}
//				return false;
				}
			});
		}
	}




	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle item selection
		switch (item.getItemId()) {
			case R.id.guide:
//				guideView = (ImageView)findViewById(R.id.guideImage);
//				Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadeo);
//				guideView.startAnimation(animation);
				createDialogGuide();
				return true;
			case R.id.reset:
				Intent level = new Intent(LevelActivity.this,
						LevelActivity.class);
				level.putExtra("levelnumber",levelNumber); // id is
				System.gc();
				finish();
				startActivity(level);
				return true;
			case R.id.coin:

				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}

	private void createDialogGuide() {
		Dialog dialog = new Dialog(LevelActivity.this);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.guide_fragment);
		dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
		LinearLayout linearLayout = (LinearLayout)findViewById(R.id.guide_linear);
		int width = (int)(displayMetrics.widthPixels);
		int height = (int) (width*0.71);
		dialog.getWindow().setLayout(width, height);
//		dialog.setCancelable(false);
//		dialog.setCanceledOnTouchOutside(false);
//		guideView = (ImageView)findViewById(R.id.guide_image);
//		guideView.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View view) {
//				Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadeo);
//				guideView.startAnimation(animation);
//				dialog.dismiss();
//			}
//		});

		dialog.show();
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
				if (level.helpGoToNextLevel()) {
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

	private void createDialogWin(int numberOfCoins,int userMove , int minMove) {
		final Dialog dialog = new Dialog(LevelActivity.this);

		Rect displayRectangle = new Rect();
		Window window = this.getWindow();
		window.getDecorView().getWindowVisibleDisplayFrame(displayRectangle);

		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		LayoutInflater inflater = (LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View layout = inflater.inflate(R.layout.win_fragment, null);
		dialog.setContentView(layout, new LinearLayout.LayoutParams(
				displayRectangle.width(),
				(int)(displayRectangle.width()*0.733)));
		dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
		dialog.setCancelable(false);
		dialog.setCanceledOnTouchOutside(false);

		ImageView menu = (ImageView) dialog.findViewById(R.id.imgMenu);

		ImageView prize = (ImageView) dialog.findViewById(R.id.imgCoin);
		Bitmap basePic =  BitmapFactory.decodeResource(getResources(), R.drawable.coin);
		Bitmap resPic = Bitmap.createBitmap(basePic.getWidth(), basePic.getHeight(), Bitmap.Config.ARGB_8888);
		Canvas tempCanvas = new Canvas(resPic);

		String text = "+" + numberOfCoins;
		Rect bounds = new Rect();
		Paint paint = new Paint();
		paint.setColor(Color.BLACK);


		int pixel= (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
				120, getResources().getDisplayMetrics());
		paint.setTextSize(pixel);
		Log.e("font : ", "" + paint.getTextSize());


		Typeface font = Typeface.createFromAsset(getAssets(),
				"swissko.ttf");
		paint.setTypeface(font);
		paint.getTextBounds(text, 0, text.length(), bounds);
		tempCanvas.drawBitmap(basePic, 0, 0, null);
		tempCanvas.drawText(text, (int) ((resPic.getWidth() - bounds.width()) / 2 * 0.8), resPic.getHeight() / 2 /*+ 30*/ + bounds.height() / 2, paint);
//		tempCanvas.drawLine(resPic.getWidth() / 2 /*- 20*/ - bounds.width() / 2, resPic.getHeight() / 2 /*+ 30*/ - bounds.height() / 2, resPic.getWidth() / 2 + bounds.width() / 2, resPic.getHeight() / 2 + bounds.height() / 2, paint);
		prize.setImageDrawable(new BitmapDrawable(getResources(), resPic));


		ImageView mark = (ImageView) dialog.findViewById(R.id.mark);
		Bitmap line =  BitmapFactory.decodeResource(getResources(), R.drawable.line);
		Bitmap markPic = Bitmap.createBitmap(line.getWidth(), line.getHeight(), Bitmap.Config.ARGB_8888);
		Canvas markCanvas = new Canvas(markPic);
		markCanvas.drawBitmap(line,0,0,null);
//		Bitmap markPic = Bitmap.createBitmap(prize.getWidth(), prize.getHeight(), Bitmap.Config.ARGB_8888);
//		Canvas markCanvas = new Canvas(resPic);
		Paint p =new Paint();
		p.setStrokeWidth(10);
		float textSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
				350, getResources().getDisplayMetrics());
		textSize =((float) (markPic.getHeight()*0.5));
		p.setTextSize(textSize);

		String userMoveText = ""+userMove;
		String minMoveText = ""+minMove;

		p.getTextBounds(userMoveText, 0, userMoveText.length(), bounds);

		markCanvas.drawText(userMoveText, markPic.getWidth() / 2 - bounds.width(), (int) (markPic.getHeight() *0.40), p);

		p.getTextBounds(minMoveText, 0, minMoveText.length(), bounds);
		markCanvas.drawText(minMoveText, (int) (markPic.getWidth() * 0.5), (int) (markPic.getHeight()*0.55 + bounds.height()), p);


//		markCanvas.drawText(userMoveText, (int) (markPic.getWidth() * 0.25), (int) (markPic.getHeight() *0.45), p);
//		markCanvas.drawText(minMoveText,(int)(markPic.getWidth() *0.5), (int)(markPic.getHeight() *0.9), p);
		mark.setImageDrawable(new BitmapDrawable(getResources(), markPic));

		menu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent main = new Intent(LevelActivity.this, MainActivity.class);
				main.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(main);
				finish();
			}
		});
		ImageView again = (ImageView) dialog.findViewById(R.id.imgReset);
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
		ImageView next = (ImageView) dialog.findViewById(R.id.imgNext);
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
	private int counter;
	private void createDialogNextPossibleWord(final String[] words) {
		if (words == null) {
			// TODO : پیام مناسب
			Toast.makeText(this, "سکه کافی ندارید!", Toast.LENGTH_LONG);
			return;
		}
		final Dialog dialog = new Dialog(LevelActivity.this);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.next_possible_words_fragment);

		TableLayout table = (TableLayout) dialog.findViewById(R.id.tblNextWord);

		ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(this,
				R.layout.listview_row, words);
		TableRow row = new TableRow(LevelActivity.this);

		for(counter = 0; counter < words.length;counter++) {
			if (counter % 2 == 0) {
				row = new TableRow(LevelActivity.this);
				table.addView(row);
			}
			Button btn = new Button(LevelActivity.this);
			btn.setText(words[counter]);
			btn.setOnClickListener(new OnClickListener() {
				String word = words[counter];

				@Override
				public void onClick(View v) {
					AddWordResult res = level.addWord(word);
					if (res.isFinish) {
						createDialogWin(res.prize,res.userMove,res.minMove);
					} else
						addWordInGraphic(word, 100, 100);
					// TODO : add view
					// Toast.makeText(getApplicationContext(), pos,
					// Toast.LENGTH_SHORT).show();
					dialog.dismiss();
				}
			});
			row.addView(btn);
		}

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
	public static Bitmap drawableToBitmap (Drawable drawable) {
		Bitmap bitmap = null;

		if (drawable instanceof BitmapDrawable) {
			BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
			if(bitmapDrawable.getBitmap() != null) {
				return bitmapDrawable.getBitmap();
			}
		}

		if(drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
			bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888); // Single color bitmap will be created of 1x1 pixel
		} else {
			bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
		}

		Canvas canvas = new Canvas(bitmap);
		drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
		drawable.draw(canvas);
		return bitmap;
	}

	void graying(int id){
		for (int i = 0; i < chars.length; i++)
		{
			if(i == id)
			{
				if(prevGray != i) {
					if(prevGray != -1) {
						chars[prevGray].setImageDrawable(prevDrawable);
					}
					prevGray = i;
					prevDrawable = chars[i].getDrawable();
					Log.e("Empty",i+"");
					chars[i].setImageDrawable(getResources().getDrawable(R.drawable.empty));
				}
				break;
			}
		}
	}

}
