package orsk.compli.repository.neo4j;

import orsk.compli.entities.neo4j.Privilege;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivilegeRepository extends Neo4jRepository<Privilege, Long> {
    // Custom query methods if needed
    Privilege findByName(String name); // Example: Find privilege by name
}