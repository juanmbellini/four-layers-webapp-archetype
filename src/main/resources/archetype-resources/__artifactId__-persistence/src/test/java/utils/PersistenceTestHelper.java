package ${package}.utils;

import ${package}.models.SampleModel;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.persistence.EntityManager;
import java.lang.reflect.Field;

/**
 * This class includes methods to help the execution of the persistence module tests.
 */
public class PersistenceTestHelper {

    public static final String SAMPLE_MODEL_1 = "Alice";
    public static final String SAMPLE_MODEL_2 = "Bob";
    public static final String SAMPLE_MODEL_3 = "John";

    /**
     * Returns the {@link EntityManager} from the given {@code hibernateDaoObject},
     * or {@code null} in case no entity manager could be found in the given dao.
     *
     * @param hibernateDaoObject The object from which the entity manager will be taken
     * @return The entity manager.
     * @implNote This method expects that the given {@code hibernateDaoObject}
     * has an {@link EntityManager} field called "em"
     */
    public static EntityManager getEntityManager(Object hibernateDaoObject) {
        try {
            final Field field = hibernateDaoObject.getClass().getDeclaredField("em");
            field.setAccessible(true);
            final EntityManager em = (EntityManager) field.get(hibernateDaoObject);
            field.setAccessible(false);
            return em;
        } catch (NoSuchFieldException | IllegalAccessException e) {
            return null;
        }
    }

    /**
     * Returns a {@link SampleModel} by its id, using a {@link JdbcTemplate}.
     *
     * @param id           The {@link SampleModel}'s id
     * @param jdbcTemplate The jdbc template.
     * @return The {@link SampleModel} with the given id if it exists, or {@code null} otherwise.
     */
    public static SampleModel getSampleModelById(long id, JdbcTemplate jdbcTemplate) {
        final SampleModel[] sampleModels = new SampleModel[1];
        sampleModels[0] = null;
        jdbcTemplate.query("SELECT * from Sample_Models WHERE id = " + id, rs -> {
            final SampleModel sampleModel = new SampleModel(rs.getString("name"));
            try {
                final Field field = sampleModel.getClass().getDeclaredField("id");
                field.setAccessible(true);
                field.set(sampleModel, rs.getLong("id"));
                field.setAccessible(false);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new RuntimeException();
            }
            sampleModels[0] = sampleModel;
        });
        return sampleModels[0];
    }

    /**
     * Removes all data from the database.
     *
     * @param jdbcTemplate The jdbc template.
     */
    public static void removeAllData(JdbcTemplate jdbcTemplate) {
        jdbcTemplate.execute("DELETE FROM Sample_Models");
    }

    /**
     * Add some {@link SampleModel}s into the database.
     *
     * @param jdbcTemplate The jdbc template.
     */
    public static void addSomeSampleModels(JdbcTemplate jdbcTemplate) {
        jdbcTemplate.execute(getInsertSampleModelString(1, SAMPLE_MODEL_1));
        jdbcTemplate.execute(getInsertSampleModelString(2, SAMPLE_MODEL_2));
        jdbcTemplate.execute(getInsertSampleModelString(3, SAMPLE_MODEL_3));
    }

    /**
     * Creates a query for inserting a {@link SampleModel} into the database.
     *
     * @param id   The {@link SampleModel}'s id
     * @param name The {@link SampleModel}'s name.
     */
    private static String getInsertSampleModelString(long id, String name) {
        return "INSERT INTO Sample_Models VALUES (" + id + ", '" + name + "')";
    }


}