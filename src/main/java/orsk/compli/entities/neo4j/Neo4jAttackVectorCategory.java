package orsk.compli.entities.neo4j;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

@Getter
@Setter
@Node("neo4jAttackVectorCategory")
public class Neo4jAttackVectorCategory {

    @Id
    @GeneratedValue(generatorClass = GeneratedValue.UUIDGenerator.class)
    private String id;
    private String name;
    private String description;
    @Relationship(type = "BELONGS_TO", direction = Relationship.Direction.INCOMING)
    private Neo4jAttackVector bELONGS_TO;
}
