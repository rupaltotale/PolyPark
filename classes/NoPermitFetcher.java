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

public class NoPermitFetcher {
   private List<NoPermitLot> noPermitLots;

   public NoPermitFetcher(String noPermitFile) {
      noPermitLots = new ArrayList<NoPermitLot>();
      JSONParser parser = new JSONParser();
      try {
         JSONArray noPermitObjects = (JSONArray) parser.parse(new 
            FileReader(noPermitFile));
      }
      catch(Exception e) {}
   }
}
