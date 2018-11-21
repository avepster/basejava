package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import org.junit.Test;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import static ru.javawebinar.basejava.storage.AbstractArrayStorage.MAX_SIZE;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest {

    protected AbstractArrayStorageTest(Storage newStorage) {
        super(newStorage);
    }

    @Test(expected = StorageException.class)
    public void saveOverflow() {
        try {
            for (int i = storage.size(); i < MAX_SIZE; i++) {
                storage.save(new Resume("Anonimous"));
            }
        } catch (StorageException e) {
            Assert.fail("Test failed " + e.getMessage());
        }
        storage.save(new Resume("Anonimous"));
    }
}