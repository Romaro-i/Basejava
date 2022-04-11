package com.urise.webapp.model;

import java.time.LocalDate;

public class Experience extends AbstractSection {

    private final String companyName;
    private final String specialization;
    private final String duties;
    static LocalDate dateBegin;
    static LocalDate dateOver;


    public Experience(LocalDate dateBegin, LocalDate dateOver, String companyName, String specialization, String duties) {
        Experience.dateBegin = dateBegin;
        Experience.dateOver = dateOver;
        this.companyName = companyName;
        this.specialization = specialization;
        this.duties = duties;
    }


    public String getCompanyName() {
        return companyName;
    }


    public String getSpecialization() {
        return specialization;
    }

    public String getDuties() {
        return duties;
    }

    public LocalDate getDateBegin() {
        return dateBegin;
    }

    public LocalDate getDateOver() {
        return dateOver;
    }


    @Override
    public String toString() {
        return getDateBegin() + " - " + getDateOver() + " - " + "Компания - " + getCompanyName() + ". Специализация: "
                + getSpecialization() + ". Обязанности: " + getDuties();
    }
}
