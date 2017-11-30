package sureseats.model;

public class Mall {
	private int id;
	private String name;
	private City city;

	public static final String TABLE = "MALL";
	public static final String COL_ID = "MID";
	public static final String COL_NAME = "MName";
	public static final String COL_CITY = "CTID";

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

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return String.format("MALL ID=%s\nNAME=%s\n", id, name);
	}
}
