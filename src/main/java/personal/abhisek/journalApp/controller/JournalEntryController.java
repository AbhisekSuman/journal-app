package personal.abhisek.journalApp.controller;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import personal.abhisek.journalApp.entity.JournalEntry;
import personal.abhisek.journalApp.entity.User;
import personal.abhisek.journalApp.service.JournalEntryService;
import personal.abhisek.journalApp.service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/journal")
public class JournalEntryController {

    @Autowired
    JournalEntryService service;

    @Autowired
    UserService userService;

    @GetMapping(path = "/")
    public ResponseEntity<List<JournalEntry>> getAllJournalOfUser() {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        return new ResponseEntity<>(service.getAll(userName), HttpStatus.OK);
    }

    @GetMapping(path = "/id/{id}")
    public ResponseEntity<JournalEntry> getJournal(@PathVariable ObjectId id) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getUserByUserName(userName);

        List<JournalEntry> collect = user.getJournalEntries().stream().filter(x -> x.getId().equals(id)).toList();

        if (!collect.isEmpty()) {
            Optional<JournalEntry> journalEntry = service.get(id);

            if (journalEntry.isPresent()) {
                return new ResponseEntity<JournalEntry>(journalEntry.get(), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus. BAD_REQUEST);
    }

    @PostMapping(path = "/")
    public ResponseEntity<JournalEntry> createJournalEntry(@RequestBody JournalEntry journalEntry) {
        try {
            String userName = SecurityContextHolder.getContext().getAuthentication().getName();
            service.create(journalEntry, userName);
            return new ResponseEntity<>(journalEntry, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(path = "/id/{id}")
    public ResponseEntity<JournalEntry> updateJournalEntry(@PathVariable ObjectId id, @RequestBody JournalEntry journalEntry) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getUserByUserName(userName);

        List<JournalEntry> collect = user.getJournalEntries().stream().filter(x -> x.getId().equals(id)).toList();
        if (!collect.isEmpty()) {
            return new  ResponseEntity<>(service.update(id, journalEntry), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Object> deleteJournalEntry(@PathVariable ObjectId id) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        return service.delete(id, userName);
    }

}
