import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class noPermitFetcher {
	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
		Time start = new Time(13.5);
		Time end = new Time(15.5);
		TimePeriod tp = new TimePeriod(start, end);
		String permit = "ABC";
		String day = "Tuesday";
		getParkingSpaces(permit, tp, day);
	}

	public static void getParkingSpaces(String permit, TimePeriod tp, String day)
			throws FileNotFoundException, IOException, ParseException {
		ArrayList<PermitLot> lots = new ArrayList<PermitLot>();

		JSONParser parser = new JSONParser();
		JSONArray a = (JSONArray) parser.parse(new FileReader("permitParkingSpaces.json"));

		for (Object o : a) {
			JSONObject parkingSpace = (JSONObject) o;
			// Checks if parking lot fits criteria
			boolean potentialLot = true;
			
			// Checks permit criteria
			ArrayList<String> permits = new ArrayList<>();
			for (String i : (String[]) parkingSpace.get("Permits")) {
				permits.add(i);
			}
			if (permits.indexOf(permit) == -1) {
				potentialLot = false;
			} 
			// Checks time criteria
			else {
				Time startReq = tp.getStart();
				Time endReq = tp.getEnd();
				
				double[] time = (double[])((JSONObject)parkingSpace.get("Hours")).get(day);
				double start = (double)time[0];
				double end = (double)time[1];
				
				if (!(startReq.getDecimalTime() <= start && endReq.getDecimalTime() >= end)) {
					potentialLot = false;
				}
			}

			if (potentialLot) {
				String lot = (String) parkingSpace.get("ParkingLot");

				double[] location = (double[]) parkingSpace.get("Location");
				Location loc = new Location(location[0], location[1]);

				String info = (String) parkingSpace.get("Info");
				
				lots.add(new PermitLot(lot, info, loc));
				
				
			}
		}
	}
}
