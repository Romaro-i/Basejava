package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.*;

public abstract class AbstractStorage implements Storage {
    protected abstract void saveResume(Resume resume, Object key);

    protected abstract void updateResume(Resume resume, Object key);

    protected abstract Resume getResume(Object key);

    protected abstract void deleteResume(Object key);

    protected abstract Object getKey(String uuid);

    protected abstract boolean existResume(Object key);

    protected abstract List<Resume> getArray();

    private static class ResumeNameComparator implements Comparator<Resume> {
        @Override
        public int compare(Resume o1, Resume o2) {
            if (o1.getFullName() != null) {
                return o1.getFullName().compareTo(o2.getFullName());
            }
            return o1.getUuid().compareTo(o2.getUuid());
        }
    }

    public void save(Resume resume) {
        Object key = getNotExistResume(resume.getUuid());
        saveResume(resume, key);
        System.out.println("Resume " + resume.getUuid() + "  created.");
    }

    public void update(Resume resume) {
        Object key = getExistResume(resume.getUuid());
        updateResume(resume, key);
        System.out.println("Update " + resume.getUuid() + " completed.");
    }

    public Resume get(String uuid) {
        Object key = getExistResume(uuid);
        return getResume(key);
    }

    public List<Resume> getAllSorted() {
        List<Resume> list = getArray();
        Arrays.sort(list, new ResumeNameComparator());
        return list;
    }

    public void delete(String uuid) {
        Object key = getExistResume(uuid);
        deleteResume(key);
        System.out.println("Resume " + uuid + " deleted.");
    }

    private Object getNotExistResume(String uuid) {
        Object key = getKey(uuid);
        if (existResume(key)) {
            throw new ExistStorageException(uuid);
        }
        return key;
    }

    private Object getExistResume(String uuid) {
        Object key = getKey(uuid);
        if (!existResume(key)) {
            throw new NotExistStorageException(uuid);
        }
        return key;
    }
}