package personal.abhisek.journalApp.controller;

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
    public JournalEntry getJournal(@PathVariable String id) {
        return service.get(id);
    }

    @PostMapping(path = "/")
    public void createJournalEntry(@RequestBody JournalEntry journalEntry) {
        service.create(journalEntry);
    }

    @PutMapping(path = "/")
    public void updateJournalEntry(@RequestBody JournalEntry journalEntry) {

    }

    @DeleteMapping("/id/{id}")
    public void deleteJournalEntry(@PathVariable String id) {}

}
