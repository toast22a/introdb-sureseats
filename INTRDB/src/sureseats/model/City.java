package sureseats.model;

public class City {
	private int id;
	private String name;
	private String type;
	private Province province;

	public static final String TABLE = "CITY";
	public static final String COL_ID = "CTID";
	public static final String COL_NAME = "CTName";
	public static final String COL_TYPE = "CTType";
	public static final String COL_PROVINCE = "PID";

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Province getProvince() {
		return province;
	}

	public void setProvince(Province province) {
		this.province = province;
	}

	@Override
	public String toString() {
		return String.format("CITY ID=%s\nNAME=%s\n", id, name);
	}

}
