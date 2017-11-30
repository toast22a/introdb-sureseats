package model;

import java.time.LocalDate;

public class Film {
	private int id;
	private String title;
	private String genre;
	private LocalDate date;
	private String rating;
	private String cast;
	private int runtime;
	private double price;
	private String synopsis;
	private String image;

	public static final String TABLE = "FILM";
	public static final String COL_ID = "FID";
	public static final String COL_TITLE = "FTitle";
	public static final String COL_GENRE = "FGenre";
	public static final String COL_DATE = "FDate";
	public static final String COL_RATING = "FRating";
	public static final String COL_CAST = "FCast";
	public static final String COL_RUNTIME = "FRuntime";
	public static final String COL_PRICE = "FPrice";
	public static final String COL_SYNOPSIS = "FSynopsis";
	public static final String COL_IMAGE = "FImage";

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getCast() {
		return cast;
	}

	public void setCast(String cast) {
		this.cast = cast;
	}

	public int getRuntime() {
		return runtime;
	}

	public void setRuntime(int runtime) {
		this.runtime = runtime;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return String.format("FILM ID=%s\nTITLE=%s\nGENRE=%s\nDATE=%s\n"
				+ "RATING=%s\nCAST=%s\nRUNTIME=%s\nPRICE=%s\n"
				+ "SYNOPSIS=%s\nIMAGE=%s\n", id, title, genre, date.toString(),
				rating, cast, runtime, price, synopsis, image);
	}
}
