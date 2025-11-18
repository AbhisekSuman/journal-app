package personal.abhisek.journalApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import personal.abhisek.journalApp.api.response.WeatherReesponse;
import personal.abhisek.journalApp.cache.AppCache;
import personal.abhisek.journalApp.constants.PlaceHolder;

@Service
public class WeatherService {

    @Value("${weather.api.key}")
    private String API_KEY;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AppCache appCache;

    public WeatherReesponse getWeather(String city) {
        String finalApi = appCache.appCache.get(AppCache.keys.WEATHER_API.toString()).replace(PlaceHolder.CITY, city).replace(PlaceHolder.API_KEY, API_KEY);

        ResponseEntity<WeatherReesponse> response = restTemplate.exchange(finalApi, HttpMethod.GET, null, WeatherReesponse.class);
        return response.getBody();
    }
}
