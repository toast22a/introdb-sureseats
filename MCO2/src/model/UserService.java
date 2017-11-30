package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class UserService {
	private SureseatsDB connection;
	private ProvinceService provinceService;
	private CityService cityService;
	private MallService mallService;

	public UserService(SureseatsDB sureseatsDB) {
		this.connection = sureseatsDB;
		this.provinceService = new ProvinceService(sureseatsDB);
		this.cityService = new CityService(sureseatsDB);
		this.mallService = new MallService(sureseatsDB);
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
		user.setPrefmalls(mallService.getPreferred(rs.getInt(User.COL_ID)));

		return user;
	}

	public void addUser(User user) {
		// get connection from db
		Connection cnt = connection.getConnection();

		// create a query
		String query = "INSERT INTO " + User.TABLE + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			// create prepared statement
			PreparedStatement ps = cnt.prepareStatement(query);

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
			ps.setDate(10, Date.valueOf(user.getRdate()));
			ps.setTimestamp(11, Timestamp.valueOf(user.getLastlogin()));
			ps.setBoolean(12, user.islocked());
			ps.setInt(13, user.getProvince().getId());
			ps.setInt(14, user.getCity().getId());

			// execute the update
			ps.executeUpdate();

			// close resources
			ps.close();
			cnt.close();
			System.out.println("[USER] INSERT SUCCESS");
		} catch (SQLException e) {
			System.out.println("[USER] INSERT FAILED");
			e.printStackTrace();
		}
	}

	public void updateUser(User user) {
		// get connection from db
		Connection cnt = connection.getConnection();

		// create a query
		String query = "UPDATE " + User.TABLE
				+ " SET "
				+ User.COL_USERNAME + " = ?,"
				+ User.COL_EMAIL + " = ?,"
				+ User.COL_PASSWORD + " = ?"
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
			ps.setInt(1, Types.NULL); // because id is auto-increment anyway
			ps.setInt(2, user.getId());
			ps.setInt(3, mall.getId());

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

	public static void main(String[] args) {
		UserService service = new UserService(new SureseatsDB());
		List<User> users = service.getAll();
		User user = service.getUser(1);

		for (User u : users) {
			System.out.println(u.toString());
		}
		System.out.println(user.toString());
	}
}
