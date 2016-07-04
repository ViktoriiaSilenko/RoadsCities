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
	public void testAddRoadWithNotExistingCityFrom() {
		City cityKirovsk = new City("Kirovsk", new Point (19, 29));
		mapService.addCity(cityKirovsk);
		
		mapService.addRoad(new Road("road", new City("Tomsk", new Point(100, 300)), cityKirovsk));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testAddRoadWithNotExistingCityTo() {
		City cityTernopil = new City("Ternopil", new Point (118, 218));
		mapService.addCity(cityTernopil);
		
		mapService.addRoad(new Road("road", cityTernopil, new City("Tomsk", new Point(100, 300))));
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
		mapService.addCity(cityTernopil);
		mapService.addCity(cityKirovsk);
		
		Road road = new Road("road", cityTernopil, cityKirovsk);
		mapService.addRoad(road);
		
		assertEquals(true, mapService.getRoadsFromCity(road.getCityFrom()).contains(road));
		assertEquals(true, mapService.getRoadsFromCity(road.getCityTo()).contains(road));
		
		boolean actual = mapService.addRoad(new Road("road1Invert", cityKirovsk, cityTernopil));
		assertEquals(false, actual);
	}

	//********************
	
	@Test(expected = NullPointerException.class)
	public void testRemoveCityForNullCity() {
		mapService.removeCity(null);
	}
	
	@Test
	public void testRemoveNotExistCity() {
		City cityTernopil = new City("Ternopil", new Point (118, 218));
		mapService.addCity(cityTernopil); 
		boolean actual = mapService.removeCity(new City("Ternopil", new Point (119, 129)));
		assertEquals(false, actual);
	}
	
	@Test
	public void testRemoveCityWithSameCoordinates() {
		mapService.addCity(new City("Ternopil", new Point (118, 218)));
		boolean actual = mapService.removeCity(new City("Ternopil2", new Point (118, 218)));
		assertEquals(true, actual);
	}
	
	@Test
	public void testRemoveCity() {
		City cityTernopil = new City("Ternopil", new Point (118, 218));
		City cityKirovsk = new City("Kirovsk", new Point (19, 29));
		City cityMoscow = new City("Moscow", new Point (119, 129));
		mapService.addCity(cityTernopil);
		mapService.addCity(cityKirovsk);
		mapService.addCity(cityMoscow);
		
		Road road1 = new Road("road1", cityTernopil, cityKirovsk);
		Road road2 = new Road("road2", cityTernopil, cityMoscow);
		Road road3 = new Road("road3", cityKirovsk, cityMoscow);
		mapService.addRoad(road1); 
		mapService.addRoad(road2); 
		mapService.addRoad(road3); 
		
		boolean actual = mapService.removeCity(cityTernopil);
		assertEquals(true, actual);
		
		actual = mapService.getCities().contains(cityTernopil);
		assertEquals(false, actual);
		
		assertEquals(new HashSet<Road>(), mapService.getRoadsFromCity(cityTernopil));
	
		actual = mapService.getRoadsFromCity(cityKirovsk).contains(road1);
		assertEquals(false, actual);
		actual = mapService.getRoadsFromCity(cityMoscow).contains(road2);
		assertEquals(false, actual);
	}
	
	//********************

	@Test(expected = NullPointerException.class)
	public void testRemoveRoadForNullRoad() {
		mapService.removeRoad(null);
	}
	
	@Test
	public void testRemoveNotExistRoad() {
		City cityTernopil = new City("Ternopil", new Point (118, 218));
		City cityKirovsk = new City("Kirovsk", new Point (19, 29));
		City cityMoscow = new City("Moscow", new Point (119, 129));
		mapService.addCity(cityTernopil);
		mapService.addCity(cityKirovsk);
		mapService.addCity(cityMoscow);
		
		mapService.addRoad(new Road("road1", cityTernopil, cityKirovsk));
		boolean actual = mapService.removeRoad(new Road("road2", cityTernopil, cityMoscow));
		assertEquals(false, actual);
	}
	
	@Test
	public void testRemoveInvertRoad() {
		City cityTernopil = new City("Ternopil", new Point (118, 218));
		City cityKirovsk = new City("Kirovsk", new Point (19, 29));
		mapService.addCity(cityTernopil);
		mapService.addCity(cityKirovsk);
		
		mapService.addRoad(new Road("road", cityTernopil, cityKirovsk));
		boolean actual = mapService.removeRoad(new Road("invert", cityKirovsk, cityTernopil));
		assertEquals(true, actual);
	}
	
	@Test
	public void testRemoveRoad() {
		City cityTernopil = new City("Ternopil", new Point (118, 218));
		City cityKirovsk = new City("Kirovsk", new Point (19, 29));
		mapService.addCity(cityTernopil);
		mapService.addCity(cityKirovsk);
		
		Road road = new Road("road", cityTernopil, cityKirovsk);
		mapService.addRoad(road);
		assertEquals(true, mapService.removeRoad(road));
		
		assertEquals(false, mapService.getRoadsFromCity(road.getCityFrom()).contains(road));
		assertEquals(false, mapService.getRoadsFromCity(road.getCityTo()).contains(road));
	}
	
	//********************

	public void testGetRoadsFromCityForNullCity() {
		assertEquals(new HashSet<Road>() , mapService.getRoadsFromCity(null));
	}
	
	@Test
	public void testGetRoadsFromCity() {
		City cityTernopil = new City("Ternopil", new Point (118, 218));
		City cityKirovsk = new City("Kirovsk", new Point (19, 29));
		City cityMoscow = new City("Moscow", new Point (119, 129));
		mapService.addCity(cityTernopil);
		mapService.addCity(cityKirovsk);
		mapService.addCity(cityMoscow);
		
		Road road1 = new Road("road1", cityTernopil, cityKirovsk);
		Road road2 = new Road("road2", cityMoscow, cityTernopil);
		Road road3 = new Road("road3", cityKirovsk, cityMoscow);
		mapService.addRoad(road1);
		mapService.addRoad(road2);
		mapService.addRoad(road3);
		
		Set<Road> actual = mapService.getRoadsFromCity(new City("Ternopil3", new Point (5, 5)));
		assertEquals(new HashSet<Road>(), actual);
		
		actual = mapService.getRoadsFromCity(cityTernopil);
		Set<Road> expected = new HashSet<Road>();
		expected.add(road1);
		expected.add(road2);
		assertEquals(expected, actual);
	}
}
