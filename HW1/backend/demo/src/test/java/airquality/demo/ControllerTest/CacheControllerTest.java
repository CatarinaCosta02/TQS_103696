package airquality.demo.ControllerTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.http.MediaType;

import airquality.demo.cache.Cache;
import airquality.demo.controllers.CacheController;
import airquality.demo.service.AirQualityService;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status; 

@WebMvcTest(CacheController.class)
public class CacheControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AirQualityService airQualityService;

    @BeforeEach
    public void setUp() {   
    }

    @Test
    public void getCacheTest() throws Exception{
        Mockito.when(airQualityService.getCache()).thenReturn(new ResponseEntity<>(Cache.printCache(), HttpStatus.OK));
        mvc.perform(get("/api/cache").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("numRequests").value(0))
            .andExpect(jsonPath("missCount").value(0))
            .andExpect(jsonPath("hitCount").value(0));
    }
}
