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

    protected AbstractArrayStorageTest(Storage storage) {
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
        assertSize(0);
    }

    @Test
    public void save() {
        Resume r4 = new Resume("uuid4");
        storage.save(r4);
        assertGet(r4);
        assertSize(4);
    }

    @Test(expected = ExistStorageException.class)
    public void saveExistResume() {
        Resume r1 = new Resume("uuid1");
        storage.save(r1);
    }

    @Test
    public void update() {
        Resume updateResume = new Resume(UUID_1);
        storage.update(updateResume);
        Assert.assertEquals(updateResume, storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExistResume() {
        Resume updateResume = new Resume("uuid4");
        storage.update(updateResume);
    }

    @Test
    public void get() {
        Resume r1 = new Resume("uuid1");
        assertGet(r1);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }

    @Test
    public void getAll() {
        Resume[] storage1 = new Resume[storage.size()];
        storage1[0] = new Resume("uuid1");
        storage1[1] = new Resume("uuid2");
        storage1[2] = new Resume("uuid3");
        Assert.assertArrayEquals(storage1, storage.getAll());
        Assert.assertEquals(3, storage1.length);
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(UUID_1);
        assertSize(2);
        storage.get(UUID_1);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExistResume() {
        storage.delete("uuid4");
        assertSize(3);
    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
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

    private void assertGet(Resume r) {
        Assert.assertEquals(r, storage.get(r.getUuid()));
    }

    private void assertSize(int size) {
        Assert.assertEquals(size, storage.size());
    }
}