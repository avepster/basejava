package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

/**
 * Abstract storage for Resumes
 */
public abstract class AbstractStorage<SK> implements Storage {
    private static final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());

    static final Comparator<Resume> RESUME_COMPARATOR = Comparator.comparing(Resume::getFullName).thenComparing(Resume::getUuid);

    protected abstract SK getSearchKey(String uuid);

    protected abstract void doSave(SK searchKey, Resume resume);

    protected abstract void doUpdate(SK searchKey, Resume resume);

    protected abstract void doDelete(SK searchKey);

    protected abstract Resume doGet(SK searchKey);

    protected abstract List<Resume> getAll();

    protected abstract boolean isExist(SK searchKey);

    public abstract void clear();

    public void save(Resume resume) {
        LOG.info("Save " + resume);
        doSave(getNotExistedSearchKey(resume.getUuid()), resume);
    }

    public void update(Resume resume) {
        LOG.info("Update " + resume);
        doUpdate(getExistedSearchKey(resume.getUuid()), resume);
    }

    public void delete(String uuid) {
        LOG.info("Delete " + uuid);
        doDelete(getExistedSearchKey(uuid));
    }

    public Resume get(String uuid) {
        LOG.info("Get " + uuid);
        return doGet(getExistedSearchKey(uuid));
    }

    private SK getExistedSearchKey(String uuid) {
        SK key = getSearchKey(uuid);
        if (!isExist(key)) {
            LOG.warning("Resume " + uuid + " not exist");
            throw new NotExistStorageException(uuid);
        }
        return key;
    }

    private SK getNotExistedSearchKey(String uuid) {
        SK key = getSearchKey(uuid);
        if (isExist(key)) {
            LOG.warning("Resume " + uuid + " already exist");
            throw new ExistStorageException(uuid);
        }
        return key;
    }

    /**
     * @return sorted List, contains only Resumes in storage (without null)
     */
    public List<Resume> getAllSorted() {
        List<Resume> sortedList = getAll();
        sortedList.sort(RESUME_COMPARATOR);
        return sortedList;
    }
}