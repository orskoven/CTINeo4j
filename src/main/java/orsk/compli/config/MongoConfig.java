package orsk.compli.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableMongoRepositories(
        basePackages = "orsk.compli.repository.mongo"
)
public class MongoConfig {
    // Define MongoDB-specific beans here
}