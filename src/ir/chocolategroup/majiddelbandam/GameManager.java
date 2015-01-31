package ir.chocolategroup.majiddelbandam;

import ir.chocolategroup.majiddelbandam.database.DataBaseManager;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Application;

public class GameManager extends Application{
	private final int LevelCoin = 30;
	
	private int mCoins;
	private HashMap<Integer,Level> mLevels;
	private DataBaseManager dataBaseManager;
	public GameManager() {
		//TODO set mCoins from prefrence
	}
	
	protected boolean spendCoins(int c)
	{
		if(mCoins < c) 
			return false;
		mCoins -= c;
		return true;
		//TODO file
	}
	
	private void addCoins(int coin)
	{
		mCoins += coin;
		//TODO file
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
	
	public void goToNextLevel(Level level , boolean done)
	{
		if(done)
			addCoins(LevelCoin);
		
		
		
	}
	public void goToNextLevel(Level level )
	{
		goToNextLevel(level, true);
	}
	
	public Level getLevel(Integer i)
	{
		return null; // TO DO
	}
}
