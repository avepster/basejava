package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.storage.Serialize.SerializeStrategy;
import ru.javawebinar.basejava.storage.Serialize.SerializeStrategyBinary;

public class ObjectStreamBinaryPathStorageTest extends AbstractStorageTest {
    private static SerializeStrategy strategy = new SerializeStrategyBinary();

    public ObjectStreamBinaryPathStorageTest() {
        super(new ObjectStreamPathStorage(STORAGE_DIR, strategy));
    }
}
