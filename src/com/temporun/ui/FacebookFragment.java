//package com.temporun.ui;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.service.textservice.SpellCheckerService.Session;
//import android.support.v4.app.Fragment;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import com.facebook.Session.StatusCallback;
//import com.facebook.SessionState;
//import com.facebook.UiLifecycleHelper;
//import com.facebook.widget.LoginButton;
//import com.temporun.R;
//
//public class FacebookFragment extends Fragment {
//	
//	
//	private static final String TAG = "MainFragment";
//	private UiLifecycleHelper uiHelper;
//	
//	private StatusCallback callback = new StatusCallback() {
//		@Override
//		public void call(com.facebook.Session session, SessionState state,
//				Exception exception) {
//			// TODO Auto-generated method stub
//			
//		}
//	};
//	
//	@Override
//	public void onCreate(Bundle savedInstanceState){
//		
//		super.onCreate(savedInstanceState);
//	    uiHelper = new UiLifecycleHelper(getActivity(), callback);
//	    uiHelper.onCreate(savedInstanceState);
//	}
//	@Override
//	public View onCreateView(LayoutInflater inflater, 
//	        ViewGroup container, 
//	        Bundle savedInstanceState) {
//	    View view = inflater.inflate(R.layout.activity_main, container, false);
//	    LoginButton authButton = (LoginButton) view.findViewById(R.id.authButton);
//	    authButton.setFragment(this);
//
//	    return view;
//	}
//	
//	
//	private void onSessionStateChange(Session session, SessionState state, Exception exception) {
//	    if (state.isOpened()) {
//	        Log.i(TAG, "Logged in...");
//	    } else if (state.isClosed()) {
//	        Log.i(TAG, "Logged out...");
//	    }
//	}
//	
//	@Override
//	public void onResume() {
//	    super.onResume();
//	    Session session = Session.getActiveSession();
//	    if (session != null &&
//	           (session.isOpened() || session.isClosed()) ) {
//	        onSessionStateChange(session, session.getState(), null);
//	    }
//
//	    uiHelper.onResume();
//	    
//	}
//
//	@Override
//	public void onActivityResult(int requestCode, int resultCode, Intent data) {
//	    super.onActivityResult(requestCode, resultCode, data);
//	    uiHelper.onActivityResult(requestCode, resultCode, data);
//	}
//
//	@Override
//	public void onPause() {
//	    super.onPause();
//	    uiHelper.onPause();
//	}
//
//	@Override
//	public void onDestroy() {
//	    super.onDestroy();
//	    uiHelper.onDestroy();
//	}
//
//	@Override
//	public void onSaveInstanceState(Bundle outState) {
//	    super.onSaveInstanceState(outState);
//	    uiHelper.onSaveInstanceState(outState);
//	}
//	
//	
//}
