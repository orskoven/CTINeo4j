package orsk.compli.service.neo4j;

import java.util.List;
import java.util.Optional;

public interface CrudNeo4jService<T, ID> {
    T create(T entity);

    List<T> getAll();

    Optional<T> getById(ID id);

    T update(ID id, T entity);

    boolean delete(ID id);
}
