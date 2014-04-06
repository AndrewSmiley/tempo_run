package com.temporun.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.Session;
import com.google.android.gms.internal.cu;
import com.temporun.R;
import com.temporun.dao.RunSQLite;
import com.temporun.dto.Run;
import com.temporun.integration.RunIntegration;
import com.temporun.integration.WaypointIntegration;

public class EndWorkoutActivity extends Activity {
		TextView txtDistanceRun;
		TextView txtTotalTime;
		TextView txtCaloriesBurned;
		EditText txtRunNotes;
		RunSQLite runSQLite;
		WaypointIntegration waypointIntegration;
		RunIntegration runIntegration;
		Run currentRun;
		
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_end_workout);
		runSQLite = new RunSQLite(this);
		runIntegration = new RunIntegration(this);
		waypointIntegration = new WaypointIntegration(this);
		txtRunNotes = (EditText) findViewById(R.id.txtRunNotesEdit);
		txtDistanceRun = (TextView) findViewById(R.id.txtDistanceRun);
		txtCaloriesBurned = (TextView) findViewById(R.id.txtCaloriesBurned);
		txtTotalTime = (TextView) findViewById(R.id.txtTotalTime);
		
		try {
			currentRun = runSQLite.getCurrentRun();
			txtDistanceRun.setText(waypointIntegration.getDistanceRun(currentRun));
			txtTotalTime.setText(currentRun.getTime());
			txtCaloriesBurned.setText(runIntegration.calculateCaloriesBurned(currentRun).toString());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
		Toast.makeText(this, getResources().getText(R.string.strUnableToFetchRun), Toast.LENGTH_LONG).show();
		}
		
		
//		/txtDistanceRun.setText(text);
		
//When we load, we want to check to see if the user is logged in or not
		 Session session = Session.getActiveSession();
	     //if they're logged in, bring them to the workout page 
		 if (session == null | !session.isOpened()) {
	         Intent workoutIntent = new Intent(this, WorkoutBeginActivity.class);
	         startActivity(workoutIntent);
	      
	      
	      } 
		 }
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
	public void onSaveRunClicked(View v){
		currentRun.setNotes(txtRunNotes.toString());
		runSQLite.saveRunDetails(currentRun);
		Toast.makeText(this, getResources().getText(R.string.strRunSaved), Toast.LENGTH_LONG).show();
		
		
	}
}
