package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Map based storage for Resumes
 */
public class MapStorage extends AbstractStorage {
    protected Map<String, Resume> storage = new TreeMap<>();

    @Override
    public int size() {
        return storage.size();
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    @Override
    public Resume[] getAll() {
        return storage.values().toArray(new Resume[0]);
    }

    /**
     * @return List, contains only Resumes in storage (without null)
     */
    @Override
    public List<Resume> getAllSorted() {
        return new ArrayList<Resume>(storage.values());
    }

    @Override
    protected String getKey(String uuid) {
        return uuid;
    }

    @Override
    protected void doSave(Object key, Resume resume) {
        storage.put(resume.getUuid(), resume);
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