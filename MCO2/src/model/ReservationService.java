package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class ReservationService {
	private SureseatsDB connection;
	private UserService userService;
	private SeatService seatService;
	private ScheduleService scheduleService;

	public ReservationService(SureseatsDB sureseatsDB) {
		this.connection = sureseatsDB;
		this.userService = new UserService(sureseatsDB);
		this.seatService = new SeatService(sureseatsDB);
		this.scheduleService = new ScheduleService(sureseatsDB);
	}

	public List<Reservation> getAll() {
		// create empty list of contacts
		List<Reservation> reservations = new ArrayList<Reservation>();

		// get connection from db
		Connection cnt = connection.getConnection();

		// create string query
		String query = "SELECT * FROM " + Reservation.TABLE;

		try {
			// create prepared statement
			PreparedStatement ps = cnt.prepareStatement(query);

			// get result and store in result set
			ResultSet rs = ps.executeQuery();

			// transform set to list
			// rs.next() means get next in result set
			while (rs.next()) {
				reservations.add(toReservation(rs));
			}

			// close all the resources
			ps.close();
			rs.close();
			cnt.close();

			System.out.println("[RESERVATION] SELECT SUCCESS!");
		} catch (SQLException e) {
			System.out.println("[RESERVATION] SELECT FAILED!");
			e.printStackTrace();
		}

		// return list
		return reservations;
	}

	public Reservation getReservation(int id) {
		Reservation reservation = new Reservation();

		// get connection from db
		Connection cnt = connection.getConnection();

		// create string query
		String query = "SELECT * FROM " + Reservation.TABLE + " WHERE " + Reservation.COL_ID + " = ?";

		try {
			// create prepared statement
			PreparedStatement ps = cnt.prepareStatement(query);
			
			ps.setInt(1, id);

			// get result and store in result set
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				reservation = toReservation(rs);
			}

			// close all the resources
			ps.close();
			rs.close();
			cnt.close();

			System.out.println("[RESERVATION] SELECT SUCCESS!");
		} catch (SQLException e) {
			System.out.println("[RESERVATION] SELECT FAILED!");
			e.printStackTrace();
		}

		// return list
		return reservation;
	}

	private Reservation toReservation(ResultSet rs) throws SQLException {
		Reservation reservation = new Reservation();

		reservation.setId(rs.getInt(Reservation.COL_ID));
		reservation.setCode(rs.getString(Reservation.COL_CODE));
		reservation.setType(rs.getString(Reservation.COL_TYPE));
		reservation.setDatetime(rs.getTimestamp(Reservation.COL_DATETIME).toLocalDateTime());
		reservation.setStatus(rs.getString(Reservation.COL_STATUS));
		reservation.setUser(userService.getUser(rs.getInt(Reservation.COL_USER)));
		reservation.setSeat(seatService.getSeat(rs.getInt(Reservation.COL_SEAT)));
		reservation.setSchedule(scheduleService.getSchedule(rs.getInt(Reservation.COL_SCHEDULE)));

		return reservation;
	}

	public void addReservation(Reservation reservation) {
		// get connection from db
		Connection cnt = connection.getConnection();

		// create a query
		String query = "INSERT INTO " + Reservation.TABLE + " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			// create prepared statement
			PreparedStatement ps = cnt.prepareStatement(query);

			// prepare the values
			ps.setInt(1, Types.NULL); // because id is auto-increment anyway
			ps.setString(2, reservation.getCode());
			ps.setString(3, reservation.getType());
			ps.setTimestamp(4, Timestamp.valueOf(reservation.getDatetime()));
			ps.setString(5, reservation.getStatus());
			ps.setInt(6, reservation.getUser().getId());
			ps.setInt(7, reservation.getSeat().getId());
			ps.setInt(8, reservation.getSchedule().getId());

			// execute the update
			ps.executeUpdate();

			// close resources
			ps.close();
			cnt.close();
			System.out.println("[RESERVATION] INSERT SUCCESS");
		} catch (SQLException e) {
			System.out.println("[RESERVATION] INSERT FAILED");
			e.printStackTrace();
		}
	}

	public void updateReservation(Reservation reservation) {
		// get connection from db
		Connection cnt = connection.getConnection();

		// create a query
		String query = "UPDATE " + Reservation.TABLE
				+ " SET "
				+ Reservation.COL_CODE + " = ?,"
				+ Reservation.COL_TYPE + " = ?,"
				+ Reservation.COL_DATETIME + " = ?,"
				+ Reservation.COL_STATUS + " = ?,"
				+ Reservation.COL_USER + " = ?,"
				+ Reservation.COL_SEAT + " = ?,"
				+ Reservation.COL_SCHEDULE + " = ?"
				+ " WHERE " + Reservation.COL_ID + " = ?";

		try {
			// create prepared statement
			PreparedStatement ps = cnt.prepareStatement(query);

			// prepare the values
			ps.setInt(8, reservation.getId()); // because id is auto-increment anyway
			ps.setString(1, reservation.getCode());
			ps.setString(2, reservation.getType());
			ps.setTimestamp(3, Timestamp.valueOf(reservation.getDatetime()));
			ps.setString(4, reservation.getStatus());
			ps.setInt(5, reservation.getUser().getId());
			ps.setInt(6, reservation.getSeat().getId());
			ps.setInt(7, reservation.getSchedule().getId());

			// execute the update
			ps.executeUpdate();

			// close resources
			ps.close();
			cnt.close();
			System.out.println("[RESERVATION] UPDATE SUCCESS");
		} catch (SQLException e) {
			System.out.println("[RESERVATION] UPDATE FAILED");
			e.printStackTrace();
		}
	}

	public void deleteReservation(int id) {
		// get connection from db
		Connection cnt = connection.getConnection();

		// create a query
		String query = "DELETE FROM " + Reservation.TABLE + " WHERE " + Reservation.COL_ID + " = ?";

		try {
			// create prepared statement
			PreparedStatement ps = cnt.prepareStatement(query);

			// prepare the values
			ps.setInt(1, id); // gets the first question mark and sets it to id

			// execute the update
			ps.executeUpdate();

			// close resources
			ps.close();
			cnt.close();
			System.out.println("[RESERVATION] DELETE SUCCESS");
		} catch (SQLException e) {
			System.out.println("[RESERVATION] DELETE FAILED");
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		ReservationService service = new ReservationService(new SureseatsDB());
		List<Reservation> reservations = service.getAll();
		Reservation reservation = service.getReservation(1);

		for (Reservation r : reservations) {
			System.out.println(r.toString());
		}
		System.out.println(reservation.toString());
	}
}
