package example.testtask.roads.service;

import static java.util.Objects.requireNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.SetMultimap;
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
	SetMultimap<City, Road> roadsOfCity;
	
	public MapServiceImpl() {
		cities = new HashSet<>();
		roads = new HashSet<>();
		roadsOfCity = HashMultimap.create();
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
			
			roadsOfCity.put(cityFrom, road);
			roadsOfCity.put(cityTo, road);
			return true;
		}
		return false;
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
		return roadsOfCity.get(city);
	}

}
