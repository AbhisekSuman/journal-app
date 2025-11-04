package personal.abhisek.journalApp.service;

import org.springframework.stereotype.Service;
import personal.abhisek.journalApp.entity.JournalEntry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JournalEntryService {
    Map<String, JournalEntry> journalEntryMap = new HashMap<>();


    public List<JournalEntry> getAll() {
        return new ArrayList<>(journalEntryMap.values());
    }

    public JournalEntry get(String id) {
        return journalEntryMap.get(id);
    }

    public void create(JournalEntry journalEntry) {
        journalEntryMap.put(journalEntry.getId(), journalEntry);
    }
}
