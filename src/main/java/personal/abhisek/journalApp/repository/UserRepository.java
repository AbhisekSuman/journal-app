package personal.abhisek.journalApp.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import personal.abhisek.journalApp.entity.User;

public interface UserRepository extends MongoRepository<User, ObjectId> {

    User findByUserName(String userName);
}
