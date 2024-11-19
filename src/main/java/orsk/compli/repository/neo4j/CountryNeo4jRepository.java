package orsk.compli.repository.neo4j;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;
import orsk.compli.entities.neo4j.Neo4jCountry;

import java.util.List;

@Repository("neo4jCountryRepository")
public interface CountryNeo4jRepository extends Neo4jRepository<Neo4jCountry, String> {

    @Query("MATCH (c:Country) RETURN c.name AS name")
    List<String> findAllCountryNames();
}