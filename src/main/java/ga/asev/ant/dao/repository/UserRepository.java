package ga.asev.ant.dao.repository;

import ga.asev.ant.dao.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

}
