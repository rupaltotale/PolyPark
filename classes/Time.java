public class Time {
   private int hour, minute;

   public Time(int hour, int minute) {
      this.hour = hour;
      this.minute = minute;
   }

   public boolean isAfter(Time that) {
      return that.hour < this.hour && that.minute < that.hour;
   }

   public boolean isBefore(Time that) {
      return that.hour > this.hour && that.minute > that.hour;
   }

   public Time cloneThis() {
      return new Time(this.hour, this.minute);
   }
}
