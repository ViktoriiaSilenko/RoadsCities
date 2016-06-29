package example.testtask.roads.model;

import java.util.Objects;
import java.util.Set;

public class MyMap {
	private Set<City> cities;
	private Set<Road> roads;
	
	public MyMap(Set<City> cities, Set<Road> roads) {
		this.cities = Objects.requireNonNull(cities);
		this.roads = Objects.requireNonNull(roads);
	}

	public Set<City> getCities() {
		return cities;
	}

	public void setCities(Set<City> cities) {
		this.cities = Objects.requireNonNull(cities);
	}

	public Set<Road> getRoads() {
		return roads;
	}

	public void setRoads(Set<Road> roads) {
		this.roads = Objects.requireNonNull(roads);
	}

}
