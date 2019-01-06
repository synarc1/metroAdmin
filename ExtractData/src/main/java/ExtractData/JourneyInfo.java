package ExtractData;

public class JourneyInfo {

	private String clientName;
	private String payType;
	private String fare;
	private String driver;
	private String plateNumber;
	private String desLat;
	private String year;
	private String oriLat;
	private String oriLon;
	
	public JourneyInfo(String clientName, String payType, String fare, String driver, String plateNumber,
			String desLat, String oriLat, String oriLon, String year) {
		super();
		this.clientName = clientName;
		this.payType = payType;
		this.fare = fare;
		this.driver = driver;
		this.plateNumber = plateNumber;
		this.desLat = desLat;
		this.oriLat = oriLat;
		this.oriLon = oriLon;
		this.year = year;
	}
	

	public String getYear() {
		return year;
	}


	public void setYear(String year) {
		this.year = year;
	}


	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getFare() {
		return fare;
	}

	public void setFare(String fare) {
		this.fare = fare;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getPlateNumber() {
		return plateNumber;
	}

	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}

	public String getDesLat() {
		return desLat;
	}

	public void setDesLat(String desLat) {
		this.desLat = desLat;
	}

	public String getOriLat() {
		return oriLat;
	}

	public void setOriLat(String oriLat) {
		this.oriLat = oriLat;
	}

	public String getOriLon() {
		return oriLon;
	}

	public void setOriLon(String oriLon) {
		this.oriLon = oriLon;
	}
	
	
	
	
	
}
