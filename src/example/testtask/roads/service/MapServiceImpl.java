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
	
	private Set<City> cities; //все города
	private SetMultimap<City, Road> roadsOfAllCity; //мультимапа городов на множество дорог, через которые они проходят
	
	public MapServiceImpl() {
		cities = new HashSet<>();
		roadsOfAllCity = HashMultimap.create();
	}

	/**
	 * Получить список всех городов в системе
	 */
	public Set<City> getCities() {
		return Collections.unmodifiableSet(cities);
	}

	@Override
	public boolean addCity(City city) {
		city = requireNonNull(city);
		return cities.add(city);
	}

	@Override
	public boolean addRoad(Road road) {
		road = requireNonNull(road);
		Validator.checkRoadConnectsExistingCities(road, cities);
		boolean roadAddedToCityFrom = roadsOfAllCity.put(road.getCityFrom(), road);
		boolean roadAddedToCityTo = roadsOfAllCity.put(road.getCityTo(), road);
		
		return roadAddedToCityFrom || roadAddedToCityTo;
	}

	@Override
	public boolean removeCity(City city) {
		city = requireNonNull(city);
		if (cities.remove(city)) {
			Set<Road> roadsFromCity = getRoadsFromCity(city);
			Road [] roadsFromCityList = roadsFromCity.toArray(new Road[roadsFromCity.size()]);
			for (Road roadFromCity: roadsFromCityList) {
			    removeRoad(roadFromCity);
			}
			return true;
		}
		return false;
	}


	@Override
	public boolean removeRoad(Road road) {
		road = requireNonNull(road);
		boolean roadRemovedFromCityFrom = roadsOfAllCity.get(road.getCityFrom()).remove(road);
		boolean roadRemovedFromCityTo = roadsOfAllCity.get(road.getCityTo()).remove(road);
		
		return roadRemovedFromCityFrom || roadRemovedFromCityTo;
	}

	@Override
	public Set<Road> getRoadsFromCity(City city) {
		return Collections.unmodifiableSet(roadsOfAllCity.get(city));
	}

}
