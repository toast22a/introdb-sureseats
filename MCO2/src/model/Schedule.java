package model;

import java.time.LocalDateTime;

public class Schedule {
	private int id;
	private LocalDateTime start;
	private LocalDateTime end;
	private Cinema cinema;
	private Film film;

	public static final String TABLE = "SCHEDULE";
	public static final String COL_ID = "SID";
	public static final String COL_START = "SStart";
	public static final String COL_END = "SEnd";
	public static final String COL_CINEMA = "CID";
	public static final String COL_FILM = "FID";

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getStart() {
		return start;
	}

	public void setStart(LocalDateTime start) {
		this.start = start;
	}

	public LocalDateTime getEnd() {
		return end;
	}

	public void setEnd(LocalDateTime end) {
		this.end = end;
	}

	public Cinema getCinema() {
		return cinema;
	}

	public void setCinema(Cinema cinema) {
		this.cinema = cinema;
	}

	public Film getFilm() {
		return film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}

	@Override
	public String toString() {
		return String.format("SCHEDULE ID=%s\nSTART=%s\nEND=%s\n", id, start, end);
	}
}
