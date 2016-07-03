package example.testtask.roads.model;

import static java.util.Objects.requireNonNull;
import example.testtask.roads.service.Validator;

public class City {
	
	private String name;
	private Point coordinates;
	
	public City(String name, Point coordinates) {
		Validator.checkNameForContent(name);
		this.name = name;
		this.coordinates = requireNonNull(coordinates);
	}

	@Override
	public boolean equals(Object obj) {	
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		City other = (City) obj;
		return other.coordinates.equals(coordinates);
	}
	
	@Override
	public int hashCode() {
		return coordinates.hashCode();
	}

	@Override
	public String toString() {
		return "City [name=" + name + ", coordinates=" + coordinates + "]";
	}
}
