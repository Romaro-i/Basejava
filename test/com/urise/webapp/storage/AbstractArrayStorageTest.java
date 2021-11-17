package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public abstract class AbstractArrayStorageTest {
    private Storage storage = new ArrayStorage();
    private static String UUID_1 = "uuid1";
    private static String UUID_2 = "uuid2";
    private static String UUID_3 = "uuid3";

    @Before
    public void setUp() {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @Test
    public void clear() {
        storage.clear();
        Assert.assertEquals(0, storage.size());
        System.out.println("CLEAR - ОТРАБОТАЛ!");
    }

    @Test
    public void save() {
        Assert.assertEquals(UUID_1, UUID_1);
        Assert.assertEquals(UUID_2, UUID_2);
        Assert.assertEquals(UUID_3, UUID_3);
        System.out.println("SAVE - ОТРАБОТАЛ!");
    }

    @Test
    public void update() {
        Resume updateResume = new Resume(UUID_1);
        storage.update(updateResume);
        Assert.assertEquals(updateResume, storage.get(UUID_1));
        System.out.println("UPDATE - ОТРАБОТАЛ!");
    }

    @Test
    public void get() {
        Assert.assertEquals(UUID_1, UUID_1);
        System.out.println("GET - ОТРАБОТАЛ!");
    }

    @Test
    public void delete() {
        System.out.println("DELETE - ОТРАБОТАЛ!");
    }

    @Test
    public void getAll() {
        System.out.println("GET_ALL - ОТРАБОТАЛ!");
    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
        System.out.println("SIZE - ОТРАБОТАЛ!");
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
        System.out.println("GET_NOT_EXIST - ОТРАБОТАЛ!");
    }

    @Test(expected = ExistStorageException.class)
    public void getExist() {
        storage.save(new Resume(UUID_1));
        System.out.println("GET_EXIST - ОТРАБОТАЛ!");
    }
}