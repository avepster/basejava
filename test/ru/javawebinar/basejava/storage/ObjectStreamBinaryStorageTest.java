package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.storage.Serialize.SerializeStrategy;
import ru.javawebinar.basejava.storage.Serialize.SerializeStrategyBinary;

import java.io.File;

public class ObjectStreamBinaryStorageTest extends AbstractStorageTest {
    private static SerializeStrategy strategy = new SerializeStrategyBinary();

    public ObjectStreamBinaryStorageTest() {
        super(new ObjectStreamBinaryStorage(new File(STORAGE_DIR), strategy));
    }
}
