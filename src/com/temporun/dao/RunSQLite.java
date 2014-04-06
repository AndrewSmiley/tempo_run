package com.temporun.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

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
	public static final String COL_CALORIES = "Calories Burned";

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

}
