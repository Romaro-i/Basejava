package com.urise.webapp.storage;/*
 * Array based storage for Resumes
 */


import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10_000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    protected abstract void saveResumeStorage(Resume resume, int index);

    protected abstract void deleteResumeStorage(int index);

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void saveResume(Resume resume, int index) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Storage overflow.", resume.getUuid());
        }
        saveResumeStorage(resume, index);
        size++;
    }

    public void updateResume(Resume resume, int index) {
        storage[index] = resume;
    }

    public Resume getResume(int index) {
        return storage[index];
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public void deleteResume(int index) {
        deleteResumeStorage(index);
        size--;
    }

    public int size() {
        return size;
    }
}