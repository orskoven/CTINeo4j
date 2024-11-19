package orsk.compli.repository.neo4j;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;
import orsk.compli.entities.neo4j.Neo4jThreatActor;

import java.util.List;

@Repository("neo4jThreatActorRepository")
public interface ThreatActorNeo4jRepository extends Neo4jRepository<Neo4jThreatActor, String> {

    // Custom Query 2: Trace Attack Vectors Used by Specific Threat Actors
    @Query("MATCH (ta:ThreatActor)-[:USES]->(av:AttackVector)-[:BELONGS_TO]->(avc:AttackVectorCategory) RETURN ta.name AS ThreatActor, av.name AS AttackVector, avc.name AS AttackVectorCategory")
    List<ThreatActorAttackVectorProjection> findAttackVectorsByThreatActors();

    interface ThreatActorAttackVectorProjection {
        String getThreatActor();

        String getAttackVector();

        String getAttackVectorCategory();
    }

    // Custom Query 7: Identify Emerging Threat Actors with Shared Techniques
    @Query("MATCH (ta1:ThreatActor)-[:USES]->(av:AttackVector)<-[:USES]-(ta2:ThreatActor) WHERE ta1.name <> ta2.name RETURN ta1.name AS ThreatActor1, ta2.name AS ThreatActor2, av.name AS CommonVector")
    List<ThreatActorSharedTechniquesProjection> findThreatActorsWithSharedTechniques();

    interface ThreatActorSharedTechniquesProjection {
        String getThreatActor1();

        String getThreatActor2();

        String getCommonVector();
    }

    // Custom Query 9: Historical Breach Analysis for Specific Threat Actor
    @Query("MATCH (ta:ThreatActor)-[:RESPONSIBLE_FOR]->(b:Breach)-[:AFFECTED_SECTOR]->(s:Sector) WHERE ta.name = $name RETURN b.date AS BreachDate, s.name AS Sector, b.impact AS ImpactDetails")
    List<BreachAnalysisProjection> findBreachAnalysisByThreatActor(String name);

    interface BreachAnalysisProjection {
        String getBreachDate();

        String getSector();

        String getImpactDetails();
    }
}
