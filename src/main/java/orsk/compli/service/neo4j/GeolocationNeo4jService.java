package orsk.compli.service.neo4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import orsk.compli.entities.neo4j.Neo4jGeolocation;
import orsk.compli.repository.neo4j.GeolocationNeo4jRepository;

import java.util.List;
import java.util.Optional;

@Service("neo4jGeolocationService")
public class GeolocationNeo4jService implements CrudNeo4jService<Neo4jGeolocation, Long> {

    private final GeolocationNeo4jRepository geolocationServiceRepository;

    @Autowired
    public GeolocationNeo4jService(GeolocationNeo4jRepository geolocationServiceRepository) {
        this.geolocationServiceRepository = geolocationServiceRepository;
    }

    @Override
    public Neo4jGeolocation create(Neo4jGeolocation entity) {
        return geolocationServiceRepository.save(entity);
    }

    @Override
    public List<Neo4jGeolocation> getAll() {
        return geolocationServiceRepository.findAll();
    }

    @Override
    public Optional<Neo4jGeolocation> getById(Long id) {
        return geolocationServiceRepository.findById(String.valueOf(id));
    }

    @Override
    public Neo4jGeolocation update(Long id, Neo4jGeolocation entity) {
        Optional<Neo4jGeolocation> optionalEntity = geolocationServiceRepository.findById(String.valueOf(id));
        if (optionalEntity.isPresent()) {
            Neo4jGeolocation existingEntity = optionalEntity.get();
            // TODO: Update fields of existingEntity with values from entity
            return geolocationServiceRepository.save(existingEntity);
        } else {
            throw new RuntimeException("Entity not found with id " + id);
        }
    }

    @Override
    public boolean delete(Long id) {
        if (geolocationServiceRepository.existsById(String.valueOf(id))) {
            geolocationServiceRepository.deleteById(String.valueOf(id));
            return true;
        }
        return false;
    }
}
