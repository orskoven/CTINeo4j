package orsk.compli.repository.neo4j;

import orsk.compli.entities.neo4j.Role;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends Neo4jRepository<Role, Long> {
    // Custom query methods if needed
    Role findByName(String name); // Example: Find role by name
}