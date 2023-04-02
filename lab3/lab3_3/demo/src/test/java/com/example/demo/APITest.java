package com.example.demo;

import com.example.Car;
import com.example.repository.CarRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "application-integrationtest.properties")
// @AutoConfigureTestDatabase
public class APITest {

    @LocalServerPort
    int port;

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
        restTemplate.postForEntity("/api/v1/cars/cars", car, Car.class);
        assertThat(carRepository.findAll()).extracting(Car::getModel).containsOnly("GLA SUV");
    }

    @Test
    public void givenCars_whenGetCars_thenStatus200() {
        createTestCar("GLA SUV", "Mercedes-Benz");
        createTestCar("C-Class", "Mercedes-Benz");

        ResponseEntity<List<Car>> response = restTemplate.exchange("/api/v1/cars/cars", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Car>>() {
        });

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).extracting(Car::getModel).containsExactly("GLA SUV", "C-Class");
    }

    private void createTestCar(String model, String maker) {
        Car car = new Car(model, maker);
        carRepository.saveAndFlush(car);

    }


}
