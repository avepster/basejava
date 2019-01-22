package ru.javawebinar.basejava.storage.serialize;

import java.io.IOException;

@FunctionalInterface
public interface MapReader {
    void getMap() throws IOException;
}
