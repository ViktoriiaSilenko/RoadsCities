package example.testtask.roads.service;

import static java.util.Objects.requireNonNull;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import example.testtask.roads.model.City;
import example.testtask.roads.model.Road;

/**
 * @author Viktoria Silenko
 * 
 * Задача о городах и дорогах. 
 * Необходимо реализовать интерфейс для получения следующих данных: Добавить новый город (город определяется по имени и координатам), 
 * может быть два города с одним именем, но разными координатами. 
 * - Нужно уметь добавлять дороги. Дорога имеет имя. 
 * Дорога может соединять только два разных города, дорога не может проходить через город и соединять третий город. 
 * При таком примере, будет две разных дороги. Дорога всегда при создании имеет два города, между которыми она проведена. Не может быть дорога без городов. 
 * Надо уметь удалять дороги и города. Надо уметь получить по городу дороги, которые из этого города ведут.
 */

public class MapServiceImpl implements MapService {
	
	private Set<City> cities;
	private Set<Road> roads;
	private Map<City, Set<Road>> roadsOfCity;
	
	public MapServiceImpl() {
		cities = new HashSet<>();
		roads = new HashSet<>();
		roadsOfCity = new HashMap<>();
	}

	public Set<City> getCities() {
		return Collections.unmodifiableSet(cities);
	}

	public Set<Road> getRoads() {
		return Collections.unmodifiableSet(roads);
	}

	@Override
	public boolean addCity(City city) {
		city = requireNonNull(city);
		return cities.add(city);
	}

	@Override
	public boolean addRoad(Road road) {
		road = requireNonNull(road);
		if (roads.add(road)) { 
			// Пусть мы провели дорогу между городами, которые еще не добавлены
			City cityFrom = road.getCityFrom();
			City cityTo = road.getCityTo();
			addCity(cityFrom);
			addCity(cityTo);
			
			addRoadToRoadsOfCity(cityFrom, road);
			addRoadToRoadsOfCity(cityTo, road);
			return true;
		}
		return false;
	}
	
	private void addRoadToRoadsOfCity(City city, Road road) {
		Set<Road> roads = roadsOfCity.get(city);
		if (roads != null) {
			roads.add(road);
		} else {
			Set<Road> roadsCity = new HashSet<>();
			roadsCity.add(road);
			roadsOfCity.put(city, roadsCity);
		}
	}

	@Override
	public boolean removeCity(City city) {
		city = requireNonNull(city);
		if (cities.remove(city)) {
			roads.removeAll(getRoadsFromCity(city));
			return true;
		}
		return false;
	}

	@Override
	public boolean removeRoad(Road road) {
		road = requireNonNull(road);
		return roads.remove(road);
	}

	@Override
	public Set<Road> getRoadsFromCity(City city) {
		city = requireNonNull(city);

		Set<Road> roads = roadsOfCity.get(city);
		if (roads != null) {
			return roads;
		}
		return new HashSet<Road>();
	}

}
