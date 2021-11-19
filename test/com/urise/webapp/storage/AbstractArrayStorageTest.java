package com.urise.webapp.storage;

import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static com.urise.webapp.storage.AbstractArrayStorage.STORAGE_LIMIT;

public abstract class AbstractArrayStorageTest {
    private final Storage storage;
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
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
        storage.get("uuid4");
        Assert.assertEquals("uuid4", "uuid4");
    }

    @Test
    public void update() {
        Resume updateResume = new Resume(UUID_1);
        storage.update(updateResume);
        Assert.assertEquals(updateResume, storage.get(UUID_1));
    }

    @Test
    public void get() {
        storage.get("uuid1");
        Assert.assertEquals(UUID_1, UUID_1);
    }

    @Test
    public void delete() {
        storage.delete(UUID_2);
        Assert.assertEquals(2, storage.size());
    }

    @Test
    public void getAll() {
        Storage storage1 = new ArrayStorage();
        storage1.save(new Resume("uuid1"));
        storage1.save(new Resume("uuid2"));
        storage1.save(new Resume("uuid3"));
        Assert.assertArrayEquals(storage1.getAll(), storage.getAll());
    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }

    @Test()
    public void getExist() {
        storage.get(UUID_1);
    }

    @Test(expected = StorageException.class)
    public void storageOverflow() {
        for (int i = 4; i < 10000; i++) {
            storage.save(new Resume("uuid" + i));
        }
        storage.save(new Resume("uuid10001"));
    }
}