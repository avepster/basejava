package ru.javawebinar.basejava.storage.serialize;

import ru.javawebinar.basejava.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class DataStreamSerializer implements StreamSerializer {

    void writeContacts(Collection collection, DataOutputStream dos, CollectionWriter c) throws IOException {
        c.write(collection, dos);
    }

    void writeList(List list, DataOutputStream dos, CollectionWriter c) throws IOException {
        c.write(list, dos);
    }

    private void organizationWriter(Collection collection, DataOutputStream dos, CollectionWriter c) throws IOException {
        c.write(collection, dos);
    }

    @Override
    public void doWrite(OutputStream os, Resume resume) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(resume.getUuid());
            dos.writeUTF(resume.getFullName());
            writeContacts(resume.getContacts().entrySet(), dos, new ContactsWriter());
            // TODO implements section
            Map<SectionType, AbstractSection> sections = resume.getSections();
            dos.writeInt(sections.size());
            for (Map.Entry<SectionType, AbstractSection> entry : sections.entrySet()) {
                String mapKey = entry.getKey().toString();
                switch (entry.getKey()) {
                    case PERSONAL:
                    case OBJECTIVE:
                        dos.writeUTF(mapKey);
                        dos.writeUTF(entry.getValue().toString());
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        dos.writeUTF(mapKey);
                        List list = ((ListSection) entry.getValue()).getList();
                        writeList(list, dos, new ListWriter());
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        dos.writeUTF(mapKey);
                        organizationWriter(((OrganizationSection) entry.getValue()).getOrganizationList(), dos, new OrganizatiosWriter());
                        break;
                }
            }
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            int size = dis.readInt();
            for (int i = 0; i < size; i++) {
                resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF());
            }
            size = dis.readInt();
            SectionType sectionType;
            for (int i = 0; i < size; i++) {
                sectionType = SectionType.valueOf(dis.readUTF());
                switch (sectionType) {
                    case PERSONAL:
                    case OBJECTIVE:
                        resume.addSection(sectionType, new TextSection(dis.readUTF()));
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        List list = new ArrayList<>();
                        int sizeList = dis.readInt();
                        for (int l = 0; l < sizeList; l++) {
                            list.add(dis.readUTF());
                        }
                        resume.addSection(sectionType, new ListSection(list));
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        List<Organization> listOrganizations = new ArrayList<>();
                        String name;
                        String url;
                        Link link;
                        int sizeOrgList = dis.readInt();
                        for (int j = 0; j < sizeOrgList; j++) {
                            name = dis.readUTF();
                            url = dis.readUTF();
                            if (url.equals("")) {
                                link = new Link(name, null);
                            } else {
                                link = new Link(name, url);
                            }
                            int sizePositions = dis.readInt();
                            List<Organization.Position> positions = new ArrayList<>();
                            LocalDate startDate;
                            LocalDate endDate;
                            String title;
                            String description;
                            for (int k = 0; k < sizePositions; k++) {
                                startDate = LocalDate.parse(dis.readUTF());
                                endDate = LocalDate.parse(dis.readUTF());
                                title = dis.readUTF();
                                description = dis.readUTF();
                                if (description.equals("")) {
                                    positions.add(new Organization.Position(startDate, endDate, title, null));
                                } else {
                                    positions.add(new Organization.Position(startDate, endDate, title, description));
                                }
                            }
                            listOrganizations.add(new Organization(link, positions));
                        }
                        resume.addSection(sectionType, new OrganizationSection(listOrganizations));
                        break;
                }
            }
            return resume;
        }
    }
}
