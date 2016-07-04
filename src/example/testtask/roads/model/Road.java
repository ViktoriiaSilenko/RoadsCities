package example.testtask.roads.model;

import static java.util.Objects.requireNonNull;
import example.testtask.roads.service.Validator;

public class Road {
	private String name;
	private City cityFrom;
	private City cityTo;
	
	public Road(String name, City cityFrom, City cityTo) {
		this.cityFrom = requireNonNull(cityFrom);
		this.cityTo = requireNonNull(cityTo);
		Validator.checkCitiesForDifference(cityFrom, cityTo);
		Validator.checkNameForContent(name);
		this.name = name;
	}

	public City getCityFrom() {
		return cityFrom;
	}

	public City getCityTo() {
		return cityTo;
	}

	@Override
	public boolean equals(Object obj) {		
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Road other = (Road) obj;
		return (other.cityFrom.equals(cityFrom) && other.cityTo.equals(cityTo)) 
				|| (other.cityFrom.equals(cityTo) && other.cityTo.equals(cityFrom));
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		return prime * (cityFrom.hashCode() + cityTo.hashCode());
	}

	@Override
	public String toString() {
		return "Road [name=" + name + ", cityFrom=" + cityFrom + ", cityTo=" + cityTo + "]";
	}
}
