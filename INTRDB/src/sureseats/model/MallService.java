package sureseats.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class MallService {
	private SureseatsDB connection;
	private CityService cityService;

	public MallService(SureseatsDB sureseatsDB) {
		this.connection = sureseatsDB;
		this.cityService = new CityService(sureseatsDB);
	}

	public List<Mall> getAll() {
		// create empty list of contacts
		List<Mall> malls = new ArrayList<Mall>();

		// get connection from db
		Connection cnt = connection.getConnection();

		// create string query
		String query = "SELECT * FROM " + Mall.TABLE;

		try {
			// create prepared statement
			PreparedStatement ps = cnt.prepareStatement(query);

			// get result and store in result set
			ResultSet rs = ps.executeQuery();

			// transform set to list
			// rs.next() means get next in result set
			while (rs.next()) {
				malls.add(toMall(rs));
			}

			// close all the resources
			ps.close();
			rs.close();
			cnt.close();

			System.out.println("[MALL] SELECT SUCCESS!");
		} catch (SQLException e) {
			System.out.println("[MALL] SELECT FAILED!");
			e.printStackTrace();
		}

		// return list
		return malls;
	}
	
	public List<Mall> getPreferred(User user) {
		// create empty list of contacts
		List<Mall> malls = new ArrayList<Mall>();

		// get connection from db
		Connection cnt = connection.getConnection();

		// create string query
		String query = "SELECT * FROM " + Mall.TABLE +
				" WHERE " + Mall.COL_ID + " IN (SELECT " +
				Mall.COL_ID + " FROM " + User.TABLE_PREFMALLS + " WHERE " +
				User.PMCOL_USER + " = ?)";

		try {
			// create prepared statement
			PreparedStatement ps = cnt.prepareStatement(query);
			
			ps.setInt(1, user.getId());

			// get result and store in result set
			ResultSet rs = ps.executeQuery();

			// transform set to list
			// rs.next() means get next in result set
			while (rs.next()) {
				malls.add(toMall(rs));
			}

			// close all the resources
			ps.close();
			rs.close();
			cnt.close();

			System.out.println("[MALL] SELECT SUCCESS!");
		} catch (SQLException e) {
			System.out.println("[MALL] SELECT FAILED!");
			e.printStackTrace();
		}

		// return list
		return malls;
	}

	public Mall getMall(int id) {
		Mall mall = new Mall();

		// get connection from db
		Connection cnt = connection.getConnection();

		// create string query
		String query = "SELECT * FROM " + Mall.TABLE + " WHERE " + Mall.COL_ID + " = ?";

		try {
			// create prepared statement
			PreparedStatement ps = cnt.prepareStatement(query);
			
			ps.setInt(1, id);

			// get result and store in result set
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				mall = toMall(rs);
			}

			// close all the resources
			ps.close();
			rs.close();
			cnt.close();

			System.out.println("[MALL] SELECT SUCCESS!");
		} catch (SQLException e) {
			System.out.println("[MALL] SELECT FAILED!");
			e.printStackTrace();
		}

		// return list
		return mall;
	}

	private Mall toMall(ResultSet rs) throws SQLException {
		Mall mall = new Mall();

		mall.setId(rs.getInt(Mall.COL_ID));
		mall.setName(rs.getString(Mall.COL_NAME));
		mall.setCity(cityService.getCity(rs.getInt(Mall.COL_CITY)));

		return mall;
	}

	public void addMall(Mall mall) {
		// get connection from db
		Connection cnt = connection.getConnection();

		// create a query
		String query = "INSERT INTO " + Mall.TABLE + " VALUES (?, ?, ?)";

		try {
			// create prepared statement
			PreparedStatement ps = cnt.prepareStatement(query);

			// prepare the values
			ps.setInt(1, Types.NULL); // because id is auto-increment anyway
			ps.setString(2, mall.getName());
			ps.setInt(3, mall.getCity().getId());

			// execute the update
			ps.executeUpdate();

			// close resources
			ps.close();
			cnt.close();
			System.out.println("[MALL] INSERT SUCCESS");
		} catch (SQLException e) {
			System.out.println("[MALL] INSERT FAILED");
			e.printStackTrace();
		}
	}

	public void updateMall(Mall mall) {
		// get connection from db
		Connection cnt = connection.getConnection();

		// create a query
		String query = "UPDATE " + Mall.TABLE
				+ " SET "
				+ Mall.COL_NAME + " = ?,"
				+ Mall.COL_CITY + " = ?"
				+ " WHERE " + Mall.COL_ID + " = ?";

		try {
			// create prepared statement
			PreparedStatement ps = cnt.prepareStatement(query);

			// prepare the values
			ps.setInt(3, mall.getId()); // because id is auto-increment anyway
			ps.setString(1, mall.getName());
			ps.setInt(2, mall.getCity().getId());

			// execute the update
			ps.executeUpdate();

			// close resources
			ps.close();
			cnt.close();
			System.out.println("[MALL] UPDATE SUCCESS");
		} catch (SQLException e) {
			System.out.println("[MALL] UPDATE FAILED");
			e.printStackTrace();
		}
	}

	public void deleteMall(int id) {
		// get connection from db
		Connection cnt = connection.getConnection();

		// create a query
		String query = "DELETE FROM " + Mall.TABLE + " WHERE " + Mall.COL_ID + " = ?";

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
			System.out.println("[MALL] DELETE SUCCESS");
		} catch (SQLException e) {
			System.out.println("[MALL] DELETE FAILED");
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		MallService service = new MallService(new SureseatsDB());
		List<Mall> malls = service.getAll();
		Mall mall = service.getMall(1);

		for (Mall m : malls) {
			System.out.println(m.toString());
		}
		System.out.println(mall.toString());
	}
}
