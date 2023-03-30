package com.example.demo;

import com.example.Car;
import com.example.repository.CarRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase

public class API_Test {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CarRepository carRepository;

    @AfterEach
    public void resetDb() {
        carRepository.deleteAll();
    }

    @Test
    public void whenValidInput_thenCreateCar() {
        Car car = new Car("GLA SUV", "Mercedes-Benz");
        ResponseEntity<Car> entity = restTemplate.postForEntity("/api/cars", car, Car.class);

        List<Car> found = carRepository.findAll();
        //assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(found).extracting(Car::getModel).containsOnly("GLA SUV");
    }

    private void createTestCar(String model, String brand) {
        Car car = new Car(model, brand);
        restTemplate.postForEntity("/api/cars", car, Car.class);

    }

}
