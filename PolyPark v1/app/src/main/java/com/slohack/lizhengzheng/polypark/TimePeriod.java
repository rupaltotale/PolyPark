package com.slohack.lizhengzheng.polypark;

public class TimePeriod {
	private Time start, end;

	public Time getStart() {
		return start;
	}

	public void setStart(Time start) {
		this.start = start;
	}

	public Time getEnd() {
		return end;
	}

	public void setEnd(Time end) {
		this.end = end;
	}

	public TimePeriod(Time start, Time end) {
//		if (!start.isBefore(end)) {
//			throw new java.lang.IllegalArgumentException();
//		}
		this.start = start;
		this.end = end;
	}

	public boolean isWithin(TimePeriod that) {
		return this.start.isAfter(that.start) && this.end.isBefore(that.end);
	}

	public TimePeriod overlappingPeriod(TimePeriod that) {
		Time starting = ((that.start.isBefore(this.start)) ? this.start.cloneThis() : that.start.cloneThis());
		Time ending = ((that.end.isAfter(this.end)) ? this.end.cloneThis() : that.end.cloneThis());
		return new TimePeriod(starting, ending);
	}
}
