/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int size_of_array = 0;

    void clear() {
        for (int i = 0; i < size_of_array; i++) {
            storage[i] = null;
        }
        size_of_array = 0;
    }

    void save(Resume r) {
        storage[size_of_array++] = r;
    }

    Resume get(String uuid) {
        Resume r = new Resume();
        int i = 0;
        boolean condition = true;
        do {
            if (storage[i].uuid == uuid) {
                r = storage[i];
                condition = false;
            }
            i++;
            if (i >= size_of_array) {
                condition = false;
            }
        } while (condition);
        return r;
    }

    void delete(String uuid) {
        for (int i = 0; i < size_of_array; i++) {
            if (storage[i].uuid == uuid) {
                storage[i] = null;
                if (i < size_of_array - 1) {
                    for (int j = i + 1; j < size_of_array; j++) {
                        storage[j - 1] = storage[j];
                    }
                }
                storage[size_of_array] = null;
                size_of_array--;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] storage_out = new Resume[size_of_array];

        for (int i = 0; i < size_of_array; i++) {
            storage_out[i] = storage[i];
        }
        return storage_out;
    }

    int size() {
        return size_of_array;
    }
}
