import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

public class PermitFetcher {
   private List<PermitLot> permit;
   
   public PermitFetcher (String permitFile) {
      JSONParser parser = new JSONParser();
      JSONArray permitObjects = parser(new FileReader(permitFile));
      for (JSONObject permitObject: permitObjects) {
         permit.add(createPermitLot((String) permitObject.get("ParkingLot"),
            (JSONArray) permitObject.get("Permits"), (JSONObject) 
            permitObject.get("Hours"), (JSONArray) permitObject.get(
            "Location")));
   }

   private static PermitLot createPermitLot(String name, JSONArray 
      JSONpermits, JSONObject JSONhours, JSONArray JSONlocation) {
      List<String> permits = new ArrayList<>();
      for (Object permit : JSONpermits) {
         permits.add((String) permit);
      }
      Hours hours = 
      return new PermitLot(name, permitList, );
