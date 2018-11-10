
public class PermitLot {
	private String lot;
	private String info;
	private Location loc;
	public PermitLot(String lot, String info, Location loc) {
		super();
		this.lot = lot;
		this.info = info;
		this.loc = loc;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String str = "";
		str += "Lot: " + lot;
		str += "Infor: " + info;
		str += "Location: " + loc.toString();
		return str;
	}
	
	
	
}
