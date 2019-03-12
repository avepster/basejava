package ru.javawebinar.basejava.storage;

public class SQLStorageTest extends AbstractStorageTest {

    public SQLStorageTest() {
        super(new SQLStorage("jdbc:postgresql://localhost:5433/resumes", "postgres", "Maxima99"));
    }
}
