package orsk.compli.controller.neo4j;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import orsk.compli.entities.neo4j.Neo4jAttackVector;
import orsk.compli.service.neo4j.AttackVectorNeo4jService;
import orsk.compli.service.neo4j.CrudNeo4jService;

@RestController("neo4jAttackVectorController")
@RequestMapping("/api/neo4j/attack-vectors")
public class AttackVectorControllerNeo4jNeo4J extends AbstractCrudControllerNeo4j<Neo4jAttackVector, Long> {

    private final AttackVectorNeo4jService attackVectorService;

    public AttackVectorControllerNeo4jNeo4J(AttackVectorNeo4jService attackVectorService) {
        this.attackVectorService = attackVectorService;
    }

    @Override
    protected CrudNeo4jService<Neo4jAttackVector, Long> getService() {
        return attackVectorService;
    }
}
