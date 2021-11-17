package com.urise.webapp.storage;/*
 * Array based storage for Resumes
 */

import com.urise.webapp.model.Resume;

public interface Storage {

    void clear();

    void save(Resume resume);

    void update(Resume resume);

    Resume get(String uuid);

    void delete(String uuid);

    Resume[] getAll();

    int size();
}
