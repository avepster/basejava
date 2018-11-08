package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * List based storage for Resumes
 */
public class ListStorage extends AbstractStorage {
    protected List<Resume> storage = new ArrayList<>();

    @Override
    public int size() {
        return storage.size();
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    @Override
    public Resume[] getAll() {
        return storage.toArray(new Resume[0]);
    }

    @Override
    protected Integer getIndex(String uuid) {
        for (int i = 0; i < size(); i++) {
            if (Objects.equals(storage.get(i).getUuid(), uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void saveOne(Object index, Resume resume) {
        storage.add(resume);
    }

    @Override
    protected void updateOne(Object index, Resume resume) {
        storage.set((int) index, resume);
    }

    @Override
    protected void deleteOne(Object index) {
        storage.remove((int) index);
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    protected Resume getOne(Object index) {
        return storage.get((int) index);
    }

    @Override
    protected boolean isExist(Object index) {
        return ((int) index >= 0);
    }
}