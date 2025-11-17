package personal.abhisek.journalApp.cache;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class AppCache {

    private Map<String, String> appChache = new HashMap<>();

    public void init() {
        
    }
}
