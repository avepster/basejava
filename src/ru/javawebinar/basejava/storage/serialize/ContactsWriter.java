package ru.javawebinar.basejava.storage.serialize;

import ru.javawebinar.basejava.model.ContactType;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class ContactsWriter implements CollectionWriter {
    @Override
    public void write(Collection collection, DataOutputStream dos) throws IOException {
        dos.writeInt(collection.size());
        for (Map.Entry<ContactType, String> entry : (Set<Map.Entry<ContactType, String>>) collection) {
            dos.writeUTF(entry.getKey().name());
            dos.writeUTF(entry.getValue());
        }
    }
}
