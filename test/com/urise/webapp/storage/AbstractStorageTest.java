package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public abstract class AbstractStorageTest {
    protected final Storage storage;

    private static final String UUID_1 = "uuid1";
    Resume r1 = new Resume(UUID_1);

    private static final String UUID_2 = "uuid2";
    Resume r2 = new Resume(UUID_2);

    private static final String UUID_3 = "uuid3";
    Resume r3 = new Resume(UUID_3);

    protected AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
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
        Assert.assertSame(updateResume, storage.get(UUID_1));
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
        Resume[] expectedResumes = new Resume[storage.size()];
        expectedResumes[0] = new Resume("uuid1");
        expectedResumes[1] = new Resume("uuid2");
        expectedResumes[2] = new Resume("uuid3");
        Assert.assertArrayEquals(expectedResumes, storage.getAll());
        Assert.assertEquals(3, expectedResumes.length);
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

    private void assertGet(Resume r) {
        Assert.assertEquals(r, storage.get(r.getUuid()));
    }

    private void assertSize(int size) {
        Assert.assertEquals(size, storage.size());
    }
}

