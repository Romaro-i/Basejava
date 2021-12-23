package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.HashMap;
import java.util.Map;


public class MapStorage extends AbstractStorage {

    private final Map<Integer, Resume> storage = new HashMap<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    protected void saveResume(Resume resume, int index) {
        storage.put(index, resume);
    }

    @Override
    protected void updateResume(Resume resume, int index) {
        storage.put(index, resume);
    }

    @Override
    protected Resume getResume(int index) {
        return storage.get(index);
    }

    @Override
    public Resume[] getAll() {
        return storage.values().toArray(new Resume[0]);
    }

    @Override
    protected void deleteResume(int index) {
        storage.remove(index);
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected int getIndex(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (uuid.equals(storage.get(i).getUuid())) {
                return i;
            }
        }
        return -1;
    }
}
