package airquality.demo.ControllerTest;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.MediaType;

import airquality.demo.cache.Cache;
import airquality.demo.controllers.AirQualityRestController;
import airquality.demo.controllers.CacheController;
import airquality.demo.models.City;
import airquality.demo.service.AirQualityService;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status; 
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@WebMvcTest(AirQualityRestController.class)
public class AirQualityControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AirQualityService airQualityService;

    @BeforeEach
    public void setUp() {   
    }

    final String key = "&key=a06833c3d40f4f8d93d4b2c47c39fa8f";
    final String url = "https://api.weatherbit.io/v2.0/current/airquality?city=";
    final String url2 = "https://api.weatherbit.io/v2.0/forecast/airquality?city=";
    final String city = "London";
    final String data = "{\"city_name\":\"London\",\"lat\":51.51279,\"lon\":-0.09184,\"data\":[{\"aqi\":19,\"o3\":40.41195,\"so2\":0.14156103,\"no2\":0.0224245,\"co\":213.62305,\"pm10\":4.8164916,\"pm25\":1.0876131,\"pollen_level_tree\":1,\"pollen_level_grass\":1,\"pollen_level_weed\":1,\"mold_level\":1,\"predominant_pollen_type\":\"Molds\"}]}";
    final String data2 = "{\"city_name\":\"London\",\"lat\":51.51279,\"lon\":-0.09184,\"data\":[{\"aqi\":60,\"o3\":46.2,\"so2\":0.1,\"no2\":14.2,\"co\":213.6,\"pm10\":15.5,\"pm25\":10.7,\"pollen_level_tree\":0,\"pollen_level_grass\":0,\"pollen_level_weed\":0,\"mold_level\":0,\"predominant_pollen_typ\":null}]}";

    @Test
    public void getAirQualityCurrentTest() throws Exception{
        City cityObject = new ObjectMapper().readValue(data, City.class);
        Mockito.when(airQualityService.getAirQuality(city)).thenReturn(new ResponseEntity<>(cityObject, HttpStatus.OK));
        mvc.perform(get("/api/current/" + city).contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.city_name", is(cityObject.getCityName())))
            .andExpect(jsonPath("$.lat", is(cityObject.getLat())))
            .andExpect(jsonPath("$.lon", is(cityObject.getLon())))
            .andExpect(jsonPath("$.data", hasSize(1)))
            .andExpect(jsonPath("$.data[0].aqi", notNullValue()))
            .andExpect(jsonPath("$.data[0].o3", notNullValue()))
            .andExpect(jsonPath("$.data[0].so2", notNullValue()))
            .andExpect(jsonPath("$.data[0].no2", notNullValue()))
            .andExpect(jsonPath("$.data[0].co", notNullValue()))
            .andExpect(jsonPath("$.data[0].pm10", notNullValue()))
            .andExpect(jsonPath("$.data[0].pm25", notNullValue()))
            .andExpect(jsonPath("$.data[0].pollen_level_tree", notNullValue()))
            .andExpect(jsonPath("$.data[0].pollen_level_grass", notNullValue()))
            .andExpect(jsonPath("$.data[0].pollen_level_weed", notNullValue()))
            .andExpect(jsonPath("$.data[0].mold_level", notNullValue()))
            .andExpect(jsonPath("$.data[0].predominant_pollen_type", notNullValue()));

    }

    @Test
    public void getAirQualityForecastTest() throws Exception{
        City cityObject = new ObjectMapper().readValue(data2, City.class);
        Mockito.when(airQualityService.getAirQualityForecast(city)).thenReturn(new ResponseEntity<>(cityObject, HttpStatus.OK));
        mvc.perform(get("/api/forecast/" + city).contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.city_name", is(cityObject.getCityName())))
            .andExpect(jsonPath("$.lat", is(cityObject.getLat())))
            .andExpect(jsonPath("$.lon", is(cityObject.getLon())))
            .andExpect(jsonPath("$.data", hasSize(1)))
            .andExpect(jsonPath("$.data[0].aqi", notNullValue()))
            .andExpect(jsonPath("$.data[0].o3", notNullValue()))
            .andExpect(jsonPath("$.data[0].so2", notNullValue()))
            .andExpect(jsonPath("$.data[0].no2", notNullValue()))
            .andExpect(jsonPath("$.data[0].co", notNullValue()))
            .andExpect(jsonPath("$.data[0].pm10", notNullValue()))
            .andExpect(jsonPath("$.data[0].pm25", notNullValue()));

    }
}
