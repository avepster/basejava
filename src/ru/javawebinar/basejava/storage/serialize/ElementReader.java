package ru.javawebinar.basejava.storage.serialize;

import java.io.IOException;

@FunctionalInterface
public interface ElementReader<T> {
    T get() throws IOException;
}
