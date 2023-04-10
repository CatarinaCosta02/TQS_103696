package airquality.demo.UnitTest;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import airquality.demo.cache.Cache;
import airquality.demo.cache.ObjectCache;
import airquality.demo.models.City;

public class CacheTest {

    private Cache cache;
    private City city;


    @Test
    @DisplayName("When cache is 0, then a cache request, NumMisses should be 1")
    public void testEmptyCache() {
        assertThat(cache.getCachedRequest("?")).isNull();
        assertThat(cache.getNumRequests()).isEqualTo(1);
        assertThat(cache.getMissCount()).isEqualTo(1);
        assertThat(cache.getHitCount()).isEqualTo(0);
        
    }

    @Test
    @DisplayName("When their is a cache request, then the request returns null, NumHits should be 1")
    public void testCacheRequest() {
        String key = "key";
        cache.add(key, null);
        assertThat(cache.getCachedRequest("key")).isNull();
        assertThat(cache.getNumRequests()).isEqualTo(1);
        assertThat(cache.getMissCount()).isEqualTo(0);
        assertThat(cache.getHitCount()).isEqualTo(1);        
    }

    @Test
    @DisplayName("When the cache request is expired, then the numMisses should be 1")
    public void isExpiredTest() throws InterruptedException{
        ObjectCache objectCache = new ObjectCache(city, System.currentTimeMillis() + 1000L);
        assertThat(objectCache.isExpired()).isFalse();
        Thread.sleep(1001L);
        assertThat(objectCache.isExpired()).isTrue();
        
    }
    
    
}
