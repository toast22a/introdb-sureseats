package sureseats.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

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

	public static final String TABLE = "USER";
	public static final String COL_ID = TABLE + ".UID";
	public static final String COL_USERNAME = TABLE + ".UUsername";
	public static final String COL_EMAIL = TABLE + ".UEmail";
	public static final String COL_PASSWORD = TABLE + ".UPassword";
	public static final String COL_MOBILENO = TABLE + ".UMobileNo";
	public static final String COL_FIRSTNAME = TABLE + ".UFirstName";
	public static final String COL_LASTNAME = TABLE + ".ULastName";
	public static final String COL_GENDER = TABLE + ".UGender";
	public static final String COL_BDATE = TABLE + ".UBDate";
	public static final String COL_RDATE = TABLE + ".URDate";
	public static final String COL_LASTLOGIN = TABLE + ".ULastLogin";
	public static final String COL_ISLOCKED = TABLE + ".UIsLocked";
	public static final String COL_PROVINCE = TABLE + ".PID";
	public static final String COL_CITY = TABLE + ".CTID";

	public static final String TABLE_PREFMALLS = "PREFERS";
	public static final String PMCOL_USER = TABLE_PREFMALLS + "." + User.COL_ID;
	public static final String PMCOL_MALL = TABLE_PREFMALLS + "." + Mall.COL_ID;

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
