package orsk.compli.controller.neo4j;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import orsk.compli.entities.neo4j.Neo4jAttackVectorCategory;
import orsk.compli.service.neo4j.AttackVectorCategoryNeo4jService;
import orsk.compli.service.neo4j.CrudNeo4jService;

@RestController("neo4jAttackVectorCategoryController")
@RequestMapping("/api/neo4j/attack-vector-categories")
public class AttackVectorCategoryControllerNeo4jNeo4J extends AbstractCrudControllerNeo4j<Neo4jAttackVectorCategory, Long> {

    private final AttackVectorCategoryNeo4jService attackVectorCategoryService;

    public AttackVectorCategoryControllerNeo4jNeo4J(AttackVectorCategoryNeo4jService attackVectorCategoryService) {
        this.attackVectorCategoryService = attackVectorCategoryService;
    }

    @Override
    protected CrudNeo4jService<Neo4jAttackVectorCategory, Long> getService() {
        return attackVectorCategoryService;
    }
}
