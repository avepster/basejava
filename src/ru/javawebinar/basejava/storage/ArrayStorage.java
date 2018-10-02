package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void saveOne(Resume resume, int index) {
        storage[size] = resume;
        size++;
    }

    @Override
    protected void deleteOne(int index) {
        storage[index] = storage[size - 1];
        storage[size - 1] = null;
        size--;
    }
}