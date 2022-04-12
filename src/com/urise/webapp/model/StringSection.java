package com.urise.webapp.model;

public class StringSection extends AbstractSection {
    public final String text;

    public StringSection(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
