package personal.abhisek.journalApp.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import personal.abhisek.journalApp.entity.ConfigJournalApp;

public interface ConfigJournalAppRepository extends MongoRepository<ConfigJournalApp, ObjectId> {
}
