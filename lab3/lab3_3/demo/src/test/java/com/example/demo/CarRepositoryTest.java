package com.example.demo;

import com.example.Car;
import com.example.repository.CarRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;



@DataJpaTest
public class CarRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CarRepository carRepository;

    @Test
    public void whenFindById_thenReturnCar() {
        Car car = new Car("GLA SUV", "Mercedes-Benz");
        entityManager.persistAndFlush(car);
        Car found = carRepository.findByCarId(car.getCarId());
        assertThat(found.getModel()).isEqualTo(car.getModel());
    }

    @Test
    public void whenFindAll_thenReturnAllCars() {
        Car car1 = new Car("GLA SUV", "Mercedes-Benz");
        entityManager.persistAndFlush(car1);
        Car car2 = new Car("C-Class", "Mercedes-Benz");
        entityManager.persistAndFlush(car2);
        assertThat(carRepository.findAll()).hasSize(2);
    }

    @Test
    public void whenInvalidId_thenReturnNull() {
        Car fromDb = carRepository.findByCarId(-11L);
        assertThat(fromDb).isNull();
    }

    @Test
    public void givenSetOfCars_whenFindAll_thenReturnAllCars() {
        Car car1 = new Car("GLA SUV", "Mercedes-Benz");
        entityManager.persistAndFlush(car1);
        Car car2 = new Car("C-Class", "Mercedes-Benz");
        entityManager.persistAndFlush(car2);
        Car car3 = new Car("A-Class", "Mercedes-Benz");
        entityManager.persistAndFlush(car3);

        List<Car> allCars = carRepository.findAll();
        assertThat(carRepository.findAll()).hasSize(3).extracting(Car::getModel).containsOnly(car1.getModel(), car2.getModel(), car3.getModel());
    }


}
