package com.example.myapplication.lock;

import org.junit.Test;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {

    ReentrantLock lock = new ReentrantLock(true);

    public void run() {
        for (int i = 0; i < 1000; i++) {
            try {
                lock.lock();
                System.out.println("Thread " + Thread.currentThread().getName() + " " + i);
            } finally {
                lock.unlock();
            }
        }
    }

    @Test
    public void test() {
        Thread thread1 = new Thread(this::run);
        Thread thread2 = new Thread(this::run);
        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
