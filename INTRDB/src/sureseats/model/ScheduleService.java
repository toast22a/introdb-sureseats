package sureseats.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class ScheduleService {
	private SureseatsDB connection;
	private CinemaService cinemaService;
	private FilmService filmService;

	public ScheduleService(SureseatsDB sureseatsDB) {
		this.connection = sureseatsDB;
		this.cinemaService = new CinemaService(sureseatsDB);
		this.filmService = new FilmService(sureseatsDB);
	}

	public List<Schedule> getAll() {
		// create empty list of contacts
		List<Schedule> schedules = new ArrayList<Schedule>();

		// get connection from db
		Connection cnt = connection.getConnection();

		// create string query
		String query = "SELECT * FROM " + Schedule.TABLE;

		try {
			// create prepared statement
			PreparedStatement ps = cnt.prepareStatement(query);

			// get result and store in result set
			ResultSet rs = ps.executeQuery();

			// transform set to list
			// rs.next() means get next in result set
			while (rs.next()) {
				schedules.add(toSchedule(rs));
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
		return schedules;
	}
	
	public List<Schedule> getSchedulesOfFilm(Film film) {
		// create empty list of contacts
		List<Schedule> schedules = new ArrayList<Schedule>();

		// get connection from db
		Connection cnt = connection.getConnection();

		// create string query
		String query = "SELECT * FROM " + Schedule.TABLE + " WHERE " + Schedule.COL_FILM + " = ?";

		try {
			// create prepared statement
			PreparedStatement ps = cnt.prepareStatement(query);
			ps.setInt(1, film.getId());

			// get result and store in result set
			ResultSet rs = ps.executeQuery();

			// transform set to list
			// rs.next() means get next in result set
			while (rs.next()) {
				schedules.add(toSchedule(rs));
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
		return schedules;
	}

	public Schedule getSchedule(int id) {
		Schedule schedule = new Schedule();

		// get connection from db
		Connection cnt = connection.getConnection();

		// create string query
		String query = "SELECT * FROM " + Schedule.TABLE + " WHERE " + Schedule.COL_ID + " = ?";

		try {
			// create prepared statement
			PreparedStatement ps = cnt.prepareStatement(query);
			
			ps.setInt(1, id);

			// get result and store in result set
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				schedule = toSchedule(rs);
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
		return schedule;
	}

	private Schedule toSchedule(ResultSet rs) throws SQLException {
		Schedule schedule = new Schedule();

		schedule.setId(rs.getInt(Schedule.COL_ID));
		schedule.setStart(rs.getTimestamp(Schedule.COL_START).toLocalDateTime());
		schedule.setEnd(rs.getTimestamp(Schedule.COL_END).toLocalDateTime());
		schedule.setCinema(cinemaService.getCinema(rs.getInt(Schedule.COL_CINEMA)));
		schedule.setFilm(filmService.getFilm(rs.getInt(Schedule.COL_FILM)));

		return schedule;
	}

	public void addSchedule(Schedule schedule) {
		// get connection from db
		Connection cnt = connection.getConnection();

		// create a query
		String query = "INSERT INTO " + Schedule.TABLE + " VALUES (?, ?, ?, ?, ?)";

		try {
			// create prepared statement
			PreparedStatement ps = cnt.prepareStatement(query);

			// prepare the values
			ps.setInt(1, Types.NULL); // because id is auto-increment anyway
			ps.setTimestamp(2, Timestamp.valueOf(schedule.getStart()));
			ps.setTimestamp(3, Timestamp.valueOf(schedule.getEnd()));
			ps.setInt(4, schedule.getCinema().getId());
			ps.setInt(5, schedule.getFilm().getId());

			// execute the update
			ps.executeUpdate();

			// close resources
			ps.close();
			cnt.close();
			System.out.println("[SCHEDULE] INSERT SUCCESS");
		} catch (SQLException e) {
			System.out.println("[SCHEDULE] INSERT FAILED");
			e.printStackTrace();
		}
	}

	public void updateSchedule(Schedule schedule) {
		// get connection from db
		Connection cnt = connection.getConnection();

		// create a query
		String query = "UPDATE " + Schedule.TABLE
				+ " SET "
				+ Schedule.COL_START + " = ?,"
				+ Schedule.COL_END + " = ?,"
				+ Schedule.COL_CINEMA + " = ?,"
				+ Schedule.COL_FILM + " = ?"
				+ " WHERE " + Schedule.COL_ID + " = ?";

		try {
			// create prepared statement
			PreparedStatement ps = cnt.prepareStatement(query);

			// prepare the values
			ps.setInt(5, schedule.getId()); // because id is auto-increment anyway
			ps.setTimestamp(1, Timestamp.valueOf(schedule.getStart()));
			ps.setTimestamp(2, Timestamp.valueOf(schedule.getEnd()));
			ps.setInt(3, schedule.getCinema().getId());
			ps.setInt(4, schedule.getFilm().getId());

			// execute the update
			ps.executeUpdate();

			// close resources
			ps.close();
			cnt.close();
			System.out.println("[SCHEDULE] UPDATE SUCCESS");
		} catch (SQLException e) {
			System.out.println("[SCHEDULE] UPDATE FAILED");
			e.printStackTrace();
		}
	}

	public void deleteSchedule(int id) {
		// get connection from db
		Connection cnt = connection.getConnection();

		// create a query
		String query = "DELETE FROM " + Schedule.TABLE + " WHERE " + Schedule.COL_ID + " = ?";

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
			System.out.println("[SCHEDULE] DELETE SUCCESS");
		} catch (SQLException e) {
			System.out.println("[SCHEDULE] DELETE FAILED");
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		ScheduleService service = new ScheduleService(new SureseatsDB());
		List<Schedule> schedules = service.getAll();
		Schedule schedule = service.getSchedule(1);

		for (Schedule s : schedules) {
			System.out.println(s.toString());
		}
		System.out.println(schedule.toString());
	}
}
