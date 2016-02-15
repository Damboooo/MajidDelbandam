package ir.chocolategroup.majiddelbandam;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class Splash extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
//		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView(R.layout.splash);
		GameManager gameManager = (GameManager)getApplication();
		gameManager.load();
		Thread timer = new Thread()
		{
			@Override
			public void run() {
				try{
					sleep(2500);
				}
				catch(InterruptedException e)
				{
					
				}
				finally
				{

					
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

		timer.start();
	}

	
}
