package com.example.myapplication.lock;

import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockTest {

    public int a = 0;
    ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();
    ReentrantReadWriteLock.ReadLock readLock = lock.readLock();

    public void read() {
        for (int i = 0; i < 5; i++) {
            try {
                readLock.lock();
                TimeUnit.SECONDS.sleep(1);
                System.out.println("read a " + a);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                readLock.unlock();
            }
        }
    }

    public void write() {
        try {
            writeLock.lock();
            TimeUnit.SECONDS.sleep(3);
            a++;
            System.out.println("write a " + a);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            writeLock.unlock();
        }
    }

    @Test
    public void test() {
        Thread[] readThread = new Thread[18];
        Thread[] writeThread = new Thread[2];
        for (int i = 0; i < readThread.length; i++) {
            readThread[i] = new Thread(this::read);
        }
        for (int i = 0; i < writeThread.length; i++) {
            writeThread[i] = new Thread(this::write);
        }
        for (int i = 0; i < readThread.length; i++) {
            readThread[i].start();
        }
        for (int i = 0; i < writeThread.length; i++) {
            writeThread[i].start();
        }

        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
