package ir.chocolategroup.majiddelbandam;

import java.util.ArrayList;
import java.util.HashMap;

public class GameManager {
	private int mCoins;
	private HashMap<Integer,Level> mLevels;
	
	public GameManager() {
		//TODO set mCoins from prefrence
	}
	
	protected boolean spendCoins(int c)
	{
		if(mCoins < c) 
			return false;
		mCoins -= c;
		return true;
	}
	
	public void loadAllLevels()
	{
		//TODO
	}
	
	public void loadUnlockedLevel()
	{
		//TODO
	}
	
	public void loadLevel(int levelNumber)
	{
		if(!mLevels.containsKey(levelNumber))
		{		
			//TODO
		}
	}

}
