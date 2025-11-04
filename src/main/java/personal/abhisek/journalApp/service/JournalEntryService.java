package personal.abhisek.journalApp.service;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import personal.abhisek.journalApp.entity.JournalEntry;
import personal.abhisek.journalApp.repository.JournalEntryRepository;
import java.util.List;

@Service
public class JournalEntryService {

    @Autowired
    JournalEntryRepository repository;


    public List<JournalEntry> getAll() {
        return repository.findAll();
    }

    public JournalEntry get(ObjectId id) {
        return repository.findById(id).orElse(null);
    }

    public void create(JournalEntry journalEntry) {
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

    public void delete(ObjectId id) {
        repository.findById(id).ifPresent(journal -> repository.delete(journal));

    }
}
