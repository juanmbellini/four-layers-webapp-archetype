package ${package}.persistence;

import ${package}.test_config.PersistenceTestConfig;
import ${package}.utils.PersistenceTestHelper;
import ${package}.interfaces.SampleDao;
import ${package}.models.SampleModel;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import org.junit.Test;
import org.junit.Assert;
import org.junit.Before;
import org.junit.After;
import org.junit.runner.RunWith;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;


/**
 * A sample test for a sample dao
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PersistenceTestConfig.class)
public class SampleDaoTest {

    // Add utils methods tests here...

    @Autowired
    private DataSource dataSource;
    @Autowired
    private SampleDao sampleDao;

    private JdbcTemplate jdbcTemplate;

    private EntityManager em;

    @Before
    public void setUp() {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        em = PersistenceTestHelper.getEntityManager(sampleDao);
        removeAllData();
    }

    @After
    public void removeAllData() {
        PersistenceTestHelper.removeAllData(jdbcTemplate);
    }

    @Test
    public void testGetAllWithFilledDatabase() {
        PersistenceTestHelper.addSomeSampleModels(jdbcTemplate);

        List<SampleModel> result = sampleDao.getAll();
        Assert.assertNotNull(result);
        Assert.assertFalse(result.isEmpty());

        List<String> names = result.stream().map(SampleModel::getName).collect(Collectors.toList());

        Assert.assertTrue(names.contains(PersistenceTestHelper.SAMPLE_MODEL_1));
        Assert.assertTrue(names.contains(PersistenceTestHelper.SAMPLE_MODEL_2));
        Assert.assertTrue(names.contains(PersistenceTestHelper.SAMPLE_MODEL_3));
    }

    @Test
    public void testGetAllWithNoObjectsInDatabase() {
        List<SampleModel> result = sampleDao.getAll();
        Assert.assertNotNull(result);
        Assert.assertTrue(result.isEmpty());
    }

    @Test
    public void testGetByIdWithExistingObject() {
        PersistenceTestHelper.addSomeSampleModels(jdbcTemplate);

        Optional<SampleModel> sampleModelOptional = sampleDao.getById(1);
        Assert.assertNotNull(sampleModelOptional);
        Assert.assertTrue(sampleModelOptional.isPresent());
        Assert.assertTrue(sampleModelOptional.get().getName().equals(PersistenceTestHelper.SAMPLE_MODEL_1));
    }

    @Test
    public void testGetByIdWithNonExistingObject() {
        Optional<SampleModel> sampleModelOptional = sampleDao.getById(1);
        Assert.assertNotNull(sampleModelOptional);
        Assert.assertFalse(sampleModelOptional.isPresent());
    }

    @Test
    @Transactional
    public void testCreateWithNoObjectsInDatabase() {
        final String name = "The new Sample Model";
        final SampleModel sampleModelFromCreate = sampleDao.create(name);
        Assert.assertNotNull(sampleModelFromCreate);
        em.flush();

        final SampleModel sampleModelFromHelper = PersistenceTestHelper
                .getSampleModelById(sampleModelFromCreate.getId(), jdbcTemplate);
        Assert.assertNotNull(sampleModelFromHelper);
        Assert.assertEquals(sampleModelFromCreate.getId(), sampleModelFromHelper.getId());
        Assert.assertEquals(sampleModelFromCreate.getName(), sampleModelFromHelper.getName());
    }

    @Test
    @Transactional
    public void testCreateWithObjectsInDatabase() {
        PersistenceTestHelper.addSomeSampleModels(jdbcTemplate);
        final String name = "The new Sample Model";
        final SampleModel sampleModelFromCreate = sampleDao.create(name);
        Assert.assertNotNull(sampleModelFromCreate);
        em.flush();

        final SampleModel sampleModelFromHelper = PersistenceTestHelper
                .getSampleModelById(sampleModelFromCreate.getId(), jdbcTemplate);
        Assert.assertNotNull(sampleModelFromHelper);
        Assert.assertEquals(sampleModelFromCreate.getId(), sampleModelFromHelper.getId());
        Assert.assertEquals(sampleModelFromCreate.getName(), sampleModelFromHelper.getName());
    }

    @Test
    @Transactional
    public void testUpdateExistingEntity() {
        PersistenceTestHelper.addSomeSampleModels(jdbcTemplate);
        final SampleModel sampleModel = PersistenceTestHelper.getSampleModelById(1, jdbcTemplate);
        final String newName = "This is the new name";
        sampleDao.update(sampleModel, newName);
        em.flush(); // Saves changes in database

        final SampleModel updatedSampleModel = PersistenceTestHelper.getSampleModelById(1, jdbcTemplate);

        Assert.assertNotNull(updatedSampleModel);
        Assert.assertEquals(sampleModel.getId(), updatedSampleModel.getId());
        Assert.assertEquals(newName, updatedSampleModel.getName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateNullSampleModel() {
        sampleDao.update(null, "Some string");
    }

    @Test
    @Transactional
    public void testDeleteExistingEntity() {
        PersistenceTestHelper.addSomeSampleModels(jdbcTemplate);
        final SampleModel sampleModel = PersistenceTestHelper.getSampleModelById(1, jdbcTemplate);
        sampleDao.delete(em.merge(sampleModel));
        em.flush(); // Saves changes in database

        final SampleModel deletedSampleModel = PersistenceTestHelper.getSampleModelById(1, jdbcTemplate);

        Assert.assertNull(deletedSampleModel);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeleteNullSampleModel() {
        sampleDao.delete(null);
    }
}