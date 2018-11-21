package ru.javawebinar.basejava.storage;

/**
 * Map based storage for Resumes
 */
public class MapUuidStorage extends AbstractMapStorage {

    @Override
    protected String getKey(Object uuid) {
        return (String) uuid;
    }
}