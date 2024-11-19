package orsk.compli.service.neo4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import orsk.compli.entities.neo4j.Neo4jGlobalThreat;
import orsk.compli.repository.neo4j.GlobalThreatNeo4jRepository;

import java.util.List;
import java.util.Optional;

@Component("neo4jGlobalThreatService")
public class GlobalThreatNeo4jService implements CrudNeo4jService<Neo4jGlobalThreat, Long> {

    private final GlobalThreatNeo4jRepository globalThreatServiceRepository;

    @Autowired
    public GlobalThreatNeo4jService(GlobalThreatNeo4jRepository globalThreatServiceRepository) {
        this.globalThreatServiceRepository = globalThreatServiceRepository;
    }

    @Override
    public Neo4jGlobalThreat create(Neo4jGlobalThreat entity) {
        return globalThreatServiceRepository.save(entity);
    }

    @Override
    public List<Neo4jGlobalThreat> getAll() {
        return globalThreatServiceRepository.findAll();
    }

    @Override
    public Optional<Neo4jGlobalThreat> getById(Long id) {
        return globalThreatServiceRepository.findById(String.valueOf(id));
    }

    @Override
    public Neo4jGlobalThreat update(Long id, Neo4jGlobalThreat entity) {
        Optional<Neo4jGlobalThreat> optionalEntity = globalThreatServiceRepository.findById(String.valueOf(id));
        if (optionalEntity.isPresent()) {
            Neo4jGlobalThreat existingEntity = optionalEntity.get();
            // TODO: Update fields of existingEntity with values from entity
            return globalThreatServiceRepository.save(existingEntity);
        } else {
            throw new RuntimeException("Entity not found with id " + id);
        }
    }

    @Override
    public boolean delete(Long id) {
        if (globalThreatServiceRepository.existsById(String.valueOf(id))) {
            globalThreatServiceRepository.deleteById(String.valueOf(id));
            return true;
        }
        return false;
    }
}
