package ${package}.interfaces;

import ${package}.models.SampleModel;

import java.util.List;

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
     * @return The {@link SampleModel} with the given {@code id}.
     */
    SampleModel getById(long id);

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
     * @param sampleModel The already modified {@link SampleModel}.
     */
    void update(SampleModel sampleModel);

    /**
     * Deletes the given {@link SampleModel}.
     *
     * @param sampleModel The {@link SampleModel} to be removed.
     */
    void delete(SampleModel sampleModel);
}