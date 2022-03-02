package com.urise.webapp.storage;/*
 * Array based storage for Resumes
 */

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {
    protected static final int STORAGE_LIMIT = 10_000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    protected abstract void saveToStorage(Resume resume, int key);

    protected abstract void deleteFromStorage(int key);

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void saveResume(Resume resume, Integer key) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Storage overflow.", resume.getUuid());
        }
        saveToStorage(resume, key);
        size++;
    }

    public void updateResume(Resume resume, Integer key) {
        storage[key] = resume;
    }

    public Resume getResume(Integer key) {
        return storage[key];
    }

    @Override
    public List<Resume> getAll() {
        return Arrays.asList(Arrays.copyOf(storage, size));
    }

    public void deleteResume(Integer key) {
        deleteFromStorage(key);
        size--;
    }

    public int size() {
        return size;
    }

    protected boolean isExist(Integer key) {
        return key >= 0;
    }
}