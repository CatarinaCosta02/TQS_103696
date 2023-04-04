package com.example.controller;

import com.example.Car;
import com.example.service.CarManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/cars")
public class CarController {

    @Autowired
    private CarManagerService carManagerService;

    @PostMapping("/cars")
    public ResponseEntity<Car> createCar(@RequestBody Car car) {
        return new ResponseEntity<Car>( carManagerService.save(car), HttpStatus.CREATED );
    }

    @GetMapping("/cars")
    public List<Car> getAllCars() {
        return carManagerService.getAllCars();
    }

    @GetMapping("/cars/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable(value = "id") Long id) {
        HttpStatus status = HttpStatus.CREATED;
        Car car = carManagerService.getCarDetails(id).orElseThrow(() -> new RuntimeException("Car not found"));
        return new ResponseEntity<Car>(car, status);
    }


}
