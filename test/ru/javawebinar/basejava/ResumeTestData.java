package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static ru.javawebinar.basejava.model.SectionType.*;

public class ResumeTestData {
    static Resume resume = new Resume("Григорий Кислин");

    public static void main(String[] args) {
        resume.addContact(ContactType.PHONE, "+7(921) 855-0482");
        resume.addContact(ContactType.SKYPE, "grigory.kislin");
        resume.addContact(ContactType.EMAIL, "gkislin@yandex.ru");
        resume.addContact(ContactType.PROFILE1, "https://www.linkedin.com/in/gkislin");
        resume.addContact(ContactType.PROFILE2, "https://github.com/gkislin");
        resume.addContact(ContactType.PROFILE3, "https://stackoverflow.com/users/548473");
        resume.addContact(ContactType.HOMEPAGE, "http://gkislin.ru/");

        for (ContactType type : resume.contacts.keySet()) {
            System.out.println(type.getTitle() + ": " + resume.getContact(type));
        }

        TextSection textSection = new TextSection();
        textSection.setText("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям");
        resume.addSection(PERSONAL, textSection);
        textSection.setText("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры.");
        resume.addSection(OBJECTIVE, textSection);

        List tmpList = new ArrayList();
        ListSection listSection = new ListSection();
        tmpList.add("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 1000 выпускников.");
        tmpList.add("Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.");
        tmpList.add("Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. Интеграция с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением на стеке: Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и авторизации различных ERP модулей, интеграция CIFS/SMB java сервера.");
        tmpList.add("Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга.");
        tmpList.add("Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных сервисов (SOA-base архитектура, JAX-WS, JMS, AS Glassfish). Сбор статистики сервисов и информации о состоянии через систему мониторинга Nagios. Реализация онлайн клиента для администрирования и мониторинга системы по JMX (Jython/ Django).");
        tmpList.add("Реализация протоколов по приему платежей всех основных платежных системы России (Cyberplat, Eport, Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа.");
        listSection.setList(tmpList);
        resume.addSection(ACHIEVEMENT, listSection);
        tmpList.clear();
        tmpList.add("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");
        tmpList.add("Version control: Subversion, Git, Mercury, ClearCase, Perforce");
        tmpList.add("DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle, MySQL, SQLite, MS SQL, HSQLDB");
        tmpList.add("Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy, XML/XSD/XSLT, SQL, C/C++, Unix shell scripts");
        tmpList.add("Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor, MyBatis, Spring (MVC, Security, Data, Clouds, Boot), JPA (Hibernate, EclipseLink), Guice, GWT(SmartGWT, ExtGWT/GXT), Vaadin, Jasperreports, Apache Commons, Eclipse SWT, JUnit, Selenium (htmlelements)");
        tmpList.add("Python: Django");
        tmpList.add("JavaScript: jQuery, ExtJS, Bootstrap.js, underscore.js");
        tmpList.add("Scala: SBT, Play2, Specs2, Anorm, Spray, Akka");
        tmpList.add("Технологии: Servlet, JSP/JSTL, JAX-WS, REST, EJB, RMI, JMS, JavaMail, JAXB, StAX, SAX, DOM, XSLT, MDB, JMX, JDBC, JPA, JNDI, JAAS, SOAP, AJAX, Commet, HTML5, ESB, CMIS, BPMN2, LDAP, OAuth1, OAuth2, JWT");
        tmpList.add("Инструменты: Maven + plugin development, Gradle, настройка Ngnix");
        tmpList.add("администрирование Hudson/Jenkins, Ant + custom task, SoapUI, JPublisher, Flyway, Nagios, iReport, OpenCmis, Bonita, pgBouncer");
        tmpList.add("Отличное знание и опыт применения концепций ООП, SOA, шаблонов проектрирования, архитектурных шаблонов, UML, функционального программирования");
        tmpList.add("Родной русский, английский \"upper intermediate\"");
        listSection.setList(tmpList);
        resume.addSection(QUALIFICATIONS, listSection);

        ListWithHeaderAndPeriodSection periodList = new ListWithHeaderAndPeriodSection();
        List<HeaderAndPeriodData> hapList = new ArrayList();
        HeaderAndPeriodData data = new HeaderAndPeriodData();
        data.setHeader("Java Online Projects");
        data.setLink("http://javaops.ru/");
        data.setDateBegin(LocalDate.of(2013, 10, 1));
        data.setDateEnd(LocalDate.now());
        data.setText("Автор проекта.\n" + "Создание, организация и проведение Java онлайн проектов и стажировок.");
        hapList.add(data);

        data.setHeader("Wrike");
        data.setLink("https://www.wrike.com/");
        data.setDateBegin(LocalDate.of(2014, 10, 1));
        data.setDateEnd(LocalDate.of(2016, 1, 1));
        data.setText("Старший разработчик (backend)\n" + "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO.");
        hapList.add(data);

        periodList.setHeaderAndPeriodList(hapList);
        resume.addSection(EXPERIENCE, listSection);

        data.setHeader("Coursera");
        data.setLink("https://www.coursera.org/course/progfun");
        data.setDateBegin(LocalDate.of(2013, 3, 1));
        data.setDateEnd(LocalDate.of(2013, 5, 1));
        data.setText("\"Functional Programming Principles in Scala\" by Martin Odersky");
        hapList.add(data);
        data.setHeader("Luxoft");
        data.setLink("https://www.coursera.org/course/progfun");
        data.setDateBegin(LocalDate.of(2011, 3, 1));
        data.setDateEnd(LocalDate.of(2011, 4, 1));
        data.setText("Курс \"Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.\"");
        hapList.add(data);

        periodList.setHeaderAndPeriodList(hapList);
        resume.addSection(EXPERIENCE, listSection);

        AbstractSection section;
        for (SectionType type : resume.content.keySet()) {
            switch (type) {
                case PERSONAL:
                case OBJECTIVE:
                    section = resume.getSection(type);
                    System.out.println(type.getTitle() + ":");
                    System.out.println(((TextSection) section).getText());
                    break;
                case ACHIEVEMENT:
                case QUALIFICATIONS:
                    section = resume.getSection(type);
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
                    System.out.println("Ещё не готово.");;
                    break;
                default:
                    System.out.println("Не существующая секция.");
            }
        }
    }
}
