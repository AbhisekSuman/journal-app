package personal.abhisek.journalApp.controller;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import personal.abhisek.journalApp.entity.JournalEntry;
import personal.abhisek.journalApp.service.JournalEntryService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/journal")
public class JournalEntryController {

    @Autowired
    JournalEntryService service;

    @GetMapping(path = "/")
    public List<JournalEntry> getAll() {
        return service.getAll();
    }

    @GetMapping(path = "/id/{id}")
    public JournalEntry getJournal(@PathVariable ObjectId id) {
        return service.get(id);
    }

    @PostMapping(path = "/")
    public void createJournalEntry(@RequestBody JournalEntry journalEntry) {
        service.create(journalEntry);
    }

    @PutMapping(path = "/id/{id}")
    public JournalEntry updateJournalEntry(@PathVariable ObjectId id, @RequestBody JournalEntry journalEntry) {
        return service.update(id, journalEntry);
    }

    @DeleteMapping("/id/{id}")
    public void deleteJournalEntry(@PathVariable ObjectId id) {
        service.delete(id);
    }

}
