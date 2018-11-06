package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

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

    @Override
    protected String getIndex(String uuid) {
        if (storage.containsKey(uuid)) {
            return uuid;
        }
        return null;
    }

    @Override
    protected void saveOne(Object index, Resume resume) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected void updateOne(Object index, Resume resume) {
        storage.replace((String) index, resume);
    }

    @Override
    protected void deleteOne(Object index) {
        storage.remove(index);
    }

    @Override
    protected void deleteAll() {
        storage.clear();
    }

    @Override
    protected Resume getOne(Object index) {
        return storage.get(index);
    }

    @Override
    protected boolean isExist(Object index) {
        return (index != null);
    }
}