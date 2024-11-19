package orsk.compli.service.neo4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import orsk.compli.entities.neo4j.Neo4jAffectedProduct;
import orsk.compli.repository.neo4j.AffectedProductNeo4jRepository;

import java.util.List;
import java.util.Optional;

@Component("neo4jAffectedProductService")
public class AffectedProductNeo4jService implements CrudNeo4jService<Neo4jAffectedProduct, Long> {

    private final AffectedProductNeo4jRepository affectedProductServiceRepository;

    @Autowired
    public AffectedProductNeo4jService(AffectedProductNeo4jRepository affectedProductServiceRepository) {
        this.affectedProductServiceRepository = affectedProductServiceRepository;
    }

    @Override
    public Neo4jAffectedProduct create(Neo4jAffectedProduct entity) {
        return affectedProductServiceRepository.save(entity);
    }

    @Override
    public List<Neo4jAffectedProduct> getAll() {
        return affectedProductServiceRepository.findAll();
    }

    @Override
    public Optional<Neo4jAffectedProduct> getById(Long id) {
        return affectedProductServiceRepository.findById((String.valueOf(id)));
    }

    @Override
    public Neo4jAffectedProduct update(Long id, Neo4jAffectedProduct entity) {
        Optional<Neo4jAffectedProduct> optionalEntity = affectedProductServiceRepository.findById((String.valueOf(id)));
        if (optionalEntity.isPresent()) {
            Neo4jAffectedProduct existingEntity = optionalEntity.get();
            // TODO: Update fields of existingEntity with values from entity
            return affectedProductServiceRepository.save(existingEntity);
        } else {
            throw new RuntimeException("Entity not found with id " + id);
        }
    }

    @Override
    public boolean delete(Long id) {
        if (affectedProductServiceRepository.existsById((String.valueOf(id)))) {
            affectedProductServiceRepository.deleteById((String.valueOf(id)));
            return true;
        }
        return false;
    }
}
