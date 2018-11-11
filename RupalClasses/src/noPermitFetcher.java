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
		String permit = "ABC";
		String day = "Tuesday";
		getParkingSpaces(permit, start, end, day);
	}

	public static void getParkingSpaces(String permit, Time startReq, Time endReq, String day)
			throws FileNotFoundException, IOException, ParseException {
		ArrayList<PermitLot> lots = new ArrayList<PermitLot>();

		JSONParser parser = new JSONParser();
		JSONArray a = (JSONArray) parser.parse(new FileReader("noPermitLots.json"));

		for (Object o : a) {
			JSONObject parkingSpace = (JSONObject) o;
			// Checks if parking lot fits criteria --> Checks time criteria
			boolean potentialLot = true;

			double[] time = { 0, 0 };
			JSONObject obj = (JSONObject) parkingSpace.get("Hours");
			JSONArray obj2 = (JSONArray) obj.get(day);
			for (int i = 0; i < 2; i++) {
				time[i] = (double) obj2.get(i);
			}
			double start = (double) time[0];
			double end = (double) time[1];

			if (startReq.getDecimalTime() < start || endReq.getDecimalTime() > end) {
				potentialLot = false;
			}

			if (potentialLot) {
				String lot = (String) parkingSpace.get("ParkingLot");

				JSONArray location = (JSONArray) parkingSpace.get("Location");
				Location loc = new Location((double) location.get(0), (double) location.get(1));

				String info = (String) parkingSpace.get("Info");

				PermitLot permitLot = new PermitLot(lot, info, loc);
				lots.add(permitLot);

				System.out.println(permitLot);
			}
		}
	}
}
