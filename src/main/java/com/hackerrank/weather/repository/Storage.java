package com.hackerrank.weather.repository;

import java.util.ArrayList;
import java.util.List;

import com.hackerrank.weather.model.Weather;

public class Storage {

	public static List<Weather> weatherStorage = new ArrayList<Weather>();
	
	public static void ListData(){
		weatherStorage.stream().forEach(System.out::println);
	}

}
