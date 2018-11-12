import java.util.HashMap;

public class Hours {
   private HashMap<String, TimePeriod> dayToHours;

   public Hours() {
      this.dayToHours = new HashMap<String, TimePeriod>();
   }

   public void setHours(String day, TimePeriod parkingHours) {
      dayToHours.put(day, parkingHours);
   }

   public boolean isOpen(String day, TimePeriod period) {
      return period.isWithin(dayToHours.get(day));
   }

   public String toString() {
      String result = "";
      result = result.concat(String.format("Monday: " + 
         this.dayToHours.get("Monday")));
      result = result.concat(String.format("\nTuesday: " + 
         this.dayToHours.get("Tuesday")));
      result = result.concat(String.format("\nWednesday: " + 
         this.dayToHours.get("Wednesday")));
      result = result.concat(String.format("\nThursday: " + 
         this.dayToHours.get("Thursday")));
      result = result.concat(String.format("\nFriday: " + 
         this.dayToHours.get("Friday")));
      result = result.concat(String.format("\nSaturday: " + 
         this.dayToHours.get("Saturday")));
      result = result.concat(String.format("\nSunday: " + 
         this.dayToHours.get("Sunday")));
      return result;
   }
}
