package orsk.compli.controller.neo4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import orsk.compli.entities.neo4j.Neo4jCountry;
import orsk.compli.service.neo4j.CountryNeo4jService;

import java.util.List;

@RestController("neo4jCountryController")
@RequestMapping("/api/neo4j/countries")
public class CountryControllerNeo4jNeo4J extends AbstractCrudControllerNeo4j<Neo4jCountry, String> {

    private static final Logger logger = LoggerFactory.getLogger(CountryControllerNeo4jNeo4J.class);
    private final CountryNeo4jService countryService;

    public CountryControllerNeo4jNeo4J(CountryNeo4jService countryService) {
        this.countryService = countryService;
    }

    @Override
    protected CountryNeo4jService getService() {
        return countryService;
    }

    // Batch create endpoint
    @PostMapping("/batch")
    public ResponseEntity<List<Neo4jCountry>> createBatch(@RequestBody List<Neo4jCountry> countries) {
        try {
            List<Neo4jCountry> createdCountries = countryService.createAll(countries);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdCountries);
        } catch (DataAccessException e) {
            logger.error("Error creating countries: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error creating countries");
        }
    }
}
