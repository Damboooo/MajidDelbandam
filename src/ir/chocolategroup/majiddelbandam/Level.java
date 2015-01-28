package ir.chocolategroup.majiddelbandam;

import java.util.ArrayList;
import java.util.Currency;
import java.util.StringTokenizer;

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
	private GameManager mGameManager;
	private final int neededCoins_NextLevel = 50; 
	private final int neededCoins_NextWord = 30; 
	private final int neededCoins_NextPossible = 20; 
	
	public Level(int levelNumber, boolean lock , boolean done , String startWord , String endWord , String[] bestResult , int minMove , String[] bestUserResult, GameManager gameManager  ) {
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
		mGameManager = gameManager;
	}
	public Level(int levelNumber, boolean lock , boolean done , String startWord , String endWord , String bestResult , int minMove , String bestUserResult , GameManager gameManager ) {
		mLevelNumber = levelNumber;
		mLock = lock;
		mDone = done;
		mStartWord = startWord;
		mEndWord = endWord;
		mBestResult = strtinToArray(bestResult);
		mMinMove = minMove;
		mBestUserResult = strtinToArray(bestUserResult);
		mCurrenUserResult = new ArrayList<String>();
		mCurrenUserResult.add(startWord);
		mNextValidWord = getNextPossible(startWord);
		mGameManager = gameManager;
	}
	
	private String[] strtinToArray(String input)
	{
		if(input == null || input == "")
		{
			return null;
		}
		input = input.substring(1,input.length()-2);
		String[] splited = input.split(",");
		return splited;
	}
	
	private String ArrayToString(String[] array)
	{
		if(array == null)
		{
			return "{}";
		}
		StringBuilder res = new StringBuilder("{");
		for (int i = 0; i < array.length; i++) {
			res.append(array[i]);
			if(i != array.length-1)
				res.append(",");
		}
		return res.toString();
		
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
	
	public String getBestResult()
	{
		return ArrayToString(mBestResult);
	}
	
	public String getBestUserResult()
	{
		return ArrayToString(mBestUserResult);
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
			if(mNextValidWord.contains(mEndWord))
			{
				finishLevel();
			}
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public void deleteFrom(String word)
	{
		int index = mCurrenUserResult.indexOf(word);
		while(mCurrenUserResult.size()-1 >= index)
		{
			mCurrenUserResult.remove(index);
		}
		mNextValidWord = getNextPossible(mCurrenUserResult.get(mCurrenUserResult.size()-1));
	}
	
	private ArrayList<String> getNextPossible(String word)
	{
		return mGameManager.getNextPosibleWords(word);
	}
	
	private void finishLevel()
	{
		mDone = true;
		if(mBestUserResult == null || mBestUserResult.length == 0 || mBestUserResult.length > mCurrenUserResult.size())
		{
			mBestUserResult = (String[])mCurrenUserResult.toArray();
		}
		//TODO
	}
	
	//help
	//return false if haven't enough coins
	public boolean helpGoToNextLevel()
	{
		if(!mGameManager.spendCoins(neededCoins_NextLevel))
		{			
			return false;
		}
		//TODO
		return true;
	}
	
	//help
	//return null if haven't enough coins
	public String HelpGetNextWord()
	{
		if(!mGameManager.spendCoins(neededCoins_NextWord))
		{			
			return null;
		}
		//TODO 
		return null;
	}
	
	//help
	//return null if haven't enough coins
	public String[] helpGetNextPossibleWords()
	{
		if(!mGameManager.spendCoins(neededCoins_NextPossible))
		{			
			return null;
		}//TODO : check Coins
		return (String[])mNextValidWord.toArray();
	}

}
