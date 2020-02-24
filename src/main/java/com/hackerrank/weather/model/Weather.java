package com.hackerrank.weather.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Weather {
	private Long id;
	private Date dateRecorded;
	private Location location;
	private Float[] temperature;

	public Weather() {
	}

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	public Weather(Long id, Date dateRecorded, Location location, Float[] temperature) {
		this.id = id;
		this.dateRecorded = dateRecorded;
		this.location = location;
		this.temperature = temperature;
	}

	public boolean isDate(String dateString) {

		Date temp = null;
		try {
			temp = sdf.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println("This Date : " + this.getDateRecorded() + "temp date : " + temp);
		return (this.getDateRecorded().equals(temp));
	}

	public boolean isLocation(Float lat, Float lon) {
		return (this.location.getLatitude().equals(lat) && this.location.getLongitude().equals(lon));
	}

	public boolean matchForErase(String start, String end, String latS, String lonS) {
		try {
			Date sd = sdf.parse(start);
			Date ed = sdf.parse(end);

			Float lat = Float.parseFloat(latS);
			Float lon = Float.parseFloat(lonS);

			return (!this.dateRecorded.before(sd) && !this.getDateRecorded().after(ed) && isLocation(lat, lon));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return false;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDateRecorded() {
		return dateRecorded;
	}

	@JsonProperty("date")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "EST")
	public void setDateRecorded(Date dateRecorded) {
		this.dateRecorded = dateRecorded;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	@JsonProperty("location")
	public void setLocationFromJson(Map<String, String> v) {
		this.location = new Location(v.get("city"), v.get("state"), Float.parseFloat(v.get("lat")),
				Float.parseFloat(v.get("lon")));
	}

	public Float[] getTemperature() {
		return temperature;
	}

	public void setTemperature(Float[] temperature) {
		this.temperature = temperature;
	}

	public boolean equals(Weather other) {
		return (this.id.equals(other.id));
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Weather [id=");
		builder.append(id);
		builder.append(", dateRecorded=");
		builder.append(dateRecorded);
		builder.append(", location=");
		builder.append(location);
		builder.append(", temperature=");
		builder.append(Arrays.toString(temperature));
		builder.append("]");
		return builder.toString();
	}

}
