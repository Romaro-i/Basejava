package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.*;

public abstract class AbstractStorage<SK> implements Storage {
    protected abstract void saveResume(Resume resume, SK key);

    protected abstract void updateResume(Resume resume, SK key);

    protected abstract Resume getResume(SK key);

    protected abstract void deleteResume(SK key);

    protected abstract SK getKey(String uuid);

    protected abstract boolean isExist(SK key);

    protected abstract List<Resume> getAll();

    private static final Comparator<Resume> RESUME_COMPARATOR = Comparator.comparing(Resume::getFullName).thenComparing(Resume::getUuid);

    public void save(Resume resume) {
        SK key = getNotExistResume(resume.getUuid());
        saveResume(resume, key);
        System.out.println("Resume " + resume.getUuid() + " " + resume.getFullName() + "  created.");
    }

    public void update(Resume resume) {
        SK key = getExistResume(resume.getUuid());
        updateResume(resume, key);
        System.out.println("Update " + resume.getUuid() + " " + resume.getFullName() + " completed.");
    }

    public Resume get(String uuid) {
        SK key = getExistResume(uuid);
        return getResume(key);
    }

    public List<Resume> getAllSorted() {
        List<Resume> list = getAll();
        list.sort(RESUME_COMPARATOR);
        return list;
    }

    public void delete(String uuid) {
        SK key = getExistResume(uuid);
        deleteResume(key);
        System.out.println("Resume " + uuid + " deleted.");
    }

    private SK getNotExistResume(String uuid) {
        SK key = getKey(uuid);
        if (isExist(key)) {
            throw new ExistStorageException(uuid);
        }
        return key;
    }

    private SK getExistResume(String uuid) {
        SK key = getKey(uuid);
        if (!isExist(key)) {
            throw new NotExistStorageException(uuid);
        }
        return key;
    }
}