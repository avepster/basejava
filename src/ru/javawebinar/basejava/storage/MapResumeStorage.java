package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Map based storage for Resumes
 */
public class MapResumeStorage extends AbstractStorage {
    protected Map<Object, Resume> storage = new HashMap<>();

    @Override
    protected Resume getKey(String uuid) {
        Resume key = storage.get(uuid);
        if (key != null) {
            return key;
        }
        return new Resume(uuid, "Anonymous");
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
        storage.put(key, resume);
    }

    @Override
    protected void doUpdate(Object key, Resume resume) {
        storage.replace(key, resume);
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