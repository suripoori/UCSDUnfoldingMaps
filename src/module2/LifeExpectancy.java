package module2;

import processing.core.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.GeoJSONReader;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.providers.AbstractMapProvider;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.providers.MBTilesMapProvider;
import de.fhpotsdam.unfolding.utils.MapUtils;

public class LifeExpectancy extends PApplet{
	
	// You can ignore this.  It's to keep eclipse from reporting a warning
	private static final long serialVersionUID = 1L;
	UnfoldingMap map;
	Map<String, Float> lifeExpByCountry;
	List<Feature> countries;
	List<Marker> countryMarkers;
	
	public void setup(){
		size(1600, 1200, OPENGL);
		map = new UnfoldingMap(this, 50, 50, 1400, 1000, new Google.GoogleMapProvider());
		MapUtils.createDefaultEventDispatcher(this, map);
		lifeExpByCountry = loadLifeExpectancyFromCSV("C:/devtrees/version-control/UCSDUnfoldingMaps/data/LifeExpectancyWorldBank.csv");
		countries = GeoJSONReader.loadData(this, "C:/devtrees/version-control/UCSDUnfoldingMaps/data/countries.geo.json");
		countryMarkers = MapUtils.createSimpleMarkers(countries);
		map.addMarkers(countryMarkers);
		shadeCountries();
	}
	
	public void draw(){
		map.draw();
	}
	
	private Map<String, Float> loadLifeExpectancyFromCSV(String fileName){
		Map<String, Float> lifeExpMap = new HashMap<String, Float>();
		String[] rows = loadStrings(fileName);
		for (String row : rows){
			String[] columns = row.split(",");
			System.out.println(columns[6]);
			if (!columns[6].equals("..")){
				float value = Float.parseFloat(columns[6]);
				lifeExpMap.put(columns[4], value);
			}
		}
		return lifeExpMap;
	}
	
	private void shadeCountries(){
		for(Marker marker : countryMarkers){
			String countryId = marker.getId();
			if(lifeExpByCountry.containsKey(countryId)){
				float lifeExp = lifeExpByCountry.get(countryId);
				int colorLevel = (int) map(lifeExp, 40, 90, 10, 255);
				marker.setColor(color(255-colorLevel, 100, colorLevel));
			}
			else{
				marker.setColor(color(150, 150, 150));
			}
		}
	}
}
