package orsk.compli.repository.neo4j;

import orsk.compli.entities.neo4j.User;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends Neo4jRepository<User, Long> {
    // Custom query methods

    // Find a user by username
    User findByUsername(String username);

    // Find a user by email
    User findByEmail(String email);
}