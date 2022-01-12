package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class ListStorage extends AbstractStorage {

    private final List<Resume> storage = new ArrayList<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public void saveResume(Resume resume, Object key) {
        storage.add(resume);
    }

    @Override
    public void updateResume(Resume resume, int index) {
        storage.set(storage.indexOf(resume), resume);
    }

    @Override
    public Resume getResume(Object key) {
        return storage.get((Integer) key);
    }

    @Override
    public Resume[] getAll() {
        return storage.toArray(new Resume[0]);
    }


    public void deleteResume(int index) {
        storage.remove(index);
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected Object getKey(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (uuid.equals(storage.get(i).getUuid())) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected boolean existResume(Object key) {
        return (Integer) key >= 0;
    }
}
