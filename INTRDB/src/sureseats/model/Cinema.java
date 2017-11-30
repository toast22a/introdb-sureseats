package sureseats.model;

public class Cinema {
	private int id;
	private String no;
	private String type;
	private Mall mall;

	public static final String TABLE = "CINEMA";
	public static final String COL_ID = "CID";
	public static final String COL_NO = "CNo";
	public static final String COL_TYPE = "CType";
	public static final String COL_MALL = "MID";

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Mall getMall() {
		return mall;
	}

	public void setMall(Mall mall) {
		this.mall = mall;
	}

	@Override
	public String toString() {
		return String.format("CINEMA ID=%s\nMALL=%s\nNO=%s\nTYPE=%s\n", id, mall.getName(), no, type);
	}
}
