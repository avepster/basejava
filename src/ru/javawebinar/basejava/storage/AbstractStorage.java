package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.List;

/**
 * Abstract storage for Resumes
 */
public abstract class AbstractStorage implements Storage {

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

    public Object checkExistAndGetKey(String uuid) {
        Object key = getKey(uuid);
        if (isExist(key)) {
            throw new ExistStorageException(uuid);
        }
        return key;
    }

    public Object checkNotExistAndGetKey(String uuid) {
        Object key = getKey(uuid);
        if (!isExist(key)) {
            throw new NotExistStorageException(uuid);
        }
        return key;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public abstract Resume[] getAll();

    public abstract List<Resume> getAllSorted();

    protected abstract Object getKey(String uuid);

    protected abstract void doSave(Object index, Resume resume);

    protected abstract void doUpdate(Object index, Resume resume);

    protected abstract void doDelete(Object index);

    protected abstract Resume getOne(Object index);

    protected abstract boolean isExist(Object index);
}