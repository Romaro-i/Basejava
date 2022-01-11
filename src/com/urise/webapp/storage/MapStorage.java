package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

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
    protected void updateResume(Resume resume, int index) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected Resume getResume(int index) {
        return storage.get(Integer.toString(index));
    }

    @Override
    public Resume[] getAll() {
        return storage.values().toArray(new Resume[0]);
    }

    @Override
    protected void deleteResume(int index) {
        storage.remove(Integer.toString(index));
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected Object searchKey(String uuid) {
        return storage.get(uuid);
    }

    @Override
    protected boolean existResume(Object key) {
        return storage.isEmpty();
    }
}
