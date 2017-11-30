package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class ProvinceService {
	private SureseatsDB connection;

	public ProvinceService(SureseatsDB sureseatsDB) {
		this.connection = sureseatsDB;
	}

	public List<Province> getAll() {
		// create empty list of contacts
		List<Province> provinces = new ArrayList<Province>();

		// get connection from db
		Connection cnt = connection.getConnection();

		// create string query
		String query = "SELECT * FROM " + Province.TABLE;

		try {
			// create prepared statement
			PreparedStatement ps = cnt.prepareStatement(query);

			// get result and store in result set
			ResultSet rs = ps.executeQuery();

			// transform set to list
			// rs.next() means get next in result set
			while (rs.next()) {
				provinces.add(toProvince(rs));
			}

			// close all the resources
			ps.close();
			rs.close();
			cnt.close();

			System.out.println("[PROVINCE] SELECT SUCCESS!");
		} catch (SQLException e) {
			System.out.println("[PROVINCE] SELECT FAILED!");
			e.printStackTrace();
		}

		// return list
		return provinces;
	}

	public Province getProvince(int id) {
		Province province = new Province();

		// get connection from db
		Connection cnt = connection.getConnection();

		// create string query
		String query = "SELECT * FROM " + Province.TABLE + " WHERE " + Province.COL_ID + " = ?";

		try {
			// create prepared statement
			PreparedStatement ps = cnt.prepareStatement(query);
			
			ps.setInt(1, id);

			// get result and store in result set
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				province = toProvince(rs);
			}

			// close all the resources
			ps.close();
			rs.close();
			cnt.close();

			System.out.println("[PROVINCE] SELECT SUCCESS!");
		} catch (SQLException e) {
			System.out.println("[PROVINCE] SELECT FAILED!");
			e.printStackTrace();
		}

		// return list
		return province;
	}

	private Province toProvince(ResultSet rs) throws SQLException {
		Province province = new Province();

		province.setId(rs.getInt(Province.COL_ID));
		province.setName(rs.getString(Province.COL_NAME));

		return province;
	}

	public void addProvince(Province province) {
		// get connection from db
		Connection cnt = connection.getConnection();

		// create a query
		String query = "INSERT INTO " + Province.TABLE + " VALUES (?, ?)";

		try {
			// create prepared statement
			PreparedStatement ps = cnt.prepareStatement(query);

			// prepare the values
			ps.setInt(1, Types.NULL); // because id is auto-increment anyway
			ps.setString(2, province.getName());

			// execute the update
			ps.executeUpdate();

			// close resources
			ps.close();
			cnt.close();
			System.out.println("[PROVINCE] INSERT SUCCESS");
		} catch (SQLException e) {
			System.out.println("[PROVINCE] INSERT FAILED");
			e.printStackTrace();
		}
	}

	public void updateProvince(Province province) {
		// get connection from db
		Connection cnt = connection.getConnection();

		// create a query
		String query = "UPDATE " + Province.TABLE + " SET " + Province.COL_NAME + " = ?" + " WHERE " + Province.COL_ID
				+ " = ?";

		try {
			// create prepared statement
			PreparedStatement ps = cnt.prepareStatement(query);

			// prepare the values
			ps.setInt(2, province.getId()); // because id is auto-increment anyway
			ps.setString(1, province.getName());

			// execute the update
			ps.executeUpdate();

			// close resources
			ps.close();
			cnt.close();
			System.out.println("[PROVINCE] UPDATE SUCCESS");
		} catch (SQLException e) {
			System.out.println("[PROVINCE] UPDATE FAILED");
			e.printStackTrace();
		}
	}

	public void deleteProvince(int id) {
		// get connection from db
		Connection cnt = connection.getConnection();

		// create a query
		String query = "DELETE FROM " + Province.TABLE + " WHERE " + Province.COL_ID + " = ?";

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
			System.out.println("[PROVINCE] DELETE SUCCESS");
		} catch (SQLException e) {
			System.out.println("[PROVINCE] DELETE FAILED");
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		ProvinceService service = new ProvinceService(new SureseatsDB());
		List<Province> provinces = service.getAll();
		Province province = service.getProvince(1);

		for (Province p : provinces) {
			System.out.println(p.toString());
		}
		System.out.println(province.toString());
	}
}
