package example.testtask.roads.service;

import static org.junit.Assert.assertEquals;
import java.util.HashSet;
import java.util.Set;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import example.testtask.roads.model.City;
import example.testtask.roads.model.Point;
import example.testtask.roads.model.Road;

public class MapServiceImplTest {
	
	private MapServiceImpl mapService;

	@Before
	public void setUp() {
		mapService = new MapServiceImpl();
	}

	@After
	public void tearDown() {
		mapService = null;
	}
	
	@Test(expected = NullPointerException.class)
	public void testAddCityForNullCity() {
		mapService.addCity(null);
	} 
	
	@Test(expected = IllegalArgumentException.class)
	public void testAddCityWithBlankCityName() {
		mapService.addCity(new City(" ", new Point(14, 17)));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testAddCityForCityWithNegativeXCoordinate() {
		mapService.addCity(new City("Kharkiv", new Point(-1, 17)));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testAddCityForCityWithNegativeYCoordinate() {
		mapService.addCity(new City("Kharkiv", new Point(10, -17)));
	}
	
	@Test(expected = NullPointerException.class)
	public void testAddCityForCityWithNullCoordinates() {
		mapService.addCity(new City("Minsk", null));
	}

	@Test
	public void testAddCityWithSameCoordinates() {
		mapService.addCity(new City("Kirovsk", new Point (11, 21)));
		
		boolean actual = mapService.addCity(new City("Ternopil", new Point (11, 21)));
		assertEquals(false, actual);
	}
	
	@Test
	public void testAddCityWithSameNamesAndDifferentCoordinates() {
		mapService.addCity(new City("Kirovsk", new Point (11, 21)));

		boolean actual = mapService.addCity(new City("Kirovsk", new Point (11, 219)));
		assertEquals(true, actual);
	}
	
	//********************
	@Test(expected = NullPointerException.class)
	public void testAddRoadForNullCity() {
		mapService.addRoad(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testAddRoadWithSameCities() {
		mapService.addRoad(new Road("road", 
				new City("Moscow", new Point(100, 200)), new City("Moscow1", new Point(100, 200))));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testAddRoadWithBlankName() {
		mapService.addRoad(new Road(" ", 
				new City("Moscow", new Point(100, 200)), new City("Kyiv", new Point(102, 202))));
	}
	
	@Test(expected = NullPointerException.class)
	public void testAddRoadWithNullCityFrom() {
		mapService.addRoad(new Road("road1", null, new City("Kyiv", new Point(102, 202))));
	}
	
	@Test(expected = NullPointerException.class)
	public void testAddRoadWithNullCityTo() {
		mapService.addRoad(new Road("road1", new City("Kyiv", new Point(102, 202)), null));
	}

	@Test
	public void testAddRoadAndInvertRoad() {
		City cityTernopil = new City("Ternopil", new Point (118, 218));
		City cityKirovsk = new City("Kirovsk", new Point (19, 29));

		mapService.addRoad(new Road("road1", cityTernopil, cityKirovsk));
		Set<City> cities = mapService.getCities();
		assertEquals(true, cities.contains(cityKirovsk));
		assertEquals(true, cities.contains(cityTernopil));
		
		boolean actual = mapService.addRoad(new Road("road1Invert", cityKirovsk, cityTernopil));
		assertEquals(false, actual);
	}

	@Test(expected = NullPointerException.class)
	public void testRemoveCityForNullCity() {
		mapService.removeCity(null);
	}
	
	@Test
	public void testRemoveNotExistCity() {
		Road road = new Road("road", new City("Ternopil", 
				new Point (118, 218)), new City("Kirovsk", new Point (19, 29)));
		mapService.addRoad(road); // also added cities
		boolean actual = mapService.removeCity(new City("Moscow", new Point (119, 129)));
		assertEquals(false, actual);
		assertEquals(true, mapService.getRoads().contains(road));
	}
	
	@Test
	public void testRemoveCity() {
		City cityTernopil = new City("Ternopil", new Point (118, 218));
		City cityKirovsk = new City("Kirovsk", new Point (19, 29));
		City cityMoscow = new City("Moscow", new Point (119, 129));
		Road road1 = new Road("road1", cityTernopil, cityKirovsk);
		Road road2 = new Road("road2", cityTernopil, cityMoscow);
		Road road3 = new Road("road2", cityKirovsk, cityMoscow);
		mapService.addRoad(road1); // also added cities
		mapService.addRoad(road2); // also added cities
		mapService.addRoad(road3); // also added cities
		
		boolean actual = mapService.removeCity(cityTernopil);
		assertEquals(true, actual);
		
		Set<Road> roads = mapService.getRoads();
		assertEquals(false, roads.contains(road1));
		assertEquals(false, roads.contains(road2));
		assertEquals(true, roads.contains(road3));
	}
	
	@Test
	public void testRemoveCityWithSameCoordinates() {
		Road road1 = new Road("road1", new City("Ternopil", 
				new Point (118, 218)), new City("Kirovsk", new Point (19, 29)));
		mapService.addRoad(road1); // also added cities

		boolean actual = mapService.removeCity(new City("Ternopil2", new Point (118, 218)));
		assertEquals(true, actual);
		
		Set<Road> roads = mapService.getRoads();
		assertEquals(false, roads.contains(road1));
	}

	@Test(expected = NullPointerException.class)
	public void testRemoveRoadForNullRoad() {
		mapService.removeRoad(null);
	}
	
	@Test
	public void testRemoveNotExistRoad() {
		City cityTernopil = new City("Ternopil", new Point (118, 218));
		City cityKirovsk = new City("Kirovsk", new Point (19, 29));
		City cityMoscow = new City("Moscow", new Point (119, 129));
		Road road1 = new Road("road1", cityTernopil, cityKirovsk);
		mapService.addRoad(road1); // also added cities
		boolean actual = mapService.removeRoad(new Road("road2", cityTernopil, cityMoscow));
		assertEquals(false, actual);
		Set<City> cities = mapService.getCities();
		assertEquals(true, cities.contains(cityTernopil));
		assertEquals(true, cities.contains(cityKirovsk));
		assertEquals(false, cities.contains(cityMoscow));
	}
	
	@Test
	public void testRemoveRoad() {
		City cityTernopil = new City("Ternopil", new Point (118, 218));
		City cityKirovsk = new City("Kirovsk", new Point (19, 29));
		Road road = new Road("road1", cityTernopil, cityKirovsk);
		mapService.addRoad(road); // also added cities
		boolean actual = mapService.removeRoad(road);
		assertEquals(true, actual);
		
		Set<City> cities = mapService.getCities();
		assertEquals(true, cities.contains(cityTernopil));
		assertEquals(true, cities.contains(cityKirovsk));
	}
	
	@Test
	public void testRemoveInvertRoad() {
		City cityTernopil = new City("Ternopil", new Point (118, 218));
		City cityKirovsk = new City("Kirovsk", new Point (19, 29));
		Road road = new Road("road", cityTernopil, cityKirovsk);
		mapService.addRoad(road); // also added cities
		boolean actual = mapService.removeRoad(new Road("invert", cityKirovsk, cityTernopil));
		assertEquals(true, actual);
		
		Set<City> cities = mapService.getCities();
		assertEquals(true, cities.contains(cityTernopil));
		assertEquals(true, cities.contains(cityKirovsk));
	}

	@Test(expected = NullPointerException.class)
	public void testGetRoadsFromCityForNullCity() {
		mapService.getRoadsFromCity(null);
	}
	
	@Test
	public void testGetRoadsFromCity() {
		City cityTernopil = new City("Ternopil", new Point (118, 218));
		City cityKirovsk = new City("Kirovsk", new Point (19, 29));
		City cityMoscow = new City("Moscow", new Point (119, 129));
		City cityLviv = new City("Lviv", new Point (119, 229));
		Road road1 = new Road("road1", cityTernopil, cityKirovsk);
		Road road2 = new Road("road2", cityMoscow, cityTernopil);
		Road road3 = new Road("road3", cityKirovsk, cityMoscow);
		Road road4 = new Road("road4", cityLviv, cityMoscow);
		mapService.addRoad(road1); // also added cities
		mapService.addRoad(road2); // also added cities
		mapService.addRoad(road3); // also added cities
		mapService.addRoad(road4); // also added cities
		
		Set<Road> actual = mapService.getRoadsFromCity(new City("Ternopil3", new Point (5, 5)));
		assertEquals(new HashSet<Road>(), actual);
		
		actual = mapService.getRoadsFromCity(cityTernopil);
		Set<Road> expected = new HashSet<Road>();
		expected.add(road1);
		expected.add(road2);
		assertEquals(expected, actual);
		
		actual = mapService.getRoadsFromCity(cityMoscow);
		expected = new HashSet<Road>();
		expected.add(road2);
		expected.add(road3);
		expected.add(road4);
		assertEquals(expected, actual);
	}

}
