import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.util.stream.Collectors;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class PermitFetcher {
   private List<PermitLot> permitLots;
   
   public PermitFetcher(String permitFile) {
      permitLots = new ArrayList<PermitLot>();
      JSONParser parser = new JSONParser();
      JSONArray permitObjects = null;
      try {
         permitObjects = (JSONArray)parser.parse(new FileReader(
            permitFile));
      }
      catch(Exception e) {
         System.out.print("Error reading database.");
      }
      JSONObject permitObject;
      for (Object o : permitObjects) {
         permitObject = (JSONObject)o;
         permitLots.add(createPermitLot((String)permitObject.get(
            "ParkingLot"), (JSONArray)permitObject.get("Permits"),
            (JSONObject)permitObject.get("Hours"),
            (JSONArray)permitObject.get("Location")));
      }
   }

   private static PermitLot createPermitLot(String name, JSONArray 
      JSONPermits, JSONObject JSONHours, JSONArray JSONLocation) {
      List<String> permits = new ArrayList<String>();
      for (Object permit : JSONPermits) {
         permits.add((String) permit);
      }
      Hours hours = createHours(JSONHours);
      Location location = createLocation(JSONLocation);
      return new PermitLot(name, hours, location, permits);
   }

   private static Hours createHours(JSONObject JSONHours) {
      Hours hours = new Hours();
      JSONArray period;
      for (Object key : JSONHours.keySet()) {
         period = (JSONArray) JSONHours.get((String) key);
         hours.setHours((String) key, new TimePeriod((long)period.get(0),
            (long)period.get(1)));
      }
      return hours;
   }

   private static Location createLocation(JSONArray JSONLocation) {
      return new Location((double) JSONLocation.get(0),
         (double) JSONLocation.get(1));
   }

   public List<PermitLot> getPotentialLots(String permit, String day, 
      TimePeriod parkingPeriod) {
      return this.permitLots.stream().filter(lot -> lot.canPark(permit, day,
         parkingPeriod)).collect(
         Collectors.toList());
   }

   public static void main(String[] args) {
      PermitFetcher haha = new PermitFetcher("permitParkingSpaces.json");
      List<PermitLot> wow = haha.getPotentialLots("ABC", "Monday", new 
         TimePeriod(new Time(7), new Time(8)));
      System.out.print(wow.get(0));
   }
}
