package com.noirix;

import com.noirix.domain.Car;
import com.noirix.repository.CarRepository;
import com.noirix.service.CarService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.Timestamp;

public class SpringTest {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext("com.noirix");
        CarRepository carRepository = applicationContext.getBean("carRepositoryImpl", CarRepository.class);
        CarService carService = applicationContext.getBean("carServiceImpl", CarService.class);

        System.out.println(carRepository.findAll());
        System.out.println(carRepository.searchMostExpensiveCars());
        Car car = new Car(new Long(0L), "DA11111-02", "Honda", new Float(119001.0f), new Long(5L), new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()), new Boolean(false));
        carService.addNewCarIfItsPriceHighest(car);
        //carService.deleteCarWithMaxPrice();
        System.out.println(carService.searchMostExpensiveCars());
    }
}
