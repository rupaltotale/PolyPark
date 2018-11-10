public class PermitLot {
   private String name;
   private List<String> permits;
   private Hours hours;
   private Locaton location;

   public PermitLot(String name, List<String> permits, Hours hours,
      Location location) {
      this.name = name;
      this.permits = permits;
      this.hours = hours;
      this.location = location;
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
