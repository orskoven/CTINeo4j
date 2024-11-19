package orsk.compli.service.neo4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import orsk.compli.entities.neo4j.Neo4jAttackVector;
import orsk.compli.repository.neo4j.AttackVectorNeo4jRepository;

import java.util.List;
import java.util.Optional;

@Component("neo4jAttackVectorService")
public class AttackVectorNeo4jService implements CrudNeo4jService<Neo4jAttackVector, Long> {

    private final AttackVectorNeo4jRepository attackVectorServiceRepository;

    @Autowired
    public AttackVectorNeo4jService(AttackVectorNeo4jRepository attackVectorServiceRepository) {
        this.attackVectorServiceRepository = attackVectorServiceRepository;
    }

    @Override
    public Neo4jAttackVector create(Neo4jAttackVector entity) {
        return attackVectorServiceRepository.save(entity);
    }

    @Override
    public List<Neo4jAttackVector> getAll() {
        return attackVectorServiceRepository.findAll();
    }

    @Override
    public Optional<Neo4jAttackVector> getById(Long id) {
        return attackVectorServiceRepository.findById(String.valueOf(id));
    }

    @Override
    public Neo4jAttackVector update(Long id, Neo4jAttackVector entity) {
        Optional<Neo4jAttackVector> optionalEntity = attackVectorServiceRepository.findById(String.valueOf(id));
        if (optionalEntity.isPresent()) {
            Neo4jAttackVector existingEntity = optionalEntity.get();
            // TODO: Update fields of existingEntity with values from entity
            return attackVectorServiceRepository.save(existingEntity);
        } else {
            throw new RuntimeException("Entity not found with id " + id);
        }
    }

    @Override
    public boolean delete(Long id) {
        if (attackVectorServiceRepository.existsById(String.valueOf(id))) {
            attackVectorServiceRepository.deleteById(String.valueOf(id));
            return true;
        }
        return false;
    }
}
