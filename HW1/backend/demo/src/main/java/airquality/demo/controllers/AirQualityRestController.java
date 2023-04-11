package airquality.demo.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import airquality.demo.models.City;
import airquality.demo.service.AirQualityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Air Quality", description = "The Air Quality API")
@RestController
@RequestMapping("/api")
public class AirQualityRestController {
    final AirQualityService airQualityService;
    @Autowired
    private AirQualityRestController(AirQualityService airQualityService) {
        this.airQualityService = airQualityService;
    }
    

    @GetMapping("/current/{city}")
    public ResponseEntity<Object> getCurrentAirQuality(@PathVariable String city){
        ResponseEntity<City> request = airQualityService.getAirQuality(city);
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

    
}
