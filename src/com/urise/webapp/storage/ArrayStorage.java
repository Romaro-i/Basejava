package com.urise.webapp.storage;/*
 * Array based storage for Resumes
 */

import com.urise.webapp.model.Resume;

public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected Integer getKey(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void deleteFromStorage(int key) {
        storage[key] = storage[size - 1];
    }

    @Override
    protected void saveToStorage(Resume resume, int key) {
        storage[size] = resume;
    }
}