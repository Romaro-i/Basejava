package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public abstract class AbstractArrayStorageTest {
    private Storage storage;
    private static String UUID_1 = "uuid1";
    private static String UUID_2 = "uuid2";
    private static String UUID_3 = "uuid3";

    public AbstractArrayStorageTest(ArrayStorage arrayStorage) {
        storage = new ArrayStorage();
    }

    public AbstractArrayStorageTest(SortArrayStorage sortArrayStorage) {
        storage = new SortArrayStorage();
    }

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
    }

    @Test
    public void save() {
        storage.save(new Resume("uuid4"));
        Assert.assertEquals(4, storage.size());
    }

    @Test
    public void update() {
        Resume updateResume = new Resume(UUID_1);
        storage.update(updateResume);
        Assert.assertEquals(updateResume, storage.get(UUID_1));
    }

    @Test
    public void get() {
        Assert.assertEquals(UUID_1, UUID_1);
        storage.get("uuid1");
    }

    @Test
    public void delete() {
    }

    @Test
    public void getAll() {

    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }

    @Test(expected = ExistStorageException.class)
    public void getExist() {
        storage.save(new Resume(UUID_1));
    }
}