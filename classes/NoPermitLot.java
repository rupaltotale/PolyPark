public class NoPermitLot extends Lot {
   private double hourlyRate;

   public NoPermitLot(String name, Hours hours, Location location, double
      hourlyRate) {
      super(name, hours, location);
      this.hourlyRate = hourlyRate;
   }

   public boolean canPark(String day, TimePeriod period) {
      return this.hours.isOpen(day, period);
   }
} 
