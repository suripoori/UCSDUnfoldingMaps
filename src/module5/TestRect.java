package module5;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.GeoJSONReader;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.AbstractShapeMarker;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.marker.MultiMarker;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.providers.MBTilesMapProvider;
import de.fhpotsdam.unfolding.utils.MapUtils;
import parsing.ParseFeed;
import processing.core.PApplet;

public class TestRect extends PApplet {
	
	// You can ignore this.  It's to get rid of eclipse warnings
		private static final long serialVersionUID = 1L;

		// IF YOU ARE WORKING OFFILINE, change the value of this variable to true
		private static final boolean offline = false;
		
		/** This is where to find the local tiles, for working without an Internet connection */
		public static String mbTilesString = "blankLight-1-3.mbtiles";
		
		private UnfoldingMap map;

		//feed with magnitude 2.5+ Earthquakes
		private String earthquakesURL = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/2.5_week.atom";
		
		public void setup() {
			map = new UnfoldingMap(this, 200, 50, 1300, 1200, new Google.GoogleMapProvider());
			MapUtils.createDefaultEventDispatcher(this, map);
		}
		
		public void draw() {
			background(0);
			map.draw();
			fill(255, 255, 255);
			rect(25, 50, 50, 50);
			fill(100, 100, 100);
			rect(25, 150, 50, 50);
		}
}
