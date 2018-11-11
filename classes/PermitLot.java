import java.util.*;

public class PermitLot {
   private String name;
   private List<String> permits;
   private Hours hours;
   private Location location;

   public PermitLot(String name, List<String> permits, Hours hours,
      Location location) {
      this.name = name;
      this.permits = permits;
      this.hours = hours;
      this.location = location;
   }

   public String getName() {
      return this.name;
   }

   private boolean permitIsValid(String permit) {
      return permits.contains(permit);
   }

   private boolean isOpen(String day, TimePeriod period) {
      return this.hours.isOpen(day, period);
   }

   public boolean canPark(String permit, String day, TimePeriod period) {
      return this.permitIsValid(permit) && this.isOpen(day, period);
   }
}
