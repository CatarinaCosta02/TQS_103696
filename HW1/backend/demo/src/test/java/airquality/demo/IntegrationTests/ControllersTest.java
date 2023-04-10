package airquality.demo.IntegrationTests;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.beans.Transient;

import airquality.demo.controllers.AirQualityRestController;
import airquality.demo.models.City;
import airquality.demo.service.AirQualityService;
import static org.mockito.Mockito.when;

@WebMvcTest(AirQualityRestController.class)
public class ControllersTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AirQualityService airQualityService;

    final String key = "&key=a06833c3d40f4f8d93d4b2c47c39fa8f";
    final String url = "https://api.weatherbit.io/v2.0/current/airquality?city=";
    final String url2 = "https://api.weatherbit.io/v2.0/forecast/airquality?city=";
    final String city = "London";
    final String data = "{\"city_name\":\"London\",\"lat\":51.51279,\"lon\":-0.09184,\"data\":[{\"aqi\":19,\"o3\":40.41195,\"so2\":0.14156103,\"no2\":0.0224245,\"co\":213.62305,\"pm10\":4.8164916,\"pm25\":1.0876131,\"pollen_level_tree\":1,\"pollen_level_grass\":1,\"pollen_level_weed\":1,\"mold_level\":1,\"predominant_pollen_type\":\"Molds\"}]}";
    final String data2 = "{\"city_name\":\"London\",\"lat\":51.51279,\"lon\":-0.09184,\"data\":[{\"aqi\":60,\"o3\":46.2,\"so2\":0.1,\"no2\":14.2,\"co\":213.6,\"pm10\":15.5,\"pm25\":10.7,\"pollen_level_tree\":0,\"pollen_level_grass\":0,\"pollen_level_weed\":0,\"mold_level\":0,\"predominant_pollen_typ\":null}]}";

    /* 
    @Test
    public void currentTestController() throws Exception {
        when(airQualityService.getCurrentAirQuality(url, city, key)).thenReturn(data);
        mvc.perform(get("/current?city=" + city).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].city_name", is("London")));
        Mockito.verify(airQualityService, Mockito.times(1)).getCurrentAirQuality(url, city, key);
    }

    @Test
    public void forecastTestController() throws Exception {
        when(airQualityService.getForecastAirQuality(url2, city, key)).thenReturn(data2);
        mvc.perform(get("/forecast?city=" + city).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].city_name", is("London")));
        Mockito.verify(airQualityService, Mockito.times(1)).getForecastAirQuality(url2, city, key);
    }
    */
}               
