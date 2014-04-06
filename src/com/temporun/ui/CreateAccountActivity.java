package com.temporun.ui;

import java.security.NoSuchAlgorithmException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.Session;
import com.temporun.R;
import com.temporun.dao.IUserServiceSQLite;
import com.temporun.dao.UserServiceSQLite;
import com.temporun.dto.User;
import com.temporun.integration.IUserIntegration;
import com.temporun.integration.UserIntegration;


public class CreateAccountActivity extends Activity {
	private EditText username;
	private EditText password;
	private EditText passwordConfirm;
	
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
		 
		 username = (EditText) findViewById(R.id.txtEmailCreate);
		 password = (EditText) findViewById(R.id.txtPasswordCreate);
		 passwordConfirm = (EditText) findViewById(R.id.txtPasswordConfirmCreate);

		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
		
	}
	

	
	public void onCreateAccountClick(View v){
		
		if (password == passwordConfirm) {
//			make sure the two passwords are equal
			
			IUserIntegration userIntegration = new UserIntegration(this);
			IUserServiceSQLite userServiceSQLite = new UserServiceSQLite(this);
			User user = new User();
			user.setUsername(username.toString());
			try {
				user.setPassword(userServiceSQLite.encryptPassword(password.toString()));
				//try to send the user data
				userIntegration.executeSendUserData(user);
				
				//try to get the user data back from the remote server
				userIntegration.executeGetUserData();
				
				//if we've gotten the user data back, then we can check to see if the user is logged in or not
				if(userIntegration.isLoggedIn(user.getUsername(), user.getPassword())){
					userIntegration.updateUsernameAndPasswordInPrefs(user.getUsername(), user.getUsername());
					
				}else{
					//if they're not logged in, then redirect them to the main page
					Toast.makeText(this, getApplicationContext().getString(R.string.strBadUsernameOrPassword), Toast.LENGTH_LONG).show();
					Intent mainIntent = new Intent(this, MainActivity.class);
					startActivity(mainIntent);
				}
				
				
				
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				Toast.makeText(this, getApplicationContext().getString(R.string.strGenericError), Toast.LENGTH_LONG).show();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				Toast.makeText(this, getApplicationContext().getString(R.string.strGenericError), Toast.LENGTH_LONG).show();
			}
			

			
			
		}
		
		
		
	}
}
