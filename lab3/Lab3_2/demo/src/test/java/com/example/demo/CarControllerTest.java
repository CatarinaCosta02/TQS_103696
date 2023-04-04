package com.example.demo;

import com.example.Car;
import com.example.controller.CarController;
import com.example.service.CarManagerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.hamcrest.CoreMatchers.is;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
@WebMvcTest(CarController.class)
public class CarControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CarManagerService carManagerService;

    @BeforeEach
    public void setUp() {

    }

    @Test
    void testCreateCar() throws Exception {
        Car car = new Car("GLA SUV", "Mercedes-Benz");

        when( carManagerService.save(Mockito.any()) ).thenReturn(car);

        mvc.perform(
                post("/api/v1/cars/cars").contentType(MediaType.APPLICATION_JSON).content(JsonUtils.toJson(car)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.model", is("GLA SUV")))
                .andExpect(jsonPath("$.maker", is("Mercedes-Benz")));

                Mockito.verify(carManagerService, times(1)).save(Mockito.any());
    }

    @Test
    public void getAllCarsTest() throws Exception {
        Car car1 = new Car("GLA SUV", "Mercedes-Benz");
        Car car2 = new Car("500", "Fiat");
        Car car3 = new Car("Civic", "Honda");
        List<Car> cars = Arrays.asList(car1, car2, car3);

        when( carManagerService.getAllCars() ).thenReturn(cars);

        mvc.perform(
                get("/api/v1/cars/cars").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].model", is(car1.getModel())))
                .andExpect(jsonPath("$[1].model", is(car2.getModel())))
                .andExpect(jsonPath("$[2].model", is(car3.getModel())));

        Mockito.verify(carManagerService, times(1)).getAllCars();
    }
}

