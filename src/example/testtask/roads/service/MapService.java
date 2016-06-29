package example.testtask.roads.service;

import java.util.Set;

import example.testtask.roads.model.City;
import example.testtask.roads.model.Road;

public interface MapService {
	boolean addCity(City city); 
	boolean addRoad(Road road);
	boolean removeCity(City city);
	boolean removeRoad(Road road);
	Set<Road> getRoadsFromCity(City city);
}
