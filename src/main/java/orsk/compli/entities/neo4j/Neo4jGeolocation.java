package orsk.compli.entities.neo4j;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

@Getter
@Setter
@Node("Geolocation")
public class Neo4jGeolocation {

    @Id
    @GeneratedValue(generatorClass = GeneratedValue.UUIDGenerator.class)
    private String id;
    private String ipAddress;
    private String region;
    private String city;
    private double latitude;
    private double longitude;
    @Relationship(type = "LOCATED_IN", direction = Relationship.Direction.OUTGOING)
    private Neo4jCountry lOCATED_IN;
}
