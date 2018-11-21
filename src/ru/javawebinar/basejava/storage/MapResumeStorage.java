package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Objects;

/**
 * Map based storage for Resumes
 */
public class MapResumeStorage extends AbstractMapStorage {
    @Override
    protected Resume getKey(Object uuid) {
        for (int i = 0; i < size(); i++) {
            if (Objects.equals(storage.get(i).getUuid(), uuid)) {
                return storage.get(i);
            }
        }
        return new Resume((String) uuid, "Anonymous");
    }
}