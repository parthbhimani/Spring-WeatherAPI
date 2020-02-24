package com.hackerrank.weather.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Location {
    private String cityName;
    private String stateName;
    private Float latitude;
    private Float longitude;

    public Location() {
    }

    public Location(String cityName, String stateName, Float latitude, Float longitude) {
        this.cityName = cityName;
        this.stateName = stateName;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getCityName() {
        return cityName;
    }

    @JsonProperty("city")
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getStateName() {
        return stateName;
    }

    @JsonProperty("state")
    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public Float getLatitude() {
        return latitude;
    }
    @JsonProperty("lat")
    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }
    @JsonProperty("lon")
    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Location [cityName=");
		builder.append(cityName);
		builder.append(", stateName=");
		builder.append(stateName);
		builder.append(", latitude=");
		builder.append(latitude);
		builder.append(", longitude=");
		builder.append(longitude);
		builder.append("]");
		return builder.toString();
	}
    
    
}
