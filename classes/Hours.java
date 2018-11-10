public class Hours {
   private HashMap<DayEnum, TimePeriod> dayToParkingHours;

   public Hours() {
      this.dayToParkingHours = new HashMap<DayEnum, TimePeriod>()
   }

   public addParkingHours(DayEnum day, TimePeriod parkingHours) {
      dayToParkingHours.put(day, parkingHours);
   }
}
