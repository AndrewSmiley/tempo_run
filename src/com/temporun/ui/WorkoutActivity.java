package com.temporun.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Chronometer;

import com.facebook.Session;
import com.temporun.R;

public class WorkoutActivity extends Activity {
	Chronometer stopwatch;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_workout);
//When we load, we want to check to see if the user is logged in or not
		 Session session = Session.getActiveSession();
	     //if they're logged in, bring them to the workout page 
		 if (session == null | !session.isOpened()) {
	         Intent mainIntent = new Intent(this, MainActivity.class);
	         startActivity(mainIntent);
	      } 

		 stopwatch = (Chronometer) findViewById(R.id.runTimeChronometer);
		 stopwatch.start();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
}
