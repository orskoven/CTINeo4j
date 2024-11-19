package orsk.compli.controller.neo4j;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import orsk.compli.entities.neo4j.Neo4jGlobalThreat;
import orsk.compli.service.neo4j.CrudNeo4jService;
import orsk.compli.service.neo4j.GlobalThreatNeo4jService;

@RestController("neo4jGlobalThreatController")
@RequestMapping("/api/neo4j/global-threats")
public class GlobalThreatControllerNeo4jNeo4J extends AbstractCrudControllerNeo4j<Neo4jGlobalThreat, Long> {

    private final GlobalThreatNeo4jService globalThreatService;

    public GlobalThreatControllerNeo4jNeo4J(GlobalThreatNeo4jService globalThreatService) {
        this.globalThreatService = globalThreatService;
    }

    @Override
    protected CrudNeo4jService<Neo4jGlobalThreat, Long> getService() {
        return globalThreatService;
    }
}
