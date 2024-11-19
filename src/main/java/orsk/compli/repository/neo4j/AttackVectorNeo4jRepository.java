package orsk.compli.repository.neo4j;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;
import orsk.compli.entities.neo4j.Neo4jAttackVector;

import java.util.List;


@Repository("neo4jAttackVectorRepository")
public interface AttackVectorNeo4jRepository extends Neo4jRepository<Neo4jAttackVector, String> {

    // Custom Query 5: Monitor Network Anomalies Tied to Known Attack Vectors
    @Query("MATCH (na:NetworkActivity)-[:RELATED_TO]->(av:AttackVector)-[:BELONGS_TO]->(avc:AttackVectorCategory) WHERE na.anomalyScore > 0.8 AND av.severityLevel > 6 RETURN na.timestamp AS ActivityTime, av.name AS AttackVector, avc.name AS AttackCategory")
    List<NetworkAnomalyProjection> findNetworkAnomalies();

    interface NetworkAnomalyProjection {
        String getActivityTime();

        String getAttackVector();

        String getAttackCategory();
    }
}
