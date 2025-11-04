package personal.abhisek.journalApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import personal.abhisek.journalApp.entity.JournalEntry;
import personal.abhisek.journalApp.repository.JournalEntryRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JournalEntryService {

    @Autowired
    JournalEntryRepository repository;


    public List<JournalEntry> getAll() {
        return repository.findAll();
    }

    public JournalEntry get(String id) {
        return repository.findById(id).orElse(null);
    }

    public void create(JournalEntry journalEntry) {
        repository.save(journalEntry);
    }
}
