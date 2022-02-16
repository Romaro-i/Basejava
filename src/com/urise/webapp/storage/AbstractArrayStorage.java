package com.urise.webapp.storage;/*
 * Array based storage for Resumes
 */

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10_000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    protected abstract void saveToStorage(Resume resume, int key);

    protected abstract void deleteFromStorage(int key);

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void saveResume(Resume resume, Object key) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Storage overflow.", resume.getUuid());
        }
        saveToStorage(resume, (int) key);
        size++;
    }

    public void updateResume(Resume resume, Object key) {
        storage[(int) key] = resume;
    }

    public Resume getResume(Object key) {
        return storage[(int) key];
    }

    @Override
    public List<Resume> getAll() {
        return Arrays.asList(Arrays.copyOf(storage, size));
    }

    public void deleteResume(Object key) {
        deleteFromStorage((Integer) key);
        size--;
    }

    public int size() {
        return size;
    }

    protected boolean existResume(Object key) {
        return (Integer) key >= 0;
    }
}