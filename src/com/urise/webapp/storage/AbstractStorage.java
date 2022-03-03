package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.*;
import java.util.logging.Logger;

public abstract class AbstractStorage<SK> implements Storage {
    protected abstract void saveResume(Resume resume, SK key);

    protected abstract void updateResume(Resume resume, SK key);

    protected abstract Resume getResume(SK key);

    protected abstract void deleteResume(SK key);

    protected abstract SK getKey(String uuid);

    protected abstract boolean isExist(SK key);

    protected abstract List<Resume> getAll();

    //    protected final Logger LOG = Logger.getLogger(getClass().getName());
    private static final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());

    private static final Comparator<Resume> RESUME_COMPARATOR = Comparator.comparing(Resume::getFullName).thenComparing(Resume::getUuid);

    public void save(Resume resume) {
        LOG.info("Save " + resume);
        SK key = getNotExistResume(resume.getUuid());
        saveResume(resume, key);
    }

    public void update(Resume resume) {
        LOG.info("Update " + resume);
        SK key = getExistResume(resume.getUuid());
        updateResume(resume, key);
    }

    public Resume get(String uuid) {
        LOG.info("Get " + uuid);
        SK key = getExistResume(uuid);
        return getResume(key);
    }

    public List<Resume> getAllSorted() {
        List<Resume> list = getAll();
        list.sort(RESUME_COMPARATOR);
        return list;
    }

    public void delete(String uuid) {
        LOG.info("Delete " + uuid);
        SK key = getExistResume(uuid);
        deleteResume(key);
    }

    private SK getNotExistResume(String uuid) {
        SK key = getKey(uuid);
        if (isExist(key)) {
            LOG.warning("Resume " + uuid + " not exist");
            throw new ExistStorageException(uuid);
        }
        return key;
    }

    private SK getExistResume(String uuid) {
        SK key = getKey(uuid);
        if (!isExist(key)) {
            LOG.warning("Resume " + uuid + " already exist");
            throw new NotExistStorageException(uuid);
        }
        return key;
    }
}