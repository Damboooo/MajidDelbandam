package ir.chocolategroup.majiddelbandam;

import ir.chocolategroup.majiddelbandam.database.DataBaseManager;

import java.util.ArrayList;
import java.util.HashMap;

import android.R.integer;
import android.app.Application;
import android.os.AsyncTask;

public class GameManager extends Application{
	private final int LevelCoin = 30;
	
	private int mCoins;
	private HashMap<Integer,Level> mLevels;
	private long mLevelCount;
	private DataBaseManager dataBaseManager;
	private GameManager mGameManager;
	
	private boolean loadLevels = false;
	public void setLoadLevels()
	{
		loadLevels = true;
	}
	public boolean getLoadLevels()
	{
		return loadLevels;
	}
//	public GameManager() {
//		super();
//		if(SharePrefrencesManager.isExistKey(this, getResources().getString(R.string.Coins)))
//		{
//			mCoins = Integer.parseInt(SharePrefrencesManager.getValue(this, getResources().getString(R.string.Coins)));
//		}
//		else
//		{
//			mCoins = 200;//initial coins
//			updateCoinsInSharePrefrences();
//		}
//	}
	
	@Override
    public void onCreate() {
        super.onCreate();
        mGameManager = this;
        if(SharePrefrencesManager.isExistKey(this, getResources().getString(R.string.Coins)))
		{
			mCoins = Integer.parseInt(SharePrefrencesManager.getValue(this, getResources().getString(R.string.Coins)));
		}
		else
		{
			mCoins = 500;//initial coins
			updateCoinsInSharePrefrences();
		}
        //TODO : pak shavad
        mCoins = 1000;
        
        
        mLevels = new HashMap<Integer, Level>();
        loadUnlockedLevel();
	}
	
	private void updateCoinsInSharePrefrences()
	{
		SharePrefrencesManager.addValue(this, getResources().getString(R.string.Coins), mCoins+"");
	}
	
	protected boolean spendCoins(int c)
	{
		if(mCoins < c) 
			return false;
		mCoins -= c;
		updateCoinsInSharePrefrences();
		return true;
	}
	
	private void addCoins(int coin)
	{
		mCoins += coin;
		updateCoinsInSharePrefrences();
	}
	
	public int getCoins()
	{
		return mCoins;
	}

	public void loadAllLevels()
	{
		mLevels.clear();
		for (Level level : dataBaseManager.loadAllLevels(this)) {
			mLevels.put(level.getLevelNumber(), level);
		} 
	}
	
	public void loadUnlockedLevel()
	{
//		loadLevels = false;
//		if(dataBaseManager == null)
//		{
//			dataBaseManager = new DataBaseManager(getApplicationContext());
//		}
//		mLevelCount = dataBaseManager.getNumberOfLevel();
//		mLevels.clear();
//		for (Level level : dataBaseManager.loadUnlockLevels(mGameManager)) {
//			mLevels.put(level.getLevelNumber(), level);
//		} 
//		setLoadLevels();
		
		
		new LoadUnlockLevelAsync().execute(new Void[]{});
	}
	
	public void loadLevel(int levelNumber)
	{
		if(!mLevels.containsKey(levelNumber) && levelNumber < mLevelCount)
		{		
			new LoadLevelAsync().execute(new Integer[]{levelNumber});
		}
	}
	
	public ArrayList<String> getNextPosibleWords(String word)
	{
		return dataBaseManager.getNextPosibleWords(word);
	}

	public void goToNextLevel(Level level , int prizeCoins , boolean done)
	{
		if(done)
			addCoins(prizeCoins);
		dataBaseManager.updateLevel(level);
		Level nextLevel;
		int nextLevelNumber = level.getLevelNumber()+1;
		if(!mLevels.containsKey(nextLevelNumber))
		{
			loadLevel(nextLevelNumber);
		}
		nextLevel = mLevels.get(nextLevelNumber);
		//TODO start new Activity
		
		
	}
	
	public void goToNextLevel(Level level , int prizeCoins )
	{
		goToNextLevel(level,prizeCoins, true);
	}
	
	public Level getLevel(Integer i)
	{
		return mLevels.get(i);
	}

	public MetaData getMetaData()
	{
		MetaData result = new MetaData();
		result.LevelCount = mLevelCount;
		result.minMove = new int[mLevels.size()];
		result.userMove = new int[mLevels.size()];
		for (Level level : mLevels.values()) {
			result.minMove[level.getLevelNumber()-1] = level.getMinMove();
			result.userMove[level.getLevelNumber()-1] = level.getMinUserMove();
		}
		return result;
	} 
	
	public void updateLevel(Level level)
	{ 
		new UpdateLevelAsync().execute(new Level[]{level});
	}
	
	public class LoadUnlockLevelAsync extends AsyncTask<Void, Void, Void> {
		
		public LoadUnlockLevelAsync() {
			super();
		}

		@Override
		protected Void doInBackground(Void... arg0) {
			loadLevels = false;
			if(dataBaseManager == null)
			{
				dataBaseManager = new DataBaseManager(getApplicationContext());
			}
			mLevelCount = dataBaseManager.getNumberOfLevel();
			mLevels.clear();
			for (Level level : dataBaseManager.loadUnlockLevels(mGameManager)) {
				mLevels.put(level.getLevelNumber(), level);
			} 
			setLoadLevels();
			return null;
		}
	}
	
	public class UpdateLevelAsync extends AsyncTask<Level, Void, Void> {
		
		public UpdateLevelAsync() {
			super();
		}

		@Override
		protected Void doInBackground(Level... arg0) {
			Level level = arg0[0];
			dataBaseManager.updateLevel(level);
			return null;
		}
	}
	public class LoadLevelAsync extends AsyncTask<Integer, Void, Void> {
		
		public LoadLevelAsync() {
			super();
		}

		@Override
		protected Void doInBackground(Integer... arg0) {
			int levelNumber = arg0[0];
			Level temp = dataBaseManager.loadLevel(levelNumber,mGameManager);
			if(!mLevels.containsKey(temp.getLevelNumber()))
				mLevels.put(temp.getLevelNumber(), temp);
			return null;
		}
	}

}
