package sureseats.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class User {
	private int id;
	private String username;
	private String email;
	private String password;
	private String mobileno;
	private String firstname;
	private String lastname;
	private String gender;
	private LocalDate bdate;
	private LocalDate rdate;
	private LocalDateTime lastlogin;
	private boolean islocked;
	private Province province;
	private City city;
	private List<Mall> prefmalls;

	public static final String TABLE = "USER";
	public static final String COL_ID = "UID";
	public static final String COL_USERNAME = "UUsername";
	public static final String COL_EMAIL = "UEmail";
	public static final String COL_PASSWORD = "UPassword";
	public static final String COL_MOBILENO = "UMobileNo";
	public static final String COL_FIRSTNAME = "UFirstName";
	public static final String COL_LASTNAME = "ULastName";
	public static final String COL_GENDER = "UGender";
	public static final String COL_BDATE = "UBDate";
	public static final String COL_RDATE = "URDate";
	public static final String COL_LASTLOGIN = "ULastLogin";
	public static final String COL_ISLOCKED = "UIsLocked";
	public static final String COL_PROVINCE = "PID";
	public static final String COL_CITY = "CTID";

	public static final String TABLE_PREFMALLS = "PREFERS";
	public static final String PMCOL_USER = User.COL_ID;
	public static final String PMCOL_MALL = Mall.COL_ID;

	public User() {
		this.prefmalls = new ArrayList<Mall>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobileno() {
		return mobileno;
	}

	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public LocalDate getBdate() {
		return bdate;
	}

	public void setBdate(LocalDate bdate) {
		this.bdate = bdate;
	}

	public LocalDate getRdate() {
		return rdate;
	}

	public void setRdate(LocalDate rdate) {
		this.rdate = rdate;
	}

	public LocalDateTime getLastlogin() {
		return lastlogin;
	}

	public void setLastlogin(LocalDateTime lastlogin) {
		this.lastlogin = lastlogin;
	}

	public boolean islocked() {
		return islocked;
	}

	public void setIslocked(boolean islocked) {
		this.islocked = islocked;
	}

	public Province getProvince() {
		return province;
	}

	public void setProvince(Province province) {
		this.province = province;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public List<Mall> getPrefmalls() {
		return prefmalls;
	}

	public void setPrefmalls(List<Mall> prefmalls) {
		this.prefmalls = prefmalls;
	}

	@Override
	public String toString() {
		return String.format(
				"User ID=%s\nUsername=%s\nEmail=%s\nPassword=%s\n"
				+ "Mobile No=%s\nName=%s %s\nGender=%s\nBirthdate=%s\n"
				+ "Register Date=%s\nLast Login=%s\nLocked?=%s\n",
				id, username, email, password, mobileno, firstname, lastname, gender, bdate.toString(),
				rdate.toString(), lastlogin.toString(), islocked);
	}
}
