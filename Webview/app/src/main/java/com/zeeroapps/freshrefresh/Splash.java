package com.zeeroapps.freshrefresh;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Splash extends Activity {
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        
        final Handler handel = new Handler();
        handel.postDelayed(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Intent loadSplash = new Intent(Splash.this, MainActivity.class);
				startActivity(loadSplash);
				finish();
			}
		}, 2000);
    }
}
