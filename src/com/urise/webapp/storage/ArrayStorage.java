package com.urise.webapp.storage;/*
 * Array based storage for Resumes
 */

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class ArrayStorage {
    private final Resume[] storage = new Resume[10_000];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume resume) {
        int j = uuidPresent(resume.getUuid());
        if (j == -1) {
            System.out.println("Резюме " + resume.getUuid() + " не найдено.");
        } else if (storage[j].equals(resume)) {
            storage[j] = resume;
            System.out.println("Update " + resume + " is completed.");
        } else {
            System.out.println("Резюме " + resume.getUuid() + " не найдено.");
        }
    }

    public void save(Resume resume) {
        int j = uuidPresent(resume.getUuid());
        if (size == storage.length) {
            System.out.println("База данных резюме заполнена.");
            System.out.println("Для продолжения удалите одно из резюме");
        } else if (j == -1 || j == size) {
            storage[size] = resume;
            System.out.println("Резюме " + resume.getUuid() + " создано.");
            size++;
        } else if (storage[j].equals(resume)) {
            System.out.println("Резюме " + resume.getUuid() + " уже существует.");

        }
    }

    public Resume get(String uuid) {
        int j = uuidPresent(uuid);
        if (j == -1 || j == size) {
            System.out.println("Резюме " + uuid + " не найдено.");
        } else if (storage[j].getUuid().equals(uuid)) {
            return storage[j];
        }
        return null;
    }

    public void delete(String uuid) {
        int j = uuidPresent(uuid);
        if (storage[j].getUuid().equals(uuid)) {
            storage[j] = storage[size - 1];
            System.out.println("Резюме " + uuid + " удалено!");
            size--;
        } else {
            System.out.println("Резюме " + uuid + " не найдено.");
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

    public int uuidPresent(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}