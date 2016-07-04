package example.testtask.roads.service;

import static org.apache.commons.lang.StringUtils.isBlank;
import java.util.Set;
import example.testtask.roads.model.City;
import example.testtask.roads.model.Road;

public class Validator {
	
	private Validator() { 
	}
	
	public static void checkNameForContent(String name) {
		if (isBlank(name)) {
			throw new IllegalArgumentException("Name should be not null and not blank");
		}
	}
	
	public static void checkCoordinatesForNotNegative(int x, int y) {
		if (x < 0 || y < 0) {
			throw new IllegalArgumentException("x < 0 or y < 0");
		}
	}
	
	public static void checkCitiesForDifference(City cityFrom, City cityTo) {
		if (cityFrom.equals(cityTo)) {
			throw new IllegalArgumentException("cityFrom and cityTo should be different");
		}
	}
	
	public static void checkRoadConnectsExistingCities(Road road, Set<City> cities) {
		if (!cities.contains(road.getCityFrom()) || !cities.contains(road.getCityTo())) {
			throw new IllegalArgumentException("road connects not existing cities");
		}
	}

}
