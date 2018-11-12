public abstract class Lot {
   protected final String name;
   protected Hours hours;
   protected final Location location;

   protected Lot(String name, Hours hours, Location location) {
      this.name = name;
      this.hours = hours;
      this.location = location;
   }

   public String getName() {
      return this.name;
   }

   public Hours getHours() {
      return this.hours;
   }

   public Location getLocation() {
      return this.location;
   }
   
/*   public void setOpenTime(String day, Time time) {
      this.hours.setOpenTime(day, time);
   }

   public void setOpenTime(String day, long time) {
      this.hours.setOpenTime(day, time);
   }

   public void setCloseTime(String day, Time time) {
      this.hours.setCloseTime(day, time);
   }

   public void setCloseTime(String day, long time) {
      this.hours.setCloseTime(day, time);
   }*/
}
