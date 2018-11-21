package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.*;

public abstract class AbstractMapStorage extends AbstractStorage {
    protected Map<Object, Resume> storage = new TreeMap<>();

    protected abstract Object getKey(Object uuid);

    @Override
    public int size() {
        return storage.size();
    }

    /**
     * @return List, contains only Resumes in storage (without null)
     */
    @Override
    public List<Resume> getAllSorted() {
        List<Resume> sortedList = new ArrayList<Resume>(storage.values());
        sortedList.sort(RESUME_COMPARATOR);
        return sortedList;
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
