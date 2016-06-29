package example.testtask.roads.model;

import static java.util.Objects.requireNonNull;
import java.util.Set;

public class MyMap {
	private Set<City> cities;
	private Set<Road> roads;
	
	public MyMap(Set<City> cities, Set<Road> roads) {
		this.cities = requireNonNull(cities);
		this.roads = requireNonNull(roads);
	}

	public Set<City> getCities() {
		return cities;
	}

	public void setCities(Set<City> cities) {
		this.cities = requireNonNull(cities);
	}

	public Set<Road> getRoads() {
		return roads;
	}

	public void setRoads(Set<Road> roads) {
		this.roads = requireNonNull(roads);
	}

}
