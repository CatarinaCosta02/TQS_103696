package airquality.demo.cache;

import airquality.demo.models.City;
import lombok.Getter;

public class ObjectCache {


    @Getter
    private City city;
    private long expiryTime;

    public ObjectCache(City city, long expiryTime) {
        this.city = city;
        this.expiryTime = expiryTime;
    }

    public boolean isExpired() {
        return System.currentTimeMillis() > expiryTime;
    }
    
}
