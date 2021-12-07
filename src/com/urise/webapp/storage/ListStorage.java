package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    private final List<Resume> storage = new ArrayList<>();


    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public void save(Resume resume) {
        storage.add(resume);
    }

    @Override
    public void update(Resume resume) {

    }

    @Override
    public Resume get(String uuid) {
        int index = storage.indexOf(uuid);
        return storage.get(index);
    }

    @Override
    public void delete(String uuid) {
        storage.remove(uuid);
    }

    @Override
    public Resume[] getAll() {
        return new Resume[0];
    }

    @Override
    public int size() {
        return storage.size();
    }

}
