import java.util.List;

public class PermitLot extends Lot {
   private List<String> permits;

   public PermitLot(String name, Hours hours, Location location,
      List<String> permits) {
      super(name, hours, location);
      this.permits = permits;
   }

   public List<String> getPermits() {
      return this.permits;
   }

   public void setPermits(List<String> newPermits) {
      this.permits = permits;
   }

   private boolean permitIsValid(String permit) {
      return this.permits.contains(permit);
   }

   private boolean isOpen(String day, TimePeriod period) {
      return this.hours.isOpen(day, period);
   }

   public boolean canPark(String permit, String day, TimePeriod period) {
      return this.permitIsValid(permit) && this.isOpen(day, period);
   }

   @Override
   public String toString() {
      String result = "";
      result = result.concat(String.format("Lot: " + this.name +
         "\nValid Permits:"));
      for (String permit : this.permits) {
         result = result.concat(String.format(" " + permit));
      }
      result = result.concat(String.format("\nOpen: \n" + this.hours));
      return result;
   }
}
