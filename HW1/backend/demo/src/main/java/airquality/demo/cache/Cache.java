package airquality.demo.cache;

import java.util.HashMap;
import java.util.Map;

import airquality.demo.models.City;

public class Cache {

    private static final long TIMETOLIVE = 100L;
    private static Map<String, ObjectCache> cache = new HashMap<>();
    private static int missCount = 0;
    private static int hitCount = 0;
    private static int numRequests = 0;

    static { 
        Thread cleanerThread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Thread.sleep(TIMETOLIVE * 1000L);
                    cache.entrySet().removeIf(entry -> entry.getValue().isExpired());
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        cleanerThread.setDaemon(true);
        cleanerThread.start();
    }

    public static int getMissCount() {
        return missCount;
    }

    public static int getHitCount() {
        return hitCount;
    }

    public static int getNumRequests() {
        return numRequests;
    }

    public static City getCachedRequest(String key) {
        numRequests++;
        if (cache.containsKey(key)) {
            hitCount++;
            return cache.get(key).getCity();
        }
        missCount++;
        return null;
    }

    public static void add(String key, City city) {
        cache.put(key, new ObjectCache(city));
    }

    public static Object printCache(){
        return new Object(){
            public final int numRequests = getNumRequests();
            public final int hitCount = getHitCount();
            public final int missCount = getMissCount();
        };
    }
}