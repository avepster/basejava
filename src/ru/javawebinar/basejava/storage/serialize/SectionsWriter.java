package ru.javawebinar.basejava.storage.serialize;

import ru.javawebinar.basejava.model.AbstractSection;
import ru.javawebinar.basejava.model.SectionType;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class SectionsWriter implements CollectionWriter {
    @Override
    public void write(Collection collection, DataOutputStream dos) throws IOException {
        dos.writeInt(collection.size());
        for (Map.Entry<SectionType, AbstractSection> entry : (Set<Map.Entry<SectionType, AbstractSection>>) collection) {
            dos.writeUTF(entry.getKey().name());
            dos.writeUTF(String.valueOf(entry.getValue()));
        }
    }
}
