package personal.abhisek.journalApp.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WeatherReesponse {

    private Current current;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Current{
        @JsonProperty("temp_c")
        private double tempC;
        @JsonProperty("temp_f")
        private double tempF;
        @JsonProperty("is_day")
        private int isDay;
        private int humidity;
        private int cloud;
        @JsonProperty("feelslike_c")
        private double feelsLikeC;
        @JsonProperty("feelslike_f")
        private double feelsLikeF;
    }
}
