package ru.javawebinar.basejava.storage.serialize;

import ru.javawebinar.basejava.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class DataStreamSerializer implements StreamSerializer {

    private <T> void writeWithException(Collection<T> collection, DataOutputStream dos, ElementWriter<T> elementWriter) throws IOException {
        dos.writeInt(collection.size());
        for (T element : collection) {
            elementWriter.accept(element);
        }
    }

    @Override
    public void doWrite(OutputStream os, Resume resume) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(resume.getUuid());
            dos.writeUTF(resume.getFullName());
            Map<ContactType, String> contacts = resume.getContacts();
            writeWithException(contacts.entrySet(), dos, element -> {
                dos.writeUTF(element.getKey().name());
                dos.writeUTF(element.getValue());
            });
            // TODO implements section
            Map<SectionType, AbstractSection> sections = resume.getSections();
            writeWithException(sections.entrySet(), dos, element -> {
                SectionType sectionType = element.getKey();
                AbstractSection sectionValue = element.getValue();
                dos.writeUTF(sectionType.name());
                switch (sectionType) {
                    case PERSONAL:
                    case OBJECTIVE:
                        dos.writeUTF(((TextSection) sectionValue).getText());
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        List<String> list = ((ListSection) sectionValue).getList();
                        writeWithException(list, dos, str -> dos.writeUTF(str));
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        List<Organization> organizations = ((OrganizationSection) sectionValue).getOrganizationList();
                        writeWithException(organizations, dos, organization -> {
                            dos.writeUTF(organization.getHomePage().getName());
                            String url = organization.getHomePage().getUrl();
                            if (url == null) {
                                dos.writeUTF("");
                            } else {
                                dos.writeUTF(url);
                            }
                            List<Organization.Position> positions = organization.getPositions();
                            writeWithException(positions, dos, position -> {
                                dos.writeUTF(position.getStartDate().toString());
                                dos.writeUTF(position.getEndDate().toString());
                                dos.writeUTF(position.getTitle());
                                String description = position.getDescription();
                                if (description == null) {
                                   dos.writeUTF("");
                                } else {
                                    dos.writeUTF(description);
                                }
                            });

                        });
                        break;
                }
            });
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
