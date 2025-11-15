package personal.abhisek.journalApp.service;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import personal.abhisek.journalApp.entity.JournalEntry;
import personal.abhisek.journalApp.entity.User;
import personal.abhisek.journalApp.repository.JournalEntryRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class JournalEntryService {

    @Autowired
    JournalEntryRepository repository;

    @Autowired
    UserService userService;


    public List<JournalEntry> getAll(String userName) {
        User user =  userService.getUserByUserName(userName);
        return user.getJournalEntries();
    }

    public Optional<JournalEntry> get(ObjectId id) {
        return repository.findById(id);
    }

    @Transactional
    public void create(JournalEntry journalEntry, String userName) {
        User user = userService.getUserByUserName(userName);
        journalEntry.setDate(LocalDateTime.now());
        JournalEntry savedEntry = repository.save(journalEntry);
        user.getJournalEntries().add(savedEntry);
        userService.saveEntry(user);
    }

    public JournalEntry update(ObjectId id, JournalEntry journalEntry) {
        JournalEntry oldJournal = repository.findById(id).orElse(null);

        if (oldJournal != null) {
            oldJournal.setTitle(journalEntry.getTitle() != null && journalEntry.getTitle().isEmpty() ? oldJournal.getTitle() : journalEntry.getTitle());
            oldJournal.setDescription(journalEntry.getDescription() != null && journalEntry.getDescription().isEmpty() ? oldJournal.getDescription() : journalEntry.getDescription());
            repository.save(oldJournal);
        }
        return oldJournal;
    }

    @Transactional
    public ResponseEntity<Object> delete(ObjectId id, String userName) {
        User user = userService.getUserByUserName(userName);

        if (user != null) {
            boolean isRemoved = user.getJournalEntries().removeIf(x -> x.getId().equals(id));
            if (isRemoved) {
                userService.saveEntry(user);
                repository.deleteById(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
