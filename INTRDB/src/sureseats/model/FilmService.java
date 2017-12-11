package sureseats.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FilmService {
	private SureseatsDB connection;

	public FilmService(SureseatsDB sureseatsDB) {
		this.connection = sureseatsDB;
	}

	public List<Film> getAll() {
		// create empty list of contacts
		List<Film> films = new ArrayList<Film>();

		// get connection from db
		Connection cnt = connection.getConnection();

		// create string query
		String query = "SELECT * FROM " + Film.TABLE;

		try {
			// create prepared statement
			PreparedStatement ps = cnt.prepareStatement(query);

			// get result and store in result set
			ResultSet rs = ps.executeQuery();

			// transform set to list
			// rs.next() means get next in result set
			while (rs.next()) {
				films.add(toFilm(rs));
			}

			// close all the resources
			ps.close();
			rs.close();
			cnt.close();

			System.out.println("[FILM] SELECT SUCCESS!");
		} catch (SQLException e) {
			System.out.println("[FILM] SELECT FAILED!");
			e.printStackTrace();
		}

		// return list
		return films;
	}
	
	// no arguments -- use current month
	public List<Film> getFilmsInMonth() {
		// create empty list of contacts
		List<Film> films = new ArrayList<Film>();

		// get connection from db
		Connection cnt = connection.getConnection();

		// create string query
		String query = "SELECT * FROM " + Film.TABLE + " WHERE "
				+ "DATEDIFF(NOW()," + Film.COL_DATE + ") >= 0 AND "
				+ "MONTH(" + Film.COL_DATE + ") = MONTH(NOW()) ORDER BY " + Film.COL_DATE;

		try {
			// create prepared statement
			PreparedStatement ps = cnt.prepareStatement(query);
			
			//ps.setDate(1, Date.valueOf(LocalDate.now()));

			// get result and store in result set
			ResultSet rs = ps.executeQuery();

			// transform set to list
			// rs.next() means get next in result set
			while (rs.next()) {
				films.add(toFilm(rs));
			}

			// close all the resources
			ps.close();
			rs.close();
			cnt.close();

			System.out.println("[FILM] SELECT SUCCESS!");
		} catch (SQLException e) {
			System.out.println("[FILM] SELECT FAILED!");
			e.printStackTrace();
		}

		// return list
		return films;
	}
	
	public List<Film> getComingSoon(){
		// create empty list of contacts
		List<Film> films = new ArrayList<Film>();

		// get connection from db
		Connection cnt = connection.getConnection();

		// create string query
		String query = "SELECT * FROM " + Film.TABLE + " WHERE "
				+ Film.COL_DATE + " > DATE(NOW()) AND "
				+ Film.COL_DATE + " <= LAST_DAY(NOW()) "
				+ "ORDER BY " + Film.COL_DATE;

		try {
			// create prepared statement
			PreparedStatement ps = cnt.prepareStatement(query);
			
			//ps.setDate(1, Date.valueOf(LocalDate.now()));

			// get result and store in result set
			ResultSet rs = ps.executeQuery();

			// transform set to list
			// rs.next() means get next in result set
			while (rs.next()) {
				films.add(toFilm(rs));
			}

			// close all the resources
			ps.close();
			rs.close();
			cnt.close();

			System.out.println("[FILM] SELECT SUCCESS!");
		} catch (SQLException e) {
			System.out.println("[FILM] SELECT FAILED!");
			e.printStackTrace();
		}

		// return list
		return films;
	}

	public Film getFilm(int id) {
		Film film = new Film();

		// get connection from db
		Connection cnt = connection.getConnection();

		// create string query
		String query = "SELECT * FROM " + Film.TABLE + " WHERE " + Film.COL_ID + " = ?";

		try {
			// create prepared statement
			PreparedStatement ps = cnt.prepareStatement(query);
			
			ps.setInt(1, id);

			// get result and store in result set
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				film = toFilm(rs);
			}

			// close all the resources
			ps.close();
			rs.close();
			cnt.close();

			System.out.println("[FILM] SELECT SUCCESS!");
		} catch (SQLException e) {
			System.out.println("[FILM] SELECT FAILED!");
			e.printStackTrace();
		}

		// return list
		return film;
	}

	private Film toFilm(ResultSet rs) throws SQLException {
		Film film = new Film();

		film.setId(rs.getInt(Film.COL_ID));
		film.setTitle(rs.getString(Film.COL_TITLE));
		film.setGenre(rs.getString(Film.COL_GENRE));
		film.setDate(rs.getDate(Film.COL_DATE).toLocalDate());
		film.setRating(rs.getString(Film.COL_RATING));
		film.setCast(rs.getString(Film.COL_CAST));
		film.setRuntime(rs.getInt(Film.COL_RUNTIME));
		film.setPrice(rs.getDouble(Film.COL_PRICE));
		film.setSynopsis(rs.getString(Film.COL_SYNOPSIS));
		film.setImage(rs.getString(Film.COL_IMAGE));

		return film;
	}

	public void addFilm(Film film) {
		// get connection from db
		Connection cnt = connection.getConnection();

		// create a query
		String query = "INSERT INTO " + Film.TABLE + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			// create prepared statement
			PreparedStatement ps = cnt.prepareStatement(query);

			// prepare the values
			ps.setInt(1, Types.NULL); // because id is auto-increment anyway
			ps.setString(2, film.getTitle());
			ps.setString(3, film.getGenre());
			ps.setDate(4, Date.valueOf(film.getDate()));
			ps.setString(5, film.getRating());
			ps.setString(6, film.getCast());
			ps.setInt(7, film.getRuntime());
			ps.setDouble(8, film.getPrice());
			ps.setString(9, film.getSynopsis());
			ps.setString(10, film.getImage());

			// execute the update
			ps.executeUpdate();

			// close resources
			ps.close();
			cnt.close();
			System.out.println("[FILM] INSERT SUCCESS");
		} catch (SQLException e) {
			System.out.println("[FILM] INSERT FAILED");
			e.printStackTrace();
		}
	}

	public void updateFilm(Film film) {
		// get connection from db
		Connection cnt = connection.getConnection();

		// create a query
		String query = "UPDATE " + Film.TABLE
				+ " SET "
				+ Film.COL_TITLE + " = ?,"
				+ Film.COL_GENRE + " = ?,"
				+ Film.COL_DATE + " = ?,"
				+ Film.COL_RATING + " = ?,"
				+ Film.COL_CAST + " = ?,"
				+ Film.COL_RUNTIME + " = ?,"
				+ Film.COL_PRICE + " = ?,"
				+ Film.COL_SYNOPSIS + " = ?,"
				+ Film.COL_IMAGE + " = ?,"
				+ " WHERE " + Film.COL_ID + " = ?";

		try {
			// create prepared statement
			PreparedStatement ps = cnt.prepareStatement(query);

			// prepare the values
			ps.setInt(10, film.getId()); // because id is auto-increment anyway
			ps.setString(1, film.getTitle());
			ps.setString(2, film.getGenre());
			ps.setDate(3, Date.valueOf(film.getDate()));
			ps.setString(4, film.getRating());
			ps.setString(5, film.getCast());
			ps.setInt(6, film.getRuntime());
			ps.setDouble(7, film.getPrice());
			ps.setString(8, film.getSynopsis());
			ps.setString(9, film.getImage());

			// execute the update
			ps.executeUpdate();

			// close resources
			ps.close();
			cnt.close();
			System.out.println("[FILM] UPDATE SUCCESS");
		} catch (SQLException e) {
			System.out.println("[FILM] UPDATE FAILED");
			e.printStackTrace();
		}
	}

	public void deleteFilm(int id) {
		// get connection from db
		Connection cnt = connection.getConnection();

		// create a query
		String query = "DELETE FROM " + Film.TABLE + " WHERE " + Film.COL_ID + " = ?";

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
			System.out.println("[FILM] DELETE SUCCESS");
		} catch (SQLException e) {
			System.out.println("[FILM] DELETE FAILED");
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		FilmService service = new FilmService(new SureseatsDB());
		List<Film> films = service.getAll();
		Film film = service.getFilm(1);

		for (Film f : films) {
			System.out.println(f.toString());
		}
		System.out.println(film.toString());
	}
}
