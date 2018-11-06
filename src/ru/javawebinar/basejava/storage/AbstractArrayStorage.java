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
    protected void saveOne(Object index, Resume resume) {
        if (size == MAX_SIZE) {
            throw new StorageException(resume.getUuid(), "Storage overflow");
        }
        saveOneReal(index, resume);
        size++;
    }

    @Override
    protected void updateOne(Object index, Resume resume) {
        storage[(int) index] = resume;
    }

    @Override
    protected void deleteOne(Object index) {
        size--;
    }

    @Override
    protected void deleteAll() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    protected Resume getOne(Object index) {
        return storage[(int) index];
    }

    @Override
    protected boolean isExist(Object index) {
        if ((int) index >= 0) {
            return true;
        }
        return false;
    }

    protected abstract void saveOneReal(Object index, Resume resume);
}