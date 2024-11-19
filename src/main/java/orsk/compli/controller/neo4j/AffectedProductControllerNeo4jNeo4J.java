package orsk.compli.controller.neo4j;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import orsk.compli.entities.neo4j.Neo4jAffectedProduct;
import orsk.compli.service.neo4j.AffectedProductNeo4jService;

@RestController("neo4jAffectedProductController")
@RequestMapping("/api/neo4j/affected-products")
public class AffectedProductControllerNeo4jNeo4J extends AbstractCrudControllerNeo4j<Neo4jAffectedProduct, Long> {

    private final AffectedProductNeo4jService affectedProductService;

    public AffectedProductControllerNeo4jNeo4J(AffectedProductNeo4jService affectedProductService) {
        this.affectedProductService = affectedProductService;
    }

    protected AffectedProductNeo4jService getService() {
        return affectedProductService;
    }
}
