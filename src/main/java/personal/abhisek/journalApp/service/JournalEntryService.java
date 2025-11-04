package personal.abhisek.journalApp.service;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import personal.abhisek.journalApp.entity.JournalEntry;
import personal.abhisek.journalApp.repository.JournalEntryRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class JournalEntryService {

    @Autowired
    JournalEntryRepository repository;


    public List<JournalEntry> getAll() {
        return repository.findAll();
    }

    public Optional<JournalEntry> get(ObjectId id) {
        return repository.findById(id);
    }

    public void create(JournalEntry journalEntry) {
        journalEntry.setDate(LocalDateTime.now());
        repository.save(journalEntry);
    }

    public JournalEntry update(ObjectId id, JournalEntry journalEntry) {
        JournalEntry oldJournal = repository.findById(id).orElse(null);

        if (oldJournal != null) {
            oldJournal.setTitle(journalEntry.getTitle() != null && journalEntry.getTitle().isEmpty() ? oldJournal.getTitle() : journalEntry.getTitle());
            oldJournal.setDescription(journalEntry.getDescription() != null && journalEntry.getDescription().isEmpty() ? oldJournal.getDescription() : journalEntry.getDescription());
        }
        repository.save(journalEntry);
        return journalEntry;
    }

    public ResponseEntity<Object> delete(ObjectId id) {
        Optional<JournalEntry> journalEntry = repository.findById(id);

        if (journalEntry.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
