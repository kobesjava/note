package com.example.myapplication.lock;

import org.junit.Test;

import java.util.concurrent.Exchanger;

public class ExchangerTest {

    Exchanger<String> exchanger = new Exchanger<>();

    public void t1() {
        try {
            String s = exchanger.exchange("t1");
            System.out.println("t1 exchanger " + s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void t2() {
        try {
            String s = exchanger.exchange("t2");
            System.out.println("t2 exchanger " + s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test() {
        new Thread(this::t1).start();
        new Thread(this::t2).start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
