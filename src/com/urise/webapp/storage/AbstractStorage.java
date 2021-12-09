package com.urise.webapp.storage;

import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    public abstract void clear();

    public abstract void save(Resume resume);

    public abstract void update(Resume resume);

    public abstract Resume get(String uuid);

    public abstract Resume[] getAll();

    public abstract void delete(String uuid);

    public abstract int size();

    protected abstract int getIndex(String uuid);

    protected int checkIndex(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        return index;
    }
}
