package orsk.compli.controller.neo4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import orsk.compli.service.neo4j.CrudNeo4jService;

import java.util.List;

public abstract class AbstractCrudControllerNeo4j<T, ID> {

    private static final Logger logger = LoggerFactory.getLogger(AbstractCrudControllerNeo4j.class);

    /**
     * Factory method to provide the appropriate service for each controller subclass.
     *
     * @return CrudMongoService instance for the entity type
     */
    protected abstract CrudNeo4jService<T, ID> getService();

    /**
     * Creates a new entity.
     *
     * @param entity The entity to create
     * @return The created entity wrapped in ResponseEntity
     */
    @PostMapping
    public ResponseEntity<T> create(@RequestBody T entity) {
        try {
            T created = getService().create(entity);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (DataAccessException e) {
            logger.error("Error creating entity: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error creating entity");
        }
    }

    /**
     * Retrieves all entities.
     *
     * @return List of entities wrapped in ResponseEntity
     */
    @GetMapping
    public ResponseEntity<List<T>> getAll() {
        try {
            List<T> entities = getService().getAll();
            if (entities.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(entities);
        } catch (DataAccessException e) {
            logger.error("Error retrieving entities: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error retrieving entities");
        }
    }

    /**
     * Retrieves an entity by ID.
     *
     * @param id The ID of the entity
     * @return The entity if found, or a 404 status if not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<T> getById(@PathVariable ID id) {
        try {
            return getService().getById(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (DataAccessException e) {
            logger.error("Error retrieving entity by ID {}: {}", id, e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error retrieving entity");
        }
    }

    /**
     * Updates an existing entity by ID.
     *
     * @param id     The ID of the entity to update
     * @param entity The updated entity data
     * @return The updated entity if successful
     */
    @PutMapping("/{id}")
    public ResponseEntity<T> update(@PathVariable ID id, @RequestBody T entity) {
        try {
            T updated = getService().update(id, entity);
            return ResponseEntity.ok(updated);
        } catch (DataAccessException e) {
            logger.error("Error updating entity with ID {}: {}", id, e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error updating entity");
        }
    }

    /**
     * Deletes an entity by ID.
     *
     * @param id The ID of the entity to delete
     * @return A 204 status if successful, or a 404 if the entity was not found
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable ID id) {
        try {
            boolean deleted = getService().delete(id);
            return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
        } catch (DataAccessException e) {
            logger.error("Error deleting entity with ID {}: {}", id, e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error deleting entity");
        }
    }
}