package com.example.myapplication.lock;

import org.junit.Test;
import org.openjdk.jol.info.ClassLayout;

import java.nio.ByteOrder;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

//线程同步
public class JavaThreadTest {

    private char[] data1 = "123456".toCharArray();
    private char[] data2 = "ABCDEF".toCharArray();
    Thread thread1;
    Thread thread2;
    volatile boolean thread1Run = false;

    @Test
    public void tt() {
        int a = 1;
        Object object = new Object();
        //查看字节序
        System.out.println(ByteOrder.nativeOrder());
        System.out.println(ClassLayout.parseInstance(object).toPrintable());
        synchronized (object) {
            System.out.println(ClassLayout.parseInstance(object).toPrintable());
        }

    }

    @Test
    public void test() {
        Object o1 = new Object();
        Object o2 = new Object();
        synchronized (o1) {
            System.out.println("获取锁1");
        }

        System.out.println(o1.toString());

        synchronized (o2) {
            System.out.println("获取锁2");
        }
    }

    @Test
    public void test0() {
        new Thread() {
            @Override
            public void run() {
                System.out.println("thead run start");
                LockSupport.unpark(this);
                LockSupport.park(this);
                System.out.println("thead run over");
            }
        }.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test1() {
        thread1 = new Thread() {
            @Override
            public void run() {
                for (char c : data1) {
                    System.out.println(c);
                    LockSupport.unpark(thread2);
                    LockSupport.park();
                }
            }
        };

        thread2 = new Thread() {
            @Override
            public void run() {
                for (char c : data2) {
                    LockSupport.park();
                    System.out.println(c);
                    LockSupport.unpark(thread1);
                }
            }
        };

        thread1.start();
        thread2.start();

        new Thread() {
            @Override
            public void run() {
                System.out.println("thread 3 start");
                LockSupport.unpark(this);
                LockSupport.park();
                System.out.println("thread 3 end");
            }
        }.start();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    Lock lock = new ReentrantLock();
    Condition condition1 = lock.newCondition();
    Condition condition2 = lock.newCondition();

    @Test
    public void test2() {
        thread1 = new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (char c : data1) {
                    lock.lock();
                    System.out.println(c);
                    thread1Run = true;
                    condition2.signal();
                    try {
                        condition1.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                condition2.signal();
            }
        };

        thread2 = new Thread() {
            @Override
            public void run() {
                while (!thread1Run) {

                }
                for (char c : data2) {
                    lock.lock();
                    System.out.println(c);
                    condition1.signal();
                    try {
                        condition2.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                condition1.signal();
            }
        };

        thread1.start();
        thread2.start();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    final Object o = new Object();

    @Test
    public void test3() {
        thread1 = new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (char c : data1) {
                    synchronized (o) {
                        System.out.println(c);
                        thread1Run = true;
                        o.notify();
                        try {
                            o.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        };

        thread2 = new Thread() {
            @Override
            public void run() {
                while (!thread1Run) {

                }
                for (char c : data2) {
                    synchronized (o) {
                        System.out.println(c);
                        o.notify();
                        try {
                            o.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        };

        thread1.start();
        thread2.start();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //ArrayBlockingQueue
    @Test
    public void test4() {
        ArrayBlockingQueue<Byte> blockingQueue1 = new ArrayBlockingQueue<>(1);
        ArrayBlockingQueue<Byte> blockingQueue2 = new ArrayBlockingQueue<>(1);
        Thread thread1 = new Thread() {
            @Override
            public void run() {
                try {
                    for (char c : data1) {
                        System.out.println(c);
                        blockingQueue2.add((byte) 0);
                        blockingQueue1.take();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread thread2 = new Thread() {
            @Override
            public void run() {
                try {
                    for (char c : data2) {
                        blockingQueue2.take();
                        System.out.println(c);
                        blockingQueue1.add((byte) 0);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        thread1.start();
        thread2.start();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public volatile int runThread = 1;

    @Test
    public void test5() {
        Thread thread1 = new Thread() {
            @Override
            public void run() {
                for (char c : data1) {
                    while (runThread == 2) {

                    }
                    System.out.println(c);
                    runThread = 2;
                }
            }
        };
        Thread thread2 = new Thread() {
            @Override
            public void run() {
                for (char c : data2) {
                    while (runThread == 1) {

                    }
                    System.out.println(c);
                    runThread = 1;
                }
            }
        };

        thread1.start();
        thread2.start();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //Semaphore
    @Test
    public void test6() {

    }

    //Exchanger
    @Test
    public void test7() {

    }

    //TransferQueue
    @Test
    public void test8() {

    }
}
