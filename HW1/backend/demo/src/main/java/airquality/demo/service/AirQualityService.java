package airquality.demo.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import airquality.demo.cache.Cache;
import airquality.demo.cache.ObjectCache;
import airquality.demo.models.City;
@Service
public class AirQualityService {
    private static final String API_KEY = "a06833c3d40f4f8d93d4b2c47c39fa8f";
    private static final String API_URL = "https://api.weatherbit.io/v2.0/current/airquality";
    private static final String API_FORECAST = "https://api.weatherbit.io/v2.0/forecast/airquality";
    
    RestTemplate restTemplate = new RestTemplate();

    public ResponseEntity<City> getAirQuality(String city) {
        City cacheRequest = Cache.getCachedRequest("current/"+city);
        if (cacheRequest == null) {
            ResponseEntity<String> response = apiChoice(API.current, city, null);
            ResponseEntity<City> cityObject = processResponse(response);
            Cache.add("current/" + city, cityObject.getBody());
            return cityObject;
        } else {
            return new ResponseEntity<>(cacheRequest, HttpStatus.OK);
        }
    }

    public ResponseEntity<City> getAirQualityForecast(String city) {
        City cacheRequest = Cache.getCachedRequest("forecast/"+city);
        if (cacheRequest == null) {
            ResponseEntity<String> response = apiChoice(API.forecast, city, null);
            ResponseEntity<City> cityObject = processResponse(response);
            Cache.add(" forecast/" + city, cityObject.getBody());
            return cityObject;
        } else {
            return new ResponseEntity<>(cacheRequest, HttpStatus.OK);
        }
    }

    private ResponseEntity<City> processResponse(ResponseEntity<String> response) {
        if (response.getBody() == null)
            return null;
        City city = null;
        ObjectMapper mapper = new ObjectMapper();

        try {
            city = mapper.readValue(response.getBody(), City.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(city, response.getStatusCode());
    }

    public ResponseEntity<String> apiChoice(API api,String city, String date) {
        String url1 = "";
        String url2 = "";
        switch (api) {
            case current:
                url1 = API_URL + "?city=" + city + "&key=" + API_KEY;
                break;
            case forecast:
                url2 = API_FORECAST + "?city=" + city + "&key=" + API_KEY ;
                break;
            }

        if (api == API.forecast){
            ResponseEntity<String> responseCountries = restTemplate.getForEntity(url2, String.class);
            return responseCountries;
        }else{
            ResponseEntity<String> responseCountries = restTemplate.getForEntity(url1, String.class);
            return responseCountries;
        }
    }

}
