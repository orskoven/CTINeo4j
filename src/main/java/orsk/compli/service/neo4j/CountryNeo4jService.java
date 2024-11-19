package orsk.compli.service.neo4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import orsk.compli.entities.neo4j.Neo4jCountry;
import orsk.compli.repository.neo4j.CountryNeo4jRepository;

import java.util.List;
import java.util.Optional;

@Component("neo4jCountryService")
public class CountryNeo4jService implements CrudNeo4jService<Neo4jCountry, String> {

    private final CountryNeo4jRepository countryServiceRepository;

    @Autowired
    public CountryNeo4jService(CountryNeo4jRepository countryServiceRepository) {
        this.countryServiceRepository = countryServiceRepository;
    }

    @Override
    public Neo4jCountry create(Neo4jCountry entity) {
        return countryServiceRepository.save(entity);
    }

    @Override
    public List<Neo4jCountry> getAll() {
        return countryServiceRepository.findAll();
    }

    @Override
    public Optional<Neo4jCountry> getById(String id) {
        return countryServiceRepository.findById((id));
    }

    @Override
    public Neo4jCountry update(String id, Neo4jCountry entity) {
        Optional<Neo4jCountry> optionalEntity = countryServiceRepository.findById((id));
        if (optionalEntity.isPresent()) {
            Neo4jCountry existingEntity = optionalEntity.get();
            // TODO: Update fields of existingEntity with values from entity
            return countryServiceRepository.save(existingEntity);
        } else {
            throw new RuntimeException("Entity not found with id " + id);
        }
    }

    @Override
    public boolean delete(String id) {
        if (countryServiceRepository.existsById((id))) {
            countryServiceRepository.deleteById((id));
            return true;
        }
        return false;
    }

    // Batch creation of countries
    @Transactional
    public List<Neo4jCountry> createAll(List<Neo4jCountry> countries) {
        try {
            return countryServiceRepository.saveAll(countries);
        } catch (DataAccessException e) {
            throw new RuntimeException("Error creating countries", e);
        }
    }
}
