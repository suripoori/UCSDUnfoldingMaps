package module6;

public class Airport {
	private String city;
	private String country;
	private String code3;
	
	public String getCity() { return this.city; }
	public String getCountry() { return this.country; }
	public String getCode() { return this.code3; }
	
	public static String findAirportCode(String toFind, Airport[] airports){
		for (Airport airport : airports){
			if (airport.getCity().equals(toFind)){
				return airport.getCode();
			}
		}
		return null;
	}
	
	public static String findAirportCodeBS(String toFind, Airport[] airports){
		int low = 0;
		int high = airports.length-1;
		int mid;
		while (low <= high){
			mid = low + (high - low)/2;
			int compare = toFind.compareTo(airports[mid].getCity());
			if (compare < 0){
				high = mid - 1;
			}
			else if (compare > 0){
				low = mid + 1;
			}
			else{
				return airports[mid].getCode();
			}
		}
		return null;
	}
}
