package com.jairrillo.HelloRxJava;

import java.util.Arrays;
import java.util.List;

import rx.Observable;
import rx.Observable.OnSubscribe;
import rx.Subscriber;

public class Main {

	public static void main(String[] args) {
		new Main();
	}
	
	public Main() {
		
		Observable<String> value = Observable.create(new OnSubscribe<String>() {

			@Override
			public void call(Subscriber<? super String> subscribe) {
				subscribe.onNext("Just a test");
				subscribe.onCompleted();				
			}						
			
		});
		
		value.subscribe(System.out::println);
		
		// -- List<String>
		System.out.println("\n ---- LIST<STRING> ----");
		List<String> a = Arrays.asList("A","B","C");
		List<String> b = Arrays.asList("A1","B1","C1", null);
		List<String> c = Arrays.asList("A2","B2","C2");
		Observable<List<String>> listBla = Observable.just(a, b, c);
		listBla.subscribe(v -> v.forEach(System.out::println));
		
		System.out.println("\n ---- FLAT MAP ----");
		listBla.subscribe(System.out::println);
		listBla.flatMap(v -> Observable.from(v))
							.filter(v -> v != null)
							.take(5)
							.doOnNext(v -> System.out.println("Saving: " + v))
							.subscribe(System.out::println);
		
		// -- onError and onComplete
		System.out.println("\n ---- onError and OnComplete ----");
		
		Observable<String> heroes = Observable.from(Arrays.asList("Batman","Chapolin Colorado", "Hulk", "Super Main"))
											  .map(hero -> hero.toUpperCase())
											  .doOnNext(hero -> potentialException()); //Remove this line to run properly
		heroes.subscribe(new Subscriber<String>() {

			@Override
			public void onCompleted() {
				System.out.println("Completed");
				
			}

			@Override
			public void onError(Throwable arg0) {
				System.out.println("Error: " + arg0.getMessage());
				
			}

			@Override
			public void onNext(String arg0) {
				System.out.println(arg0);
			}
			
		});
		
		
		
	}
	
	public void potentialException() {
		throw new RuntimeException("AAAA");
	}

}
