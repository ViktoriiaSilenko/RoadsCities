package example.testtask.roads.service;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import example.testtask.roads.model.City;
import example.testtask.roads.model.Point;
import example.testtask.roads.model.Road;
import example.testtask.roads.model.RoadMap;

public class MapServiceImplTest {

	@Test
	public void testAddCity() {
		City cityTernopil = new City("Ternopil", new Point (11, 21));
		City cityAnotherKirovsk = new City("Kirovsk", new Point (11, 210));
		
		Set<City> cities = new HashSet<> ();
		cities.add(new City("Kirovsk", new Point (11, 21)));
		
		boolean actual = new MapServiceImpl(new RoadMap(cities, new HashSet<Road>())).addCity(cityTernopil);
		assertEquals(false, actual);
		
		actual = new MapServiceImpl(new RoadMap(cities, new HashSet<Road>())).addCity(cityAnotherKirovsk);
		assertEquals(true, actual);
	}
	
	@Test(expected = NullPointerException.class)
	public void testAddCityForNullCity() {
		new MapServiceImpl(new RoadMap(new HashSet<City>(), new HashSet<Road>())).addCity(null);
	} 
	
	@Test(expected = IllegalArgumentException.class)
	public void testAddCityForBlankCity() {
		new MapServiceImpl(new RoadMap(new HashSet<City>(), new HashSet<Road>())).addCity(new City(" ", new Point(14, 17)));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testAddCityForCityWithNegativeCoordinates() {
		new MapServiceImpl(new RoadMap(new HashSet<City>(), new HashSet<Road>())).addCity(new City("Kharkiv", new Point(-1, 17)));
	}
	
	@Test(expected = NullPointerException.class)
	public void testAddCityForCityWithNullCoordinates() {
		new MapServiceImpl(new RoadMap(new HashSet<City>(), new HashSet<Road>())).addCity(new City("Minsk", null));
	}

	@Test
	public void testAddRoad() {
		City cityTernopil = new City("Ternopil", new Point (118, 218));
		City cityKirovsk = new City("Kirovsk", new Point (19, 29));
		City cityKyiv = new City("Kyiv", new Point (110, 210));
		Road road = new Road("road1", cityTernopil, cityKirovsk);
		Road invertRoad = new Road("road1Invert", cityKirovsk, cityTernopil);
		Road road2 = new Road("road1", cityTernopil, cityKyiv);
		
		Set<City> cities = new HashSet<> ();
		cities.add(cityTernopil);
		cities.add(cityKirovsk);
		
		Set<Road> roads = new HashSet<> ();
		roads.add(road);
		
		boolean actual = new MapServiceImpl(new RoadMap(cities, roads)).addRoad(invertRoad);
		assertEquals(false, actual);
		
		actual = new MapServiceImpl(new RoadMap(cities, roads)).addRoad(road2);
		assertEquals(true, actual);
	}

	/*@Test
	public void testRemoveCity() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveRoad() {
		fail("Not yet implemented");
	
	}

	@Test
	public void testGetRoadsFromCity() {
		fail("Not yet implemented");
	} */

}
