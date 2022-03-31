package com.urise.webapp.model;

import java.time.LocalDate;

public class CompanySection extends AbstractSection {

    private final String companyName;
    private final String specialization;
    private final String duties;
    static LocalDate dateBegin;
    static LocalDate dateOver;

    public CompanySection(LocalDate dateBegin, LocalDate dateOver, String companyName, String specialization, String duties) {
        CompanySection.dateBegin = dateBegin;
        CompanySection.dateOver = dateOver;
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

    public LocalDate getDateBegin() {
        return dateBegin;
    }

    public LocalDate getDateOver() {
        return dateOver;
    }

    public String getDuties() {
        return duties;
    }

    @Override
    public String toString() {
        return getDateBegin() + " " + getDateOver() + " " + getCompanyName() + " " + getSpecialization() + " " + getDuties();
    }
}
