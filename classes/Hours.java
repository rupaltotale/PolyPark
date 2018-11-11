import java.util.HashMap;

public class Hours {
   private HashMap<String, TimePeriod> dayToParkingHours;

   public Hours() {
      this.dayToParkingHours = new HashMap<String, TimePeriod>();
   }

   public void addParkingHours(String day, TimePeriod parkingHours) {
      dayToParkingHours.put(day, parkingHours);
   }

   public boolean isOpen(String day, TimePeriod period) {
      return period.isWithin(dayToParkingHours.get(day));
   }
}
