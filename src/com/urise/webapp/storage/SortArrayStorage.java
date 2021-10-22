package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortArrayStorage extends AbstractArrayStorage {

    @Override
    public void clear() {

    }

    @Override
    public void update(Resume resume) {

    }

    @Override
    public void save(Resume resume) {

    }

    @Override
    public void delete(String uuid) {

    }

    @Override
    public Resume[] getAll() {
        return new Resume[0];
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size(), searchKey);

    }

    @Override
    protected void changeIndex(int index) {
        for (int i = 0; i < size; i++) {
            storage[index] = storage[index + 1];
        }
    }
}
