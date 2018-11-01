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
    protected void saveOneReal(Resume resume) {
        storage[size] = resume;
    }

    @Override
    protected void deleteOne(String uuid) {
        storage[getIndex(uuid)] = storage[size - 1];
        size--;
    }

}