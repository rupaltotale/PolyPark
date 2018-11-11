import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Collectors;
import java.util.*;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class PermitFetcher {
   private List<PermitLot> permitLots;
   
   public PermitFetcher (String permitFile) {
      permitLots = new ArrayList<PermitLot>();
      JSONParser parser = new JSONParser();
      try {
         JSONArray permitObjects = (JSONArray) parser.parse(new FileReader(
            permitFile));
         JSONObject permitObject;
         for (int i = 0; i < permitObjects.length(); i++) {
            permitObject = permitObjects.getJSONObject(i);
            permitLots.add(createPermitLot(permitObject.getString(
               "ParkingLot"), permitObject.getJSONArray("Permits"), 
               permitObject.getJSONObject("Hours"), 
               permitObject.getJSONArray("Location")));
         }
      }
      catch(Exception e) {}
   }

   private static PermitLot createPermitLot(String name, JSONArray 
      JSONPermits, JSONObject JSONHours, JSONArray JSONLocation) {
      List<String> permits = new ArrayList<>();
      Hours hours = null;
      Location location = null;
      try {
      for (int i = 0; i < JSONPermits.length(); i++) {
         permits.add(JSONPermits.getString(i));
      }
      hours = createHours(JSONHours);
      location = createLocation(JSONLocation);
      }
      catch (Exception e) {}
      return new PermitLot(name, permits, hours, location);
   }

   private static Hours createHours(JSONObject JSONHours) {
      Hours hours = new Hours();
      Iterator<String> keys = JSONHours.keys();
      JSONArray period;
      try {
      while(keys.hasNext()) {
         String key = keys.next();
         period = JSONHours.getJSONArray(key);
         hours.addParkingHours(key, new TimePeriod(Time.convertDoubleToTime(
            period.getDouble(0)), Time.convertDoubleToTime(
            period.getDouble(1))));
      }
      }
      catch(Exception e) {}
      return hours;
   }

   private static Location createLocation(JSONArray JSONLocation) {
      try {
      return new Location(JSONLocation.getDouble(0),
         JSONLocation.getDouble(1));
      }
      catch(Exception e) {}
      return null;
   }

   public List<PermitLot> getPotentialLots(String permit, String day, 
      TimePeriod parkingPeriod) {
      return permitLots.stream().filter(lot -> lot.canPark(permit, day,
         parkingPeriod)).collect(
         Collectors.toList());
   }

   public static void main(String[] args) {
      PermitFetcher haha = new PermitFetcher("permitParkingSpaces.json");
      List<PermitLot> wow = haha.getPotentialLots("ABC", "Monday", new 
         TimePeriod(Time.convertDoubleToTime(7), Time.convertDoubleToTime(
         19)));
      System.out.print(wow.get(0).getName());
   }
}
