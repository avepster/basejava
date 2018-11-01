package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int MAX_SIZE = 10000;

    protected Resume[] storage = new Resume[MAX_SIZE];
    protected int size = 0;

    public int size() {
        return size;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    @Override
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    @Override
    protected void saveOne(Resume resume) {
        if (size == MAX_SIZE) {
            throw new StorageException(resume.getUuid(), "Storage overflow");
        }
        saveOneReal(resume);
        size++;
    }

    @Override
    protected void updateOne(Resume resume) {
        storage[getIndex(resume.getUuid())] = resume;
    }

    @Override
    protected void deleteOne(String uuid) {
        size--;
    }

    @Override
    protected void deleteAll() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    protected Resume getOne(String uuid) {
        return storage[getIndex(uuid)];
    }

    protected abstract void saveOneReal(Resume resume);
}