package ${package}.interfaces;

import ${package}.models.SampleModel;

import java.util.List;
import java.util.Optional;

/**
 * A sample service interface to expose services related to {@link SampleModel} objects.
 */
public interface SampleService {

    /**
     * Gets all stored {@link SampleModel}s.
     *
     * @return A {@link List} of all stored {@link SampleModel}s.
     */
    List<SampleModel> getAll();

    /**
     * Gets a {@link SampleModel} by its id.
     *
     * @param id The {@link SampleModel}'s id.
     * @return The {@link SampleModel} with the given {@code id}.
     */
    Optional<SampleModel> getById(long id);

    /**
     * Creates a new {@link SampleModel}.
     *
     * @param name The name of the {@link SampleModel}
     * @return The new {@link SampleModel}.
     */
    SampleModel create(String name);

    /**
     * Updates the {@link SampleModel} with the given {@code id}.
     *
     * @param id   The id of the {@link SampleModel} to be updated.
     * @param name The new name of the {@link SampleModel}.
     */
    void update(long id, String name);

    /**
     * Deletes the given {@link SampleModel}.
     *
     * @param id The id of the {@link SampleModel} to be removed.
     */
    void delete(long id);
}