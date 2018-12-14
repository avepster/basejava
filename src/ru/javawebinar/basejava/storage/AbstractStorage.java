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

    static final Comparator<Resume> RESUME_COMPARATOR = (o1, o2) -> {
        if (o1.getFullName() != o2.getFullName()) {
            return o1.getFullName().compareTo(o2.getFullName());
        }
        return o1.getUuid().compareTo(o2.getUuid());
    };

    protected abstract SK getKey(String uuid);

    protected abstract void doSave(SK key, Resume resume);

    protected abstract void doUpdate(SK key, Resume resume);

    protected abstract void doDelete(SK key);

    protected abstract Resume getOne(SK key);

    protected abstract List<Resume> getAll();

    protected abstract boolean isExist(SK key);

    public abstract void clear();

    public void save(Resume resume) {
        LOG.info("Save " + resume);
        doSave(checkExistAndGetKey(resume.getUuid()), resume);
    }

    public void update(Resume resume) {
        LOG.info("Update " + resume);
        doUpdate(checkNotExistAndGetKey(resume.getUuid()), resume);
    }

    public void delete(String uuid) {
        LOG.info("Delete " + uuid);
        doDelete(checkNotExistAndGetKey(uuid));
    }

    public Resume get(String uuid) {
        LOG.info("Get " + uuid);
        return getOne(checkNotExistAndGetKey(uuid));
    }

    private SK checkExistAndGetKey(String uuid) {
        SK key = getKey(uuid);
        if (isExist(key)) {
            LOG.warning("Resume " + uuid + " already exist");
            throw new ExistStorageException(uuid);
        }
        return key;
    }

    private SK checkNotExistAndGetKey(String uuid) {
        SK key = getKey(uuid);
        if (!isExist(key)) {
            LOG.warning("Resume " + uuid + " not exist");
            throw new NotExistStorageException(uuid);
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