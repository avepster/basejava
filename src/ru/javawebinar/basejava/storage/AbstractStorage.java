package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractStorage implements Storage {

    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index >= 0) {
            throw new ExistStorageException(resume.getUuid());
        } else {
            saveOne(resume);
        }
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index < 0) {
            throw new NotExistStorageException(resume.getUuid());
        }
        updateOne(resume);
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        } else {
            deleteOne(uuid);
        }
    }

    public void clear() {
        deleteAll();
    }

    public Resume get(String uuid) {
        if (getIndex(uuid) < 0) {
            throw new NotExistStorageException(uuid);
        }
        return getOne(uuid);
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public abstract Resume[] getAll();

    protected abstract int getIndex(String uuid);

    protected abstract void saveOne(Resume resume);

    protected abstract void updateOne(Resume resume);

    protected abstract void deleteOne(String uuid);

    protected abstract void deleteAll();

    protected abstract Resume getOne(String uuid);
}