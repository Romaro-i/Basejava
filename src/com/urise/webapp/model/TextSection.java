package com.urise.webapp.model;

import java.util.List;

public class TextSection extends AbstractSection {

    private final String value;
    private List<String> text;

    public TextSection(String value) {
        this.value = value;
    }

    public List<String> getText() {
        return text;
    }

    public void setText(List<String> text) {
        this.text = text;
    }
}
