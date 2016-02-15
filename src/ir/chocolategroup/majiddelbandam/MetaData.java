package ir.chocolategroup.majiddelbandam;

public class MetaData {
	public long LevelCount;
	
	//only for unLock level
	public int[] minMove;
	//if not done yet userMove = infinity
	public int[] userMove;
	public int numberOfUnlockLevel;
	public MetaData() {
		
	}
	public int getLastUnlockLevel()
	{
		return numberOfUnlockLevel;
	}

}
