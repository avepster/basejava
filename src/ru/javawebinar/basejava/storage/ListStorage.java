package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;

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
    protected int getIndex(String uuid) {
        for (int i = 0; i < size(); i++) {
            if (storage.get(i).getUuid() == uuid) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void saveOne(Resume resume) {
        storage.add(resume);
    }

    @Override
    protected void updateOne(Resume resume) {
        storage.set(storage.indexOf(resume), resume);
    }

    @Override
    protected void deleteOne(String uuid) {
        storage.remove(getOne(uuid));
    }

    @Override
    protected void deleteAll() {
        storage.clear();
    }

    @Override
    protected Resume getOne(String uuid) {
        return storage.get(getIndex(uuid));
    }
}