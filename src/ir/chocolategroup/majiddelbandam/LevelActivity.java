package ir.chocolategroup.majiddelbandam;

import java.util.Random;

import android.R.layout;
import android.os.Bundle;
import android.app.Activity;
import android.app.Application;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.text.Layout;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TimePicker;
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
	
	
	private Level level;
	private GameManager mGameManager;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_level);
		// get level from MainActivity
		mGameManager = (GameManager) getApplication();
		Integer levelNumber = (Integer)getIntent().getExtras().get("levelnumber");
		level = mGameManager.getLevel(levelNumber);
		
		// set level details
		
		// start and end properties
		final TextView  start = (TextView) findViewById(R.id.start);
		final TextView  end = (TextView) findViewById(R.id.end);
		
	// TODO read from level.getStartWord() & level.getEndWord()
		start.setText(level.getStartWord());
		end.setText(level.getEndWord());
		
		
		final ImageView IM = (ImageView) findViewById(R.id.imageView2);
		IM.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				createDialogHelp();
			}
		});
		final Button button = (Button) findViewById(R.id.submit);
		button.setOnClickListener(new OnClickListener() {
			TextView previous = (TextView) findViewById(R.id.start);
			int id = 0;
			@Override
			public void onClick(View v) {
				
				
				// TODO Auto-generated method stub
				
				EditText input = (EditText) findViewById(R.id.current);

					if(input.getText().equals((String)end.getText()))
						button.setText("Fenito");
						
					View levelLayout = findViewById(R.id.levelLayout);
					TextView valueTV = new TextView(LevelActivity.this);
					valueTV.setText(input.getText()); // set text

					MarginLayoutParams marginParams = new MarginLayoutParams(
							previous.getLayoutParams());
					int[] position = findPosition(id);
//					marginParams.setMargins(left_margin, top_margin, right_margin, bottom_margin);
					marginParams.setMargins(position[0],position[1],position[2],position[3]);
					RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
							marginParams);
					valueTV.setLayoutParams(layoutParams);

					((RelativeLayout) levelLayout).addView(valueTV);
					previous = valueTV;
					id++;
				// end.setText(isValid(start,input));
			}
		});

	}

	int[] findPosition(int id) {
		int[] pos = new int[4];
		pos[0] = 50+(2-id%2)*50;
		pos[1] = 30+(id/2+1)*30;
		pos[2] = (id%4)*30;
		pos[3] = 20;
		return pos;
	}

//	String isValid(TextView tv, EditText et) {
//		String first = (String) tv.getText();
//		String second = et.getText().toString();
//		char[] firstC = first.toCharArray();
//		char[] secondC = second.toCharArray();
//		if(firstC.length != secondC.length)
//			return "null";
//		int counter = 0;
//		for (int i = 0; i < firstC.length; i++) {
//			if (firstC[i] != secondC[i])
//				counter++;
//		}
//		if (counter == 1)
//			return second;
//		else
//			return "null";
//	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	
	private void createDialogHelp() {
		final Dialog dialog = new Dialog(LevelActivity.this);
		dialog.setContentView(R.layout.help_fragment);
		r1Image = (ImageView) dialog.findViewById(R.id.R1_image);
		r2Image = (ImageView) dialog.findViewById(R.id.R2_image);
		r3Image = (ImageView) dialog.findViewById(R.id.R3_image);
	
		
		r1Image.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TO DO help1
				dialog.dismiss();
			}
		});
		r2Image.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TO DO help2
				dialog.dismiss();
			}
		});
		r3Image.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TO DO help3
				dialog.dismiss();
			}
		});
		
		dialog.show();
	}
}
