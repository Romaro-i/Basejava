package com.urise.webapp.storage;/*
 * Array based storage for Resumes
 */

import com.urise.webapp.model.Resume;

public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected Integer searchKey(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void deleteResumeStorage(int index) {
        storage[index] = storage[size - 1];
    }

    @Override
    protected void saveResumeStorage(Resume resume, Object key) {
        storage[size] = resume;
    }
}