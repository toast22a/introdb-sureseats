package sureseats.model;

public class Province {
	private int id;
	private String name;

	public static final String TABLE = "PROVINCE";
	public static final String COL_ID = "PID";
	public static final String COL_NAME = "PName";

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

	@Override
	public String toString() {
		return String.format("PROVINCE ID=%s\nNAME=%s\n", id, name);
	}
}
