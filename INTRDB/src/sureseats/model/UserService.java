package sureseats.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UserService {
	private SureseatsDB connection;
	private ProvinceService provinceService;
	private CityService cityService;

	public UserService(SureseatsDB sureseatsDB) {
		this.connection = sureseatsDB;
		this.provinceService = new ProvinceService(sureseatsDB);
		this.cityService = new CityService(sureseatsDB);
	}

	public List<User> getAll() {
		// create empty list of contacts
		List<User> users = new ArrayList<User>();

		// get connection from db
		Connection cnt = connection.getConnection();

		// create string query
		String query = "SELECT * FROM " + User.TABLE;

		try {
			// create prepared statement
			PreparedStatement ps = cnt.prepareStatement(query);

			// get result and store in result set
			ResultSet rs = ps.executeQuery();

			// transform set to list
			// rs.next() means get next in result set
			while (rs.next()) {
				users.add(toUser(rs));
			}

			// close all the resources
			ps.close();
			rs.close();
			cnt.close();

			System.out.println("[USER] SELECT SUCCESS!");
		} catch (SQLException e) {
			System.out.println("[USER] SELECT FAILED!");
			e.printStackTrace();
		}

		// return list
		return users;
	}

	public User getUser(int id) {
		User user = new User();

		// get connection from db
		Connection cnt = connection.getConnection();

		// create string query
		String query = "SELECT * FROM " + User.TABLE + " WHERE " + User.COL_ID + " = ?";

		try {
			// create prepared statement
			PreparedStatement ps = cnt.prepareStatement(query);
			
			ps.setInt(1, id);

			// get result and store in result set
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				user = toUser(rs);
			}

			// close all the resources
			ps.close();
			rs.close();
			cnt.close();

			System.out.println("[USER] SELECT SUCCESS!");
		} catch (SQLException e) {
			System.out.println("[USER] SELECT FAILED!");
			e.printStackTrace();
		}

		// return list
		return user;
	}
	
	public User getUser(String username) {
		User user = new User();

		// get connection from db
		Connection cnt = connection.getConnection();

		// create string query
		String query = "SELECT * FROM " + User.TABLE + " WHERE " + User.COL_USERNAME + " = ?";

		try {
			// create prepared statement
			PreparedStatement ps = cnt.prepareStatement(query);
			
			ps.setString(1, username);

			// get result and store in result set
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				user = toUser(rs);
			}

			// close all the resources
			ps.close();
			rs.close();
			cnt.close();

			System.out.println("[USER] SELECT SUCCESS!");
		} catch (SQLException e) {
			System.out.println("[USER] SELECT FAILED!");
			e.printStackTrace();
		}

		// return list
		return user;
	}
	
	public User getUser(String username, String password) {
		User user = new User();

		// get connection from db
		Connection cnt = connection.getConnection();

		// create string query
		String query = "SELECT * FROM " + User.TABLE + " WHERE "
		+ User.COL_USERNAME + " = ? AND "
		+ User.COL_PASSWORD + " = ?";

		try {
			// create prepared statement
			PreparedStatement ps = cnt.prepareStatement(query);
			
			ps.setString(1, username);
			ps.setString(2, password);

			// get result and store in result set
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				user = toUser(rs);
			}

			// close all the resources
			ps.close();
			rs.close();
			cnt.close();

			System.out.println("[USER] SELECT SUCCESS!");
		} catch (SQLException e) {
			System.out.println("[USER] SELECT FAILED!");
			e.printStackTrace();
		}

		// return list
		return user;
	}

	private User toUser(ResultSet rs) throws SQLException {
		User user = new User();

		user.setId(rs.getInt(User.COL_ID));
		user.setUsername(rs.getString(User.COL_USERNAME));
		user.setEmail(rs.getString(User.COL_EMAIL));
		user.setPassword(rs.getString(User.COL_PASSWORD));
		user.setMobileno(rs.getString(User.COL_MOBILENO));
		user.setFirstname(rs.getString(User.COL_FIRSTNAME));
		user.setLastname(rs.getString(User.COL_LASTNAME));
		user.setGender(rs.getString(User.COL_GENDER));
		user.setBdate(rs.getDate(User.COL_BDATE).toLocalDate());
		user.setRdate(rs.getDate(User.COL_RDATE).toLocalDate());
		user.setLastlogin(rs.getTimestamp(User.COL_LASTLOGIN).toLocalDateTime());
		user.setIslocked(rs.getBoolean(User.COL_ISLOCKED));
		user.setProvince(provinceService.getProvince(rs.getInt(User.COL_PROVINCE)));
		user.setCity(cityService.getCity(rs.getInt(User.COL_CITY)));

		return user;
	}

	public User addUser(User user) {
		User retUser = new User();
		
		// get connection from db
		Connection cnt = connection.getConnection();

		// create a query
		String query = "INSERT INTO " + User.TABLE + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		String query2 = "SELECT * FROM " + User.TABLE + " WHERE " + User.COL_ID + " = LAST_INSERT_ID()";
		
		try {
			// create prepared statement
			PreparedStatement ps = cnt.prepareStatement(query);
			PreparedStatement ps2 = cnt.prepareStatement(query2);

			// prepare the values
			ps.setInt(1, Types.NULL); // because id is auto-increment anyway
			ps.setString(2, user.getUsername());
			ps.setString(3, user.getEmail());
			ps.setString(4, user.getPassword());
			ps.setString(5, user.getMobileno());
			ps.setString(6, user.getFirstname());
			ps.setString(7, user.getLastname());
			ps.setString(8, user.getGender());
			ps.setDate(9, Date.valueOf(user.getBdate()));
			ps.setDate(10, Date.valueOf(LocalDate.now()));
			ps.setTimestamp(11, Timestamp.valueOf(LocalDateTime.now()));
			ps.setBoolean(12, false);
			ps.setInt(13, user.getProvince().getId());
			ps.setInt(14, user.getCity().getId());

			// execute the update
			ps.executeUpdate();
			ResultSet rs = ps2.executeQuery();
			
			if (rs.next()) {
				retUser = toUser(rs);
			}

			// close resources
			ps.close();
			ps2.close();
			rs.close();
			cnt.close();
			System.out.println("[USER] INSERT SUCCESS");
		} catch (SQLException e) {
			System.out.println("[USER] INSERT FAILED");
			e.printStackTrace();
		}
		
		return retUser;
	}

	public void updateUser(User user) {
		// get connection from db
		Connection cnt = connection.getConnection();

		// create a query
		String query = "UPDATE " + User.TABLE
				+ " SET "
				+ User.COL_USERNAME + " = ?,"
				+ User.COL_EMAIL + " = ?,"
				+ User.COL_PASSWORD + " = ?,"
				+ User.COL_MOBILENO + " = ?,"
				+ User.COL_FIRSTNAME + " = ?,"
				+ User.COL_LASTNAME + " = ?,"
				+ User.COL_GENDER + " = ?,"
				+ User.COL_BDATE + " = ?,"
				+ User.COL_RDATE + " = ?,"
				+ User.COL_LASTLOGIN + " = ?,"
				+ User.COL_ISLOCKED + " = ?,"
				+ User.COL_PROVINCE + " = ?,"
				+ User.COL_CITY + " = ?"
				+ " WHERE " + User.COL_ID + " = ?";

		try {
			// create prepared statement
			PreparedStatement ps = cnt.prepareStatement(query);

			// prepare the values
			ps.setInt(14, user.getId()); // because id is auto-increment anyway
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getPassword());
			ps.setString(4, user.getMobileno());
			ps.setString(5, user.getFirstname());
			ps.setString(6, user.getLastname());
			ps.setString(7, user.getGender());
			ps.setDate(8, Date.valueOf(user.getBdate()));
			ps.setDate(9, Date.valueOf(user.getRdate()));
			ps.setTimestamp(10, Timestamp.valueOf(user.getLastlogin()));
			ps.setBoolean(11, user.islocked());
			ps.setInt(12, user.getProvince().getId());
			ps.setInt(13, user.getCity().getId());

			// execute the update
			ps.executeUpdate();

			// close resources
			ps.close();
			cnt.close();
			System.out.println("[USER] UPDATE SUCCESS");
		} catch (SQLException e) {
			System.out.println("[USER] UPDATE FAILED");
			e.printStackTrace();
		}
	}

	public void deleteUser(int id) {
		// get connection from db
		Connection cnt = connection.getConnection();

		// create a query
		String query = "DELETE FROM " + User.TABLE + " WHERE " + User.COL_ID + " = ?";

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
			System.out.println("[USER] DELETE SUCCESS");
		} catch (SQLException e) {
			System.out.println("[USER] DELETE FAILED");
			e.printStackTrace();
		}
	}
	
	public void addPreferred(User user, Mall mall) {
		// get connection from db
		Connection cnt = connection.getConnection();

		// create a query
		String query = "INSERT INTO " + User.TABLE_PREFMALLS + " VALUES (?, ?)";

		try {
			// create prepared statement
			PreparedStatement ps = cnt.prepareStatement(query);

			// prepare the values
			ps.setInt(1, user.getId());
			ps.setInt(2, mall.getId());

			// execute the update
			ps.executeUpdate();

			// close resources
			ps.close();
			cnt.close();
			System.out.println("[PREFERS] INSERT SUCCESS");
		} catch (SQLException e) {
			System.out.println("[PREFERS] INSERT FAILED");
			e.printStackTrace();
		}
	}
	
	public void deletePreferred(int uid, int mid) {
		// get connection from db
		Connection cnt = connection.getConnection();

		// create a query
		String query = "DELETE FROM " + User.TABLE_PREFMALLS + " WHERE " + User.PMCOL_USER + " = ? AND " + User.PMCOL_MALL + " = ?";

		try {
			// create prepared statement
			PreparedStatement ps = cnt.prepareStatement(query);

			// prepare the values
			ps.setInt(1, uid);
			ps.setInt(2, mid);

			// execute the update
			ps.executeUpdate();

			// close resources
			ps.close();
			cnt.close();
			System.out.println("[PREFERS] DELETE SUCCESS");
		} catch (SQLException e) {
			System.out.println("[PREFERS] DELETE FAILED");
			e.printStackTrace();
		}
	}
	
	public List<List<String>> getActiveUsers(int uid) {
		// create empty list of contacts
		List<List<String>> list = new ArrayList<List<String>>();

		// get connection from db
		Connection cnt = connection.getConnection();

		// create string query
		String query = 
		
	
				"SELECT"
						+ "UUsername AS Username, "
						+ "UEmail AS email, "
						+ "ULastLogin AS LastLogin, "
						+ "DATEDIFF(NOW(), ULastLogin) AS DaysInactive"
						+ "FROM USER"
						+ "WHERE DATEDIFF(NOW(), ULastLogin) = 0"
						+ "ORDER BY ULastLogin ASC, UUsername ASC"
						+ "LIMIT 5";
		try {
			// create prepared statement
			PreparedStatement ps = cnt.prepareStatement(query);
			ps.setInt(1, uid);

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

			System.out.println("[RESERVATION] SELECT SUCCESS!");
		} catch (SQLException e) {
			System.out.println("[RESERVATION] SELECT FAILED!");
			e.printStackTrace();
		}

		// return list
		return list;
	}
	
	
	public List<List<String>> getInactiveUsers(int uid) {
		// create empty list of contacts
		List<List<String>> list = new ArrayList<List<String>>();

		// get connection from db
		Connection cnt = connection.getConnection();

		// create string query
		String query = 
		
				"SELECT"
						+"UUsername AS Username, UEmail AS email, ULastLogin AS LastLogin, DATEDIFF(NOW(), ULastLogin) AS DaysInactive"
						+"FROM USER"
						+"ORDER BY ULastLogin ASC, UUsername ASC"
						+"LIMIT 5";

		try {
			// create prepared statement
			PreparedStatement ps = cnt.prepareStatement(query);
			ps.setInt(1, uid);

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

			System.out.println("[RESERVATION] SELECT SUCCESS!");
		} catch (SQLException e) {
			System.out.println("[RESERVATION] SELECT FAILED!");
			e.printStackTrace();
		}

		// return list
		return list;
	}

	public static void main(String[] args) {
		UserService service = new UserService(new SureseatsDB());
		
		List<List<String>> active = service.getActiveUsers(0);
		for (List<String> row : active) {
			for (String col : row) {
				System.out.print(col);
			}
			System.out.println(row.size());
		}
		/*MallService mservice = new MallService(new SureseatsDB());
		List<User> users = service.getAll();
		User user = service.getUser(1);

		for (User u : users) {
			System.out.println(u.toString());
			for (Mall m : mservice.getPreferred(u)) {
				System.out.println(m.getName());
			}
		}
		System.out.println(user.toString());*/
	}
}
