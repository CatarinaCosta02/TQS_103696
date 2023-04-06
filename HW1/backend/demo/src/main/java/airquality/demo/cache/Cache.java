package airquality.demo.cache;

import java.util.HashMap;
import java.util.Map;

public class Cache {

    private static final long TIMETOLIVE = 100L;
    private static Map<String, ObjectCache> cache = new HashMap<>();
    private static Map<String, Long> expiration = new HashMap<>();
    private static int missCount = 0;
    private static int hitCount = 0;
    private static int numRequests = 0;

    public Cache() {
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

    public int getMissCount() {
        return missCount;
    }

    public int getHitCount() {
        return hitCount;
    }

    public int getNumRequests() {
        return numRequests;
    }

    public ObjectCache getCachedRequest(String key){
        numRequests++;
        // vê se esta na cache e se nao esta expirado
        if(cache.containsKey(key)){
            if(expiration.get(key) < getCurTime()){
                remCachedRequest(key);
                missCount++;
                return null;
            }
            hitCount++;
            return cache.get(key);
        }
        missCount++;
        return null;
    }


    private Long getCurTime() {
        return System.currentTimeMillis();
    }
    
    private void remCachedRequest(String key) {
        cache.remove(key);
        expiration.remove(key);
    }

    // write me a function that prints the json
    public String printCache() {
        return "{\n" +
                "  \"numRequests\": " + numRequests + ",\n" +
                "  \"hitCount\": " + hitCount + ",\n" +
                "  \"missCount\": " + missCount + "\n" +
                "}";
    }

    public Map<String, ObjectCache> getCache() {
        return cache;
    }
}