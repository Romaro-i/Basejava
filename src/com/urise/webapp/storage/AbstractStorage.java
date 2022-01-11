package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {
    protected abstract void saveResume(Resume resume, Object key);

    protected abstract void updateResume(Resume resume, int index);

    protected abstract Resume getResume(int index);

    protected abstract void deleteResume(int index);

    protected abstract Object searchKey(String uuid);

    protected abstract boolean existResume(Object key);

    public void save(Resume resume) {
        Object key = searchKey(resume.getUuid());
        if (existResume(key)) {
            throw new ExistStorageException(resume.getUuid());
        }
        saveResume(resume, key);
        System.out.println("Resume " + resume.getUuid() + "  created.");
    }

    public void update(Resume resume) {
        int index = (int) searchKey(resume.getUuid());
        if (index < 0) {
            throw new NotExistStorageException(resume.getUuid());
        }
        updateResume(resume, index);
        System.out.println("Update " + resume.getUuid() + " completed.");
    }

    public Resume get(String uuid) {
        int index = (int) searchKey(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        return getResume(index);
    }

    public void delete(String uuid) {
        int index = (int) searchKey(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        deleteResume(index);
        System.out.println("Resume " + uuid + " deleted.");
    }
}