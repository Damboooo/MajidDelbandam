package ir.chocolategroup.majiddelbandam;

import ir.chocolategroup.majiddelbandam.database.DataBaseManager;

import java.util.ArrayList;
import java.util.HashMap;

import android.R.integer;
import android.app.Application;

public class GameManager extends Application{
	private final int LevelCoin = 30;
	
	private int mCoins;
	private HashMap<Integer,Level> mLevels;
	private int mLevelCount;
	private DataBaseManager dataBaseManager;
	
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
        if(SharePrefrencesManager.isExistKey(this, getResources().getString(R.string.Coins)))
		{
			mCoins = Integer.parseInt(SharePrefrencesManager.getValue(this, getResources().getString(R.string.Coins)));
		}
		else
		{
			mCoins = 200;//initial coins
			updateCoinsInSharePrefrences();
		}
        
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
		mLevels.clear();
		for (Level level : dataBaseManager.loadUnlockLevels(this)) {
			mLevels.put(level.getLevelNumber(), level);
		} 
	}
	
	public void loadLevel(int levelNumber)
	{
		if(!mLevels.containsKey(levelNumber))
		{		
			Level temp = dataBaseManager.loadLevel(levelNumber,this);
			mLevels.put(temp.getLevelNumber(), temp);
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
		result.LevelCount = dataBaseManager.getNumberOfLevel();
		result.minMove = new int[mLevels.size()];
		result.userMove = new int[mLevels.size()];
		for (Level level : mLevels.values()) {
			result.minMove[level.getLevelNumber()] = level.getMinMove();
			result.userMove[level.getLevelNumber()] = level.getMinUserMove();
		}
		return result;
	}

}
