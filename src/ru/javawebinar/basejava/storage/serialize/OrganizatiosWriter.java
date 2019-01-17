package ru.javawebinar.basejava.storage.serialize;

import ru.javawebinar.basejava.model.Organization;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

public class OrganizatiosWriter implements CollectionWriter {
    @Override
    public void write(Collection collection, DataOutputStream dos) throws IOException {
        Organization org;
        int sizeOrgs = collection.size();
        dos.writeInt(sizeOrgs);
        for (int i = 0; i < sizeOrgs; i++) {
            org = ((List<Organization>) collection).get(i);
            dos.writeUTF(org.getHomePage().getName());
            if (org.getHomePage().getUrl() == null) {
                dos.writeUTF("");
            } else {
                dos.writeUTF(org.getHomePage().getUrl());
            }
            writePositions(org.getPositions(), dos, new PositionWriter());
        }
    }

    void writePositions(List list, DataOutputStream dos, CollectionWriter c) throws IOException {
        c.write(list, dos);
    }
}
