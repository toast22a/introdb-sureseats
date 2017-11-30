package model;

public class Seat {
	private int id;
	private String row;
	private int col;
	private Cinema cinema;

	public static final String TABLE = "SEAT";
	public static final String COL_ID = "SeID";
	public static final String COL_ROW = "SeRow";
	public static final String COL_COL = "SeCol";
	public static final String COL_CINEMA = "CID";

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRow() {
		return row;
	}

	public void setRow(String row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public Cinema getCinema() {
		return cinema;
	}

	public void setCinema(Cinema cinema) {
		this.cinema = cinema;
	}

	@Override
	public String toString() {
		return String.format("SEAT ID=%s\nMALL=%s\nCINEMA=%s\nROW=%s\nCOL=%s\n", id, cinema.getMall().getName(), cinema.getNo(), row, col);
	}
}
