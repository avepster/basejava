package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Map based storage for Resumes
 */
public class MapUuidStorage extends AbstractStorage<String> {
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
    protected void doSave(String key, Resume resume) {
        storage.put(key, resume);
    }

    @Override
    protected void doUpdate(String key, Resume resume) {
        storage.replace(key, resume);
    }

    @Override
    protected void doDelete(String key) {
        storage.remove(key);
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    protected Resume getOne(String key) {
        return storage.get(key);
    }

    @Override
    protected boolean isExist(String key) {
        return storage.containsKey(key);
    }
}