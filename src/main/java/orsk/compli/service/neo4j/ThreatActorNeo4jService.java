package orsk.compli.service.neo4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import orsk.compli.entities.neo4j.Neo4jThreatActor;
import orsk.compli.repository.neo4j.ThreatActorNeo4jRepository;

import java.util.List;
import java.util.Optional;

@Component("neo4jThreatActorService")
public class ThreatActorNeo4jService implements CrudNeo4jService<Neo4jThreatActor, Long> {

    private final ThreatActorNeo4jRepository threatActorServiceRepository;

    @Autowired
    public ThreatActorNeo4jService(ThreatActorNeo4jRepository threatActorServiceRepository) {
        this.threatActorServiceRepository = threatActorServiceRepository;
    }

    @Override
    public Neo4jThreatActor create(Neo4jThreatActor entity) {
        return threatActorServiceRepository.save(entity);
    }

    @Override
    public List<Neo4jThreatActor> getAll() {
        return threatActorServiceRepository.findAll();
    }

    @Override
    public Optional<Neo4jThreatActor> getById(Long id) {
        return threatActorServiceRepository.findById(String.valueOf(id));
    }

    @Override
    public Neo4jThreatActor update(Long id, Neo4jThreatActor entity) {
        Optional<Neo4jThreatActor> optionalEntity = threatActorServiceRepository.findById(String.valueOf(id));
        if (optionalEntity.isPresent()) {
            Neo4jThreatActor existingEntity = optionalEntity.get();
            // TODO: Update fields of existingEntity with values from entity
            return threatActorServiceRepository.save(existingEntity);
        } else {
            throw new RuntimeException("Entity not found with id " + id);
        }
    }

    @Override
    public boolean delete(Long id) {
        if (threatActorServiceRepository.existsById(String.valueOf(id))) {
            threatActorServiceRepository.deleteById(String.valueOf(id));
            return true;
        }
        return false;
    }
}
