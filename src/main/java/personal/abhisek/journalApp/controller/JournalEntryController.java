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

    @GetMapping(path = "/{userName}")
    public ResponseEntity<List<JournalEntry>> getAllJournalOfUser(@PathVariable String userName) {
        return new ResponseEntity<>(service.getAll(userName), HttpStatus.OK);
    }

    @GetMapping(path = "/id/{id}")
    public ResponseEntity<JournalEntry> getJournal(@PathVariable ObjectId id) {
        Optional<JournalEntry> journalEntry = service.get(id);

        if (journalEntry.isPresent()) {
            return new ResponseEntity<JournalEntry>(journalEntry.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus. BAD_REQUEST);
    }

    @PostMapping(path = "/{userName}")
    public ResponseEntity<JournalEntry> createJournalEntry(@RequestBody JournalEntry journalEntry, @PathVariable String userName) {
        try {
            service.create(journalEntry, userName);
            return new ResponseEntity<>(journalEntry, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(path = "/id/{userName}/{id}")
    public ResponseEntity<JournalEntry> updateJournalEntry(@PathVariable ObjectId id, @RequestBody JournalEntry journalEntry, @PathVariable String userName) {
        return new  ResponseEntity<>(service.update(id, journalEntry, userName), HttpStatus.OK);
    }

    @DeleteMapping("/id/{userName}/{id}")
    public ResponseEntity<Object> deleteJournalEntry(@PathVariable ObjectId id, @PathVariable String userName) {
        return service.delete(id, userName);
    }

}
