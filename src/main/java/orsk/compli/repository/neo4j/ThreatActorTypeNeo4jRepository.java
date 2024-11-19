package orsk.compli.repository.neo4j;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import orsk.compli.entities.neo4j.Neo4jThreatActorType;

@Repository("neo4jThreatActorTypeRepository")
public interface ThreatActorTypeNeo4jRepository extends Neo4jRepository<Neo4jThreatActorType, String> {
}
