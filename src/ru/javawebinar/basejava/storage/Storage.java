package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.List;

/**
 * Interface for ru.javawebinar.basejava.storage.ArrayStorage
 */
public interface Storage {

    void save(Resume resume);

    void update(Resume resume);

    void delete(String uuid);

    void clear();

    Resume get(String uuid);

    /**
     * @return List, contains only Resumes in storage (without null)
     */
    List<Resume> getAllSorted();

    int size();
}