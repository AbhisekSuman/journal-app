package personal.abhisek.journalApp.controller;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import personal.abhisek.journalApp.entity.JournalEntry;
import personal.abhisek.journalApp.service.JournalEntryService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/journal")
public class JournalEntryController {

    @Autowired
    JournalEntryService service;

    @GetMapping(path = "/")
    public ResponseEntity<List<JournalEntry>> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/id/{id}")
    public ResponseEntity<JournalEntry> getJournal(@PathVariable ObjectId id) {
        Optional<JournalEntry> journalEntry = service.get(id);

        if (journalEntry.isPresent()) {
            return new ResponseEntity<JournalEntry>(journalEntry.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus. BAD_REQUEST);
    }

    @PostMapping(path = "/")
    public ResponseEntity<JournalEntry> createJournalEntry(@RequestBody JournalEntry journalEntry) {
        try {
            service.create(journalEntry);
            return new ResponseEntity<>(journalEntry, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(path = "/id/{id}")
    public ResponseEntity<JournalEntry> updateJournalEntry(@PathVariable ObjectId id, @RequestBody JournalEntry journalEntry) {
        return new  ResponseEntity<>(service.update(id, journalEntry), HttpStatus.OK);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Object> deleteJournalEntry(@PathVariable ObjectId id) {
        return service.delete(id);
    }

}
