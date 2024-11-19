package orsk.compli.controller.neo4j;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import orsk.compli.entities.neo4j.Neo4jThreatActorType;
import orsk.compli.service.neo4j.ThreatActorTypeNeo4jService;

@RestController("neo4jThreatActorTypeController")
@RequestMapping("/api/neo4j/threat-actor-types")
public class ThreatActorTypeControllerNeo4jNeo4J extends AbstractCrudControllerNeo4j<Neo4jThreatActorType, Long> {

    private final ThreatActorTypeNeo4jService threatActorTypeService;

    public ThreatActorTypeControllerNeo4jNeo4J(ThreatActorTypeNeo4jService threatActorTypeService) {
        this.threatActorTypeService = threatActorTypeService;
    }

    @Override
    protected ThreatActorTypeNeo4jService getService() {
        return threatActorTypeService;
    }
}
