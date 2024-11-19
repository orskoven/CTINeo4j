package orsk.compli.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableNeo4jRepositories(
        basePackages = "orsk.compli.repository.neo4j",
        transactionManagerRef = "neo4jTransactionManager"
)

public class Neo4jConfig {
    // Configure Neo4j-specific beans here
}
