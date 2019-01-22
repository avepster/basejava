package ru.javawebinar.basejava.storage;

import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.ResumeTestData;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.*;

import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static ru.javawebinar.basejava.storage.AbstractStorage.RESUME_COMPARATOR;

public class AbstractStorageTest {
    protected static final String STORAGE_DIR = "./storage";

    protected Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";
    private static final Resume R1; // = new Resume(UUID_1); non static block initialization
    private static final Resume R2;
    private static final Resume R3;
    private static final Resume R4;

    // Static block initialization (if some hard init)
    static {
        ResumeTestData rtd1 = new ResumeTestData(UUID_1, "Иванов Иван Иванович");
        ResumeTestData rtd2 = new ResumeTestData(UUID_2, "Сидоров Тимофей Николаевич");
        ResumeTestData rtd3 = new ResumeTestData(UUID_3, "Петров Петр Петрович");
        ResumeTestData rtd4 = new ResumeTestData(UUID_4, "Никифоров Андрей Андреевич");

        R1 = rtd1.resume;
        R2 = rtd2.resume;
        R3 = rtd3.resume;
        R4 = rtd4.resume;

        R1.addContact(ContactType.EMAIL, "mail@ya.ru");
        R1.addContact(ContactType.PHONE, "11111");
        R1.addSection(SectionType.OBJECTIVE, new TextSection("Objective1"));
        R1.addSection(SectionType.PERSONAL, new TextSection("Personal1"));
        R1.addSection(SectionType.ACHIEVEMENT, new ListSection("Achievement1", "Achievement2", "Achievement3"));
        R1.addSection(SectionType.QUALIFICATIONS, new ListSection("Java", "SQL", "JavaScript"));
        R1.addSection(SectionType.EXPERIENCE, new OrganizationSection(
                new Organization("Organization11", "http://organization11.ru",
                        new Organization.Position(2005, Month.JANUARY, "position1", "content1"),
                        new Organization.Position(2001, Month.MARCH, 2005, Month.JANUARY, "position2", "content2"))));
        R1.addSection(SectionType.EDUCATION, new OrganizationSection(
                new Organization("Institute", " ",
                        new Organization.Position(1996, Month.JANUARY, 2000, Month.DECEMBER, "aspirant", " "),
                        new Organization.Position(2001, Month.MARCH, 2005, Month.JANUARY, "student", "IT faculity")),
                new Organization("Organization12", "http://organization2.ru",
                        new Organization.Position(2003, Month.JANUARY, 2004, Month.DECEMBER, "xxxx", " "))));

        R2.addContact(ContactType.SKYPE, "skype2");
        R2.addContact(ContactType.PHONE, "22222");
        R2.addSection(SectionType.EXPERIENCE, new OrganizationSection(
                new Organization("Organization2", "http://organization2.ru",
                        new Organization.Position(2015, Month.JANUARY, "position2", "content2"))));
    }

    protected AbstractStorageTest(Storage newStorage) {
        this.storage = newStorage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(R1);
        storage.save(R2);
        storage.save(R3);
    }

    @Test
    public void save() {
        storage.save(R4);
        assertSize(4);
        assertGet(R4);
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(R1);
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
        storage.update(R4);
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
        assertGet(R1);
        assertGet(R2);
        assertGet(R3);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get(UUID_4);
    }

    @Test
    public void getAllSorted() {
        List<Resume> expectedResume = Arrays.asList(R1, R2, R3);
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