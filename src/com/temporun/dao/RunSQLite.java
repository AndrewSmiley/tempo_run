package com.temporun.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.temporun.dto.Run;

public class RunSQLite extends SQLiteOpenHelper {
//	private Double distance;
//	private String notes;
//	private int id;
//	private String time;
//	private Double caloriesBurned;
	public static final String RUN_TABLE = "Run";
	public static final String COL_NOTES = "Notes";
	public static final String COL_ID = "_id";
	public static final String COL_TIME = "Time";
	public static final String COL_CALORIES = "Calories_Burned";
	public static final String SELECT_MOST_RECENT_RUN = "SELECT * FROM "+RUN_TABLE+ " ORDER BY "+COL_ID+" DESC LIMIT 1";

			public RunSQLite(Context context) {
		super(context, RUN_TABLE, null, 1);
	}
		// TODO Auto-generated constructor stub
	

	@Override
	public void onCreate(SQLiteDatabase arg0) {
		arg0.execSQL("Create Table " + RUN_TABLE + " ("
				+ COL_ID + " INTEGER PRIMARY KEY, " + COL_NOTES
				+ " TEXT, "  + COL_TIME
				+ " TEXT, "+COL_CALORIES+" TEXT);");
		

	}

	

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}
	
	
	public Run createRun() throws Exception{
		
		ContentValues contentValues = new ContentValues();
contentValues.put(COL_CALORIES, "");
contentValues.put(COL_NOTES, "");
contentValues.put(COL_TIME, "");

//		contentValues.putNull(COL_NOTES);
//		contentValues.putNull(COL_TIME);
		//insert the new run into the table
		getWritableDatabase().insert(RUN_TABLE, null, contentValues);
		
//TruckOwner truck = new TruckOwner();
//		
//		String sql =  "SELECT * FROM "+SQLITE_TRUCK_DB+" WHERE "+COL_TRUCK_NAME+"= \""+truckName+"\"";
//		Cursor cursor = getReadableDatabase().rawQuery(sql, null);
//		if(cursor.getCount() != 0)
//		{
//			cursor.moveToFirst();
//			while(!cursor.isAfterLast())
//			{
//				truck.setTruckID(cursor.getInt(0));
//				truck.setTruckName(cursor.getString(1));
//				truck.setLongitude(cursor.getString(2));
//				truck.setLatitude(cursor.getString(3));
//				truck.setHoursAtLocation(cursor.getLong(4));
//				
//				cursor.moveToNext();
//			}
//			cursor.close();
//		
		Run run = new Run();
		Cursor cursor = getReadableDatabase().rawQuery(SELECT_MOST_RECENT_RUN, null);
		if(cursor.getCount() != 0 ){
			cursor.moveToFirst();
			while(!cursor.isAfterLast()){
				run.setId(cursor.getInt(0));
				cursor.moveToNext();
			}
			cursor.close();
		}else{
			throw new Exception();
		}
		
		return run;
	}
	
	public Run getCurrentRun() throws Exception
	{
		Run run = new Run();
		Cursor cursor = getReadableDatabase().rawQuery(SELECT_MOST_RECENT_RUN, null);
		if(cursor.getCount() != 0 ){
			cursor.moveToFirst();
			while(!cursor.isAfterLast()){
				run.setId(cursor.getInt(0));
				run.setTime(cursor.getString(2));
				cursor.moveToNext();
			}
			cursor.close();
		}else{
			throw new Exception();
		}
		
		return run;
	}
	
	
	public void updateRunTime(Run run){
		
//		String straightSQL = "Update "+RUN_TABLE +" SET "+COL_TIME+"=\""+run.getTime()+"\" where "+COL_ID+"="+run.getId();
//		getWritableDatabase().rawQuery(straightSQL, null);
		ContentValues contentValues = new ContentValues();
		contentValues.put(COL_ID, run.getId());
		contentValues.put(COL_TIME, run.getTime());
//		String[] args = new String[1]; 
//		args[0] = Integer.toString(run.getId());
		getWritableDatabase().replace(RUN_TABLE, null, contentValues);
		
	}
	
	
	public void saveRunDetails(Run run)
	{
		ContentValues contentValues = new ContentValues();
		contentValues.put(COL_ID, run.getId());
		contentValues.put(COL_NOTES, run.getNotes());
		contentValues.put(COL_CALORIES, run.getCaloriesBurned());
		contentValues.put(COL_TIME, run.getTime());
		getWritableDatabase().replace(RUN_TABLE, null, contentValues);
		
		
	}

}
