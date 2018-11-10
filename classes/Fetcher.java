import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

public class Fetcher {
   private JSONArray permit, noPermit;
   
   public Fetcher (String permitFile, String noPermitFile) {
      JSONParser parser = new JSONParser();
      this.permit = parser(new FileReader(permitFile));
      this.noPermit = parser(new FileReader(noPermitFile));
   }

   public List<ParkingLot> findLots(String permit, 
