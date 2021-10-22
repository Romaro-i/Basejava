package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortArrayStorage extends AbstractArrayStorage {

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        return Arrays.binarySearch(storage, 0, size(), searchKey);
    }

    @Override
    protected void deleteResume(int index) {
        for (int i = index; i < size; i++) {
            storage[index] = storage[index + 1];
            index++;
        }
    }

    @Override
    protected void saveIndex(Resume resume) {

    }
}
