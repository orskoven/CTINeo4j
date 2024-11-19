package orsk.compli.service.neo4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import orsk.compli.entities.neo4j.Neo4jThreatCategory;
import orsk.compli.repository.neo4j.ThreatCategoryNeo4jRepository;

import java.util.List;
import java.util.Optional;

@Component("neo4jThreatCategoryService")
public class ThreatCategoryNeo4jService implements CrudNeo4jService<Neo4jThreatCategory, Long> {

    private final ThreatCategoryNeo4jRepository threatCategoryServiceRepository;

    @Autowired
    public ThreatCategoryNeo4jService(ThreatCategoryNeo4jRepository threatCategoryServiceRepository) {
        this.threatCategoryServiceRepository = threatCategoryServiceRepository;
    }

    @Override
    public Neo4jThreatCategory create(Neo4jThreatCategory entity) {
        return threatCategoryServiceRepository.save(entity);
    }

    @Override
    public List<Neo4jThreatCategory> getAll() {
        return threatCategoryServiceRepository.findAll();
    }

    @Override
    public Optional<Neo4jThreatCategory> getById(Long id) {
        return threatCategoryServiceRepository.findById(String.valueOf(id));
    }

    @Override
    public Neo4jThreatCategory update(Long id, Neo4jThreatCategory entity) {
        Optional<Neo4jThreatCategory> optionalEntity = threatCategoryServiceRepository.findById(String.valueOf(id));
        if (optionalEntity.isPresent()) {
            Neo4jThreatCategory existingEntity = optionalEntity.get();
            // TODO: Update fields of existingEntity with values from entity
            return threatCategoryServiceRepository.save(existingEntity);
        } else {
            throw new RuntimeException("Entity not found with id " + id);
        }
    }

    @Override
    public boolean delete(Long id) {
        if (threatCategoryServiceRepository.existsById(String.valueOf(id))) {
            threatCategoryServiceRepository.deleteById(String.valueOf(id));
            return true;
        }
        return false;
    }
}
