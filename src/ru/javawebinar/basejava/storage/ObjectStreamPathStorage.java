package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.storage.Serialize.SerializeStrategy;

public class ObjectStreamPathStorage extends PathStorage {

    protected ObjectStreamPathStorage(String directory, SerializeStrategy strategy) {
        super(directory, strategy);
    }

}
