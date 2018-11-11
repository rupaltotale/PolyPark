package com.slohack.lizhengzheng.polypark;

public class Time {
	private int hour, minute;
	private double decimalTime;

	public Time(int hour, int minute) {
		this.hour = hour;
		this.minute = minute;
		this.decimalTime = this.hour + this.minute / 60;
	}

	public Time(double decimalTime) {
		this.decimalTime = decimalTime;
		this.hour = (int) this.decimalTime;
		this.minute = (int) (decimalTime - Math.floor(decimalTime)) * 60;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

	public double getDecimalTime() {
		return decimalTime;
	}

	public void setDecimalTime(double decimalTime) {
		this.decimalTime = decimalTime;
	}

	/**
	 * Returns true if self is less than parameter
	 */
	public boolean isAfter(double that) {
		return this.decimalTime < that;
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
