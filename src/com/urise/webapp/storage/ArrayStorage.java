package com.urise.webapp.storage;/*
 * Array based storage for Resumes
 */

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class ArrayStorage implements Storage {
    public static final int STORAGE_LIMIT = 10_000;
    private final Resume[] storage = new Resume[STORAGE_LIMIT];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume resume) {
        int index = findIndex(resume.getUuid());
        if (index == -1) {
            System.out.println("Resume " + resume.getUuid() + " not exist.");
        } else {
            storage[index] = resume;
            System.out.println("Update " + resume.getUuid() + " is completed.");
        }
    }

    public void save(Resume resume) {
        int index = findIndex(resume.getUuid());
        if (size == STORAGE_LIMIT) {
            System.out.println("Storage overflow.");
        } else if (index == -1) {
            storage[size] = resume;
            System.out.println("Resume " + resume.getUuid() + " not exist.");
            size++;
        } else {
            System.out.println("Resume " + resume.getUuid() + " already exist.");

        }
    }

    public Resume get(String uuid) {
        int index = findIndex(uuid);
        if (index == -1) {
            System.out.println("Resume " + uuid + " not exist.");
            return null;
        }
        return storage[index];
    }

    public void delete(String uuid) {
        int index = findIndex(uuid);
        if (index == -1) {
            System.out.println("Resume " + uuid + " not exist.");
        } else {
            storage[index] = storage[size - 1];
            System.out.println("Resume " + uuid + " is deleted.");
            size--;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    private int findIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}