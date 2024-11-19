package orsk.compli.entities.neo4j;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

import java.time.LocalDate;

@Getter
@Setter
@Node("GlobalThreat")
public class Neo4jGlobalThreat {

    @Id
    @GeneratedValue(generatorClass = GeneratedValue.UUIDGenerator.class)
    private String id;
    private String name;
    private String description;
    private LocalDate firstDetected;
    private int severityLevel;
    private LocalDate dataRetentionUntil;
}
