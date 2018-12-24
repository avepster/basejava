package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static ru.javawebinar.basejava.model.SectionType.*;

public class ResumeTestData {
    public static Resume resume;

    public ResumeTestData() {
        resume = new Resume("Григорий Кислин");
        resume.addContact(ContactType.PHONE, "+7(921) 855-0482");
        resume.addContact(ContactType.SKYPE, "grigory.kislin");
        resume.addContact(ContactType.EMAIL, "gkislin@yandex.ru");
        resume.addContact(ContactType.LINKEDIN, "https://www.linkedin.com/in/gkislin");
        resume.addContact(ContactType.GITHUB, "https://github.com/gkislin");
        resume.addContact(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/548473");
        resume.addContact(ContactType.HOMEPAGE, "http://gkislin.ru/");

        resume.addSection(PERSONAL, new TextSection("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям"));
        resume.addSection(OBJECTIVE, new TextSection("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры."));

        resume.addSection(ACHIEVEMENT, new ListSection(Arrays.asList("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 1000 выпускников.",
                "Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.",
                "Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. Интеграция с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением на стеке: Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и авторизации различных ERP модулей, интеграция CIFS/SMB java сервера.",
                "Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга.",
                "Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных сервисов (SOA-base архитектура, JAX-WS, JMS, AS Glassfish). Сбор статистики сервисов и информации о состоянии через систему мониторинга Nagios. Реализация онлайн клиента для администрирования и мониторинга системы по JMX (Jython/ Django).",
                "Реализация протоколов по приему платежей всех основных платежных системы России (Cyberplat, Eport, Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа.")));

        resume.addSection(QUALIFICATIONS, new ListSection(Arrays.asList("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2",
                "Version control: Subversion, Git, Mercury, ClearCase, Perforce",
                "DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle, MySQL, SQLite, MS SQL, HSQLDB",
                "Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy, XML/XSD/XSLT, SQL, C/C++, Unix shell scripts",
                "Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor, MyBatis, Spring (MVC, Security, Data, Clouds, Boot), JPA (Hibernate, EclipseLink), Guice, GWT(SmartGWT, ExtGWT/GXT), Vaadin, Jasperreports, Apache Commons, Eclipse SWT, JUnit, Selenium (htmlelements)",
                "Python: Django",
                "JavaScript: jQuery, ExtJS, Bootstrap.js, underscore.js",
                "Scala: SBT, Play2, Specs2, Anorm, Spray, Akka",
                "Технологии: Servlet, JSP/JSTL, JAX-WS, REST, EJB, RMI, JMS, JavaMail, JAXB, StAX, SAX, DOM, XSLT, MDB, JMX, JDBC, JPA, JNDI, JAAS, SOAP, AJAX, Commet, HTML5, ESB, CMIS, BPMN2, LDAP, OAuth1, OAuth2, JWT",
                "Инструменты: Maven + plugin development, Gradle, настройка Ngnix",
                "администрирование Hudson/Jenkins, Ant + custom task, SoapUI, JPublisher, Flyway, Nagios, iReport, OpenCmis, Bonita, pgBouncer",
                "Отличное знание и опыт применения концепций ООП, SOA, шаблонов проектрирования, архитектурных шаблонов, UML, функционального программирования",
                "Родной русский, английский \"upper intermediate\"")));

        List<Organization> experienceList = new ArrayList<>();
        ArrayList<Position> position1 = new ArrayList<>();
        position1.add(new Position(LocalDate.of(2013, 10, 1), LocalDate.now(), "Автор проекта.", "Создание, организация и проведение Java онлайн проектов и стажировок."));
        experienceList.add(new Organization("Java Online Projects", "http://javaops.ru/", position1));

        ArrayList<Position> position2 = new ArrayList<>();
        position2.add(new Position(LocalDate.of(2014, 10, 1), LocalDate.of(2016, 1, 1),
                "Старший разработчик (backend)", "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO."));
        experienceList.add(new Organization("Wrike", "https://www.wrike.com/", position2));
        resume.addSection(EXPERIENCE, new OrganizationSection(experienceList));

        List<Organization> educationList = new ArrayList<>();
        ArrayList<Position> position3 = new ArrayList<>();
        position3.add(new Position(LocalDate.of(2013, 3, 1), LocalDate.of(2013, 5, 1),
                "\"Functional Programming Principles in Scala\" by Martin Odersky", null));
        educationList.add(new Organization("Coursera", "https://www.coursera.org/course/progfun", position3));
        ArrayList<Position> position4 = new ArrayList<>();
        position4.add(new Position(LocalDate.of(2011, 3, 1), LocalDate.of(2011, 4, 1),
                "Курс \"Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.\"", null));
        educationList.add(new Organization("Luxoft", "https://www.coursera.org/course/progfun", position4));
        resume.addSection(EDUCATION, new OrganizationSection(educationList));

        ArrayList<Position> positions = new ArrayList<>();
        positions.add(new Position(LocalDate.of(1993, 9, 1), LocalDate.of(1996, 7, 1), "Аспирантура (программист С, С++)", null));
        positions.add(new Position(LocalDate.of(1987, 9, 1), LocalDate.of(1993, 7, 1), "Инженер (программист Fortran, C)", null));
        educationList.add(new Organization("Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики", "http://www.ifmo.ru/", positions));
    }

    public static void main(String[] args) {
        ResumeTestData rtd = new ResumeTestData();

        for (ContactType type : resume.contacts.keySet()) {
            System.out.println(type.getTitle() + ": " + resume.getContact(type));
        }

        AbstractSection section;
        Organization organization;
        for (SectionType type : resume.sections.keySet()) {
            switch (type) {
                case PERSONAL:
                case OBJECTIVE:
                    section = resume.getSection(type);
                    System.out.println("");
                    System.out.println(type.getTitle() + ":");
                    System.out.println(((TextSection) section).getText());
                    break;
                case ACHIEVEMENT:
                case QUALIFICATIONS:
                    section = resume.getSection(type);
                    System.out.println("");
                    System.out.println(type.getTitle() + ":");
                    if (((ListSection) section).getList().size() == 0) {
                        System.out.println("Empty");
                    } else {
                        for (int i = 0; i < ((ListSection) section).getList().size(); i++) {
                            System.out.println(((ListSection) section).getList().get(i));
                        }
                    }
                    break;
                case EXPERIENCE:
                case EDUCATION:
                    section = resume.getSection(type);
                    System.out.println("");
                    System.out.println(type.getTitle() + ":");
                    if (((OrganizationSection) section).getOrganizationList().size() == 0) {
                        System.out.println("Empty");
                    } else {
                        for (int i = 0; i < ((OrganizationSection) section).getOrganizationList().size(); i++) {
                            organization = ((OrganizationSection) section).getOrganizationList().get(i);
                            System.out.println(organization.getHomePage());
                            for (int j = 0; j < organization.getPositions().size(); j++) {
                                System.out.println(organization.getPositions().get(j).getDateBegin() + " - " + organization.getPositions().get(j).getDateEnd() + " " + organization.getPositions().get(j).getTitle());
                                if (organization.getPositions().get(j).getDescription() != null) {
                                    System.out.println(organization.getPositions().get(j).getDescription());
                                }
                            }
                        }
                    }
                    break;
                default:
                    System.out.println("Не существующая секция.");
            }
        }
    }
}
