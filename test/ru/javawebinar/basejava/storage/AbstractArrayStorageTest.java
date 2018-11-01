package ru.javawebinar.basejava.storage;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest {

    protected AbstractArrayStorageTest(Storage newStorage) {
        super(newStorage);
    }

//    @Test(expected = StorageException.class)
//    public void saveOverflow() throws Exception {
//        try {
//            for (int i = AbstractStorageTest.size(); i < MAX_SIZE; i++) {
//                AbstractStorageTest.save(new Resume());
//            }
//        } catch (StorageException e) {
//            Assert.fail("Test failed " + e.getMessage());
//        }
//        AbstractStorageTest.save(RESUME_4);
//    }

}