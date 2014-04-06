package com.temporun.dto;

public class Waypoint {
		//The longitude of the truck in its current location
		private String longitude;
		//The latitude of the truck in its current location
		private String latitude;
		private long time;
		private int runID;

		/**
		 * @return the longitude
		 */
		public String getLongitude() {
			return longitude;
		}

		/**
		 * @param longitude the longitude to set
		 */
		public void setLongitude(String longitude) {
			this.longitude = longitude;
		}

		/**
		 * @return the latitude
		 */
		public String getLatitude() {
			return latitude;
		}

		/**
		 * @param latitude the latitude to set
		 */
		public void setLatitude(String latitude) {
			this.latitude = latitude;
		}

		/**
		 * @return the time
		 */
		public long getTime() {
			return time;
		}

		/**
		 * @param time the time to set
		 */
		public void setTime(long time) {
			this.time = time;
		}

		public int getRunID() {
			return runID;
		}

		public void setRunID(int runID) {
			this.runID = runID;
		}
		
	
}
