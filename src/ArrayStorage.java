import static java.lang.System.arraycopy;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int SizeOfArray = 0;

    void clear() {
        for (int i = 0; i < SizeOfArray; i++) {
            storage[i] = null;
        }
        SizeOfArray = 0;
    }

    void save(Resume r) {
        storage[SizeOfArray++] = r;
    }

    Resume get(String uuid) {
        for (int i = 0; i < SizeOfArray; i++) {
            if (storage[i].uuid == uuid) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i < SizeOfArray; i++) {
            if (storage[i].uuid == uuid) {
                arraycopy(storage, i + 1, storage, i, SizeOfArray - (i + 1));
                storage[SizeOfArray - 1] = null;
                SizeOfArray--;
                break;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] StorageOut = new Resume[SizeOfArray];

        arraycopy(storage, 0, StorageOut, 0, SizeOfArray);
        return StorageOut;
    }

    int size() {
        return SizeOfArray;
    }
}