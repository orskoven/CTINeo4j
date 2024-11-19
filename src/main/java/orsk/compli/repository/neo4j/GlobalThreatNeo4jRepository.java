package orsk.compli.repository.neo4j;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import orsk.compli.entities.neo4j.Neo4jGlobalThreat;

@Repository("neo4jGlobalThreatRepository")
public interface GlobalThreatNeo4jRepository extends Neo4jRepository<Neo4jGlobalThreat, String> {
}
