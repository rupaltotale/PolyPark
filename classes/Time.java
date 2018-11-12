public class Time {
   private final int hour, minute;

   public Time(int hour, int minute) {
      this.hour = hour;
      this.minute = minute;
   }

   public Time(long longTime) {
      this.hour = (int) longTime;
      this.minute = (int) ((longTime - hour) * 60);
   }

   public boolean isAfter(Time that) {
      if (this.hour == that.hour) return this.minute > that.minute;
      return this.hour > that.hour;
   }

   public boolean isBefore(Time that) {
      if (this.hour == that.hour) return this.minute < that.minute;
      return this.hour < that.hour;
   }

   public Time copy() {
      return new Time(this.hour, this.minute);
   }

   public String toString() {
      return String.format(this.hour + ":" + this.minute);
   }
}
