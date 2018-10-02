package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    @Override
    protected void saveOne(Resume resume, int index) {
        int realIndex = -1 * index - 1;
        if ((size != 0) || (realIndex != size)) {
            System.arraycopy(storage, realIndex, storage, realIndex + 1, size - realIndex);
        }
        storage[realIndex] = resume;
        size++;
    }

    @Override
    protected void deleteOne(int index) {
        if (index < size - 1) {
            System.arraycopy(storage, index + 1, storage, index, size - index - 1);
        }
        storage[size - 1] = null;
        size--;
    }
}