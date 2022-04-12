package com.urise.webapp.model;

import java.util.List;

public class BulletedListSection extends AbstractSection {

    private final List<String> text;

    public BulletedListSection(List<String> text) {
        this.text = text;
    }

    public List<String> getText() {
        return text;
    }

}
