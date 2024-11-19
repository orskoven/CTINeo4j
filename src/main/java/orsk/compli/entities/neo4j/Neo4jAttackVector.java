package orsk.compli.entities.neo4j;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

@Node("MongoAttackVector")
@Getter
@Setter
public class Neo4jAttackVector {

    @Id
    @GeneratedValue(generatorClass = GeneratedValue.UUIDGenerator.class)
    private String elementId;
    private String name;
    private int severityLevel;
    @Relationship(type = "BELONGS_TO", direction = Relationship.Direction.OUTGOING)
    private Neo4jAttackVectorCategory bELONGS_TO;
}
