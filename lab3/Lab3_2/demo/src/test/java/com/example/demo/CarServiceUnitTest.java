package com.example.demo;

import com.example.Car;
import com.example.controller.CarController;
import com.example.repository.CarRepository;
import com.example.service.CarManagerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class CarServiceUnitTest {

    @Mock( lenient = true )
    private CarRepository carRepository;

    @InjectMocks
    private CarManagerService carManagerService;

    @BeforeEach
    public void setUp() {
        Car car1 = new Car("GLA SUV", "Mercedes-Benz");
        Car car2 = new Car("500", "Fiat");
        Car car3 = new Car("Civic", "Honda");

        List<Car> cars = Arrays.asList(car1, car2, car3);

        Mockito.when( carRepository.findByCarId(0L) ).thenReturn(car1);
        Mockito.when( carRepository.findByCarId(1L) ).thenReturn(car2);
        Mockito.when( carRepository.findByCarId(-2L) ).thenReturn(null);
        Mockito.when( carRepository.findAll() ).thenReturn(cars);

    }

    @Test
    public void getAllCarsTest() {
        Car car1 = new Car("GLA SUV", "Mercedes-Benz");
        Car car2 = new Car("500", "Fiat");
        Car car3 = new Car("Civic", "Honda");
        List<Car> cars = carManagerService.getAllCars();
        assertThat(cars).hasSize(3).extracting(Car::getModel).contains(car1.getModel(), car2.getModel());
    }

    @Test
    public void getCarDetailsTest() {

        //existing id
        Optional<Car> car1 = carManagerService.getCarDetails(0L);
        assertThat(car1.isPresent()).isTrue();
        Mockito.verify(carRepository, times(1)).findByCarId(0L);

    }

    @Test
    public void getCarDetailsTest2() {

        //non-existing id
        Optional<Car> car1 = carManagerService.getCarDetails(-2L);
        assertThat(car1.isPresent()).isFalse();
        Mockito.verify(carRepository, times(1)).findByCarId(-2L);

    }


}
