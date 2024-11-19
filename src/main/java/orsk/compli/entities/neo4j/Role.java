package orsk.compli.entities.neo4j;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.Set;

@Getter
@Setter
@Node("Role") // Specify Neo4j node label
public class Role {

    @Id
    @GeneratedValue
    private Long id; // Auto-generated ID for Neo4j

    private String name; // Role name, e.g., "ROLE_USER"

    @Relationship(type = "HAS_PRIVILEGE", direction = Relationship.Direction.OUTGOING)
    private Set<Privilege> privileges; // Related Privilege nodes
}