package example.testtask.roads.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

public class City {
	
	private String name;
	private Point coordinates;
	private Set<Road> roads;
	
	public City(String name, Point coordinates) {
		if (StringUtils.isBlank(name)) {
			throw new IllegalArgumentException("City name should be not null and not blank");
		} else {
			this.name = name;
			this.coordinates = Objects.requireNonNull(coordinates);
			this.roads = new HashSet<>();
		}
	}

	public Set<Road> getRoads() {
		return roads;
	}

	@Override
	public boolean equals(Object obj) {
		/*return this == obj ||
				obj != null &&
				obj.getClass() == getClass() &&
						((City) obj).coordinates.equals(coordinates);*/
		
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
