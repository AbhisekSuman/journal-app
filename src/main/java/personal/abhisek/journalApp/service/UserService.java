package personal.abhisek.journalApp.service;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import personal.abhisek.journalApp.entity.User;
import personal.abhisek.journalApp.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(ObjectId id) {
        return userRepository.findById(id);
    }

    public User getUserByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }


    public void delete(ObjectId id) {
       userRepository.deleteById(id);
    }
}
