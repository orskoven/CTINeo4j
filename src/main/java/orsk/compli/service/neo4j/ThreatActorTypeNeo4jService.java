package orsk.compli.service.neo4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import orsk.compli.entities.neo4j.Neo4jThreatActorType;
import orsk.compli.repository.neo4j.ThreatActorTypeNeo4jRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ThreatActorTypeNeo4jService implements CrudNeo4jService<Neo4jThreatActorType, Long> {

    private final ThreatActorTypeNeo4jRepository threatActorTypeServiceRepository;

    @Autowired
    public ThreatActorTypeNeo4jService(ThreatActorTypeNeo4jRepository threatActorTypeServiceRepository) {
        this.threatActorTypeServiceRepository = threatActorTypeServiceRepository;
    }

    @Override
    public Neo4jThreatActorType create(Neo4jThreatActorType entity) {
        return threatActorTypeServiceRepository.save(entity);
    }

    @Override
    public List<Neo4jThreatActorType> getAll() {
        return threatActorTypeServiceRepository.findAll();
    }

    @Override
    public Optional<Neo4jThreatActorType> getById(Long id) {
        return threatActorTypeServiceRepository.findById(String.valueOf(id));
    }

    @Override
    public Neo4jThreatActorType update(Long id, Neo4jThreatActorType entity) {
        Optional<Neo4jThreatActorType> optionalEntity = threatActorTypeServiceRepository.findById(String.valueOf(id));
        if (optionalEntity.isPresent()) {
            Neo4jThreatActorType existingEntity = optionalEntity.get();
            // TODO: Update fields of existingEntity with values from entity
            return threatActorTypeServiceRepository.save(existingEntity);
        } else {
            throw new RuntimeException("Entity not found with id " + id);
        }
    }

    @Override
    public boolean delete(Long id) {
        if (threatActorTypeServiceRepository.existsById(String.valueOf(id))) {
            threatActorTypeServiceRepository.deleteById(String.valueOf(id));
            return true;
        }
        return false;
    }
}
