package ir.chocolategroup.majiddelbandam;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LevelActivity2 extends Activity implements View.OnClickListener {
	GameManager mGameManager;
	//int levelNumber;
	private Level level;
	private ImageButton[] currentWordImageButtons;
	private Map<Character,Integer> charToKey;
	private Map<Integer,Character> keyToChar;
	//private ImageButton selectedImageButton;
	private int seletedBox = -1;
	private String currentWord;
	private WordPathAdapter adapter;
	private ListView wordsPath;
	private int screenWidth;

	private static final int help1Cost = 20;
	private static final int help2Cost = 100;
	private static final int help3Cost = 200;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_level2);
		initialize();
	}

	void initialize() {
		mGameManager = (GameManager) getApplication();
		int levelNumber = (Integer) getIntent().getExtras().get("levelnumber");
		level = mGameManager.getLevel(levelNumber);
		initChar_Key();
		initLevelDetails(level);
		initWordsPath();

		ImageButton btnGuide = (ImageButton)findViewById(R.id.btn_guide);
		ImageButton btnRefresh = (ImageButton)findViewById(R.id.btn_refresh);
		ImageButton btnHelp1 = (ImageButton)findViewById(R.id.btn_help1);
		ImageButton btnHelp2 = (ImageButton)findViewById(R.id.btn_help2);
		ImageButton btnHelp3 = (ImageButton)findViewById(R.id.btn_help3);

		btnGuide.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				createDialogGuide();
			}
		});

		btnRefresh.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent level = new Intent(LevelActivity2.this,
						LevelActivity2.class);
				level.putExtra("levelnumber",levelNumber); // id is
				System.gc();
				finish();
				startActivity(level);
			}
		});

		btnHelp1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				HelpResultType[] resType = new HelpResultType[1];
				String[] res = level.helpGetNextPossibleWords(resType);

				switch (resType[0]){
					case NOT_ENOUGH_CONINS:
						Toast.makeText(getApplicationContext(),getResources().getString(R.string.not_enough_coins),Toast.LENGTH_SHORT).show();
						break;
				}
				if(mGameManager.spendCoins(help1Cost))
				{
					createDialogNextPossibleWord(res);
				}else{

				}
			}
		});
	}
	private void initChar_Key()
	{
		charToKey = new HashMap<>(33);
		keyToChar = new HashMap<>(33);

		charToKey.put(' ',R.drawable.empty);
		keyToChar.put(R.drawable.empty, ' ');

		charToKey.put('ا',R.drawable.a1);
		keyToChar.put(R.drawable.a1, 'ا');

		charToKey.put('ب',R.drawable.a2);
		keyToChar.put(R.drawable.a2, 'ب');

		charToKey.put('پ',R.drawable.a3);
		keyToChar.put(R.drawable.a3, 'پ');

		charToKey.put('ت',R.drawable.a4);
		keyToChar.put(R.drawable.a4, 'ت');

		charToKey.put('ث',R.drawable.a5);
		keyToChar.put(R.drawable.a5, 'ث');

		charToKey.put('ج',R.drawable.a6);
		keyToChar.put(R.drawable.a6, 'ج');

		charToKey.put('چ',R.drawable.a7);
		keyToChar.put(R.drawable.a7, 'چ');

		charToKey.put('ح',R.drawable.a8);
		keyToChar.put(R.drawable.a8, 'ح');

		charToKey.put('خ',R.drawable.a9);
		keyToChar.put(R.drawable.a9, 'خ');

		charToKey.put('د',R.drawable.a10);
		keyToChar.put(R.drawable.a10, 'د');

		charToKey.put('ذ',R.drawable.a11);
		keyToChar.put(R.drawable.a11, 'ذ');

		charToKey.put('ر',R.drawable.a12);
		keyToChar.put(R.drawable.a12, 'ر');

		charToKey.put('ز',R.drawable.a13);
		keyToChar.put(R.drawable.a13, 'ز');

		charToKey.put('ژ',R.drawable.a14);
		keyToChar.put(R.drawable.a14, 'ژ');

		charToKey.put('س',R.drawable.a15);
		keyToChar.put(R.drawable.a15, 'س');

		charToKey.put('ش',R.drawable.a16);
		keyToChar.put(R.drawable.a16, 'ش');

		charToKey.put('ص',R.drawable.a17);
		keyToChar.put(R.drawable.a17, 'ص');

		charToKey.put('ض',R.drawable.a18);
		keyToChar.put(R.drawable.a18, 'ض');

		charToKey.put('ط',R.drawable.a19);
		keyToChar.put(R.drawable.a19, 'ط');

		charToKey.put('ظ',R.drawable.a20);
		keyToChar.put(R.drawable.a20, 'ظ');

		charToKey.put('ع',R.drawable.a21);
		keyToChar.put(R.drawable.a21, 'ع');

		charToKey.put('غ',R.drawable.a22);
		keyToChar.put(R.drawable.a22, 'غ');

		charToKey.put('ف',R.drawable.a23);
		keyToChar.put(R.drawable.a23, 'ف');

		charToKey.put('ق',R.drawable.a24);
		keyToChar.put(R.drawable.a24, 'ق');

		charToKey.put('ک',R.drawable.a25);
		keyToChar.put(R.drawable.a25, 'ک');

		charToKey.put('گ',R.drawable.a26);
		keyToChar.put(R.drawable.a26, 'گ');

		charToKey.put('ل',R.drawable.a27);
		keyToChar.put(R.drawable.a27, 'ل');

		charToKey.put('م',R.drawable.a28);
		keyToChar.put(R.drawable.a28, 'م');

		charToKey.put('ن',R.drawable.a29);
		keyToChar.put(R.drawable.a20, 'ن');

		charToKey.put('و',R.drawable.a30);
		keyToChar.put(R.drawable.a30, 'و');

		charToKey.put('ه',R.drawable.a31);
		keyToChar.put(R.drawable.a31, 'ه');

		charToKey.put('ی',R.drawable.a32);
		keyToChar.put(R.drawable.a32, 'ی');


	}

	private void initLevelDetails(Level level) {
		level.reset();
		initCurrentWord(level.getStartWord().length());
		setCurrentWord(level.getStartWord());

		View endWordBox = findViewById(R.id.endword2);
		TextView endWord = (TextView) endWordBox.findViewById(R.id.word_path_item_textview);
		endWord.setText(level.getEndWord());
	}
	private void initCurrentWord(int wordLen){
		LinearLayout currentWordPanel = (LinearLayout)findViewById(R.id.currentword);

		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		screenWidth = metrics.widthPixels;


		currentWordImageButtons = new ImageButton[wordLen];

		LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(screenWidth/8,ViewGroup.LayoutParams.WRAP_CONTENT);
		for(int i = wordLen-1; i >=0;i--){

			currentWordImageButtons[i] = new ImageButton(this);
			currentWordImageButtons[i].setLayoutParams(layoutParams);
			currentWordImageButtons[i].setAdjustViewBounds(true);
			currentWordImageButtons[i].setPadding(1, 1, 1, 1);
			currentWordImageButtons[i].setScaleType(ImageView.ScaleType.FIT_CENTER);
			currentWordImageButtons[i].setTag(i);
			currentWordImageButtons[i].setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					ImageButton selectedImageButton = (ImageButton) view;
					int selectedBox_ = (int) selectedImageButton.getTag();

					if (seletedBox != -1)
						setCurrentWord(currentWord);
					if (selectedBox_ == seletedBox) {
						seletedBox = -1;
					} else {
						selectedImageButton.setImageResource(R.drawable.empty);
						seletedBox = selectedBox_;
					}
				}
			});

			currentWordPanel.addView(currentWordImageButtons[i]);
		}
	}
	private void setCurrentWord(String currentWord) {
		this.currentWord = currentWord;
		for (int i =0 ;i < currentWord.length();i++){
			char c = currentWord.charAt(i);
			currentWordImageButtons[i].setImageResource(charToKey.get(c));
		}
	}

	@Override
	public void onClick(View view) {
		if(seletedBox != -1)
		{
			char c = ((String) view.getTag()).charAt(0);
			char[] currentWordArray = currentWord.toCharArray();
			currentWordArray[seletedBox] = c;
			String newWord =  new String(currentWordArray);
			addWord(newWord);
		}else{
			Toast.makeText(this,getString(R.string.select_a_char),Toast.LENGTH_SHORT).show();
		}
	}
	private void initWordsPath(){
		wordsPath = (ListView)findViewById(R.id.words_path);
		adapter = new WordPathAdapter(this,R.id.words_path);
		adapter.add(level.getStartWord());
		wordsPath.setAdapter(adapter);
		wordsPath.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
				if (i == 0) return true;
				String word = adapter.getItem(i);
				level.deleteFrom(word);
				currentWord = level.getLastWord();
				setCurrentWord(currentWord);

				for (int j = adapter.getCount() - 1; j >= i; j--) {
					word = adapter.getItem(j);
					adapter.remove(word);
				}


				return true;
			}
		});

		wordsPath.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			private static final long DOUBLE_CLICK_TIME_DELTA = 300;//milliseconds
			long lastClickTime = 0;

			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
				long clickTime = System.currentTimeMillis();
				if (clickTime - lastClickTime < DOUBLE_CLICK_TIME_DELTA) {
					// on double click
					//Log.e("Double Click", wordView[numberOfWords].getX() + "");
					Intent meaning = new Intent(LevelActivity2.this,
							MeaningActivity.class);
					meaning.putExtra("mean", mGameManager.getDataBaseManager().getMeaning(adapter.getItem(i)));
					startActivity(meaning);
				} else {
					// on single click
				}
				lastClickTime = clickTime;
			}
		});
	}

	private void addWord(String newWord)
	{
		AddWordResult res =  level.addWord(newWord);
		if(res.isFinish)
		{
			//TODO : finish
			Toast.makeText(this,"تمام",Toast.LENGTH_LONG).show();
		}
		else if(res.isValidWord){
			currentWord = newWord;
			setCurrentWord(currentWord);
			adapter.add(currentWord);
			wordsPath.setSelection(adapter.getCount() - 1);
			seletedBox = -1;
		}else {
			setCurrentWord(currentWord);
			Toast.makeText(this,getString(R.string.invalid_word),Toast.LENGTH_SHORT).show();
		}
		//TODO کلمه تکراری
	}

	class WordPathAdapter extends ArrayAdapter<String>
	{
		public WordPathAdapter (Context context,int resource) {
			super(context,resource);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			//return super.getView(position, convertView, parent);
			LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View root = inflater.inflate(R.layout.word_path_item, parent, false);
			TextView textView = (TextView) root.findViewById(R.id.word_path_item_textview);
			String word =  getItem(position);
			textView.setText(word);

			GradientDrawable background = (GradientDrawable) textView.getBackground();

			if(position == 0)
			{
				background.setColor(getResources().getColor(R.color.start_word_color));

				//textView.setBackgroundColor(getResources().getColor(R.color.start_word_color));
			}else if(position <= level.getMinMove())
			{
				background.setColor(getResources().getColor(R.color.allowed_word_color));
//				textView.setBackgroundColor(getResources().getColor(R.color.allowed_word_color));
			}
			else{
				background.setColor(getResources().getColor(R.color.extera_word_color));
				//textView.setBackgroundColor(getResources().getColor(R.color.extera_word_color));
			}

			return root;
		}


	}
	private void createDialogGuide() {
		Dialog dialog = new Dialog(LevelActivity2.this);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.guide_fragment);
		dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
		LinearLayout linearLayout = (LinearLayout)findViewById(R.id.guide_linear);
		int width = screenWidth;
		int height = (int) (width*0.71);
		dialog.getWindow().setLayout(width, height);
		dialog.show();
	}

	private int counter;
	private void createDialogNextPossibleWord(final String[] words) {
		if (words == null) {
			// TODO : پیام مناسب
			Toast.makeText(this, "سکه کافی ندارید!", Toast.LENGTH_LONG);
			return;
		}
		final Dialog dialog = new Dialog(LevelActivity2.this);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.next_possible_words_fragment);
		//ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams();
//		dialog.getWindow().setAttributes();
		//dialog.

		TableLayout table = (TableLayout) dialog.findViewById(R.id.tblNextWord);

//		ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(this,
//				R.layout.listview_row, words);
		TableRow row = new TableRow(LevelActivity2.this);

		for(counter = 0; counter < words.length;counter++) {
			if (counter % 2 == 0) {
				row = new TableRow(LevelActivity2.this);
				table.addView(row);
			}
			Button btn = new Button(LevelActivity2.this);
			btn.setText(words[counter]);
			btn.setOnClickListener(new View.OnClickListener() {
				String word = words[counter];

				@Override
				public void onClick(View v) {
					addWord(word);
					dialog.dismiss();
				}
			});
			row.addView(btn);
		}
		dialog.show();
	}
}
