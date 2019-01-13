package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.storage.Serialize.SerializeStrategy;
import ru.javawebinar.basejava.storage.Serialize.SerializeStrategyBinary;

import java.io.File;

public class ObjectStreamBinaryFileStorageTest extends AbstractStorageTest {
    private static SerializeStrategy strategy = new SerializeStrategyBinary();

    public ObjectStreamBinaryFileStorageTest() {
        super(new ObjectStreamFileStorage(new File(STORAGE_DIR), strategy));
    }
}
