package com.temporun.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Spinner;

import com.temporun.R;

public class WorkoutBeginActivity extends Activity {

	Spinner spinner;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_workout_begin);
		spinner = (Spinner) findViewById(R.id.workoutTypeSpinner);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.workout_begin, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	
	public void onWorkoutBeginClick(View v){
		String workoutType = spinner.getItemAtPosition(spinner.getSelectedItemPosition()).toString();
		if(workoutType.equalsIgnoreCase("Free Run")){
			Intent workoutIntent = new Intent(this, WorkoutActivity.class);
			startActivity(workoutIntent);
			
		}else if (workoutType.equalsIgnoreCase("Tempo Based Run")) {
			Intent workoutIntent = new Intent(this, WorkoutActivity.class);
			startActivity(workoutIntent);
			
		}
	
		
		
		
	}

//	/**
//	 * A placeholder fragment containing a simple view.
//	 */
//	public static class PlaceholderFragment extends Fragment {
//
//		public PlaceholderFragment() {
//		}
//
//		@Override
//		public View onCreateView(LayoutInflater inflater, ViewGroup container,
//				Bundle savedInstanceState) {
//			View rootView = inflater.inflate(R.layout.fragment_workout_begin,
//					container, false);
//			return rootView;
//		}
//	}

}
