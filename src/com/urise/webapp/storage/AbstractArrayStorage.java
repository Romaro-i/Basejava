package com.urise.webapp.storage;/*
 * Array based storage for Resumes
 */

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10_000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index < 0) {
            System.out.println("Resume " + resume.getUuid() + " does not exist.");
        } else {
            storage[index] = resume;
            System.out.println("Update " + resume.getUuid() + " completed.");
        }
    }

    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (size == STORAGE_LIMIT) {
            System.out.println("Storage overflow.");
        } else if (index < 0) {
            saveResume(resume, index);
            System.out.println("Resume " + resume.getUuid() + "  created.");
            size++;
        } else {
            System.out.println("Resume " + resume.getUuid() + " already exist.");
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("Resume " + uuid + " does not exist");
            return null;
        }
        return storage[index];
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("Resume " + uuid + " does not exist.");
        } else {
            deleteResume(index);
            System.out.println("Resume " + uuid + " deleted.");
            size--;
        }
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    protected abstract int getIndex(String uuid);

    protected abstract void deleteResume(int index);

    protected abstract void saveResume(Resume resume, int index);
}