package com.example.myapplication.lock;

import org.junit.Test;

import java.util.concurrent.Semaphore;

public class SemaphoreTest {

    Semaphore semaphore = new Semaphore(1);
    //Semaphore semaphore = new Semaphore(1,true);

    public void run() {
        try {
            semaphore.acquire();
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName() + " ok");
        } catch (Exception e) {

        } finally {
            semaphore.release();
        }
    }

    @Test
    public void test() {
        new Thread(this::run).start();
        new Thread(this::run).start();
        new Thread(this::run).start();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
