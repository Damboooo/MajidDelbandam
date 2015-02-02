package ir.chocolategroup.majiddelbandam;

import java.util.Random;

import android.R.layout;
import android.os.Bundle;
import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.text.Layout;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

public class LevelActivity extends Activity {
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
		
		
		final Button button = (Button) findViewById(R.id.submit);
		button.setOnClickListener(new OnClickListener() {
			TextView current = (TextView) findViewById(R.id.start);
			int id = 0;
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				TextView start = (TextView) findViewById(R.id.start);
				start.setText(level.getStartWord());
				
				TextView end = (TextView) findViewById(R.id.end);
				end.setText(level.getEndWord());
				
				EditText input = (EditText) findViewById(R.id.current);
				if (isValid(current, input) == "null") {
					// is invalid
				}
				if (isValid(current, input) != null
						&& isValid(current, input) != "null") {
					
					if(isValid(current, input).equals((String)end.getText()))
						button.setText("Fenito");
						
					View levelLayout = findViewById(R.id.levelLayout);
					TextView valueTV = new TextView(LevelActivity.this);
					valueTV.setText(isValid(current, input)); // set text

					MarginLayoutParams marginParams = new MarginLayoutParams(
							current.getLayoutParams());
					int[] position = findPosition(id);
//					marginParams.setMargins(left_margin, top_margin, right_margin, bottom_margin);
					marginParams.setMargins(position[0],position[1],position[2],position[3]);
					RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
							marginParams);
					valueTV.setLayoutParams(layoutParams);

					((RelativeLayout) levelLayout).addView(valueTV);
					current = valueTV;
					id++;
				}

				// end.setText(isValid(start,input));
			}
		});

	}

	int[] findPosition(int id) {
		int[] pos = new int[4];
		pos[0] = 50+(4-id%4)*30;
		pos[1] = 30+(id/4+1)*30;
		pos[2] = (id%4)*30;
		pos[3] = 20;
		return pos;
	}

	String isValid(TextView tv, EditText et) {
		String first = (String) tv.getText();
		String second = et.getText().toString();
		char[] firstC = first.toCharArray();
		char[] secondC = second.toCharArray();
		if(firstC.length != secondC.length)
			return "null";
		int counter = 0;
		for (int i = 0; i < firstC.length; i++) {
			if (firstC[i] != secondC[i])
				counter++;
		}
		if (counter == 1)
			return second;
		else
			return "null";
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
}
