package sureseats.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class SeatService {
	private SureseatsDB connection;
	private CinemaService cinemaService;

	public SeatService(SureseatsDB sureseatsDB) {
		this.connection = sureseatsDB;
		this.cinemaService = new CinemaService(sureseatsDB);
	}

	public List<Seat> getAll() {
		// create empty list of contacts
		List<Seat> seats = new ArrayList<Seat>();

		// get connection from db
		Connection cnt = connection.getConnection();

		// create string query
		String query = "SELECT * FROM " + Seat.TABLE;

		try {
			// create prepared statement
			PreparedStatement ps = cnt.prepareStatement(query);

			// get result and store in result set
			ResultSet rs = ps.executeQuery();

			// transform set to list
			// rs.next() means get next in result set
			while (rs.next()) {
				seats.add(toSeat(rs));
			}

			// close all the resources
			ps.close();
			rs.close();
			cnt.close();

			System.out.println("[SEAT] SELECT SUCCESS!");
		} catch (SQLException e) {
			System.out.println("[SEAT] SELECT FAILED!");
			e.printStackTrace();
		}

		// return list
		return seats;
	}
	
	public List<Seat> getSeatsInCinema(Cinema cinema){
		// create empty list of contacts
		List<Seat> seats = new ArrayList<Seat>();

		// get connection from db
		Connection cnt = connection.getConnection();

		// create string query
		String query = 
				"SELECT * FROM " + Seat.TABLE + " WHERE " + Seat.COL_ID + " IN "
				+ "(SELECT " + Reservation.COL_SEAT + " FROM " + Reservation.TABLE
				+ " WHERE " + Seat.COL_CINEMA + " = ?";

		try {
			// create prepared statement
			PreparedStatement ps = cnt.prepareStatement(query);
			ps.setInt(1, cinema.getId());

			// get result and store in result set
			ResultSet rs = ps.executeQuery();

			// transform set to list
			// rs.next() means get next in result set
			while (rs.next()) {
				seats.add(toSeat(rs));
			}

			// close all the resources
			ps.close();
			rs.close();
			cnt.close();

			System.out.println("[SCHEDULE] SELECT SUCCESS!");
		} catch (SQLException e) {
			System.out.println("[SCHEDULE] SELECT FAILED!");
			e.printStackTrace();
		}

		// return list
		return seats;
	}
	
	public List<Seat> getOccupied(Schedule schedule){
		// create empty list of contacts
		List<Seat> seats = new ArrayList<Seat>();

		// get connection from db
		Connection cnt = connection.getConnection();

		// create string query
		String query = 
				"SELECT * FROM " + Seat.TABLE + " WHERE " + Seat.COL_ID + " IN "
				+ "(SELECT " + Reservation.COL_SEAT + " FROM " + Reservation.TABLE
				+ " WHERE " + Reservation.COL_SCHEDULE + " = ?"
				+ " AND " + Reservation.COL_STATUS + " <> \"cancelled\")"
				+ " ORDER BY " + Seat.COL_ROW + "," + Seat.COL_COL;

		try {
			// create prepared statement
			PreparedStatement ps = cnt.prepareStatement(query);
			ps.setInt(1, schedule.getId());

			// get result and store in result set
			ResultSet rs = ps.executeQuery();

			// transform set to list
			// rs.next() means get next in result set
			while (rs.next()) {
				seats.add(toSeat(rs));
			}

			// close all the resources
			ps.close();
			rs.close();
			cnt.close();

			System.out.println("[SCHEDULE] SELECT SUCCESS!");
		} catch (SQLException e) {
			System.out.println("[SCHEDULE] SELECT FAILED!");
			e.printStackTrace();
		}

		// return list
		return seats;
	}
	
	public List<Seat> getFree(Schedule schedule){
		// create empty list of contacts
		List<Seat> seats = new ArrayList<Seat>();

		// get connection from db
		Connection cnt = connection.getConnection();

		// create string query
		String query = 
				"SELECT * FROM " + Seat.TABLE + " WHERE " + Seat.COL_ID + " NOT IN "
				+ "(SELECT " + Reservation.COL_SEAT + " FROM " + Reservation.TABLE
				+ " WHERE " + Reservation.COL_SCHEDULE + " = ?"
				+ " AND " + Reservation.COL_STATUS + " <> \"cancelled\")"
				+ " ORDER BY " + Seat.COL_ROW + "," + Seat.COL_COL;

		try {
			// create prepared statement
			PreparedStatement ps = cnt.prepareStatement(query);
			ps.setInt(1, schedule.getId());

			// get result and store in result set
			ResultSet rs = ps.executeQuery();

			// transform set to list
			// rs.next() means get next in result set
			while (rs.next()) {
				seats.add(toSeat(rs));
			}

			// close all the resources
			ps.close();
			rs.close();
			cnt.close();

			System.out.println("[SCHEDULE] SELECT SUCCESS!");
		} catch (SQLException e) {
			System.out.println("[SCHEDULE] SELECT FAILED!");
			e.printStackTrace();
		}

		// return list
		return seats;
	}

	public Seat getSeat(int id) {
		Seat seat = new Seat();

		// get connection from db
		Connection cnt = connection.getConnection();

		// create string query
		String query = "SELECT * FROM " + Seat.TABLE + " WHERE " + Seat.COL_ID + " = ?";

		try {
			// create prepared statement
			PreparedStatement ps = cnt.prepareStatement(query);
			
			ps.setInt(1, id);

			// get result and store in result set
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				seat = toSeat(rs);
			}

			// close all the resources
			ps.close();
			rs.close();
			cnt.close();

			System.out.println("[SEAT] SELECT SUCCESS!");
		} catch (SQLException e) {
			System.out.println("[SEAT] SELECT FAILED!");
			e.printStackTrace();
		}

		// return list
		return seat;
	}

	private Seat toSeat(ResultSet rs) throws SQLException {
		Seat seat = new Seat();

		seat.setId(rs.getInt(Seat.COL_ID));
		seat.setRow(rs.getString(Seat.COL_ROW));
		seat.setCol(rs.getInt(Seat.COL_COL));
		seat.setCinema(cinemaService.getCinema(rs.getInt(Seat.COL_CINEMA)));

		return seat;
	}

	public void addSeat(Seat seat) {
		// get connection from db
		Connection cnt = connection.getConnection();

		// create a query
		String query = "INSERT INTO " + Seat.TABLE + " VALUES (?, ?, ?, ?)";

		try {
			// create prepared statement
			PreparedStatement ps = cnt.prepareStatement(query);

			// prepare the values
			ps.setInt(1, Types.NULL); // because id is auto-increment anyway
			ps.setString(2, seat.getRow());
			ps.setInt(3, seat.getCol());
			ps.setInt(4, seat.getCinema().getId());

			// execute the update
			ps.executeUpdate();

			// close resources
			ps.close();
			cnt.close();
			System.out.println("[SEAT] INSERT SUCCESS");
		} catch (SQLException e) {
			System.out.println("[SEAT] INSERT FAILED");
			e.printStackTrace();
		}
	}

	public void updateSeat(Seat seat) {
		// get connection from db
		Connection cnt = connection.getConnection();

		// create a query
		String query = "UPDATE " + Seat.TABLE
				+ " SET "
				+ Seat.COL_ROW + " = ?,"
				+ Seat.COL_COL + " = ?,"
				+ Seat.COL_CINEMA + " = ?"
				+ " WHERE " + Seat.COL_ID + " = ?";

		try {
			// create prepared statement
			PreparedStatement ps = cnt.prepareStatement(query);

			// prepare the values
			ps.setInt(4, seat.getId()); // because id is auto-increment anyway
			ps.setString(1, seat.getRow());
			ps.setInt(2, seat.getCol());
			ps.setInt(3, seat.getCinema().getId());

			// execute the update
			ps.executeUpdate();

			// close resources
			ps.close();
			cnt.close();
			System.out.println("[SEAT] UPDATE SUCCESS");
		} catch (SQLException e) {
			System.out.println("[SEAT] UPDATE FAILED");
			e.printStackTrace();
		}
	}

	public void deleteSeat(int id) {
		// get connection from db
		Connection cnt = connection.getConnection();

		// create a query
		String query = "DELETE FROM " + Seat.TABLE + " WHERE " + Seat.COL_ID + " = ?";

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
			System.out.println("[SEAT] DELETE SUCCESS");
		} catch (SQLException e) {
			System.out.println("[SEAT] DELETE FAILED");
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		SeatService service = new SeatService(new SureseatsDB());
		List<Seat> seats = service.getAll();
		Seat seat = service.getSeat(1);

		for (Seat s : seats) {
			System.out.println(s.toString());
		}
		System.out.println(seat.toString());
	}
}
