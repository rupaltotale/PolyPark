public class TimePeriod {
   private Time start, end;

   public TimePeriod(Time start, Time end) {
      if (!start.isBefore(end)) {
         throw new java.lang.IllegalArgumentException();
      }
      this.start = start;
      this.end = end;
   }

   public TimePeriod(long start, long end) {
      if (start >= end) {
         throw new java.lang.IllegalArgumentException();
      }
      this.start = new Time(start);
      this.end = new Time(end);
   }

   public Time getStart() {
      return this.start;
   }

   public Time getEnd() {
      return this.end;
   }

   public void setStart(Time newStart) {
      this.start = newStart;
   }

   public void setStart(long newStart) {
      this.start = new Time(newStart);
   }

   public void setEnd(Time newEnd) {
      this.end = newEnd;
   }

   public void setEnd(long newEnd) {
      this.end = new Time(newEnd);
   }

   public boolean isWithin(TimePeriod that) {
      return this.start.isAfter(that.start) && this.end.isBefore(that.end);
   }

   public TimePeriod overlappingPeriod(TimePeriod that) {
      Time starting = ((that.start.isBefore(this.start)) ?
         this.start.copy() : that.start.copy());
      Time ending = ((that.end.isAfter(this.end)) ?
         this.end.copy() : that.end.copy());
      return new TimePeriod(starting, ending);
   }

   public String toString() {
      return String.format(this.start + " - " + this.end);
   }
}
