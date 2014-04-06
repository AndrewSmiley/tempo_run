package com.temporun.dao;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.temporun.dto.Run;
import com.temporun.dto.Waypoint;

public class WaypointSQLite extends SQLiteOpenHelper{
	public static String WAYPOINT_TABLE = "Waypoints";
	public static String COL_ID = "_id";
	public static String COL_LONGITUDE = "Longitude";
	public static String COL_LATITUDE = "Latitude";
	public static String COL_TIME =  "Time";
	public static String COL_RUN_ID = "Run_ID";
	
	
//	//The longitude of the truck in its current location
//			private String longitude;
//			//The latitude of the truck in its current location
//			private String latitude;
//			private long time;
//			private int runID;
	
	public WaypointSQLite(Context context) {
	super(context, WAYPOINT_TABLE, null, 1);
}
	// TODO Auto-generated constructor stub


@Override
public void onCreate(SQLiteDatabase arg0) {
	arg0.execSQL("Create Table " + WAYPOINT_TABLE+ " ("
			+ COL_ID + " INTEGER PRIMARY KEY, " + COL_LONGITUDE
			+ " TEXT, "  + COL_LATITUDE+
			 " TEXT, "+COL_RUN_ID+
			 " INTEGER, "+COL_TIME+" TEXT);");
	

}



@Override
public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
	// TODO Auto-generated method stub

}

public void addWaypoint(Waypoint waypoint){
	ContentValues contentValues = new ContentValues();
	contentValues.put(COL_LONGITUDE, waypoint.getLongitude());
	contentValues.put(COL_LATITUDE, waypoint.getLatitude());
	contentValues.put(COL_RUN_ID, waypoint.getRunID());
	contentValues.put(COL_TIME, waypoint.getTime());
	
	getWritableDatabase().insert(WAYPOINT_TABLE, null, contentValues);
	
	
	
}


public ArrayList<Waypoint> getAllWaypointsByRun(Run run) throws Exception{
	ArrayList<Waypoint> waypoints = new ArrayList<Waypoint>();
	String sql = "SELECT * FROM "+WAYPOINT_TABLE+" WHERE "+COL_RUN_ID+" = "+run.getId();
	Cursor cursor = getReadableDatabase().rawQuery(sql, null);
	if(cursor.getCount() != 0 ){
		cursor.moveToFirst();
		while(!cursor.isAfterLast()){
			Waypoint waypoint = new Waypoint();
			waypoint.setLongitude(cursor.getString(1));
			waypoint.setLatitude(cursor.getString(2));
			waypoint.setRunID(cursor.getInt(3));
			waypoint.setTime(cursor.getLong(4));
			waypoints.add(waypoint);
			cursor.moveToNext();
		}
		cursor.close();
	}else{
		throw new Exception();
	}
	
	return waypoints;
	
	
}
}
