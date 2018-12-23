package ru.javawebinar.basejava.storage;

import java.io.File;

public class FileStorageTest extends AbstractFileStorageTest {
    public FileStorageTest() {
        super(new FileStorage(new File("c:/temp/FileStorage/")));
    }
}
