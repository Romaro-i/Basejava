package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {

    private final Map<String, Resume> storage = new HashMap<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    protected void saveResume(Resume resume, Object key) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected void updateResume(Resume resume, Object key) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected Resume getResume(Object key) {
        return storage.get((String) key);
    }

    @Override
    public Resume[] getAll() {
        Resume[] values = new Resume[storage.size()];
        int key = 0;
        for (Map.Entry<String, Resume> mapEntry : storage.entrySet()) {
            values[key] = mapEntry.getValue();
            key++;
    //      Arrays.sort(values);
        }
        return values;
    }

    @Override
    protected void deleteResume(Object key) {
        storage.remove((String) key);
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected Object getKey(String uuid) {
        return uuid;
    }

    @Override
    protected boolean existResume(Object key) {
        return storage.containsKey((String) key);
    }
}
