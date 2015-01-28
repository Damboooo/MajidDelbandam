package ir.chocolategroup.majiddelbandam.database;


import ir.chocolategroup.majiddelbandam.GameManager;
import ir.chocolategroup.majiddelbandam.Level;

import java.io.IOException;
import java.nio.ReadOnlyBufferException;
import java.util.ArrayList;
import java.util.Arrays;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.widget.Toast;



public class DataBaseManager {
	private DatabaseHelper myDbHelper;
	private final String WordsTable = "Words";
	private final String LevelTable = "Levels";
	private final String Levels_LevelNumber = "LevelNumber";
	private final String words_word = "word";
	private final String Levels_StartWord = "StartWord";
	private final String Levels_EndWord = "EndWord";
	private final String Levels_MinMove = "MinMove";
	private final String Levels_Lock = "Lock";
	private final String Levels_Done = "Done";
	private final String Levels_ListBestUserResult = "ListBestUserResult";
	private final String Levels_ListBestResult = "ListBestResult";
	
	public DataBaseManager(Context context) {
		myDbHelper = new DatabaseHelper(context);
		try {
			myDbHelper.createDataBase();
		} catch (IOException e) {
			// TODO 
		}
		
		try {
	       myDbHelper.openDataBase();
	   	}catch(SQLException sqle){
	       //TODO 	  
	   }
	}
	public Level[] loadAllLevels(GameManager gameManager)
	{
		
		Cursor cursor;
		cursor = myDbHelper.query(LevelTable, null, null, null, null,null, null);
		return getLevelsFromCursor(cursor,gameManager);
//		if(cursor.moveToFirst())
//		{
//			do {
//				result.add(new Level(cursor.getInt(0), cursor.getInt(1) == 1, cursor.getInt(2) == 1, cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getInt(6), cursor.getString(7)));
//			} while (cursor.moveToNext());
//		}
//		
//		
//		return (Level[])result.toArray();
	}
	private Level[] getLevelsFromCursor(Cursor cursor , GameManager gameManager)
	{
		ArrayList<Level> result = new ArrayList<Level>();
		if(cursor.moveToFirst())
		{
			do {
				result.add(new Level(cursor.getInt(0), cursor.getInt(1) == 1, cursor.getInt(2) == 1, cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getInt(6), cursor.getString(7),gameManager));
			} while (cursor.moveToNext());
		}
		return (Level[])result.toArray();
	}
	public Level[] loadUnlockLevels(GameManager gameManager)
	{
		Cursor cursor;
		cursor = myDbHelper.query(LevelTable, null, Levels_Lock + " = ?", new String[]{"0"}, null,null, null);
		return getLevelsFromCursor(cursor,gameManager);
	}
	public Level loadLevel(int levelNumber,GameManager gameManager)
	{
		Cursor cursor;
		cursor = myDbHelper.query(LevelTable, null, Levels_LevelNumber + " = ?", new String[]{"0"}, null,null, null);
		return getLevelsFromCursor(cursor,gameManager)[0];
	}
	public ArrayList<String> getNextPosibleWords(String word)
	{
		char[] wordChar = word.toCharArray();
		char[] tempWordChar;
		StringBuilder selection = new StringBuilder();
		String[] selectionArg = new String[word.length()];
		for (int i = 0; i < wordChar.length; i++) {
			//tempWordChar = Arrays.copyOf(wordChar, wordChar.length);
			tempWordChar = new char[wordChar.length];
			for (int j = 0; j < wordChar.length; j++) {
				tempWordChar[i] = wordChar[i];
			}
			tempWordChar[i]= '_';
			selectionArg[i] = new String(tempWordChar);
			selection.append(words_word + " like ?");
			if(i != wordChar.length -1)
				selection.append(" OR ");
		}
		
		Cursor cursor;
		cursor = myDbHelper.query(WordsTable, new String[]{words_word}, selection.toString(), selectionArg, null,null, null);
		ArrayList<String> result = new ArrayList<String>();
		if(cursor.moveToFirst())
		{
			do {
				result.add(cursor.getString(0));
			} while (cursor.moveToNext());
		}
		return result;
		
		
	}
	
	public void addLevel(Level level)
	{
		ContentValues initValue = getContentValueFromLevel(level);
		initValue.put(Levels_LevelNumber,level.getLevelNumber());
		myDbHelper.insert(LevelTable, null, initValue);
	}
	
	private ContentValues getContentValueFromLevel(Level level)
	{
		ContentValues initValue = new ContentValues();
		initValue.put(Levels_StartWord, level.getStartWord());
		initValue.put(Levels_EndWord, level.getEndWord());
		initValue.put(Levels_MinMove, level.getMinMove());
		initValue.put(Levels_Done, level.isDone());
		initValue.put(Levels_Lock, level.isLock());
		initValue.put(Levels_ListBestResult, level.getBestResult());
		initValue.put(Levels_ListBestUserResult, level.getBestUserResult());
		return initValue;
	}
	
	public void updateLevel(Level level)
	{
		ContentValues contentValue = getContentValueFromLevel(level);
		myDbHelper.update(LevelTable, contentValue, Levels_LevelNumber + " = ?", new String[]{level.getLevelNumber()+ ""});
	}
}
