package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public abstract class AbstractStorageTest {
    protected final Storage storage;

    private static final String FULL_NAME = "fullName";

    private static final String UUID_1 = "uuid1";
    Resume r1 = new Resume(UUID_1, FULL_NAME);

    private static final String UUID_2 = "uuid2";
    Resume r2 = new Resume(UUID_2, FULL_NAME);

    private static final String UUID_3 = "uuid3";
    Resume r3 = new Resume(UUID_3, FULL_NAME);

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
        Resume r1 = new Resume("uuid1", FULL_NAME);
        storage.save(r1);
    }

    @Test
    public void update() {
        Resume updateResume = new Resume(UUID_1, FULL_NAME);
        storage.update(updateResume);
        assertSame(updateResume, storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExistResume() {
        Resume updateResume = new Resume("uuid4", FULL_NAME);
        storage.update(updateResume);
    }

    @Test
    public void get() {
        Resume r1 = new Resume("uuid1", FULL_NAME);
        assertGet(r1);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }

    @Test
    public void getAllSorted() {
        List<Resume> list = storage.getAllSorted();
        assertSize(3);
        List<Resume> expectedResumes = Arrays.asList(r1, r2, r3);
        assertArrays(expectedResumes, list);
    }

    private void assertArrays(List<Resume> expectedResumes, List<Resume> list) {
        list.equals(expectedResumes);
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
        assertEquals(3, storage.size());
    }

    private void assertGet(Resume r) {
        assertEquals(r, storage.get(r.getUuid()));
    }

    private void assertSize(int size) {
        assertEquals(size, storage.size());
    }
}