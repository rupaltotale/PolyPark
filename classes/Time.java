public class Time {
   private int hour, minute;

   public Time(int hour, int minute) {
      this.hour = hour;
      this.minute = minute;
   }

   public boolean isAfter(Time that) {
      return that.hour <= this.hour && that.minute <= that.hour;
   }

   public boolean isBefore(Time that) {
      return that.hour >= this.hour && that.minute >= that.hour;
   }

   public Time cloneThis() {
      return new Time(this.hour, this.minute);
   }

   public static Time convertDoubleToTime(double doubleTime) {
      int hour = (int) doubleTime;
      int minute = (int) ((doubleTime - hour) * 60);
      return new Time(hour, minute);
   }
}
