package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {
    protected abstract void saveResume (Resume resume, int index);

    public void save (Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index >= 0) {
            throw new ExistStorageException(resume.getUuid());
        } else {
            saveResume(resume, index);
            System.out.println("Resume " + resume.getUuid() + "  created.");
        }
    }









    protected abstract void deleteResume(int index);
    protected abstract int getIndex(String uuid);
}
