package ${package}.interfaces;

import ${package}.models.SampleModel;

import java.util.List;
import java.util.Optional;

/**
 * A sample dao interface to manipulate {@link SampleModel} data.
 */
public interface SampleDao {

    /**
     * Gets all persisted {@link SampleModel}s.
     *
     * @return A {@link List} of all persisted {@link SampleModel}s.
     */
    List<SampleModel> getAll();

    /**
     * Gets a {@link SampleModel} by its id.
     *
     * @param id The {@link SampleModel}'s id.
     * @return The {@link SampleModel} with the given {@code id}, wrapped in an {@link Optional}.
     */
    Optional<SampleModel> getById(long id);

    /**
     * Creates a new {@link SampleModel}.
     *
     * @param name The name of the {@link SampleModel}.
     * @return The new {@link SampleModel}.
     */
    SampleModel create(String name);

    /**
     * Updates the given {@link SampleModel}.
     *
     * @param sampleModel The {@link SampleModel} to be modified.
     * @param name The new name for the given {@link SampleModel}
     */
    void update(SampleModel sampleModel, String name);

    /**
     * Deletes the given {@link SampleModel}.
     *
     * @param sampleModel The {@link SampleModel} to be removed.
     */
    void delete(SampleModel sampleModel);
}