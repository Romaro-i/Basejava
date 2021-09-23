/*
 * Array based storage for Resumes
 */

import java.util.Arrays;

public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    private int size = 0;

    void clear() {
        Arrays.fill(storage, 0, size(),null);
        size = 0;
    }

    void save(Resume resume) {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == (null)) {
                storage[i] = resume;
                break;
            }
        } size++;
    }

    Resume get(String uuid) {
        for (int i = 0; i < size(); i++) {
            if (uuid.equals(storage[i].uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        for(int i = 0; i < size(); i++) {
            if (uuid.equals(storage[i].uuid)) {
                storage[i].uuid = null;
                for (int j = i; j < size - 1; j++) {
                    storage[j].uuid = storage[j + 1].uuid;
                    storage[j + 1].uuid = null;
                }
            }
        } size--;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    int size() {
        return size;
    }
}
