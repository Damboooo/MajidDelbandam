package ir.chocolategroup.majiddelbandam;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class Splash extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		
		Thread timer = new Thread()
		{
			@Override
			public void run() {
				try{
					sleep(1000);
				}
				catch(InterruptedException e)
				{
					
				}
				finally
				{
					GameManager gameManager = (GameManager)getApplication();
					
//					Intent startLevelsList = new Intent(Splash.class, MainActivity.class);
//					startLevelsList.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//					startLevelsList.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);                  
//					startLevelsList.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
					
					Intent startLevelsList = new Intent("ir.chocolategroup.majiddelbandam.LEVELSLIST");
					startLevelsList.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					while(!gameManager.getLoadLevels());
					startActivity(startLevelsList);
					finish();
					//System.exit(0);
				}
			}
		};
		//TODO : load from DB
		timer.start();
	}

	
}
