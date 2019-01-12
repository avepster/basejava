package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.*;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static ru.javawebinar.basejava.model.SectionType.*;

public class ResumeTestData {
    public Resume resume;

    public ResumeTestData(String uuid, String fullName) {
        resume = new Resume(uuid, fullName);
        resume.addContact(ContactType.PHONE, "+7(921) 855-0482");
        resume.addContact(ContactType.SKYPE, "grigory.kislin");
        resume.addContact(ContactType.EMAIL, "gkislin@yandex.ru");
        resume.addContact(ContactType.LINKEDIN, "https://www.linkedin.com/in/gkislin");
        resume.addContact(ContactType.GITHUB, "https://github.com/gkislin");
        resume.addContact(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/548473");
        resume.addContact(ContactType.HOMEPAGE, "http://gkislin.ru/");

        resume.addSection(PERSONAL, new TextSection("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям"));
        resume.addSection(OBJECTIVE, new TextSection("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры."));

        resume.addSection(ACHIEVEMENT, new ListSection("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 1000 выпускников.",
                "Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.",
                "Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. Интеграция с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением на стеке: Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и авторизации различных ERP модулей, интеграция CIFS/SMB java сервера.",
                "Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга.",
                "Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных сервисов (SOA-base архитектура, JAX-WS, JMS, AS Glassfish). Сбор статистики сервисов и информации о состоянии через систему мониторинга Nagios. Реализация онлайн клиента для администрирования и мониторинга системы по JMX (Jython/ Django).",
                "Реализация протоколов по приему платежей всех основных платежных системы России (Cyberplat, Eport, Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа."));

        resume.addSection(QUALIFICATIONS, new ListSection("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2",
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
                "Родной русский, английский \"upper intermediate\""));

        List<Organization> experienceList = new ArrayList<>();
        experienceList.add(new Organization("Java Online Projects", "http://javaops.ru/",
                new Organization.Position(2013, Month.OCTOBER, "Автор проекта.", "Создание, организация и проведение Java онлайн проектов и стажировок.")));

        experienceList.add(new Organization("Wrike", "https://www.wrike.com/",
                new Organization.Position(2014, Month.OCTOBER, 2016, Month.JANUARY,
                        "Старший разработчик (backend)", "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO.")));
        resume.addSection(EXPERIENCE, new OrganizationSection(experienceList));

        List<Organization> educationList = new ArrayList<>();
        educationList.add(new Organization("Coursera", "https://www.coursera.org/course/progfun",
                new Organization.Position(2013, Month.MARCH, 2013, Month.MAY,
                        "\"Functional Programming Principles in Scala\" by Martin Odersky", null)));
        educationList.add(new Organization("Luxoft", "https://www.coursera.org/course/progfun",
                new Organization.Position(2011, Month.MARCH, 2011, Month.APRIL,
                        "Курс \"Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.\"", null)));
        resume.addSection(EDUCATION, new OrganizationSection(educationList));

        educationList.add(new Organization("Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики", "http://www.ifmo.ru/",
                new Organization.Position(1993, Month.SEPTEMBER, 1996, Month.JULY, "Аспирантура (программист С, С++)", null),
                new Organization.Position(1987, Month.SEPTEMBER, 1993, Month.JULY, "Инженер (программист Fortran, C)", null)
        ));
    }

    public static void main(String[] args) {
        ResumeTestData rtd = new ResumeTestData(UUID.randomUUID().toString(), "Григорий Кислин");
        for (ContactType type : rtd.resume.getContacts().keySet()) {
            System.out.println(type.getTitle() + ": " + rtd.resume.getContact(type));
        }

        Object section;
        Organization organization;
        for (SectionType type : rtd.resume.getSections().keySet()) {
            switch (type) {
                case PERSONAL:
                case OBJECTIVE:
                    section = rtd.resume.getSection(type);
                    System.out.println("");
                    System.out.println(type.getTitle() + ":");
                    System.out.println(((TextSection) section).getText());
                    break;
                case ACHIEVEMENT:
                case QUALIFICATIONS:
                    section = rtd.resume.getSection(type);
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
                    section = rtd.resume.getSection(type);
                    System.out.println("");
                    System.out.println(type.getTitle() + ":");
                    if (((OrganizationSection) section).getOrganizationList().size() == 0) {
                        System.out.println("Empty");
                    } else {
                        for (int i = 0; i < ((OrganizationSection) section).getOrganizationList().size(); i++) {
                            organization = ((OrganizationSection) section).getOrganizationList().get(i);
                            System.out.println(organization.getHomePage());
                            for (int j = 0; j < organization.getPositions().size(); j++) {
                                System.out.println(organization.getPositions().get(j).getStartDate() + " - " + organization.getPositions().get(j).getEndDate() + " " + organization.getPositions().get(j).getTitle());
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
