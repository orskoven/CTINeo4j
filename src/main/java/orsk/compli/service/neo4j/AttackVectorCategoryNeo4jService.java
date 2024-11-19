package orsk.compli.service.neo4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import orsk.compli.entities.neo4j.Neo4jAttackVectorCategory;
import orsk.compli.repository.neo4j.AttackVectorCategoryNeo4jRepository;

import java.util.List;
import java.util.Optional;

@Component("neo4jAttackVectorCategoryService")
public class AttackVectorCategoryNeo4jService implements CrudNeo4jService<Neo4jAttackVectorCategory, Long> {

    private final AttackVectorCategoryNeo4jRepository attackVectorCategoryServiceRepository;

    @Autowired
    public AttackVectorCategoryNeo4jService(AttackVectorCategoryNeo4jRepository attackVectorCategoryServiceRepository) {
        this.attackVectorCategoryServiceRepository = attackVectorCategoryServiceRepository;
    }

    @Override
    public Neo4jAttackVectorCategory create(Neo4jAttackVectorCategory entity) {
        return attackVectorCategoryServiceRepository.save(entity);
    }

    @Override
    public List<Neo4jAttackVectorCategory> getAll() {
        return attackVectorCategoryServiceRepository.findAll();
    }

    @Override
    public Optional<Neo4jAttackVectorCategory> getById(Long id) {
        return attackVectorCategoryServiceRepository.findById((String.valueOf(id)));
    }

    @Override
    public Neo4jAttackVectorCategory update(Long id, Neo4jAttackVectorCategory entity) {
        Optional<Neo4jAttackVectorCategory> optionalEntity = attackVectorCategoryServiceRepository.findById(String.valueOf(id));
        if (optionalEntity.isPresent()) {
            Neo4jAttackVectorCategory existingEntity = optionalEntity.get();
            // TODO: Update fields of existingEntity with values from entity
            return attackVectorCategoryServiceRepository.save(existingEntity);
        } else {
            throw new RuntimeException("Entity not found with id " + id);
        }
    }

    @Override
    public boolean delete(Long id) {
        if (attackVectorCategoryServiceRepository.existsById(String.valueOf(id))) {
            attackVectorCategoryServiceRepository.deleteById(String.valueOf(id));
            return true;
        }
        return false;
    }
}
