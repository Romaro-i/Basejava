package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {
    protected abstract void saveResume(Resume resume, Object key);

    protected abstract void updateResume(Resume resume, Object key);

    protected abstract Resume getResume(Object key);

    protected abstract void deleteResume(Object key);

    protected abstract Object getKey(String uuid);

    protected abstract boolean existResume(Object key);

    public void save(Resume resume) {
        Object key = getExistResume(resume.getUuid());
        saveResume(resume, key);
        System.out.println("Resume " + resume.getUuid() + "  created.");
    }

    public void update(Resume resume) {
        Object key = getNotExistResume (resume.getUuid());
        updateResume(resume, key);
        System.out.println("Update " + resume.getUuid() + " completed.");
    }

    public Resume get(String uuid) {
        Object key = getNotExistResume (uuid);
        return getResume(key);
    }

    public void delete(String uuid) {
        Object key = getNotExistResume (uuid);
        deleteResume(key);
        System.out.println("Resume " + uuid + " deleted.");
    }

    private Object getNotExistResume (String uuid) {
        Object key = getKey(uuid);
        if (!existResume(key)) {
            throw new NotExistStorageException(uuid);
        }
        return key;
    }

    private Object getExistResume (String uuid) {
        Object key = getKey(uuid);
        if (existResume(key)) {
            throw new ExistStorageException(uuid);
        }
        return key;
    }
}