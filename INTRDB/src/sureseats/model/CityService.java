package sureseats.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class CityService {
	private SureseatsDB connection;
	private ProvinceService provinceService;

	public CityService(SureseatsDB sureseatsDB) {
		this.connection = sureseatsDB;
		this.provinceService = new ProvinceService(sureseatsDB);
	}

	public List<City> getAll() {
		// create empty list of contacts
		List<City> cities = new ArrayList<City>();

		// get connection from db
		Connection cnt = connection.getConnection();

		// create string query
		String query = "SELECT * FROM " + City.TABLE;

		try {
			// create prepared statement
			PreparedStatement ps = cnt.prepareStatement(query);

			// get result and store in result set
			ResultSet rs = ps.executeQuery();

			// transform set to list
			// rs.next() means get next in result set
			while (rs.next()) {
				cities.add(toCity(rs));
			}

			// close all the resources
			ps.close();
			rs.close();
			cnt.close();

			System.out.println("[CITY] SELECT SUCCESS!");
		} catch (SQLException e) {
			System.out.println("[CITY] SELECT FAILED!");
			e.printStackTrace();
		}

		// return list
		return cities;
	}

	
	public List<City> getCitiesInProvince(Province province) {
		// create empty list of contacts
		List<City> cities = new ArrayList<City>();

		// get connection from db
		Connection cnt = connection.getConnection();

		// create string query
		String query = "SELECT * FROM " + City.TABLE + " WHERE " + City.COL_PROVINCE + " = ?";

		try {
			// create prepared statement
			PreparedStatement ps = cnt.prepareStatement(query);
			
			ps.setInt(1, province.getId());

			// get result and store in result set
			ResultSet rs = ps.executeQuery();

			// transform set to list
			// rs.next() means get next in result set
			while (rs.next()) {
				cities.add(toCity(rs));
			}

			// close all the resources
			ps.close();
			rs.close();
			cnt.close();

			System.out.println("[CITY] SELECT SUCCESS!");
		} catch (SQLException e) {
			System.out.println("[CITY] SELECT FAILED!");
			e.printStackTrace();
		}

		// return list
		return cities;
	}
	
	public City getCity(int id) {
		City city = new City();

		// get connection from db
		Connection cnt = connection.getConnection();

		// create string query
		String query = "SELECT * FROM " + City.TABLE + " WHERE " + City.COL_ID + " = ?";

		try {
			// create prepared statement
			PreparedStatement ps = cnt.prepareStatement(query);
			
			ps.setInt(1, id);

			// get result and store in result set
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				city = toCity(rs);
			}

			// close all the resources
			ps.close();
			rs.close();
			cnt.close();

			System.out.println("[CITY] SELECT SUCCESS!");
		} catch (SQLException e) {
			System.out.println("[CITY] SELECT FAILED!");
			e.printStackTrace();
		}

		// return list
		return city;
	}

	private City toCity(ResultSet rs) throws SQLException {
		City city = new City();

		city.setId(rs.getInt(City.COL_ID));
		city.setName(rs.getString(City.COL_NAME));
		city.setType(rs.getString(City.COL_TYPE));
		city.setProvince(provinceService.getProvince(rs.getInt(City.COL_PROVINCE)));

		return city;
	}

	public void addCity(City city) {
		// get connection from db
		Connection cnt = connection.getConnection();

		// create a query
		String query = "INSERT INTO " + City.TABLE + " VALUES (?, ?, ?, ?)";

		try {
			// create prepared statement
			PreparedStatement ps = cnt.prepareStatement(query);

			// prepare the values
			ps.setInt(1, Types.NULL); // because id is auto-increment anyway
			ps.setString(2, city.getName());
			ps.setString(3, city.getType());
			ps.setInt(4, city.getProvince().getId());

			// execute the update
			ps.executeUpdate();

			// close resources
			ps.close();
			cnt.close();
			System.out.println("[CITY] INSERT SUCCESS");
		} catch (SQLException e) {
			System.out.println("[CITY] INSERT FAILED");
			e.printStackTrace();
		}
	}

	public void updateCity(City city) {
		// get connection from db
		Connection cnt = connection.getConnection();

		// create a query
		String query = "UPDATE " + City.TABLE
				+ " SET "
				+ City.COL_NAME + " = ?,"
				+ City.COL_TYPE + " = ?,"
				+ City.COL_PROVINCE + " = ?"
				+ " WHERE " + City.COL_ID + " = ?";

		try {
			// create prepared statement
			PreparedStatement ps = cnt.prepareStatement(query);

			// prepare the values
			ps.setInt(4, city.getId()); // because id is auto-increment anyway
			ps.setString(1, city.getName());
			ps.setString(2, city.getType());
			ps.setInt(3, city.getProvince().getId());

			// execute the update
			ps.executeUpdate();

			// close resources
			ps.close();
			cnt.close();
			System.out.println("[CITY] UPDATE SUCCESS");
		} catch (SQLException e) {
			System.out.println("[CITY] UPDATE FAILED");
			e.printStackTrace();
		}
	}

	public void deleteCity(int id) {
		// get connection from db
		Connection cnt = connection.getConnection();

		// create a query
		String query = "DELETE FROM " + City.TABLE + " WHERE " + City.COL_ID + " = ?";

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
			System.out.println("[CITY] DELETE SUCCESS");
		} catch (SQLException e) {
			System.out.println("[CITY] DELETE FAILED");
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		CityService service = new CityService(new SureseatsDB());
		List<City> cities = service.getAll();
		City city = service.getCity(1);

		for (City c : cities) {
			System.out.println(c.toString());
		}
		System.out.println(city.toString());
	}
}
