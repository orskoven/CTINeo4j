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
@Node("User") // Specify Neo4j node label
public class User {

    @Id
    @GeneratedValue
    private Long id; // Auto-generated ID for Neo4j

    private String username;

    private String password;

    private String email;

    private Boolean enabled = false;

    private Boolean consentToDataUsage = false;

    @Relationship(type = "HAS_ROLE", direction = Relationship.Direction.OUTGOING)
    private Set<Role> roles; // Reference to related Role nodes
}