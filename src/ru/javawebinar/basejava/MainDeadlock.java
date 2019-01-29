package ru.javawebinar.basejava;

public class MainDeadlock {
    public static void main(String[] args) {
        String str1 = "str1";
        String str2 = "str2";

        Thread thread1 = new Thread("My Thread 1") {
            @Override
            public void run() {
                doLock(getName(), str1, str2);
            }
        };

        Thread thread2 = new Thread("My Thread 2") {
            @Override
            public void run() {
                doLock(getName(), str2, str1);
            }
        };

        thread1.start();
        thread2.start();
    }

    private static void doLock(String threadName, String str1, String str2) {
        synchronized (str1) {
            System.out.println(threadName + ": locked " + str1.toString());
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
            synchronized (str2) {
                System.out.println(threadName + ":locked " + str2.toString());
            }
        }
    }
}
