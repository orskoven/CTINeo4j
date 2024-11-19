package orsk.compli.repository.neo4j;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;
import orsk.compli.entities.neo4j.Neo4jAffectedProduct;

import java.util.List;

@Repository("neo4jAffectedProductRepository")
public interface AffectedProductNeo4jRepository extends Neo4jRepository<Neo4jAffectedProduct, String> {

    // Custom Query 4: Zero-Trust Model: Verify User Access to Sensitive Products
    @Query("MATCH (u:User)-[:HAS_ACCESS]->(ap:AffectedProduct) WHERE ap.sensitivityLevel > 8 " +
            "RETURN u.name AS User, ap.name AS SensitiveProduct")
    List<SensitiveProductProjection> findSensitiveProductsByUserAccess();

    interface SensitiveProductProjection {
        String getUser();

        String getSensitiveProduct();
    }
}
