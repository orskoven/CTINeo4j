package orsk.compli.controller.neo4j;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import orsk.compli.entities.neo4j.Neo4jThreatCategory;
import orsk.compli.service.neo4j.CrudNeo4jService;
import orsk.compli.service.neo4j.ThreatCategoryNeo4jService;

@RestController("neo4jThreatCategoryController")
@RequestMapping("/api/neo4j/threat-categorys")
public class ThreatCategoryControllerNeo4jNeo4J extends AbstractCrudControllerNeo4j<Neo4jThreatCategory, Long> {

    private final ThreatCategoryNeo4jService threatCategoryService;

    public ThreatCategoryControllerNeo4jNeo4J(ThreatCategoryNeo4jService threatCategoryService) {
        this.threatCategoryService = threatCategoryService;
    }

    @Override
    protected CrudNeo4jService<Neo4jThreatCategory, Long> getService() {
        return threatCategoryService;
    }
}
