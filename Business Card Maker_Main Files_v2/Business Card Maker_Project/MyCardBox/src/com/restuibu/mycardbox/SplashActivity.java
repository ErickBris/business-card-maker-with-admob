package com.restuibu.mycardbox;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;

public class SplashActivity extends Activity {
	
	private ProgressBar progressBar;
	private int progressStatus = 0;
	private Handler handler = new Handler();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);

		
		
		getActionBar().hide();

		progressBar = (ProgressBar) findViewById(R.id.progressBar1);
		// Start long running operation in a background thread
		new Thread(new Runnable() {
			public void run() {
				while (progressStatus < 100) {
					progressStatus += 10;

					handler.post(new Runnable() {
						public void run() {
							progressBar.setProgress(progressStatus);
						}
					});
					try {
						Thread.sleep(300);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
				}
				Intent i = new Intent(SplashActivity.this, MainActivity.class);
				startActivity(i);
				SplashActivity.this.finish();
				

			}
		}).start();
	}

	
}
