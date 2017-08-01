package ${package}.services;

import ${package}.interfaces.SampleDao;
import ${package}.interfaces.SampleService;
import ${package}.models.SampleModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.NoSuchElementException;

/**
 * A sample service
 */
@Service
public class SampleServiceImpl implements SampleService {

    /**
     * A {@link SampleDao} used to access {@link SampleModel} data.
     */
    private final SampleDao sampleDao;

    @Autowired
    public SampleServiceImpl(SampleDao sampleDao) {
        this.sampleDao = sampleDao;
    }

    @Override
    public List<SampleModel> getAll() {
        return sampleDao.getAll();
    }

    @Override
    public Optional<SampleModel> getById(long id) {
        return sampleDao.getById(id);
    }

    @Override
    @Transactional
    public SampleModel create(String name) {
        return sampleDao.create(name);
    }

    @Override
    @Transactional
    public void update(long id, String name) {
        sampleDao.update(sampleDao.getById(id).orElseThrow(NoSuchElementException::new), name);
    }

    @Override
    @Transactional
    public void delete(long id) {
        sampleDao.delete(sampleDao.getById(id).orElseThrow(NoSuchElementException::new));
    }
}