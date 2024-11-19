package orsk.compli.controller.neo4j;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import orsk.compli.entities.neo4j.Neo4jThreatActor;
import orsk.compli.service.neo4j.CrudNeo4jService;
import orsk.compli.service.neo4j.ThreatActorNeo4jService;

@RestController("neo4jThreatActorController")
@RequestMapping("/api/neo4j/threat-actors")
public class ThreatActorControllerNeo4jNeo4J extends AbstractCrudControllerNeo4j<Neo4jThreatActor, Long> {

    private final ThreatActorNeo4jService threatActorService;

    public ThreatActorControllerNeo4jNeo4J(ThreatActorNeo4jService threatActorService) {
        this.threatActorService = threatActorService;
    }

    @Override
    protected CrudNeo4jService<Neo4jThreatActor, Long> getService() {
        return threatActorService;
    }
}
