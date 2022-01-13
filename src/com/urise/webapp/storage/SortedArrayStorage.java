package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected Object getKey(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    @Override
    protected void deleteFromStorage(int key) {
        System.arraycopy(storage, key + 1, storage, key, size - key - 1);
    }

    @Override
    protected void saveToStorage(Resume resume, int key) {
        key = -key - 1;
        if (size - key >= 0) System.arraycopy(storage, key, storage, key + 1, size - key);
        storage[key] = resume;
    }
}
