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
    protected int getIndex(String uuid) {
        if (storage.containsKey(uuid)) {
            return 0;  // not real index, just uuid exist
        }
        return -1;
    }

    @Override
    protected void saveOne(Resume resume) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected void updateOne(Resume resume) {
        storage.replace(resume.getUuid(), resume);
    }

    @Override
    protected void deleteOne(String uuid) {
        storage.remove(uuid);
    }

    @Override
    protected void deleteAll() {
        storage.clear();
    }

    @Override
    protected Resume getOne(String uuid) {
        return storage.get(uuid);
    }
}