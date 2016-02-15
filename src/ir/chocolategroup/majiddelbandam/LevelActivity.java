package ir.chocolategroup.majiddelbandam;

import java.util.Random;

import android.R.layout;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.Layout;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

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

	TextView start;
	TextView end;
	TextView coins;
	TextView previous;

	int id = 0;

	private Level level;
	private GameManager mGameManager;
	Integer levelNumber;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_level);
		// get level from MainActivity
		id = 0;
		mGameManager = (GameManager) getApplication();
		levelNumber = (Integer) getIntent().getExtras().get("levelnumber");
		level = mGameManager.getLevel(levelNumber);
		level.reset();
		// set level details

		// start and end properties

		// final TextView start = (TextView) findViewById(R.id.start);
		// // final TextView end = (TextView) findViewById(R.id.start);
		// final TextView end = (TextView) findViewById(R.id.end);

		// TODO read from level.getStartWord() & level.getEndWord()

		start = (TextView) findViewById(R.id.start);
		end = (TextView) findViewById(R.id.end);
		coins = (TextView) findViewById(R.id.numberOfCoins);

		start.setText(level.getStartWord());
		end.setText(level.getEndWord());
		coins.setText(mGameManager.getCoins() + "");

		final ImageView IM = (ImageView) findViewById(R.id.imageView2);
		IM.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				createDialogHelp();
			}
		});
		final Button button = (Button) findViewById(R.id.submit);
		previous = (TextView) findViewById(R.id.start);
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
					addWordInGraphic(input.getText().toString());
					input.setHint(input.getText().toString());
					input.setText("");
				} else
					// TO DO پیغام مناسب
					showToast("نه دیگه! باید فقط یه حرفش با حرف قبلی فرق بکنه.");
				input.setText("");
				// end.setText(isValid(start,input));
			}

		});

	}

	int[] findPosition(int id) {
		int[] pos = new int[4];
		pos[0] = 50 + (2 - id % 2) * 50;
		pos[1] = 30 + (id / 2 + 1) * 30;
		pos[2] = (id % 4) * 30;
		pos[3] = 20;
		return pos;
	}

	private void addWordInGraphic(String input) {
		// if (input.equals((String) end.getText()))
		// createDialogWin();

		View levelLayout = findViewById(R.id.linearLayout);
		TextView current = new TextView(LevelActivity.this);
		current.setText(input); // set text

		MarginLayoutParams marginParams = new MarginLayoutParams(
				start.getLayoutParams());
		int[] position = findPosition(id);
		// marginParams.setMargins(left_margin, top_margin,
		// right_margin, bottom_margin);
		// marginParams.setMargins(position[0], position[1], position[2],
		// position[3]);
		final DisplayMetrics displayMetrics = getResources()
				.getDisplayMetrics();
		final float screenWidthInDp = displayMetrics.widthPixels
				/ displayMetrics.density;
		final float screenHeightInDp = displayMetrics.heightPixels
				/ displayMetrics.density;
		final int screenWidth = Math.round(screenWidthInDp);
		final int screenHeight = Math.round(screenHeightInDp);
		previous.getLeft();
		// System.out.printf("id=%d**********************"+id);
		// marginParams.setMargins(100,100,100,100);
		// position[2], position[3]);
		// if (id % 7 == 1)
		if ((id % (screenWidth / 80)) / (screenWidth / 160) == 1)
			marginParams.setMargins(previous.getLeft() + 160, position[1],
					position[2], position[3]);
		else
			marginParams.setMargins(previous.getLeft() - 160, position[1],
					position[2], position[3]);
		RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
				marginParams);

		current.setLayoutParams(layoutParams);

		((RelativeLayout) levelLayout).addView(current);
		// draw Line
		// DrawView drawView = new
		// DrawView(LevelActivity.this,previous.getRight(),current.getRight(),previous.getBottom(),current.getBottom());
		// setContentView(drawView);
		previous = current;
		id++;
	}

	// String isValid(TextView tv, EditText et) {
	// String first = (String) tv.getText();
	// String second = et.getText().toString();
	// char[] firstC = first.toCharArray();
	// char[] secondC = second.toCharArray();
	// if(firstC.length != secondC.length)
	// return "null";
	// int counter = 0;
	// for (int i = 0; i < firstC.length; i++) {
	// if (firstC[i] != secondC[i])
	// counter++;
	// }
	// if (counter == 1)
	// return second;
	// else
	// return "null";
	// }

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
					addWordInGraphic(words[pos]);
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

}
