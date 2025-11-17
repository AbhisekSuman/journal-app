package personal.abhisek.journalApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import personal.abhisek.journalApp.api.response.WeatherReesponse;

@Service
public class WeatherService {

    @Value("${weather.api.key}")
    private String API_KEY;
    private static final String URL = "http://api.weatherapi.com/v1/current.json?key=API_KEY&q=CITY&aqi=no";

    @Autowired
    private RestTemplate restTemplate;

    public WeatherReesponse getWeather(String city) {
        String finalApi = URL.replace("CITY", city).replace("API_KEY", API_KEY);

        ResponseEntity<WeatherReesponse> response = restTemplate.exchange(finalApi, HttpMethod.GET, null, WeatherReesponse.class);
        return response.getBody();
    }
}
