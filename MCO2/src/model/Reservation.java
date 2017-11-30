package model;

import java.time.LocalDateTime;

public class Reservation {
	private int id;
	private String code;
	private String type;
	private LocalDateTime datetime;
	private String status;
	private User user;
	private Seat seat;
	private Schedule schedule;

	public static final String TABLE = "RESERVATION";
	public static final String COL_ID = "RID";
	public static final String COL_CODE = "RCode";
	public static final String COL_TYPE = "RType";
	public static final String COL_DATETIME = "RDateTime";
	public static final String COL_STATUS = "RStatus";
	public static final String COL_USER = "UID";
	public static final String COL_SEAT = "SeID";
	public static final String COL_SCHEDULE = "SID";

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public LocalDateTime getDatetime() {
		return datetime;
	}

	public void setDatetime(LocalDateTime datetime) {
		this.datetime = datetime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Seat getSeat() {
		return seat;
	}

	public void setSeat(Seat seat) {
		this.seat = seat;
	}

	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}

	@Override
	public String toString() {
		return String.format("RESERVATION ID=%s\nCODE=%s\nTYPE=%s\nDATETIME=%s\n"
				+ "STATUS=%s\n", id, code, type, datetime, status);
	}
}
