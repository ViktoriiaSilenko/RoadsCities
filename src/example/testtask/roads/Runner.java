package example.testtask.roads;

import java.util.HashSet;
import java.util.Set;

import example.testtask.roads.model.City;
import example.testtask.roads.model.Point;
import example.testtask.roads.model.Road;

/*
 * Задача о городах и дорогах. 
 * Необходимо реализовать интерфейс для получения следующих данных: Добавить новый город (город определяется по имени и координатам), 
 * может быть два города с одним именем, но разными координатами. 
 * Нужно уметь добавлять дороги. Дорога имеет имя. Дорога может соединять только два разных города, дорога не может проходить через город и соединять третий город. 
 * При таком примере, будет две разных дороги. Дорога всегда при создании имеет два города, между которыми она проведена. 
 * Не может быть дорога без городов. Надо уметь удалять дороги и города. Надо уметь получить по городу дороги, которые из этого города ведут.
 * 
 */

public class Runner {

	public static void main(String[] args) {
		
		/*Road road = new Road("road1", new City("Kharkiv", new Point (10, 20)), new City("Kyiv", new Point (10, 20)));
		Road roadOther = new Road("road1", new City("Lviv", new Point (11, 21)), new City("Ternopil", new Point (101, 201)));
		Road roadOther2 = new Road("road2", new City("Lviv1", new Point (210, 220)), new City("Ternopil1", new Point (210, 220)));
		Road roadOther3 = new Road("road1", new City("Kyiv", new Point (10, 20)), new City("Kharkiv", new Point (10, 20)));
		Set <Road> roads = new HashSet<>();
		roads.add(road);
		roads.add(roadOther);
		roads.add(roadOther2);
		roads.add(roadOther3);
		System.out.println(roads);
		
		City city1 = new City("Lviv", new Point (11, 21));
		City city2 = new City("Lviv", new Point (101, 201));
		City city3 = new City("Lviv22", new Point (11, 21));
		City city4 = new City("Lviv33", new Point (119, 21));
		Set <City> cities = new HashSet<>();
		cities.add(city1);
		cities.add(city2);
		cities.add(city3);
		cities.add(city4);
		System.out.println(cities);
		
		
		// experiment
		Set <City> cities2 = new HashSet<>();
		cities2.add(city4);
		
		cities2.add(city2);
		cities2.add(city1);
		
		
		System.out.println(cities.equals(cities2)); */

	}

}
