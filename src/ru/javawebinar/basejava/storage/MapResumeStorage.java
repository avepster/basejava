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
    protected Map<String, Resume> storage = new HashMap<>();

    @Override
    protected Resume getKey(String uuid) {
        return storage.get(uuid);
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
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected void doUpdate(Object key, Resume resume) {
        storage.replace(resume.getUuid(), resume);
    }

    @Override
    protected void doDelete(Object key) {
        storage.remove(((Resume) key).getUuid());
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    protected Resume getOne(Object key) {
        return (Resume) key;
    }

    @Override
    protected boolean isExist(Object key) {
        return key != null;
    }
}