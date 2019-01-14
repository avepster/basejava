package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.storage.Serialize.SerializeStrategyBinary;

public class ObjectStreamBinaryPathStorageTest extends AbstractStorageTest {
    public ObjectStreamBinaryPathStorageTest() {
        super(new PathStorage(STORAGE_DIR, new SerializeStrategyBinary()));
    }
}
