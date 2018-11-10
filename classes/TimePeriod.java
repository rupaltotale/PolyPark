public class TimePeriod {
   private Time start, end;

   public TimePeriod(Time start, Time end) {
      if (!start.isBefore(end)) {
         throw new java.lang.IllegalArgumentException();
      }
      this.start = start;
      this.end = end;
   }

   public TimePeriod parkablePeriod(TimePeriod wantToPark) {
      Time starting = ((wantToPark.start.isBefore(this.start)) ?
         this.start.cloneThis() : wantToPark.start.cloneThis());
      Time ending = ((wantToPark.end.isAfter(this.end)) ?
         this.end.cloneThis() : wantToPark.end.cloneThis());
   }
}
