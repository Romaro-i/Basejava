package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    protected int size;

    public abstract void clear();

    public abstract void save(Resume resume);

    public abstract void update(Resume resume);

    public abstract Resume get(String uuid);

    public abstract Resume[] getAll();

    public abstract void delete(String uuid);

    public int size() {
        return size;
    }

    protected abstract int getIndex(String uuid);
}
