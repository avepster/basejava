package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected Integer getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void saveOneReal(Object index, Resume resume) {
        storage[size] = resume;
    }

    @Override
    protected void deleteOneReal(Object index) {
        storage[(int) index] = storage[size - 1];
    }

}