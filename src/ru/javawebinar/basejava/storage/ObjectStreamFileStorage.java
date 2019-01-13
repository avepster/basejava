package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.storage.Serialize.SerializeStrategy;

import java.io.File;

public class ObjectStreamFileStorage extends FileStorage {

    protected ObjectStreamFileStorage(File directory, SerializeStrategy strategy) {
        super(directory, strategy);
    }

}
