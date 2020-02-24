package com.hackerrank.weather.controller;

import java.net.URI;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hackerrank.weather.model.Weather;
import com.hackerrank.weather.repository.Storage;

@RestController
public class WeatherApiRestController {

	@PostMapping("/weather")
	public ResponseEntity<Void> postWeather(@RequestBody Weather weather) {

		if (Storage.weatherStorage.stream().anyMatch(w -> w.getId().equals(weather.getId())))
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);

		Storage.weatherStorage.add(weather);
		Storage.ListData();
		return new ResponseEntity<Void>(HttpStatus.CREATED);

	}

	@GetMapping("/weather")
	public ResponseEntity<List<Weather>> getWeather(@RequestParam Map<String, String> rp) {
		List<Weather> results = null;
		if (rp.isEmpty()) {
			results = Storage.weatherStorage;
			return ResponseEntity.ok().body(results);
		} else if (rp.containsKey("date")) {
			results = Storage.weatherStorage.stream().filter(w -> w.isDate(rp.get("date")))
					.collect(Collectors.toList());
		} else if (rp.containsKey("lat")) {
			results = Storage.weatherStorage.stream()
					.filter(w -> w.isLocation(Float.parseFloat(rp.get("lat")), Float.parseFloat(rp.get("lon"))))
					.collect(Collectors.toList());
		}
		if (null == results || results.isEmpty())
			return new ResponseEntity<List<Weather>>(HttpStatus.NOT_FOUND);
		else
			return ResponseEntity.ok().body(results);
	}

	@DeleteMapping("/erase")
	public ResponseEntity<Void> eraseData(@RequestParam Map<String, String> rp) {
		if (rp.containsKey("start")) {
			Storage.weatherStorage
					.removeIf(w -> w.matchForErase(rp.get("start"), rp.get("end"), rp.get("lat"), rp.get("lon")));
		} else {
			Storage.weatherStorage.clear();
		}

		return ResponseEntity.ok().build();
	}
}
