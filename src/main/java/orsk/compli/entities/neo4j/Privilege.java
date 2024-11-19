package orsk.compli.entities.neo4j;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Node;

@Getter
@Setter
@Node("Privilege") // Specify Neo4j node label
public class Privilege {

    @Id
    @GeneratedValue
    private Long id; // Auto-generated ID for Privilege nodes

    private String name; // Privilege name, e.g., "READ_PRIVILEGE"
}