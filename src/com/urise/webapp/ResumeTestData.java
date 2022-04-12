package com.urise.webapp;

import com.urise.webapp.model.*;

import java.time.LocalDate;
import java.util.*;

public class ResumeTestData {
    private static final String fullName1 = "Григорий Кислин";

    public static void main(String[] args) {
        Resume resume = new Resume(fullName1);

        resume.setContacts(ContactType.PHONE, "+7(921) 855-0482");
        resume.setContacts(ContactType.MAIL, "gkislin@yandex.ru");
        resume.setContacts(ContactType.GITHUB, "github.com/gkislin");

        StringSection personal = new StringSection("Аналитический склад ума, сильная логика, креативность, инициативность." +
                " Пурист кода и архитектуры.");
        resume.setSections(SectionType.PERSONAL, personal);

        StringSection objective = new StringSection("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям");
        resume.setSections(SectionType.OBJECTIVE, objective);



        List<String> bulletedListSection = new ArrayList<>();
        String text = "С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", " +
                "\"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\"." +
                " Организация онлайн стажировок и ведение проектов. Более 1000 выпускников.";
        bulletedListSection.add(text);
        BulletedListSection achivement = new BulletedListSection(bulletedListSection);
        resume.getSections().put(SectionType.ACHIEVEMENT, achivement);

        text = "Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. " +
                "Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.";
        bulletedListSection.add(text);
        resume.getSections().put(SectionType.ACHIEVEMENT, achivement);

        text = "Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. Интеграция с 1С, Bonita BPM, CMIS, LDAP." +
                " Разработка приложения управления окружением на стеке: Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и авторизации различных ERP модулей," +
                "интеграция CIFS/SMB java сервера.";
        bulletedListSection.add(text);
        resume.getSections().put(SectionType.ACHIEVEMENT, achivement);

        text = "JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2";
        bulletedListSection.add(text);
        BulletedListSection qualification = new BulletedListSection(bulletedListSection);
        resume.getSections().put(SectionType.QUALIFICATIONS, qualification);

        text = "Version control: Subversion, Git, Mercury, ClearCase, Perforce";
        bulletedListSection.add(text);
        resume.getSections().put(SectionType.QUALIFICATIONS, qualification);

        List<Experience> experience = new ArrayList<>();
        LocalDate dateBeginAlcatel = LocalDate.of(1997, 9,1);
        LocalDate dateOverAlcatel = LocalDate.ofYearDay(2005, 1);
        String companyName = "Alcatel";
        String specialization = "Инженер по аппаратному и программному тестированию";
        String duties = "Тестирование, отладка, внедрение ПО цифровой телефонной станции Alcatel 1000 S12 (CHILL, ASM) ";

        Experience Alcatel = new Experience(dateBeginAlcatel, dateOverAlcatel, companyName, specialization, duties);
        experience.add(Alcatel);
        resume.getSections().put(SectionType.EXPERIENCE, Alcatel);

        LocalDate dateBeginSiemens = LocalDate.ofYearDay(2005, 1);
        LocalDate dateOverSiemens = LocalDate.ofYearDay(2007, 2);

        companyName = "Siemens AG";
        specialization = "Разработка ПО";
        duties = "Разработка информационной модели, проектирование интерфейсов, реализация и отладка ПО на мобильной IN платформе Siemens @vantage (Java, Unix).";

        Experience Siemens = new Experience(dateBeginSiemens, dateOverSiemens, companyName, specialization, duties);
        experience.add(Siemens);
        resume.getSections().put(SectionType.EXPERIENCE, Siemens);

        for (Experience value : experience) {
            System.out.println(value);
        }
    }
}