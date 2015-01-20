package ir.chocolategroup.majiddelbandam;

import java.util.ArrayList;
import java.util.Currency;

public class Level {

	public final int mLevelNumber;
	private final String mStartWord;
	private final String mEndWord;
	private final String[] mBestResult;
	private final int mMinMove;
	private boolean mLock;
	private boolean mDone;
	private String[] mBestUserResult;
	private ArrayList<String> mCurrenUserResult;
	private ArrayList<String> mNextValidWord;
	
	public Level(int levelNumber, boolean lock , boolean done , String startWord , String endWord , String[] bestResult , int minMove , String[] bestUserResult ) {
		mLevelNumber = levelNumber;
		mLock = lock;
		mDone = done;
		mStartWord = startWord;
		mEndWord = endWord;
		mBestResult = bestResult;
		mMinMove = minMove;
		mBestUserResult = bestUserResult;
		mCurrenUserResult = new ArrayList<String>();
		mCurrenUserResult.add(startWord);
		mNextValidWord = getNextPossible(startWord);
	}

	public int getLevelNumber() {
		return mLevelNumber;
	}

	public String getStartWord() {
		if(mLock) return null;
		return mStartWord;
	}

	public String getEndWord() {
		if(mLock) return null;
		return mEndWord;
	}

	public int getMinMove() {
		if(mLock) return 0;
		return mMinMove;
	}

	public boolean isLock() {
		return mLock;
	}

	public boolean isDone() {
		if(mLock) return false;
		return mDone;
	}
	
	public boolean addWord(String word)
	{
		if(mNextValidWord.contains(word))
		{
			mCurrenUserResult.add(word);
			mNextValidWord = getNextPossible(word);
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public void deleteFrom(String word)
	{
		//TODO
	}
	
	private static ArrayList<String> getNextPossible(String word)
	{
		//TODO 
		return null;
	}
	
	
	//help
	//return false if haven't enough coins
	public boolean helpGoToNextLevel()
	{
		//TODO
		return false;
	}
	
	//help
	//return null if haven't enough coins
	public String HelpGetNextWord()
	{
		//TODO 
		return null;
	}
	
	//help
	//return null if haven't enough coins
	public String[] helpGetNextPossibleWords()
	{
		//TODO : check Coins
		return (String[])mNextValidWord.toArray();
	}

}
