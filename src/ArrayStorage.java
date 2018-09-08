import static java.lang.System.*;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int SizeOfArray = 0;

    void clear() {
        SizeOfArray = 0;
    }

    void save(Resume r) {
        storage[SizeOfArray++] = r;
    }

    Resume get(String uuid) {
        Resume ResumeOut = new Resume();
        ResumeOut = null;
        for (int i = 0; i < SizeOfArray; i++) {
            if (storage[i].uuid == uuid) {
                ResumeOut = storage[i];
            }
        }
        return ResumeOut;
    }

    void delete(String uuid) {
        for (int i = 0; i < SizeOfArray; i++) {
            if (storage[i].uuid == uuid) {
                storage[i] = null;
                if (i < SizeOfArray - 1) {
                    arraycopy(storage, i + 1, storage, i + 1 - 1, SizeOfArray - (i + 1));
                }
                storage[SizeOfArray - 1] = null;
                SizeOfArray--;
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