package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.storage.Serialize.SerializeStrategyBinary;

import java.io.File;

public class ObjectStreamBinaryFileStorageTest extends AbstractStorageTest {
    public ObjectStreamBinaryFileStorageTest() {
        super(new FileStorage(new File(STORAGE_DIR), new SerializeStrategyBinary()));
    }
}
