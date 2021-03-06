package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Array based storage for Resumes
 */
public class SortedArrayStorage extends AbstractArrayStorage {
/*
    private static class ResumeComparator implements Comparator<Resume> {
        @Override
        public int compare(Resume o1, Resume o2) {
            return o1.getUuid().compareTo(o2.getUuid());
        }
    }

    private static final Comparator<Resume> RESUME_COMPARATOR = new Comparator<Resume>() {
        @Override
        public int compare(Resume o1, Resume o2) {
            return o1.getUuid().compareTo(o2.getUuid());
        }
    };
*/

    private static final Comparator<Resume> RESUME_COMPARATOR_UUID = Comparator.comparing(Resume::getUuid);

    @Override
    protected Integer getSearchKey(String uuid) {
        Resume searchKey = new Resume(uuid, "Anonimous");
        return Arrays.binarySearch(storage, 0, size, searchKey, RESUME_COMPARATOR_UUID);
    }

    @Override
    protected void saveOne(Integer index, Resume resume) {
        int realIndex = -index - 1;
        System.arraycopy(storage, realIndex, storage, realIndex + 1, size - realIndex);
        storage[realIndex] = resume;
    }

    @Override
    protected void deleteOne(Integer index) {
        System.arraycopy(storage, index + 1, storage, index, size - index - 1);
    }
}