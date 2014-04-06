package com.temporun.integration;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

public class LocationLoggerService extends Service implements LocationListener {

 private final static String TAG = "LocationLoggerService";
 LocationManager lm;
 

 public LocationLoggerService() {
 }

 @Override
 public IBinder onBind(Intent intent) {
  return null;
 }
 
 @Override
 public void onCreate() {
  subscribeToLocationUpdates();
  ComponentName comp = new ComponentName(getPackageName(),   LocationLoggerService.class.getName());
  ComponentName service = startService(new Intent().setComponent(comp));

 }
 
    public void onLocationChanged(Location loc) {
     Log.d(TAG, loc.toString());
    }
    public void onProviderEnabled(String s){
    }
    public void onProviderDisabled(String s){ 
    }
    public void onStatusChanged(String s, int i, Bundle b){
    }
   
    public void subscribeToLocationUpdates() {
        this.lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
  this.lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
    }
}