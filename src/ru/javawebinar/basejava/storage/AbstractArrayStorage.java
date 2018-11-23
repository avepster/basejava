package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.fill;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage {
    static final int MAX_SIZE = 10000;

    protected Resume[] storage = new Resume[MAX_SIZE];
    protected int size = 0;

    public int size() {
        return size;
    }

    @Override
    protected void doSave(Object index, Resume resume) {
        if (size == MAX_SIZE) {
            throw new StorageException(resume.getUuid(), "Storage overflow");
        }
        saveOne((int) index, resume);
        size++;
    }

    @Override
    protected void doUpdate(Object index, Resume resume) {
        storage[(int) index] = resume;
    }

    @Override
    protected void doDelete(Object index) {
        deleteOne((int) index);
        size--;
    }

    @Override
    public void clear() {
        fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    protected Resume getOne(Object index) {
        return storage[(int) index];
    }

    @Override
    protected List<Resume> getAll() {
        return new ArrayList<>(Arrays.asList(Arrays.copyOfRange(storage, 0, size - 1)));
    }

    @Override
    protected boolean isExist(Object index) {
        return ((int) index >= 0);
    }

    protected abstract Integer getKey(String uuid);

    protected abstract void saveOne(int index, Resume resume);

    protected abstract void deleteOne(int index);
}