package com.example.service;

import com.example.Car;
import com.example.repository.CarRepository;

import java.util.List;
import java.util.Optional;

public class CarManagerService {
    private CarRepository carRepository;


    public Car save(Car car) {
        return carRepository.save(car);
    }

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Optional<Car> getCarDetails(Long id){

        return Optional.ofNullable(carRepository.findByCarId(id));
    }

}
