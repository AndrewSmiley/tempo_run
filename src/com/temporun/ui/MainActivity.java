package com.temporun.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
//import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.android.Facebook;
import com.facebook.model.GraphUser;
import com.temporun.R;


public class MainActivity extends Activity {
//	private FacebookFragment facebookFragment;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
//When we load, we want to check to see if the user is logged in or not
		 Session session = Session.getActiveSession();
	     //if they're logged in, bring them to the workout page 
		 if (session != null && session.isOpened()) {
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
	
	public void onFacebookLoginClick(View v){
		// start Facebook Login
	    Session.openActiveSession(this, true, new Session.StatusCallback() {

	      // callback when session changes state
	      @SuppressLint("ShowToast")
		@Override
	      public void call(Session session, SessionState state, Exception exception) {
	        if (session.isOpened()) {

	          // make request to the /me API
	          Request.executeMeRequestAsync(session, new Request.GraphUserCallback() {

	            // callback after Graph API response with user object
	            @Override
	            public void onCompleted(GraphUser user, Response response) {
	              if (user != null) {
	                Toast.makeText(getApplicationContext(), "It worked!", Toast.LENGTH_LONG).show();
	              }
	            }
	          });
	        }
	      }
	    });
	  }
	
		
	

	
	
  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
      super.onActivityResult(requestCode, resultCode, data);
      Session.getActiveSession().onActivityResult(this, requestCode, resultCode, data);
//      Toast.makeText(getApplicationContext(), "It worked!", Toast.LENGTH_LONG).show();
      Session session = Session.getActiveSession();
      if (session != null && session.isOpened()) {
    	  Intent workoutIntent = new Intent(this, WorkoutBeginActivity.class);
	       startActivity(workoutIntent); 
      
      
      } else {
    	  //handle action if they are not logged in
    	  
    	  
          
      }
  }

}
