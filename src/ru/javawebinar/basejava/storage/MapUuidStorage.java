package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Map based storage for Resumes
 */
public class MapUuidStorage extends AbstractStorage {
    protected Map<String, Resume> storage = new TreeMap<>();

    @Override
    protected String getKey(String uuid) {
        return uuid;
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    public List<Resume> getAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    protected void doSave(Object key, Resume resume) {
        storage.put((String) key, resume);
    }

    @Override
    protected void doUpdate(Object key, Resume resume) {
        storage.replace((String) key, resume);
    }

    @Override
    protected void doDelete(Object key) {
        storage.remove(key);
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    protected Resume getOne(Object key) {
        return storage.get(key);
    }

    @Override
    protected boolean isExist(Object key) {
        return storage.containsKey(key);
    }
}