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
					sleep(3000);
				}
				catch(InterruptedException e)
				{
					
				}
				finally
				{
					Intent startLevelsList = new Intent("ir.chocolategroup.majiddelbandam.LEVELSLIST");
					startActivity(startLevelsList);
				}
			}
		};
		//TODO : load from DB
		timer.start();
	}

	
}
