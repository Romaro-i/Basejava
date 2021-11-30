package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
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
        Resume r1 = new Resume(UUID_1);
        Resume r2 = new Resume(UUID_2);
        Resume r3 = new Resume(UUID_3);
        storage.save(r1);
        storage.save(r2);
        storage.save(r3);
    }

    @Test
    public void clear() {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void save() {
        Resume r4 = new Resume("uuid4");
        storage.save(r4);
        Assert.assertEquals(r4, storage.get("uuid4"));
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
        Resume r1 = new Resume("uuid1");
        Assert.assertEquals(r1, storage.get("uuid1"));
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(UUID_1);
        Assert.assertEquals(2, storage.size());
        storage.get(UUID_1);
    }

    @Test
    public void getAll() {
        Resume[] storage1 = storage.getAll();
        Assert.assertArrayEquals(storage.getAll(), storage1);
    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }

    @Test(expected = StorageException.class)
    public void storageOverflow() throws StorageException {
        try {
            for (int i = 4; i <= STORAGE_LIMIT; i++) {
                storage.save(new Resume("uuid" + i));
            }
        } catch (StorageException e) {
            Assert.fail("Storage overflow ahead of time");
        }
        storage.save(new Resume("uuid00"));
    }

    @Test(expected = ExistStorageException.class)
    public void saveExistResume() {
        Resume r1 = new Resume("uuid1");
        storage.save(r1);
    }

    @Test (expected = NotExistStorageException.class)
    public void updateNotExistResume(){
        Resume updateResume = new Resume("uuid4");
        storage.update(updateResume);
    }

    @Test (expected = NotExistStorageException.class)
    public void deleteNotExistResume(){
        storage.delete("uuid4");
        Assert.assertEquals(3, storage.size());
    }
}