package com.example.myapplication.lock;

import org.junit.Test;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

public class RefrenceTest {

    PhantomReference<Object> reference;

    public void run() {
        System.gc();
    }

    @Test
    public void test() {
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        reference = new PhantomReference<>(new Object(), referenceQueue);

        new Thread(this::run).start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Object object = referenceQueue.poll();
        System.out.println("object is " + object);
    }

    @Test
    public void test1() {
        WeakReference<Object> reference = new WeakReference<>(new Object(), new ReferenceQueue<>());
    }

}
