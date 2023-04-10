package airquality.demo.controllers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import airquality.demo.cache.Cache;

@RestController
@RequestMapping("/api")

public class CacheController {

    @GetMapping("/cache")
    public ResponseEntity<Object> getCache(){
        Object request = Cache.printCache();
        if (request != null)
            return new ResponseEntity<>(request, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
