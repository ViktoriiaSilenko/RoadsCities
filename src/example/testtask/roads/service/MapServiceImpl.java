package example.testtask.roads.service;

import java.util.Objects;
import java.util.Set;
import example.testtask.roads.model.City;
import example.testtask.roads.model.MyMap;
import example.testtask.roads.model.Road;

public class MapServiceImpl implements MapService {
	
	private MyMap map;

	public MapServiceImpl(MyMap map) {
		this.map = Objects.requireNonNull(map);
	}

	@Override
	public boolean addCity(City city) {
		city = Objects.requireNonNull(city);
		return map.getCities().add(city);
	}

	@Override
	public boolean addRoad(Road road) {
		road = Objects.requireNonNull(road);
		if (map.getRoads().add(road)) {
			addRoadToCitiesRoads(road);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean removeCity(City city) {
		city = Objects.requireNonNull(city);
		if (map.getCities().remove(city)) {
			map.getRoads().removeAll(city.getRoads());
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean removeRoad(Road road) {
		road = Objects.requireNonNull(road);
		if (map.getRoads().remove(road)) {
			removeRoadFromCitiesRoads(road);
			return true;
			
		} else {
			return false;
		}
	}

	@Override
	public Set<Road> getRoadsFromCity(City city) {
		city = Objects.requireNonNull(city);
		return city.getRoads();
	}
	
	private void addRoadToCitiesRoads(Road road) {
		road = Objects.requireNonNull(road);
		for(City city : map.getCities()) {
			if(city.equals(road.getCityFrom()) || city.equals(road.getCityTo())) {
				city.getRoads().add(road);
			}
		}
	}
	
	private void removeRoadFromCitiesRoads(Road road) {
		road = Objects.requireNonNull(road);
		for(City city : map.getCities()) {
			city.getRoads().remove(road);
		}
	}

}
