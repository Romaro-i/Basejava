package com.urise.webapp.model;

import java.util.List;

public class TextSection extends AbstractSection {

    private List<String> text;

    public TextSection(List<String> text) {
        this.text = text;
    }

    public List<String> getText() {
        return text;
    }
}
