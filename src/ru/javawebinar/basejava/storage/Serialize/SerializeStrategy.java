package ru.javawebinar.basejava.storage.Serialize;

import ru.javawebinar.basejava.model.Resume;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface SerializeStrategy {

    void doWrite(OutputStream os, Resume resume) throws IOException;

    Resume doRead(InputStream is) throws IOException;

}