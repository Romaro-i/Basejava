package com.urise.webapp;

import com.urise.webapp.model.*;
import com.urise.webapp.storage.MapResumeStorage;

import java.util.*;

public class ResumeTestData {
    private static final String fullName1 = "Григорий Кислин";
    private final static MapResumeStorage ARRAY_STORAGE = new MapResumeStorage();
    public static void main(String[] args) {
        Resume resume = new Resume(fullName1);
        ARRAY_STORAGE.save(resume);

        resume.getContacts().put(ContactType.PHONE, "+7(921) 855-0482");
        resume.getContacts().put(ContactType.MAIL, "gkislin@yandex.ru");
        resume.getContacts().put(ContactType.GITHUB, "github.com/gkislin");

        List<String> value = new ArrayList<>();
        String text = "Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры.";
        value.add(text);
        TextSection personal = new TextSection(value);
        resume.getSections().put(SectionType.PERSONAL, personal);

        ARRAY_STORAGE.update(resume);

        text = "С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", " +
                "\"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\"." +
                " Организация онлайн стажировок и ведение проектов. Более 1000 выпускников.";
        value.add(text);
        TextSection achivement = new TextSection(value);
        resume.getSections().put(SectionType.ACHIEVEMENT, achivement);

        ARRAY_STORAGE.update(resume);

        String text1 = "Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. " +
                "Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.";
        value.add(text1);
        resume.getSections().put(SectionType.ACHIEVEMENT, achivement);

        ARRAY_STORAGE.update(resume);
        System.out.println(ARRAY_STORAGE.getAllSorted());
    }
}