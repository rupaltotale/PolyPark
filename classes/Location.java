public class Location {
   private double longitude, latitude;

   public Location(double longitude, double latitude) {
      this.longitude = longitude;
      this.latitude = latitude;
   }

   public double distance(Location that) {
      return Math.sqrt(Math.pow(this.longitude - that.longitude, 2) +
         Math.pow(this.latitude - that.latitude, 2));
   }
}
