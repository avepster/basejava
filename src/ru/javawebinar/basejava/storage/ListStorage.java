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

    @Override
    public List<Resume> getAll() {
        return new ArrayList<>(storage);
    }

    @Override
    protected Integer getKey(String uuid) {
        for (int i = 0; i < size(); i++) {
            if (Objects.equals(storage.get(i).getUuid(), uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void doSave(Object key, Resume resume) {
        storage.add(resume);
    }

    @Override
    protected void doUpdate(Object key, Resume resume) {
        storage.set((int) key, resume);
    }

    @Override
    protected void doDelete(Object key) {
        storage.remove((int) key);
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    protected Resume getOne(Object key) {
        return storage.get((int) key);
    }

    @Override
    protected boolean isExist(Object key) {
        return ((int) key >= 0);
    }
}