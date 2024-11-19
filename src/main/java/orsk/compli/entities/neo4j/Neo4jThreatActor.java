package orsk.compli.entities.neo4j;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.time.LocalDate;

@Getter
@Setter
@Node("ThreatNeo4jActor")
public class Neo4jThreatActor {

    @Id
    @GeneratedValue(generatorClass = GeneratedValue.UUIDGenerator.class)
    private String id;
    private String name;
    private String originCountry;
    private LocalDate firstObserved;
    @Relationship(type = "BELONGS_TO", direction = Relationship.Direction.OUTGOING)
    private Neo4jThreatActorType bELONGS_TO;
}
