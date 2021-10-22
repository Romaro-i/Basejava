package com.urise.webapp.storage;/*
 * Array based storage for Resumes
 */

import com.urise.webapp.model.Resume;

public class ArrayStorage extends AbstractArrayStorage {
    protected static final int STORAGE_LIMIT = 10_000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void changeIndex(int index) {
        storage[index] = storage[size - 1];
    }
}