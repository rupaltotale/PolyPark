import java.util.HashMap;

public class Hours {
	private HashMap<String, TimePeriod> dayToParkingHours;

	public Hours() {
		this.dayToParkingHours = new HashMap<String, TimePeriod>();
	}

	public void addParkingHours(String day, TimePeriod parkingHours) {
		dayToParkingHours.put(day, parkingHours);
	}
}