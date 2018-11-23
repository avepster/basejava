package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Comparator;
import java.util.List;

/**
 * Abstract storage for Resumes
 */
public abstract class AbstractStorage implements Storage {

    static final Comparator<Resume> RESUME_COMPARATOR = (o1, o2) -> {
        if (o1.getFullName() != o2.getFullName()) {
            return o1.getFullName().compareTo(o2.getFullName());
        }
        return o1.getUuid().compareTo(o2.getUuid());
    };

    public void save(Resume resume) {
        doSave(checkExistAndGetKey(resume.getUuid()), resume);
    }

    public void update(Resume resume) {
        doUpdate(checkNotExistAndGetKey(resume.getUuid()), resume);
    }

    public void delete(String uuid) {
        doDelete(checkNotExistAndGetKey(uuid));
    }

    public abstract void clear();

    public Resume get(String uuid) {
        return getOne(checkNotExistAndGetKey(uuid));
    }

    private Object checkExistAndGetKey(String uuid) {
        Object key = getKey(uuid);
        if (isExist(key)) {
            throw new ExistStorageException(uuid);
        }
        return key;
    }

    private Object checkNotExistAndGetKey(String uuid) {
        Object key = getKey(uuid);
        if (!isExist(key)) {
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

    protected abstract Object getKey(String uuid);

    protected abstract void doSave(Object key, Resume resume);

    protected abstract void doUpdate(Object key, Resume resume);

    protected abstract void doDelete(Object key);

    protected abstract Resume getOne(Object key);

    protected abstract List<Resume> getAll();

    protected abstract boolean isExist(Object key);
}