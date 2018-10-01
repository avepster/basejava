package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

/**
 * Interface for ru.javawebinar.basejava.storage.ArrayStorage
 */
public interface Storage {

    void clear();

    void save(Resume resume);

    Resume get(String uuid);

    void delete(String uuid);

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll();

    int size();

    void update(Resume resume);
}