package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortArrayStorage extends AbstractArrayStorage {

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        if (uuid.equals(searchKey.getUuid())) {
        return Arrays.binarySearch(storage, 0, size(), searchKey);
        }
        return -1;
    }

    @Override
    protected void changeIndex(int index) {
        for (int i = 0; i < size; i++) {
            storage[index] = storage[index + 1];
            index++;
        }
    }

    @Override
    protected void saveIndex(Resume resume) {

    }
}
