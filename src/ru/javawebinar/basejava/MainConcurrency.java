package ru.javawebinar.basejava;

import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MainConcurrency {
    public static final int THREADS_NUMBER = 10000;
    private static int counter;
    private static final AtomicInteger atomicCounter = new AtomicInteger();

    //    private static final Object LOCK = new Object();
    private static final Lock lock = new ReentrantLock();
    //    private static final ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
//    private static final Lock WRITE_LOCK = new reentrantReadWriteLock.WriteLock();
//    private static final Lock READ_LOCK = new reentrantReadWriteLock.ReadLock();
    private static final ThreadLocal<DateTimeFormatter> threadLocal = ThreadLocal.withInitial(() -> DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"));

    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getName());
        Thread thread0 = new Thread() {
            @Override
            public void run() {
                System.out.println(getName() + ", " + getState());
                throw new IllegalStateException();
            }
        };
        thread0.start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + ", " + Thread.currentThread().getState());
            }

            private void inc() {
                synchronized (this) {
                    counter++;
                }
            }
        }).start();

        System.out.println(thread0.getState());

        final MainConcurrency mainConcurrency = new MainConcurrency();
        CountDownLatch latch = new CountDownLatch(THREADS_NUMBER);
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
//        CompletionService completionService = new ExecutorCompletionService(executorService);
//        List<Thread> threads = new ArrayList<>(THREADS_NUMBER);
        for (int i = 0; i < THREADS_NUMBER; i++) {
            Future<Integer> future = executorService.submit(() -> {
//            Thread thread = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    mainConcurrency.inc();
                    System.out.println(threadLocal.get().parse(new Date().toString()));
                }
                latch.countDown();
                return 5;
            });
            System.out.println(future.isDone());
            try {
                System.out.println(future.get());
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
//            thread.start();
//            threads.add(thread);
        }
//        completionService.poll();
/*        threads.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });*/
        latch.await(10, TimeUnit.SECONDS);
        executorService.shutdown();
//        System.out.println(MainConcurrency.counter);
        System.out.println(MainConcurrency.atomicCounter.get());
    }

    private void inc() {
//        synchronized (this) {
//        synchronized (MainConcurrency.class) {
//        WRITE_LOCK.lock();
//        try {
        atomicCounter.incrementAndGet();
//            counter++;
//        } finally {
//            WRITE_LOCK.unlock();
//        }
//              readFile
    }
}
