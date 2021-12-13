package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    protected abstract void deleteResume(int index);

    protected abstract void saveResume(Resume resume, int index);

    protected abstract int getIndex(String uuid);
}
