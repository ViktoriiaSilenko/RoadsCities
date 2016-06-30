package example.testtask.roads.model;

import java.util.Collections;
import java.util.HashSet;
import static java.util.Objects.requireNonNull;
import java.util.Set;

import static org.apache.commons.lang.StringUtils.isBlank;

public class City {
	
	private String name;
	private Point coordinates;
	private Set<Road> roads;
	
	public City(String name, Point coordinates) {
		if (isBlank(name)) {
			throw new IllegalArgumentException("City name should be not null and not blank");
		} else {
			this.name = name;
			this.coordinates = requireNonNull(coordinates);
			this.roads = new HashSet<>();
		}
	}

	public Set<Road> getRoads() {
		return Collections.unmodifiableSet(roads);
	}
	
	public boolean addRoad(Road road) {
		if(this.equals(road.getCityFrom()) || this.equals(road.getCityTo())) {
			return roads.add(road);
		}
		return false;
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
