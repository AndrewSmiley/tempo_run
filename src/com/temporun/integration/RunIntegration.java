package com.temporun.integration;

import android.content.Context;

import com.temporun.dto.Run;

public class RunIntegration {
	WaypointIntegration waypointIntegration;
	Context context;
	
	public RunIntegration(Context context){
		this.waypointIntegration = new WaypointIntegration(context);
		this.context = context;
		
	}
	
	public Double calculateCaloriesBurned(Run run) throws Exception{
		Double distance = Double.parseDouble(waypointIntegration.getDistanceRun(run));
		//Stub this out for now
		
		Double baseWeight = 150.00;
		Double caloriesBurned = (0.75*baseWeight)*distance;
		
		return caloriesBurned;
	}
	
	
	public Double calculateAverageSpeed(Run run) throws NumberFormatException, Exception{
		Double distance = Double.parseDouble(waypointIntegration.getDistanceRun(run));
		Double speed  = distance/Double.parseDouble(run.getTime());
		return speed;
	}
	
}
