import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Collectors;
import java.util.*;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class NoPermitFetcher {
   private List<NoPermitLot> noPermitLots;

   public NoPermitFetcher(String noPermitFile) {
      noPermitLots = new ArrayList<NoPermitLot>();
      JSONParser parser = new JSONParser();
      JSONArray noPermitObjects = null;
      try {
      noPermitObjects = (JSONArray) parser.parse(new FileReader(
         noPermitFile));
      }
      catch(Exception e) {
         System.out.print("Error reading database.");
      }
      JSONObject noPermitObject;
      for (Object o : noPermitObjects) {
         noPermitObject = (JSONObject) o;
         noPermitLots.add(createNoPermitLot((String)noPermitObject.get(
            "ParkingLot"), (JSONObject) noPermitObject.get("Hours"),
            (JSONArray) noPermitObject.get("Location"), (double)
            noPermitObject.get("HourlyRate")));
      }
   }

   private static NoPermitLot createNoPermitLot(String name, JSONObject
      JSONHours, JSONArray JSONLocation, double hourlyRate) {
      Hours hours = createHours(JSONHours);
      Location location = createLocation(JSONLocation);
      return new NoPermitLot(name, hours, location, hourlyRate);
   }
      
   private static Hours createHours(JSONObject JSONHours) {
      Hours hours = new Hours();
      JSONArray period;
      for (Object key : JSONHours.keySet()) {
         period = (JSONArray) JSONHours.get((String) key);
         hours.setHours((String) key, new TimePeriod(
            (long) period.get(0),
            (long) period.get(1)));
      }
      return hours;
   }

   private static Location createLocation(JSONArray JSONLocation) {
      return new Location((double) JSONLocation.get(0),
         (double) JSONLocation.get(1));
   }

   public List<NoPermitLot> getPotentialLots(String day, TimePeriod
      period) {
      return this.noPermitLots.stream().filter(lot -> lot.canPark(day, 
         period)).collect(Collectors.toList());
   }
}
