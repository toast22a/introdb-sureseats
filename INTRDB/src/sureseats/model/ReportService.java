package sureseats.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReportService {
	private SureseatsDB connection;
	
	public ReportService(SureseatsDB sureseatsDB) {
		this.connection = sureseatsDB;
	}
	
	public List<List<String>> getTransactionHistory(User user) {
		// create empty list of contacts
		List<List<String>> list = new ArrayList<List<String>>();

		// get connection from db
		Connection cnt = connection.getConnection();

		// create string query
		String query = 
		
				"SELECT "
				+ "RDateTime AS DateTime, "
				+ "RType AS Type, "
				+ "(SELECT FTitle FROM FILM WHERE FID = (SELECT FID FROM SCHEDULE WHERE SCHEDULE.SID = RESERVATION.SID)) AS Title, "
				
				+ "(SELECT CNo FROM CINEMA WHERE CID = "
				+ "(SELECT CID FROM SCHEDULE WHERE SCHEDULE.SID = RESERVATION.SID)) AS Cinema, "
				+ "(SELECT SeRow FROM SEAT WHERE SEAT.SeID = RESERVATION.SeID) AS SeatRow, "
				+ "(SELECT SeCol FROM SEAT WHERE SEAT.SeID = RESERVATION.SeID) AS SeatCol, "
				
				+ "RStatus AS Status "
				+ "FROM RESERVATION "
				+ "WHERE RESERVATION.UID = ? "
				+ "ORDER BY RDateTime DESC";

		try {
			// create prepared statement
			PreparedStatement ps = cnt.prepareStatement(query);
			ps.setInt(1, user.getId());

			// get result and store in result set
			ResultSet rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			
			ArrayList<String> header = new ArrayList<String>();
			for (int i = 0 ; i < rsmd.getColumnCount() ; i++) {
				header.add(rsmd.getColumnName(i+1));
			}
			list.add(header);

			// transform set to list
			// rs.next() means get next in result set
			while (rs.next()) {
				ArrayList<String> row = new ArrayList<String>();
				list.add(row);
				for (int i = 0 ; i < rsmd.getColumnCount() ; i++) {
					row.add(rs.getString(i+1));
				}
			}

			// close all the resources
			ps.close();
			rs.close();
			cnt.close();

			System.out.println("[REPORT] SELECT SUCCESS!");
		} catch (SQLException e) {
			System.out.println("[REPORT] SELECT FAILED!");
			e.printStackTrace();
		}

		// return list
		return list;
	}
	
	public List<List<String>> getActiveUsers() {
		// create empty list of contacts
		List<List<String>> list = new ArrayList<List<String>>();

		// get connection from db
		Connection cnt = connection.getConnection();

		// create string query
		String query = 
		
	
				"SELECT "
						+ "UUsername AS Username, "
						+ "UEmail AS email, "
						+ "ULastLogin AS LastLogin, "
						+ "DATEDIFF(NOW(), ULastLogin) AS DaysInactive "
						+ "FROM USER "
						+ "WHERE DATEDIFF(NOW(), ULastLogin) = 0 "
						+ "ORDER BY ULastLogin ASC, UUsername ASC "
						+ "LIMIT 10";
		try {
			// create prepared statement
			PreparedStatement ps = cnt.prepareStatement(query);

			// get result and store in result set
			ResultSet rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			
			/*for (int i = 0 ; i < rsmd.getColumnCount(); i++) {
				ArrayList<String> col = new ArrayList<String>();
				col.add(rsmd.getColumnName(i+1));
				list.add(col);
			}*/
			
			ArrayList<String> header = new ArrayList<String>();
			for (int i = 0 ; i < rsmd.getColumnCount() ; i++) {
				header.add(rsmd.getColumnName(i+1));
			}
			list.add(header);

			// transform set to list
			// rs.next() means get next in result set
			while (rs.next()) {
				ArrayList<String> row = new ArrayList<String>();
				list.add(row);
				for (int i = 0 ; i < rsmd.getColumnCount() ; i++) {
					row.add(rs.getString(i+1));
				}
			}

			// close all the resources
			ps.close();
			rs.close();
			cnt.close();

			System.out.println("[REPORT] SELECT SUCCESS!");
		} catch (SQLException e) {
			System.out.println("[REPORT] SELECT FAILED!");
			e.printStackTrace();
		}

		// return list
		return list;
	}
	
	
	public List<List<String>> getInactiveUsers() {
		// create empty list of contacts
		List<List<String>> list = new ArrayList<List<String>>();

		// get connection from db
		Connection cnt = connection.getConnection();

		// create string query
		String query = 
		
				"SELECT "
						+"UUsername AS Username, UEmail AS email, ULastLogin AS LastLogin, DATEDIFF(NOW(), ULastLogin) AS DaysInactive "
						+"FROM USER "
						+"ORDER BY ULastLogin ASC, UUsername ASC "
						+"LIMIT 10";

		try {
			// create prepared statement
			PreparedStatement ps = cnt.prepareStatement(query);

			// get result and store in result set
			ResultSet rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			
			/*for (int i = 0 ; i < rsmd.getColumnCount(); i++) {
				ArrayList<String> col = new ArrayList<String>();
				col.add(rsmd.getColumnName(i+1));
				list.add(col);
			}*/
			
			ArrayList<String> header = new ArrayList<String>();
			for (int i = 0 ; i < rsmd.getColumnCount() ; i++) {
				header.add(rsmd.getColumnName(i+1));
			}
			list.add(header);

			// transform set to list
			// rs.next() means get next in result set
			while (rs.next()) {
				ArrayList<String> row = new ArrayList<String>();
				list.add(row);
				for (int i = 0 ; i < rsmd.getColumnCount() ; i++) {
					row.add(rs.getString(i+1));
				}
			}

			// close all the resources
			ps.close();
			rs.close();
			cnt.close();

			System.out.println("[REPORT] SELECT SUCCESS!");
		} catch (SQLException e) {
			System.out.println("[REPORT] SELECT FAILED!");
			e.printStackTrace();
		}

		// return list
		return list;
	}
	
	public List<List<String>> getPendingTickets() {
		// create empty list of contacts
		List<List<String>> list = new ArrayList<List<String>>();

		// get connection from db
		Connection cnt = connection.getConnection();
		

		
		// create string query
		String query = 
		
				"SELECT "
		                +"RDateTime AS date, "
		                +"RType AS type, "
		                +"(SELECT FTitle FROM FILM WHERE FID = (SELECT FID FROM SCHEDULE WHERE SCHEDULE.SID = RESERVATION.SID)) AS Title, "
		                +"(SELECT CNo FROM CINEMA WHERE CINEMA.CID = (SELECT CID FROM SCHEDULE WHERE SCHEDULE.SID = RESERVATION.SID)) AS cinema, "
		                +"(SELECT SeRow FROM SEAT WHERE SEAT.SeID = RESERVATION.SeID) AS SeatRow, "
		                +"(SELECT SeCol FROM SEAT WHERE SEAT.SeID = RESERVATION.SeID) AS SeatCol, "
		                +"RStatus AS status "
		                +"FROM RESERVATION "
		                +"WHERE RESERVATION.RStatus = \"pending\" "
		                +"ORDER BY RDateTime DESC";

		try {
			// create prepared statement
			PreparedStatement ps = cnt.prepareStatement(query);

			// get result and store in result set
			ResultSet rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			
			/*for (int i = 0 ; i < rsmd.getColumnCount(); i++) {
				ArrayList<String> col = new ArrayList<String>();
				col.add(rsmd.getColumnName(i+1));
				list.add(col);
			}*/
			
			ArrayList<String> header = new ArrayList<String>();
			for (int i = 0 ; i < rsmd.getColumnCount() ; i++) {
				header.add(rsmd.getColumnName(i+1));
			}
			list.add(header);

			// transform set to list
			// rs.next() means get next in result set
			while (rs.next()) {
				ArrayList<String> row = new ArrayList<String>();
				list.add(row);
				for (int i = 0 ; i < rsmd.getColumnCount() ; i++) {
					row.add(rs.getString(i+1));
				}
			}

			// close all the resources
			ps.close();
			rs.close();
			cnt.close();

			System.out.println("[REPORT] SELECT SUCCESS!");
		} catch (SQLException e) {
			System.out.println("[REPORT] SELECT FAILED!");
			e.printStackTrace();
		}

		// return list
		return list;
	}
	
	public List<List<String>> getCancelledTickets() {
		// create empty list of contacts
		List<List<String>> list = new ArrayList<List<String>>();

		// get connection from db
		Connection cnt = connection.getConnection();
		

		
		// create string query
		String query = 
		
				"SELECT "
		                +"RDateTime AS date, "
		                +"RType AS type, "
		                +"(SELECT FTitle FROM FILM WHERE FID = (SELECT FID FROM SCHEDULE WHERE SCHEDULE.SID = RESERVATION.SID)) AS Title, "
		                +"(SELECT CNo FROM CINEMA WHERE CINEMA.CID = (SELECT CID FROM SCHEDULE WHERE SCHEDULE.SID = RESERVATION.SID)) AS cinema, "
		                +"(SELECT SeRow FROM SEAT WHERE SEAT.SeID = RESERVATION.SeID) AS SeatRow, "
		                +"(SELECT SeCol FROM SEAT WHERE SEAT.SeID = RESERVATION.SeID) AS SeatCol, "
		                +"RStatus AS status "
		                +"FROM RESERVATION "
		                +"WHERE RESERVATION.RStatus = \"cancelled\" "
		                +"ORDER BY RDateTime DESC";

		try {
			// create prepared statement
			PreparedStatement ps = cnt.prepareStatement(query);

			// get result and store in result set
			ResultSet rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			
			/*for (int i = 0 ; i < rsmd.getColumnCount(); i++) {
				ArrayList<String> col = new ArrayList<String>();
				col.add(rsmd.getColumnName(i+1));
				list.add(col);
			}*/
			
			ArrayList<String> header = new ArrayList<String>();
			for (int i = 0 ; i < rsmd.getColumnCount() ; i++) {
				header.add(rsmd.getColumnName(i+1));
			}
			list.add(header);

			// transform set to list
			// rs.next() means get next in result set
			while (rs.next()) {
				ArrayList<String> row = new ArrayList<String>();
				list.add(row);
				for (int i = 0 ; i < rsmd.getColumnCount() ; i++) {
					row.add(rs.getString(i+1));
				}
			}

			// close all the resources
			ps.close();
			rs.close();
			cnt.close();

			System.out.println("[REPORT] SELECT SUCCESS!");
		} catch (SQLException e) {
			System.out.println("[REPORT] SELECT FAILED!");
			e.printStackTrace();
		}

		// return list
		return list;
	}
	
	public List<List<String>> getMonthXGenre() {
		// create empty list of contacts
		List<List<String>> list = new ArrayList<List<String>>();

		// get connection from db
		Connection cnt = connection.getConnection();
		
		int year = LocalDate.now().getYear();
		
		// create string query
		String query = 
				
				"SELECT FGenre, "
				+ "(SELECT COUNT(*) FROM (SCHEDULE INNER JOIN RESERVATION ON SCHEDULE.SID = RESERVATION.SID) INNER JOIN FILM F2 ON SCHEDULE.FID = F2.FID WHERE F1.FGenre = F2.FGenre AND MONTH(RDateTime) = 1 AND YEAR(RDateTime) = " + year + ") AS Jan, "
				+ "(SELECT COUNT(*) FROM (SCHEDULE INNER JOIN RESERVATION ON SCHEDULE.SID = RESERVATION.SID) INNER JOIN FILM F2 ON SCHEDULE.FID = F2.FID WHERE F1.FGenre = F2.FGenre AND MONTH(RDateTime) = 2 AND YEAR(RDateTime) = " + year + ") AS Feb, "
				+ "(SELECT COUNT(*) FROM (SCHEDULE INNER JOIN RESERVATION ON SCHEDULE.SID = RESERVATION.SID) INNER JOIN FILM F2 ON SCHEDULE.FID = F2.FID WHERE F1.FGenre = F2.FGenre AND MONTH(RDateTime) = 3 AND YEAR(RDateTime) = " + year + ") AS Mar, "
				+ "(SELECT COUNT(*) FROM (SCHEDULE INNER JOIN RESERVATION ON SCHEDULE.SID = RESERVATION.SID) INNER JOIN FILM F2 ON SCHEDULE.FID = F2.FID WHERE F1.FGenre = F2.FGenre AND MONTH(RDateTime) = 4 AND YEAR(RDateTime) = " + year + ") AS Apr, "
				+ "(SELECT COUNT(*) FROM (SCHEDULE INNER JOIN RESERVATION ON SCHEDULE.SID = RESERVATION.SID) INNER JOIN FILM F2 ON SCHEDULE.FID = F2.FID WHERE F1.FGenre = F2.FGenre AND MONTH(RDateTime) = 5 AND YEAR(RDateTime) = " + year + ") AS May, "
				+ "(SELECT COUNT(*) FROM (SCHEDULE INNER JOIN RESERVATION ON SCHEDULE.SID = RESERVATION.SID) INNER JOIN FILM F2 ON SCHEDULE.FID = F2.FID WHERE F1.FGenre = F2.FGenre AND MONTH(RDateTime) = 6 AND YEAR(RDateTime) = " + year + ") AS Jun, "
				+ "(SELECT COUNT(*) FROM (SCHEDULE INNER JOIN RESERVATION ON SCHEDULE.SID = RESERVATION.SID) INNER JOIN FILM F2 ON SCHEDULE.FID = F2.FID WHERE F1.FGenre = F2.FGenre AND MONTH(RDateTime) = 7 AND YEAR(RDateTime) = " + year + ") AS Jul, "
				+ "(SELECT COUNT(*) FROM (SCHEDULE INNER JOIN RESERVATION ON SCHEDULE.SID = RESERVATION.SID) INNER JOIN FILM F2 ON SCHEDULE.FID = F2.FID WHERE F1.FGenre = F2.FGenre AND MONTH(RDateTime) = 8 AND YEAR(RDateTime) = " + year + ") AS Aug, "
				+ "(SELECT COUNT(*) FROM (SCHEDULE INNER JOIN RESERVATION ON SCHEDULE.SID = RESERVATION.SID) INNER JOIN FILM F2 ON SCHEDULE.FID = F2.FID WHERE F1.FGenre = F2.FGenre AND MONTH(RDateTime) = 9 AND YEAR(RDateTime) = " + year + ") AS Sep, "
				+ "(SELECT COUNT(*) FROM (SCHEDULE INNER JOIN RESERVATION ON SCHEDULE.SID = RESERVATION.SID) INNER JOIN FILM F2 ON SCHEDULE.FID = F2.FID WHERE F1.FGenre = F2.FGenre AND MONTH(RDateTime) = 10 AND YEAR(RDateTime) = " + year + ") AS Oct, "
				+ "(SELECT COUNT(*) FROM (SCHEDULE INNER JOIN RESERVATION ON SCHEDULE.SID = RESERVATION.SID) INNER JOIN FILM F2 ON SCHEDULE.FID = F2.FID WHERE F1.FGenre = F2.FGenre AND MONTH(RDateTime) = 11 AND YEAR(RDateTime) = " + year + ") AS Nov, "
				+ "(SELECT COUNT(*) FROM (SCHEDULE INNER JOIN RESERVATION ON SCHEDULE.SID = RESERVATION.SID) INNER JOIN FILM F2 ON SCHEDULE.FID = F2.FID WHERE F1.FGenre = F2.FGenre AND MONTH(RDateTime) = 12 AND YEAR(RDateTime) = " + year + ") AS Dece, "
				+ "(SELECT COUNT(*) FROM (SCHEDULE INNER JOIN RESERVATION ON SCHEDULE.SID = RESERVATION.SID) INNER JOIN FILM F2 ON SCHEDULE.FID = F2.FID WHERE F1.FGenre = F2.FGenre AND YEAR(RDateTime) = " + year + ") AS Total "
				+ "FROM FILM F1 GROUP BY FGenre ORDER BY FGenre";

		try {
			// create prepared statement
			PreparedStatement ps = cnt.prepareStatement(query);

			// get result and store in result set
			ResultSet rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			
			/*for (int i = 0 ; i < rsmd.getColumnCount(); i++) {
				ArrayList<String> col = new ArrayList<String>();
				col.add(rsmd.getColumnName(i+1));
				list.add(col);
			}*/
			
			ArrayList<String> header = new ArrayList<String>();
			for (int i = 0 ; i < rsmd.getColumnCount() ; i++) {
				header.add(rsmd.getColumnName(i+1));
			}
			list.add(header);

			// transform set to list
			// rs.next() means get next in result set
			while (rs.next()) {
				ArrayList<String> row = new ArrayList<String>();
				list.add(row);
				for (int i = 0 ; i < rsmd.getColumnCount() ; i++) {
					row.add(rs.getString(i+1));
				}
			}

			// close all the resources
			ps.close();
			rs.close();
			cnt.close();

			System.out.println("[REPORT] SELECT SUCCESS!");
		} catch (SQLException e) {
			System.out.println("[REPORT] SELECT FAILED!");
			e.printStackTrace();
		}

		// return list
		return list;
	}
}
