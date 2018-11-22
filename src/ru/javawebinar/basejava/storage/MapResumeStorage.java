package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

/**
 * Map based storage for Resumes
 */
public class MapResumeStorage extends AbstractMapStorage {
    @Override
    protected Resume getKey(Object uuid) {
        Resume key = storage.get(uuid);
        if (key != null) {
            return key;
        }
        return new Resume((String) uuid, "Anonymous");
    }
}