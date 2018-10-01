package ru.jajawebinar.basejava.storage;

import ru.jajawebinar.basejava.model.Resume;

/**
 * Interface for ru.jajawebinar.basejava.storage.ArrayStorage
 */
public interface Storage {

    public void clear();

    public void save(Resume resume);

    public Resume get(String uuid);

    public void delete(String uuid);

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll();

    public int size();

    public void update(Resume resume);

}