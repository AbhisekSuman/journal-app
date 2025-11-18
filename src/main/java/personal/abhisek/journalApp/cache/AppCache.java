package personal.abhisek.journalApp.cache;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import personal.abhisek.journalApp.entity.ConfigJournalApp;
import personal.abhisek.journalApp.repository.ConfigJournalAppRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {

    public enum keys{
        WEATHER_API
    }

    @Autowired
    private ConfigJournalAppRepository repository;

    public final Map<String, String> appCache = new HashMap<>();

    @PostConstruct
    public void init() {
        List<ConfigJournalApp> list = repository.findAll();

        for (ConfigJournalApp configJournalApp : list) {
            appCache.put(configJournalApp.getKey(), configJournalApp.getValue());
        }
    }
}
