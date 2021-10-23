package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortArrayStorage extends AbstractArrayStorage {

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    @Override
    protected void deleteResume(int index) {
        for (int i = index; i < size; i++) {
            storage[index] = storage[index + 1];
            index++;
        }
    }

    @Override
    protected void saveResume(Resume resume, int index) {
        index = (index * -1) - 1;
        if (size - index >= 0) System.arraycopy(storage, index, storage, index + 1, size - index);
        storage[index] = resume;
    }
}
