import static java.lang.System.arraycopy;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private int MAX_SIZE = 10000;
    private Resume[] storage = new Resume[MAX_SIZE];
    private int size = 0;

    void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        size = 0;
    }

    void save(Resume resume) {
        if (size == MAX_SIZE) {
            System.out.println("Resume " + resume.uuid + " записать невозможно, т. к. выходит за пределы массива.");
        } else if (getIndex(resume.uuid) != -1) {
            System.out.println("Resume " + resume.uuid + " уже существует.");
        } else {
            storage[size++] = resume;
        }
    }

    Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index != -1) {
            return storage[index];
        }
        System.out.println("Resume " + uuid + " не найдено.");
        return null;
    }

    void delete(String uuid) {
        int index = getIndex(uuid);

        if (index != -1) {
            arraycopy(storage, index + 1, storage, index, size - (index + 1));
            storage[size - 1] = null;
            size--;
        } else {
            System.out.println("Resume " + uuid + " не найдено.");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] StorageOut = new Resume[size];

        arraycopy(storage, 0, StorageOut, 0, size);
        return StorageOut;
    }

    int size() {
        return size;
    }

    void update(Resume resume) {
        int index = getIndex(resume.uuid);
        if (index != -1) {
            storage[index] = resume;
        } else {
            System.out.println("Resume " + resume.uuid + " не найдено для обновления.");
        }
    }

    int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid == uuid) {
                return i;
            }
        }
        return -1;
    }
}