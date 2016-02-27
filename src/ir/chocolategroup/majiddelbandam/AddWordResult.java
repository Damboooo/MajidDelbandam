package ir.chocolategroup.majiddelbandam;

public class AddWordResult {
	public boolean isValidWord;
	public boolean isFinish;
	public int prize;
	public int minMove;
	public int userMove;
	public AddWordResult(boolean valid , boolean finish , int prize) {
		isValidWord = valid;
		isFinish = finish;
		this.prize = prize;
	}

}
