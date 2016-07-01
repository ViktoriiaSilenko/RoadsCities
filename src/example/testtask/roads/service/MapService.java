package example.testtask.roads.service;

import java.util.Set;

import example.testtask.roads.model.City;
import example.testtask.roads.model.Road;

public interface MapService {
	boolean addCity(City city); 
	boolean addRoad(Road road);
	boolean removeCity(City city);
	boolean removeRoad(Road road);
	/**
	 * получить по городу дороги, которые из этого города ведут
	 */
	Set<Road> getRoadsFromCity(City city);
}
