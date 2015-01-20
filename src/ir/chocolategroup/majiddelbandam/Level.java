package ir.chocolategroup.majiddelbandam;

import java.util.ArrayList;

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
	}

	public int getmLevelNumber() {
		return mLevelNumber;
	}

	public String getmStartWord() {
		if(mLock) return null;
		return mStartWord;
	}

	public String getmEndWord() {
		if(mLock) return null;
		return mEndWord;
	}

	public int getmMinMove() {
		if(mLock) return 0;
		return mMinMove;
	}

	public boolean ismLock() {
		return mLock;
	}

	public boolean ismDone() {
		if(mLock) return false;
		return mDone;
	}
	
	

}
