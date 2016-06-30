package example.testtask.roads.service;

import static java.util.Objects.requireNonNull;
import java.util.Set;
import example.testtask.roads.model.City;
import example.testtask.roads.model.Road;
import example.testtask.roads.model.RoadMap;

public class MapServiceImpl implements MapService {
	
	private RoadMap map;

	public MapServiceImpl(RoadMap map) {
		this.map = requireNonNull(map);
	}

	@Override
	public boolean addCity(City city) {
		city = requireNonNull(city);
		return map.getCities().add(city);
	}

	@Override
	public boolean addRoad(Road road) {
		road = requireNonNull(road);
		if (map.getRoads().add(road)) {
			addRoadToCitiesRoads(road);
			return true;
		}
		return false;
	}

	@Override
	public boolean removeCity(City city) {
		city = requireNonNull(city);
		if (map.getCities().remove(city)) {
			map.getRoads().removeAll(city.getRoads());
			return true;
		}
		return false;
	}

	@Override
	public boolean removeRoad(Road road) {
		road = requireNonNull(road);
		if (map.getRoads().remove(road)) {
			removeRoadFromCitiesRoads(road);
			return true;
		} 
		return false;
	}

	@Override
	public Set<Road> getRoadsFromCity(City city) {
		city = requireNonNull(city);
		return city.getRoads();
	}
	
	private void addRoadToCitiesRoads(Road road) {
		for(City city : map.getCities()) {
			city.addRoad(road);
		}
	}
	
	private void removeRoadFromCitiesRoads(Road road) {
		for(City city : map.getCities()) {
			city.getRoads().remove(road);
		}
	}

}
