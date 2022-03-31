package com.urise.webapp.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;
import java.util.Map;

public class ResumeTestData {
    private static final String fullName1 = "Григорий Кислин";

    public static void main(String[] args) {
        final Map<String, Resume> storage = new HashMap<>();
        Resume resume = new Resume(fullName1);
        storage.put(fullName1, resume);
        System.out.println(resume.getFullName());

        resume.contacts.put(ContactType.PHONE, "+7(921) 855-0482");
        resume.contacts.put(ContactType.MAIL, "gkislin@yandex.ru");
        resume.contacts.put(ContactType.GITHUB, "github.com/gkislin");

        List<TextSection> list = new ArrayList<>();

        TextSection personal = new TextSection("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры.");
        list.add(personal);

        TextSection achivement_1 = new TextSection("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", " +
                "\"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\"." +
                " Организация онлайн стажировок и ведение проектов. Более 1000 выпускников.");
        list.add(achivement_1);

        TextSection achivement_2 = new TextSection ("Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. " +
                "Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.");
        list.add(achivement_2);

        TextSection qualification_1 = new TextSection("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");
        list.add(qualification_1);

        TextSection qualification_2 = new TextSection("Version control: Subversion, Git, Mercury, ClearCase, Perforce");
        list.add(qualification_2);

        for (TextSection textSection : list) {
            System.out.println(textSection);
        }

        List<CompanySection> value = new ArrayList<>();
        LocalDate dateBeginAlcatel = LocalDate.ofYearDay(1997, 9);
        LocalDate dateOverAlcatel = LocalDate.ofYearDay(2005, 1);

        CompanySection experience_1 = new CompanySection(dateBeginAlcatel, dateOverAlcatel, "Alcatel",
                "Инженер по аппаратному и программному тестированию", "Тестирование, отладка, внедрение ПО цифровой телефонной станции Alcatel 1000 S12 (CHILL, ASM).");
        value.add(experience_1);

        LocalDate dateBeginSiemens_AG = LocalDate.ofYearDay(2005, 1);
        LocalDate dateOverSiemens_AG = LocalDate.ofYearDay(2007, 1);

        CompanySection experience_2 = new CompanySection(dateBeginSiemens_AG, dateOverSiemens_AG, "Siemens AG", "Разработчик ПО",
                "Разработка информационной модели, проектирование интерфейсов, реализация и отладка ПО на мобильной IN платформе Siemens @vantage (Java, Unix).");
        value.add(experience_2);

        for (CompanySection companySection : value) {
            System.out.println(companySection);
        }


    }
}
