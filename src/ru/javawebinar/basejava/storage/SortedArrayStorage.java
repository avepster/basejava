package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected Integer getIndex(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    @Override
    protected void saveOneReal(Object index, Resume resume) {
        int realIndex = -1 * (int) index - 1;
        System.arraycopy(storage, realIndex, storage, realIndex + 1, size - realIndex);
        storage[realIndex] = resume;
    }

    @Override
    protected void deleteOne(Object index) {
        System.arraycopy(storage, (int) index + 1, storage, (int) index, size - (int) index - 1);
        size--;
    }

    @Override
    protected boolean isExist(Object index) {
        if ((int) index >= 0) {
            return true;
        }
        return false;
    }
}