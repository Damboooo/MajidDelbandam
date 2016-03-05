package ir.chocolategroup.majiddelbandam;

import java.util.ArrayList;
import java.util.Currency;
import java.util.StringTokenizer;

import android.text.InputFilter.LengthFilter;

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
	private final int LevelCoinsPrize = 50;
	private final int neededCoins_NextLevel = 50; 
	private final int neededCoins_NextWord = 30; 
	private final int neededCoins_NextPossible = 20;
	private final int neededCoins_MidWord = 20;
	private final int fineForEachExtraMove = 5;
	
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
		mGameManager = gameManager;
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
		
	}
	
	public void reset()
	{
		mCurrenUserResult = new ArrayList<String>();
		mCurrenUserResult.add(mStartWord);
		mNextValidWord = getNextPossible(mStartWord);
	}
	
	private String[] strtinToArray(String input)
	{
		if(input == null || input.equals(""))
		{
			return null;
		}
		input = input.substring(1,input.length()-1);
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
		res.append('}');
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
	
	public int getMinUserMove()
	{
		if(mBestUserResult == null || mBestUserResult.length == 0)
			return Integer.MAX_VALUE;
		else
			return mBestUserResult.length;
	}
	
	public AddWordResult addWord(String word)
	{
		mGameManager.loadLevel(mLevelNumber+1);
		if(word.equals(mEndWord))
		{
			AddWordResult res = new AddWordResult(true, true, finishLevel());
			res.minMove = getMinMove();
			res.userMove = mCurrenUserResult.size();
			return res;
		}
		if(mNextValidWord.contains(word))
		{
			mCurrenUserResult.add(word);
			mNextValidWord = getNextPossible(word);
			return new AddWordResult(true, false, 0);
		}
		else
		{
			return new AddWordResult(false, false, 0);
		}
	}
	
	public void deleteFrom(String word)
	{
		int index = mCurrenUserResult.indexOf(word);
		while(mCurrenUserResult.size()-1 >= index)
		{
			mCurrenUserResult.remove(index);
		}
		mNextValidWord = getNextPossible(mCurrenUserResult.get(mCurrenUserResult.size() - 1));
	}

	public void delete(String word)
	{
		int index = mCurrenUserResult.indexOf(word);
		if(mCurrenUserResult.size()-1 >= index)
		{
			mCurrenUserResult.remove(index);
		}
		mNextValidWord = getNextPossible(mCurrenUserResult.get(mCurrenUserResult.size() - 1));
	}

	private ArrayList<String> getNextPossible(String word)
	{
		return mGameManager.getNextPosibleWords(word);
	}
	
	private int finishLevel()
	{
		mDone = true;
		int prize = 0;
		if(mBestUserResult == null || mBestUserResult.length == 0 )
		{
			mBestUserResult = new String[mCurrenUserResult.size()];
			for (int i = 0; i < mBestUserResult.length; i++) {
				mBestUserResult[i]  = mCurrenUserResult.get(i);
			}
//			mBestUserResult = (String[])mCurrenUserResult.toArray();
			prize = LevelCoinsPrize - fineForEachExtraMove * (mBestUserResult.length - mMinMove);
		}
		else if(mBestUserResult.length > mCurrenUserResult.size())
		{
			String[] temp = (String[])mCurrenUserResult.toArray();
			prize = fineForEachExtraMove * (mBestUserResult.length - temp.length);
			mBestUserResult = temp;
		}
		Level nextLevel = mGameManager.getLevel(mLevelNumber+1);
		if(nextLevel != null) {
			nextLevel.mLock = false;
			mGameManager.updateLevel(nextLevel);
		}else
		{
			//TODO : load from server
		}
		mGameManager.updateLevel(this);
		return prize;

	}
	//help
	//return false if haven't enough coins
	public boolean helpGoToNextLevel()
	{
		if(!mGameManager.spendCoins(neededCoins_NextLevel))
		{			
			return false;
		}
		mGameManager.goToNextLevel(this, 0, false);
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
		}
		String[] res = new String[mNextValidWord.size()];
		for (int i = 0; i < res.length; i++) {
			res[i] = mNextValidWord.get(i);
		}
		return res;
	}
	
	//help
	//return null if haven't enough coins
	public String helpMidWord()
	{
		if(!mGameManager.spendCoins(neededCoins_MidWord))
		{			
			return null;
		}
		int i = 0;
		while(i < mCurrenUserResult.size() && i < mBestResult.length)
		{
			if(!mCurrenUserResult.get(i).equals(mBestResult[i]))
			{
				break;
			}
			i++;
		}
		int index = (int) Math.floor((mBestResult.length + i)/2) ;
		while(mCurrenUserResult.contains(mBestResult[index])) {
			index++;
			//TODO: momkene az out of bounds exception bede
		}
		return mBestResult[index];
	}
}
