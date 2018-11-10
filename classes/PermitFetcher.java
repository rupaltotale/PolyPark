import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

public class PermitFetcher {
   private List<PermitLot> permitLots;
   
   public PermitFetcher (String permitFile) {
      JSONParser parser = new JSONParser();
      JSONArray permitObjects = parser(new FileReader(permitFile));
      for (JSONObject permitObject : permitObjects) {
         permitLots.add(createPermitLot(permitObject.getString(
            "ParkingLot"), permitObject.getJSONArray("Permits"), 
            permitObject.getJSONObject("Hours"), permitObject.getJSONArray(
            "Location")));
   }

   private static PermitLot createPermitLot(String name, JSONArray 
      JSONPermits, JSONObject JSONHours, JSONArray JSONLocation) {
      List<String> permits = new ArrayList<>();
      for (JSONValue permit : JSONpermits) {
         permits.add((String) permit);
      }
      Hours hours = createHours(JSONHours);
      
      
      return new PermitLot(name, permits, hours, location);

   private static Hours createHours(JSONObject JSONHours) {
      Hours hours = new Hours();
      Iterator<String> keys = JSONHours.keys();
      JSONArray period;

      while(keys.hasNext()) {
         String key = keys.next();
         period = Hours.getJSONArray(key);
         hours.addParkingHours(key, new TimePeriod(Time.convertDoubleToTime(
            period.getDouble(0)), Time.convertDoubleToTime(
            period.getDouble(1))));
      }
      return hours;
   }

   private static Location createLocation(JSONArray JSONLocation) {
      return new Location(JSONLocation.getDouble(0),
         JSONLocation.getDouble(1));
   }

   public List<PermitLot> getPotentialLots(String permit, String day, 
      TimePeriod parkingPeriod) {
      return permitLots.stream.filter(lot -> lot.canPark()).collect(
         Collectors.toList());
   }
}
