package airquality.demo.IntegrationTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.dockerjava.core.MediaType;

import airquality.demo.cache.Cache;
import airquality.demo.service.AirQualityService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.when;

@WebMvcTest(controllers = CacheControllerTest.class)
public class CacheControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AirQualityService airQualityService;
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        objectMapper = new ObjectMapper();
    }
    /* 
    @Test
    public void testGetAll() throws Exception { 
        when(airQualityService.getCache()).thenReturn(new Cache(1));
        mvc.perform(get("/cache").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("numRequests").value(0))
                .andExpect(jsonPath("numMisses").value(0))
                .andExpect(jsonPath("numHits").value(0));  
                */
}

