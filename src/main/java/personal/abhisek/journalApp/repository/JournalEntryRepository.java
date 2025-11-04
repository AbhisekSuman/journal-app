package personal.abhisek.journalApp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import personal.abhisek.journalApp.entity.JournalEntry;

@Repository
public interface JournalEntryRepository extends MongoRepository<JournalEntry, String> {

}
