package com.slohack.lizhengzheng.polypark;

public class PermitLot {
	private String lot;
	private String info;
	private Location loc;
	private long rate;
	public PermitLot(String lot, String info, Location loc) {
		super();
		this.lot = lot;
		this.info = info;
		this.loc = loc;
		this.rate = 0;
	}
	public PermitLot(String lot, String info, Location loc, long rate) {
		super();
		this.lot = lot;
		this.info = info;
		this.loc = loc;
		this.rate = rate;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String str = "";
		str += "Lot: " + lot + "\n";
		str += "Location: [" + loc.getLatitude() + "," +loc.getLongitude()+ "]\n";
		str += "Info: " + info + "\n";
		str += "Rate: " + rate + "\n";
		return str;
	}
	
	
	
}
