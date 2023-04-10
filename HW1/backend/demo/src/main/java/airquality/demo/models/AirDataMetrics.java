package airquality.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AirDataMetrics {
    @JsonProperty("aqi")
    private int aqi;
    @JsonProperty("o3")
    private double o3;
    @JsonProperty("so2")
    private double so2;
    @JsonProperty("no2")
    private double no2;
    @JsonProperty("co")
    private double co;
    @JsonProperty("pm10")
    private double pm10;
    @JsonProperty("pm25")
    private double pm25;
    @JsonProperty("pollen_level_tree")
    private int pollenLevelTree;
    @JsonProperty("pollen_level_grass")
    private int pollenLevelGrass;
    @JsonProperty("pollen_level_weed")
    private int pollenLevelWeed;
    @JsonProperty("mold_level")
    private int moldLevel;
    @JsonProperty("predominant_pollen_type")
    private String predominantPollenType;

}
