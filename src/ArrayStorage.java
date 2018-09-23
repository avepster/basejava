import static java.lang.System.arraycopy;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    final static int MAX_SIZE = 10000;
    private Resume[] storage = new Resume[MAX_SIZE];
    private int size = 0;

    public void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        size = 0;
    }

    public void save(Resume resume) {
        if (size == MAX_SIZE) {
            System.out.println("Resume " + resume.getUuid() + " записать невозможно, т. к. выходит за пределы массива.");
        } else if (getIndex(resume.getUuid()) != -1) {
            System.out.println("Resume " + resume.getUuid() + " уже существует.");
        } else {
            storage[size++] = resume;
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index != -1) {
            return storage[index];
        }
        System.out.println("Resume " + uuid + " не найдено.");
        return null;
    }

    public void delete(String uuid) {
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
    public Resume[] getAll() {
        Resume[] StorageOut = new Resume[size];

        arraycopy(storage, 0, StorageOut, 0, size);
        return StorageOut;
    }

    public int size() {
        return size;
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index != -1) {
            storage[index] = resume;
        } else {
            System.out.println("Resume " + resume.getUuid() + " не найдено для обновления.");
        }
    }

    private int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}