package com.noirix;

import com.noirix.domain.Car;
import com.noirix.repository.CarRepository;
import com.noirix.service.CarService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.Timestamp;

public class SpringTest {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext
                ("com.noirix");
        CarRepository carRepository = applicationContext.getBean("carRepositoryImpl", CarRepository.class);
        CarService carService = applicationContext.getBean("carServiceImpl", CarService.class);

        System.out.println(carRepository.findAll());
//        System.out.println(carRepository.searchMostExpensiveCars());
//        Car car = new Car(0L, "DA11111-02", "Honda", 119001.0f, 5L, new Timestamp(System.currentTimeMillis()),
//        new Timestamp(System.currentTimeMillis()), Boolean.FALSE);
//        carService.addNewCarIfItsPriceHighest(car);
//        //carService.deleteCarWithMaxPrice();
//        System.out.println(carService.searchMostExpensiveCars());
        System.out.println(carRepository.findOne(111l));
        System.out.println(carService.findOne(111l));
        Car car = new Car(0L, "B-777", "BMW", 115000.00f, 4L,
                new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()), Boolean.FALSE);
        System.out.println(carRepository.create(car));
    }
}
