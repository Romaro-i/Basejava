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
        int index = getIndex(uuid);
        return storage.get(index);
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        storage.remove(index);
    }

    @Override
    public Resume[] getAll() {
        return new Resume[0];
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected int getIndex(String uuid) {
        for (int i = 0; i < size(); i++) {
            if (uuid.equals(storage.get(i).getUuid())) {
                return i;
            }
        }
        return -1;
    }

}
