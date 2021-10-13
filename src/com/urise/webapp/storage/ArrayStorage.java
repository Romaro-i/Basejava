package com.urise.webapp.storage;/*
 * Array based storage for Resumes
 */

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void upDate(Resume resume) {
        if (uuidPresent(resume.getUuid())) {
            for (int i = 0; i < size; i++) {
                if (resume.equals(storage[i])) {
                    storage[i] = resume;
                    System.out.println("Update " + resume + " is completed.");
                    break;
                }
            }
        } else {
            System.out.println("Резюме " + resume.getUuid() + " не существует.");
        }
    }

    public void save(Resume resume) {
        if (size == storage.length) {
            System.out.println("База данных резюме заполнена.");
            System.out.println("Для продолжения удалите одно из резюме");
        }
        if (uuidPresent(resume.getUuid())) {
            System.out.println("Резюме " + resume.getUuid() + " уже существует.");
        } else {
            storage[size] = resume;
            System.out.println("Резюме " + resume.getUuid() + " создано.");
            size++;
        }
    }

    public Resume get(String uuid) {
        if (uuidPresent(uuid)) {
            for (int i = 0; i < size; i++) {
                if (uuid.equals(storage[i].getUuid())) {
                    return storage[i];
                }
            }
        }
        return null;
    }

    public void delete(String uuid) {
        if (uuidPresent(uuid)) {
            for (int i = 0; i < size; i++) {
                if (uuid.equals(storage[i].getUuid())) {
                    for (int j = i; j < size - 1; j++) {
                        storage[j] = storage[j + 1];
                    }
                }
            }
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

    public boolean uuidPresent(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return true;
            }
        }
        return false;
    }
}
