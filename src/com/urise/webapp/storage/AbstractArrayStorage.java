package com.urise.webapp.storage;/*
 * Array based storage for Resumes
 */

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10_000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Storage overflow.", resume.getUuid());
        } else if (index < 0) {
            saveResume(resume, index);
            System.out.println("Resume " + resume.getUuid() + "  created.");
            size++;
        } else {
            throw new ExistStorageException(resume.getUuid());
        }
    }

    public void update(Resume resume) {
        int index = checkIndex(resume.getUuid());
        storage[index] = resume;
        System.out.println("Update " + resume.getUuid() + " completed.");
    }

    public Resume get(String uuid) {
        int index = checkIndex(uuid);
        return storage[index];
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public void delete(String uuid) {
        int index = checkIndex(uuid);
        deleteResume(index);
        System.out.println("Resume " + uuid + " deleted.");
        size--;
    }

    public int size() {
        return size;
    }

    protected abstract void deleteResume(int index);

    protected abstract void saveResume(Resume resume, int index);
}