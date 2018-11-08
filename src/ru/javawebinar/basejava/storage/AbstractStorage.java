package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

/**
 * Abstract storage for Resumes
 */
public abstract class AbstractStorage implements Storage {

    public void save(Resume resume) {
        String uuid = resume.getUuid();
        Object index = getIndex(uuid);
        if (isExist(index)) {
            throw new ExistStorageException(uuid);
        } else {
            saveOne(index, resume);
        }
    }

    public void update(Resume resume) {
        String uuid = resume.getUuid();
        Object index = getIndex(uuid);
        if (!isExist(index)) {
            throw new NotExistStorageException(uuid);
        }
        updateOne(index, resume);
    }

    public void delete(String uuid) {
        Object index = getIndex(uuid);
        if (!isExist(index)) {
            throw new NotExistStorageException(uuid);
        } else {
            deleteOne(index);
        }
    }

    public abstract void clear();

    public Resume get(String uuid) {
        if (!isExist(getIndex(uuid))) {
            throw new NotExistStorageException(uuid);
        }
        return getOne(getIndex(uuid));
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public abstract Resume[] getAll();

    protected abstract Object getIndex(String uuid);

    protected abstract void saveOne(Object index, Resume resume);

    protected abstract void updateOne(Object index, Resume resume);

    protected abstract void deleteOne(Object index);

    protected abstract Resume getOne(Object index);

    protected abstract boolean isExist(Object index);
}