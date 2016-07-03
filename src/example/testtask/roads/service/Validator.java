package example.testtask.roads.service;

import static org.apache.commons.lang.StringUtils.isBlank;

import example.testtask.roads.model.City;

public class Validator {
	
	private Validator() { //empty - prevent construction
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

}
