package orsk.compli.service.neo4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import orsk.compli.dtos.UserDTO;
import orsk.compli.entities.mongo.MongoUser;
import orsk.compli.repository.mongo.UserMongoRepository;

import java.util.List;

@Service
public class UserMigrationService {

    private final UserFeignClient userFeignClient;
    private final UserMongoRepository mongoUserRepository;

    @Autowired
    public UserMigrationService(UserFeignClient userFeignClient, UserMongoRepository mongoUserRepository) {
        this.userFeignClient = userFeignClient;
        this.mongoUserRepository = mongoUserRepository;
    }

    public void migrateUsers() {
        List<UserDTO> mysqlUsers = userFeignClient.fetchAllUsers();

        List<MongoUser> mongoUsers = mysqlUsers.stream().map(this::convertToMongoUser).toList();
        mongoUserRepository.saveAll(mongoUsers);

        System.out.println("Migration completed successfully!");
    }

    private MongoUser convertToMongoUser(UserDTO userDTO) {
        MongoUser mongoUser = new MongoUser();
        mongoUser.setUsername(userDTO.getUsername());
        mongoUser.setEmail(userDTO.getEmail());
        mongoUser.setEnabled(userDTO.isEnabled());
        return mongoUser;
    }
}