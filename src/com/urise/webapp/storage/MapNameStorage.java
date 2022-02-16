package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapNameStorage extends AbstractStorage {
    private final Map<String, Resume> storage = new HashMap<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    protected void saveResume(Resume resume, Object key) {
        storage.put(resume.getFullName(), resume);
    }

    @Override
    protected void updateResume(Resume resume, Object key) {
        storage.put(resume.getFullName(), resume);
    }

    @Override
    protected Resume getResume(Object key) {
        return storage.get((String) key);
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
    protected Object getKey(String fullName) {
        return fullName;
    }

    @Override
    protected boolean existResume(Object key) {
        return storage.containsKey((String) key);
    }

    @Override
    protected List<Resume> getAll() {
        return new ArrayList<>(storage.values());
    }




}
