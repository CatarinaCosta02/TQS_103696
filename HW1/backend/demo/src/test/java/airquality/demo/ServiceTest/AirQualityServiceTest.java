package airquality.demo.ServiceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import airquality.demo.models.City;
import airquality.demo.service.AirQualityService;

@ExtendWith(MockitoExtension.class)
public class AirQualityServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private AirQualityService airQualityService;

    final String key = "&key=a06833c3d40f4f8d93d4b2c47c39fa8f";
    final String url = "https://api.weatherbit.io/v2.0/current/airquality?city=";
    final String url2 = "https://api.weatherbit.io/v2.0/forecast/airquality?city=";
    final String city = "London";
    final String data = "{\"city_name\":\"London\",\"lat\":51.51279,\"lon\":-0.09184,\"data\":[{\"aqi\":19,\"o3\":40.41195,\"so2\":0.14156103,\"no2\":0.0224245,\"co\":213.62305,\"pm10\":4.8164916,\"pm25\":1.0876131,\"pollen_level_tree\":1,\"pollen_level_grass\":1,\"pollen_level_weed\":1,\"mold_level\":1,\"predominant_pollen_type\":\"Molds\"}]}";
    final String data2 = "{\"city_name\":\"London\",\"lat\":51.51279,\"lon\":-0.09184,\"data\":[{\"aqi\":60,\"o3\":46.2,\"so2\":0.1,\"no2\":14.2,\"co\":213.6,\"pm10\":15.5,\"pm25\":10.7,\"pollen_level_tree\":0,\"pollen_level_grass\":0,\"pollen_level_weed\":0,\"mold_level\":0,\"predominant_pollen_typ\":null}]}";
    @Test
    @DisplayName("When make a request, then return the response")
    public void getAirQualityTest() {
        Mockito.when(restTemplate.getForEntity(url + city + key, String.class)).thenReturn(ResponseEntity.ok(data));
        ResponseEntity<City> response = airQualityService.getAirQuality(city);
        assertEquals(response.getBody(), airQualityService.processResponse(ResponseEntity.ok(data)).getBody());
        Mockito.verify(restTemplate).getForEntity(url + city + key, String.class);
    }

    @Test
    @DisplayName("When make a forecast request, then return the response")
    public void getAirQualityForecastTest() {
        Mockito.when(restTemplate.getForEntity(url2 + city + key, String.class)).thenReturn(ResponseEntity.ok(data2));
        ResponseEntity<City> response = airQualityService.getAirQualityForecast(city);
        assertEquals(response.getBody(), airQualityService.processResponse(ResponseEntity.ok(data2)).getBody());
        Mockito.verify(restTemplate).getForEntity(url2 + city + key, String.class);
    }

}