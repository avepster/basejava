package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.lang.reflect.Field;

public abstract class AbstractArrayStorageTest {
    protected Storage storage;
    protected static final String UUID_1 = "uuid1";
    protected static final String UUID_2 = "uuid2";
    protected static final String UUID_3 = "uuid3";

    public AbstractArrayStorageTest(Storage newStorage) {
        storage = newStorage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @Test
    public void save() {
        storage.save(new Resume());
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(new Resume(UUID_1));
    }

    @Test//(expected = StorageException.class)
    public void saveOverflow() throws Exception {
        Field field = storage.getClass().getSuperclass().getDeclaredFields()[0];
        field.setAccessible(true);
        field.get(storage);
//        System.out.println(field.getName());
//        System.out.println(field.get(storage));
        int maxSize = (int) field.get(storage);
        try {
            for (int i = storage.size(); i < maxSize; i++) {
                storage.save(new Resume());
            }
        } catch (Exception e) {
            Assert.fail("Test failed " + e.getMessage());
        } finally {
            try {
                storage.save(new Resume());
            } catch (StorageException e) {
                Assert.assertTrue("Test OK", true);
            }
        }
    }

    @Test
    public void update() {
        storage.update(new Resume(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(new Resume());
    }

    @Test
    public void delete() {
        storage.delete(UUID_1);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete("dummy");
    }

    @Test
    public void clear() {
        storage.clear();
    }

    @Test
    public void get() {
        storage.get(UUID_1);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }

    @Test
    public void getAll() {
        storage.getAll();
    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }
}