package orsk.compli.repository.neo4j;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import orsk.compli.entities.neo4j.Neo4jThreatCategory;

@Repository("neo4jThreatCategoryRepository")
public interface ThreatCategoryNeo4jRepository extends Neo4jRepository<Neo4jThreatCategory, String> {
}
