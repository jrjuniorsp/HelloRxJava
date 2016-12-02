package com.jairrillo.HelloRxJava;

import java.util.Arrays;

import rx.Observable;

/**
 * Hello world!
 *
 */
public class App {
	
	public static void main(String[] args) {
		new App();
	}
	
	public App() {
		//First example
		System.out.println("First Example");
		Observable<String> hello = Observable.just("Hello World");
		hello.subscribe(System.out::println);
		
		//Second example
		System.out.println("\nSecond Example");
		Observable<String> heroes = Observable.from(Arrays.asList("Batman", "Chapolin", "Super Man"));
		heroes.subscribe(System.out::println);
		
		//ZIp and Range
		System.out.println("\n -------- ZIP and RANGE ------");
		
		Observable<String> heroesList = Observable.from(Arrays.asList("Batman", "Chapolin", "Super Man"))
													.zipWith(Observable.range(1, Integer.MAX_VALUE), 
													 (string, count) -> count + "- " + string);
		heroesList.subscribe(System.out::println);
		
		// -- FlatMap
		System.out.println("\n -------- FLATMAP ------");
		
		Observable<String> heroesFlat = Observable.from(Arrays.asList("Batman", "Chapolin", "Super Man"))
													.flatMap(hero -> Observable.from(hero.split("")))
													.distinct()
													.sorted();
		heroesFlat.subscribe(System.out::println);
		
	}
}
