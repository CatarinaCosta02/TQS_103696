package airquality.demo.controllers;

import org.springframework.web.bind.annotation.RequestMapping;

import airquality.demo.cache.Cache;
import airquality.demo.cache.ObjectCache;
import airquality.demo.models.City;
import airquality.demo.service.AirQualityService;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AirQualityRestController {
    @Autowired
    private AirQualityService airQualityService;
    

    @GetMapping("/current/{city}")
    public ResponseEntity<Object> getCurrentAirQuality(@PathVariable String city){
        ResponseEntity<City> request = airQualityService.getAirQuality(city);
        if (request != null)
            return new ResponseEntity<>(request.getBody(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/forecast/{city}/date/{date}")
    public ResponseEntity<Object> getAirQualityForecast(@PathVariable String city, @PathVariable String date){
        ResponseEntity<City> request = airQualityService.getAirQualityForecast(city + "&date=" + date);
        if (request != null)
            return new ResponseEntity<>(request.getBody(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/forecast/{city}")
    public ResponseEntity<Object> getAirQualityForecast(@PathVariable String city){
        ResponseEntity<City> request = airQualityService.getAirQualityForecast(city);
        if (request != null)
            return new ResponseEntity<>(request.getBody(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/cache")
    public ResponseEntity<Object> getCache(){
        Map<String, ObjectCache> cache = airQualityService.getCache();
        if (cache != null)
            return new ResponseEntity<>(cache, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    
}
