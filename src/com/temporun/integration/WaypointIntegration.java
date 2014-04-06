package com.temporun.integration;

import java.math.BigDecimal;
import java.util.ArrayList;

import android.content.Context;
import android.location.Location;

import com.temporun.dao.WaypointSQLite;
import com.temporun.dto.Run;
import com.temporun.dto.Waypoint;

public class WaypointIntegration {
	private WaypointSQLite waypointSQLite;
	private Context context;
	
	public WaypointIntegration(Context context){
		this.context = context;
		this.waypointSQLite = new WaypointSQLite(this.context);
	}
	
	public String getDistanceRun(Run run) throws Exception{
		
		
		
		ArrayList<Waypoint> waypoints = this.waypointSQLite.getAllWaypointsByRun(run);
		float totalDistanceRun = 0.0f;
		for (int i = 0; i < waypoints.size() -1; i++) {
			float [] dist = new float[1];
			Location.distanceBetween(Double.parseDouble(waypoints.get(i).getLatitude()), Double.parseDouble(waypoints.get(i).getLongitude()), Double.parseDouble(waypoints.get(i+1).getLatitude()), Double.parseDouble(waypoints.get(i+1).getLongitude()), dist);
			totalDistanceRun += dist[0];
			
		}
		
		//start at index 1 because there are n-1 segments
		BigDecimal bd = new BigDecimal(Float.toString(totalDistanceRun/1600));
        bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
       
		return bd.toString();
		
	}
}
