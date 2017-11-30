package sureseats.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class CinemaService {
	private SureseatsDB connection;
	private MallService mallService;

	public CinemaService(SureseatsDB sureseatsDB) {
		this.connection = sureseatsDB;
		this.mallService = new MallService(sureseatsDB);
	}

	public List<Cinema> getAll() {
		// create empty list of contacts
		List<Cinema> cinemas = new ArrayList<Cinema>();

		// get connection from db
		Connection cnt = connection.getConnection();

		// create string query
		String query = "SELECT * FROM " + Cinema.TABLE;

		try {
			// create prepared statement
			PreparedStatement ps = cnt.prepareStatement(query);

			// get result and store in result set
			ResultSet rs = ps.executeQuery();

			// transform set to list
			// rs.next() means get next in result set
			while (rs.next()) {
				cinemas.add(toCinema(rs));
			}

			// close all the resources
			ps.close();
			rs.close();
			cnt.close();

			System.out.println("[CINEMA] SELECT SUCCESS!");
		} catch (SQLException e) {
			System.out.println("[CINEMA] SELECT FAILED!");
			e.printStackTrace();
		}

		// return list
		return cinemas;
	}

	public Cinema getCinema(int id) {
		Cinema cinema = new Cinema();

		// get connection from db
		Connection cnt = connection.getConnection();

		// create string query
		String query = "SELECT * FROM " + Cinema.TABLE + " WHERE " + Cinema.COL_ID + " = ?";

		try {
			// create prepared statement
			PreparedStatement ps = cnt.prepareStatement(query);
			
			ps.setInt(1, id);

			// get result and store in result set
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				cinema = toCinema(rs);
			}

			// close all the resources
			ps.close();
			rs.close();
			cnt.close();

			System.out.println("[CINEMA] SELECT SUCCESS!");
		} catch (SQLException e) {
			System.out.println("[CINEMA] SELECT FAILED!");
			e.printStackTrace();
		}

		// return list
		return cinema;
	}

	private Cinema toCinema(ResultSet rs) throws SQLException {
		Cinema cinema = new Cinema();

		cinema.setId(rs.getInt(Cinema.COL_ID));
		cinema.setNo(rs.getString(Cinema.COL_NO));
		cinema.setType(rs.getString(Cinema.COL_TYPE));
		cinema.setMall(mallService.getMall(rs.getInt(Cinema.COL_MALL)));

		return cinema;
	}

	public void addCinema(Cinema cinema) {
		// get connection from db
		Connection cnt = connection.getConnection();

		// create a query
		String query = "INSERT INTO " + Cinema.TABLE + " VALUES (?, ?, ?, ?)";

		try {
			// create prepared statement
			PreparedStatement ps = cnt.prepareStatement(query);

			// prepare the values
			ps.setInt(1, Types.NULL); // because id is auto-increment anyway
			ps.setString(2, cinema.getNo());
			ps.setString(3, cinema.getType());
			ps.setInt(3, cinema.getMall().getId());

			// execute the update
			ps.executeUpdate();

			// close resources
			ps.close();
			cnt.close();
			System.out.println("[CINEMA] INSERT SUCCESS");
		} catch (SQLException e) {
			System.out.println("[CINEMA] INSERT FAILED");
			e.printStackTrace();
		}
	}

	public void updateCinema(Cinema cinema) {
		// get connection from db
		Connection cnt = connection.getConnection();

		// create a query
		String query = "UPDATE " + Cinema.TABLE
				+ " SET "
				+ Cinema.COL_NO + " = ?,"
				+ Cinema.COL_TYPE + " = ?,"
				+ Cinema.COL_MALL + " = ?"
				+ " WHERE " + Cinema.COL_ID + " = ?";

		try {
			// create prepared statement
			PreparedStatement ps = cnt.prepareStatement(query);

			// prepare the values
			ps.setInt(4, cinema.getId()); // because id is auto-increment anyway
			ps.setString(1, cinema.getNo());
			ps.setString(2, cinema.getType());
			ps.setInt(3, cinema.getMall().getId());

			// execute the update
			ps.executeUpdate();

			// close resources
			ps.close();
			cnt.close();
			System.out.println("[CINEMA] UPDATE SUCCESS");
		} catch (SQLException e) {
			System.out.println("[CINEMA] UPDATE FAILED");
			e.printStackTrace();
		}
	}

	public void deleteCinema(int id) {
		// get connection from db
		Connection cnt = connection.getConnection();

		// create a query
		String query = "DELETE FROM " + Cinema.TABLE + " WHERE " + Cinema.COL_ID + " = ?";

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
			System.out.println("[CINEMA] DELETE SUCCESS");
		} catch (SQLException e) {
			System.out.println("[CINEMA] DELETE FAILED");
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		CinemaService service = new CinemaService(new SureseatsDB());
		List<Cinema> cinemas = service.getAll();
		Cinema cinema = service.getCinema(1);

		for (Cinema c : cinemas) {
			System.out.println(c.toString());
		}
		System.out.println(cinema.toString());
	}
}
