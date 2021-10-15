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
        if (resume.getUuid().equals(uuidPresent(resume.getUuid()).getUuid())) {
            uuidPresent(resume.getUuid()) = resume;
            System.out.println("Update " + resume + " is completed.");
        } else {
            System.out.println("Резюме " + resume.getUuid() + " не найдено.");
        }
    }

    public void save(Resume resume) {
        if (size == storage.length) {
            System.out.println("База данных резюме заполнена.");
            System.out.println("Для продолжения удалите одно из резюме");
        }
        if (resume.equals(uuidPresent(resume.getUuid()))) {
            System.out.println("Резюме " + resume.getUuid() + " уже существует.");
        } else {
            uuidPresent(resume.getUuid()) = resume;
            System.out.println("Резюме " + resume.getUuid() + " создано.");
            size++;
        }
    }

    public Resume get(String uuid) {
        if (uuidPresent(uuid).equals(uuid)) {
            return uuidPresent(uuid);
        } else {
            System.out.println("Резюме " + uuid + " не найдено.");
        }
        return null;
    }

    public void delete(String uuid) {
        if (uuidPresent(uuid).equals(uuid)) {
            uuidPresent(uuid) = storage[size];


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

    public Resume uuidPresent(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return storage[i];
            }
        }
        return null;
    }
}