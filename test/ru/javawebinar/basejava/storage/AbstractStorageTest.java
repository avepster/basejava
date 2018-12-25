package ru.javawebinar.basejava.storage;

import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.ResumeTestData;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static ru.javawebinar.basejava.storage.AbstractStorage.RESUME_COMPARATOR;

public class AbstractStorageTest {
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";
    private static final Resume RESUME_1; // = new Resume(UUID_1); non static block initialization
    private static final Resume RESUME_2;
    private static final Resume RESUME_3;
    private static final Resume RESUME_4;

    // Static block initialization (if some hard init)
    static {
        ResumeTestData rtd1 = new ResumeTestData(UUID_1, "Иванов Иван Иванович");
        ResumeTestData rtd2 = new ResumeTestData(UUID_2, "Сидоров Тимофей Николаевич");
        ResumeTestData rtd3 = new ResumeTestData(UUID_3, "Петров Петр Петрович");
        ResumeTestData rtd4 = new ResumeTestData(UUID_4, "Никифоров Андрей Андреевич");

        RESUME_1 = rtd1.resume;
        RESUME_2 = rtd2.resume;
        RESUME_3 = rtd3.resume;
        RESUME_4 = rtd4.resume;
    }

    protected Storage storage;

    protected AbstractStorageTest(Storage newStorage) {
        this.storage = newStorage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    public void save() {
        storage.save(RESUME_4);
        assertSize(4);
        assertGet(RESUME_4);
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(RESUME_1);
    }

    @Test
    public void update() {
        ResumeTestData rtd = new ResumeTestData(UUID_1, "Новиков Николай Николаевич");
        Resume resume = rtd.resume;
        storage.update(resume);
        assertGet(resume);
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(RESUME_4);
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(UUID_1);
        assertSize(2);
        storage.get(UUID_1);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete(UUID_4);
    }

    @Test
    public void clear() {
        storage.clear();
        assertSize(0);
    }

    @Test
    public void get() {
        assertGet(RESUME_1);
        assertGet(RESUME_2);
        assertGet(RESUME_3);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get(UUID_4);
    }

    @Test
    public void getAllSorted() {
        List<Resume> expectedResume = Arrays.asList(RESUME_1, RESUME_2, RESUME_3);
        expectedResume.sort(RESUME_COMPARATOR);
        assertEquals(expectedResume, storage.getAllSorted());
    }

    @Test
    public void size() {
        assertSize(3);
    }

    private void assertGet(Resume resume) {
        assertEquals(resume, storage.get(resume.getUuid()));
    }

    private void assertSize(int size) {
        assertEquals(size, storage.size());
    }
}