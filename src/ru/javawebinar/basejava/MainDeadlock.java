package ru.javawebinar.basejava;

public class MainDeadlock {
    public static void main(String[] args) {
        String str1 = "str1";
        String str2 = "str2";

        Thread thread1 = new Thread("My Thread 1") {
            public void run() {
                while (true) {
                    synchronized (str1) {
                        System.out.println("Thread 1: locked str1");
                        try {
                            Thread.sleep(100);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        synchronized (str2) {
                            System.out.println("Thread 1: locked str2");
                        }
                    }
                }
            }
        };

        Thread thread2 = new Thread("My Thread 2") {
            public void run() {
                while (true) {
                    synchronized (str2) {
                        System.out.println("Thread 2: locked str2");
                        try {
                            Thread.sleep(100);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        synchronized (str1) {
                            System.out.println("Thread 1: locked str1");
                        }
                    }
                }
            }
        };

        thread1.start();
        thread2.start();
    }
}
