package com.example.myapplication.lock;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.Stream;

public class ExampleVoliteUnitTest {

    public static class CT {
        public long p0, p1, p2, p3, p4, p5, p6, p7;
        public volatile long i = 0;
        public long p9, p10, p11, p12, p13, p14, p15;
    }

    public static CT[] cts = new CT[2];

    static {
        cts[0] = new CT();
        cts[1] = new CT();
    }

    @Test
    public void test() {
        Thread thread1 = new Thread() {
            @Override
            public void run() {
                for (long i = 0; i < 1000_0000; i++) {
                    cts[0].i = i;
                }
            }
        };

        Thread thread2 = new Thread() {
            @Override
            public void run() {
                for (long i = 0; i < 1000_0000; i++) {
                    cts[1].i = i;
                }
            }
        };

        long start = System.currentTimeMillis();
        thread1.start();
        thread2.start();
        try {
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("time: " + ( System.currentTimeMillis() - start ));
    }

    @Test
    public void test1() {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        atomicInteger.incrementAndGet();
    }

    LongAdder count3 = new LongAdder();

    @Test
    public void test2() {
        Thread[] threads = new Thread[1000];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread() {
                @Override
                public void run() {
                    for (int index = 0; index < 100000; index++) {
                        count3.increment();
                    }
                }
            };
        }
        Stream.of(threads).forEach(Thread::start);
        Stream.of(threads).forEach((thread) -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println("count3: " + count3);
    }

}
