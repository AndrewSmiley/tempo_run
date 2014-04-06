package com.temporun.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.Menu;
import android.view.View;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.Session;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.maps.LocationSource.OnLocationChangedListener;
import com.google.android.gms.maps.model.LatLng;
import com.temporun.R;
import com.temporun.dao.RunSQLite;
import com.temporun.dao.WaypointSQLite;
import com.temporun.dto.Run;
import com.temporun.dto.Waypoint;
import com.temporun.integration.LocationLoggerService;
import com.temporun.integration.WaypointIntegration;

public class WorkoutActivity extends Activity implements 
 LocationListener  {
	Chronometer stopwatch;
	TextView distanceText;
	private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;
	Location myLocation;
	OnLocationChangedListener locationListener;
	LatLng currentLatLng;
	LocationManager locationManager;
	Run currentRun;
	private String provider;
	RunSQLite runSQLite;
	WaypointIntegration waypointIntegration;
	WaypointSQLite waypointSQLite;
	private static final String TAG = "DisplayGPSInfoActivity";
//	private FollowMeLocationSource followMeLocationSource;
    private Context mContext;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
				//When we load, we want to check to see if the user is logged in or not
//		 Session session = Session.getActiveSession();
//	     //if they're logged in, bring them to the workout page 
//		 if (session == null | !session.isOpened()) {
//	         Intent mainIntent = new Intent(this, MainActivity.class);
//	         startActivity(mainIntent);
//	      } 
		 runSQLite = new RunSQLite(this);
			//instanciate the run object
			try {
				currentRun = runSQLite.createRun();
			} catch (Exception e) {
				Toast.makeText(this, getResources().getString(R.string.strUnableToCreateRun), Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(this, WorkoutBeginActivity.class);
				startActivity(intent);
			}
			
		waypointSQLite = new WaypointSQLite(this);
		waypointIntegration = new WaypointIntegration(this);
		 
		 //LocationLoggerService service = new LocationLoggerService();
		 
		 this.locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		 this.mContext = getApplicationContext();
//		 ComponentName compName = new ComponentName(getPackageName(),   Service.class.getName());
//		 ComponentName service = startService(new Intent().setComponent(compName));
//		 
		//Choosing the best criteria depending on what is available.
		 Criteria crit = new Criteria();
		 crit.setAccuracy(Criteria.ACCURACY_FINE);
//		 best = mgr.getBestProvider(crit, false);
//		 mgr.requestLocationUpdates(best, 0, 1, locationListener);
		provider = locationManager.getBestProvider(crit, false);
		// creates our custom LocationSource and initializes some of its members
//        followMeLocationSource = new FollowMeLocationSource();

		//provider = LocationManager.GPS_PROVIDER; // We want to use the GPS

		// Initialize the location fields
//		Location location = locationManager.getLastKnownLocation(provider);
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
		locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);
		setContentView(R.layout.activity_workout);
		distanceText = (TextView) findViewById(R.id.txtDistanceRun);
		distanceText.setText("0.0");
		stopwatch = (Chronometer) findViewById(R.id.runTimeChronometer);
		 stopwatch.setBase(SystemClock.elapsedRealtime());
		 stopwatch.start();

//	
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
	public void onWorkoutEndClicked(View v){
		stopwatch.stop();
		currentRun.setTime(Long.toString(SystemClock.elapsedRealtime()));
		runSQLite.updateRunTime(currentRun);
		Intent endWorkoutIntent = new Intent(this, EndWorkoutActivity.class);
		startActivity(endWorkoutIntent);
		
		
		
		
		
		
	}

	
	

	@Override
	public void onLocationChanged(Location location) {
		 Toast.makeText(this, "Change", Toast.LENGTH_SHORT).show();
			Waypoint waypoint = new Waypoint();
			waypoint.setLongitude(Double.toString(location.getLongitude()));
			waypoint.setLatitude(Double.toString(location.getLatitude()));
			waypoint.setRunID(currentRun.getId());
			waypoint.setTime(SystemClock.elapsedRealtime());
			waypointSQLite.addWaypoint(waypoint);
			try {
				distanceText.setText(waypointIntegration.getDistanceRun(currentRun));
				
			} catch (Exception e) {
				distanceText.setText("Cannot Calculate Distance Run");
			}

		
	}


	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	protected void onResume() {
	    super.onResume();
	    Toast.makeText(this, "Resume", Toast.LENGTH_SHORT).show();
	    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
		locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);
	}

	@Override
	protected void onPause() {
	    super.onPause();
	    locationManager.removeUpdates(this);
	}

    


//	@Override
//	public void onLocationChanged(Location location) {
////		Toast.makeText(this, "Location Changed", Toast.LENGTH_SHORT).show();
////		Toast.makeText(this, "Location Changed", Toast.LENGTH_SHORT).show();
////		locationListener.onLocationChanged(location);
//		Log.d(TAG, "GPS LocationChanged");
//		Waypoint waypoint = new Waypoint();
//		waypoint.setLongitude(Double.toString(location.getLongitude()));
//		waypoint.setLatitude(Double.toString(location.getLatitude()));
//		waypoint.setRunID(currentRun.getId());
//		waypoint.setTime(SystemClock.elapsedRealtime());
//		waypointSQLite.addWaypoint(waypoint);
//		try {
//			distanceText.setText(waypointIntegration.getDistanceRun(currentRun));
//			
//		} catch (Exception e) {
//			distanceText.setText("Cannot Calculate Distance Run");
//		}
//		
//		
//	}
	
//	@Override
//	public void onResume(){
//		super.onResume();
//		 followMeLocationSource.getBestAvailableProvider();
//
//	}
//
//	/* Our custom LocationSource. 
//     * We register this class to receive location updates from the Location Manager
//     * and for that reason we need to also implement the LocationListener interface. */
//    private class FollowMeLocationSource implements LocationSource, LocationListener {
//
//        private OnLocationChangedListener mListener;
//        private LocationManager locationManager;
//        private final Criteria criteria = new Criteria();
//        private String bestAvailableProvider;
//        /* Updates are restricted to one every 10 seconds, and only when
//         * movement of more than 10 meters has been detected.*/
//        private final int minTime = 1000;     // minimum time interval between location updates, in milliseconds
//        private final int minDistance = 10;    // minimum distance between location updates, in meters
//
//        private FollowMeLocationSource() {
//            // Get reference to Location Manager
//            locationManager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
//
//            // Specify Location Provider criteria
//            criteria.setAccuracy(Criteria.ACCURACY_FINE);
//            criteria.setPowerRequirement(Criteria.POWER_LOW);
//            criteria.setAltitudeRequired(true);
//            criteria.setBearingRequired(true);
//            criteria.setSpeedRequired(true);
//            criteria.setCostAllowed(true);
//        }
//
//        private void getBestAvailableProvider() {
//            /* The preffered way of specifying the location provider (e.g. GPS, NETWORK) to use 
//             * is to ask the Location Manager for the one that best satisfies our criteria.
//             * By passing the 'true' boolean we ask for the best available (enabled) provider. */
//            bestAvailableProvider = locationManager.getBestProvider(criteria, true);
//        }
//
//        /* Activates this provider. This provider will notify the supplied listener
//         * periodically, until you call deactivate().
//         * This method is automatically invoked by enabling my-location layer. */
//        @Override
//        public void activate(OnLocationChangedListener listener) {
//            // We need to keep a reference to my-location layer's listener so we can push forward
//            // location updates to it when we receive them from Location Manager.
//            mListener = listener;
//
//            // Request location updates from Location Manager
//            if (bestAvailableProvider != null) {
//                locationManager.requestLocationUpdates(bestAvailableProvider, minTime, minDistance, this);
//            } else {
//                // (Display a message/dialog) No Location Providers currently available.
//            }
//        }
//
//        /* Deactivates this provider.
//         * This method is automatically invoked by disabling my-location layer. */
//        @Override
//        public void deactivate() {
//            // Remove location updates from Location Manager
//            locationManager.removeUpdates(this);
//
//            mListener = null;
//        }
//
//        @Override
//        public void onLocationChanged(Location location) {
//            /* Push location updates to the registered listener..
//             * (this ensures that my-location layer will set the blue dot at the new/received location) */
//            if (mListener != null) {
//                mListener.onLocationChanged(location);
//                Toast.makeText(mContext, "Change", Toast.LENGTH_SHORT).show();
//            }
//
//            /* ..and Animate camera to center on that location !
//             * (the reason for we created this custom Location Source !) */
////            mMap.animateCamera(CameraUpdateFactory.newLatLng(new LatLng(location.getLatitude(), location.getLongitude())));
//        }
//
//        @Override
//        public void onStatusChanged(String s, int i, Bundle bundle) {
//
//        }
//
//        @Override
//        public void onProviderEnabled(String s) {
//
//        }
//
//        @Override
//        public void onProviderDisabled(String s) {
//
//        }
//    }
//
//
//	
//	
}
