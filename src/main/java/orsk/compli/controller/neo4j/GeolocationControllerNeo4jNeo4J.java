package orsk.compli.controller.neo4j;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import orsk.compli.entities.neo4j.Neo4jGeolocation;
import orsk.compli.service.neo4j.CrudNeo4jService;
import orsk.compli.service.neo4j.GeolocationNeo4jService;

@RestController("neo4jGeolocationController")
@RequestMapping("/api/neo4j/geolocations")
public class GeolocationControllerNeo4jNeo4J extends AbstractCrudControllerNeo4j<Neo4jGeolocation, Long> {

    private final GeolocationNeo4jService geolocationService;

    public GeolocationControllerNeo4jNeo4J(GeolocationNeo4jService geolocationService) {
        this.geolocationService = geolocationService;
    }

    @Override
    protected CrudNeo4jService<Neo4jGeolocation, Long> getService() {
        return geolocationService;
    }
}
