package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class ListStorage extends AbstractStorage<Integer> {

    private final List<Resume> storage = new ArrayList<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public void saveResume(Resume resume, Integer key) {
        storage.add(resume);
    }

    @Override
    public void updateResume(Resume resume, Integer key) {
        storage.set(key, resume);
    }

    @Override
    public Resume getResume(Integer key) {
        return storage.get(key);
    }

    public void deleteResume(Integer key) {
        storage.remove((int) key);
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected Integer getKey(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (uuid.equals(storage.get(i).getUuid())) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected boolean isExist(Integer key) {
        return key >= 0;
    }

    @Override
    protected List<Resume> getAll() {
        return new ArrayList<>(storage);
    }
}
