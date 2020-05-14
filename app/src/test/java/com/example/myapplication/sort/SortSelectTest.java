package com.example.myapplication.sort;

import org.junit.Test;

//选择排序
//不稳定
//O(n^2)
public class SortSelectTest {

    int[] data = {4, 5, 2, 8, 7, 6, 9, 1, 3, 0};

    int minPosition = 0;

    @Test
    public void test() {
        for (int i = 0; i < data.length - 1; i++) {
            minPosition = i;
            for (int j = i + 1; j < data.length; j++) {
                if (data[j] < data[minPosition]) {
                    minPosition = j;
                }
            }
            if (minPosition != i) {
                int temp = data[i];
                data[i] = data[minPosition];
                data[minPosition] = temp;
            }
        }
        for (int i = 0; i < data.length; i++) {
            System.out.print(data[i] + " ");
        }
    }

    //优化
    int maxPosition = 0;

    @Test
    public void test1() {
        for (int i = 0; i < data.length / 2; i++) {
            minPosition = i;
            maxPosition = data.length - 1 - i;
            for (int j = i + 1; j < data.length - i; j++) {
                if (data[j] < data[minPosition]) {
                    minPosition = j;
                }
                if (data[j] > data[maxPosition]) {
                    maxPosition = j;
                }
            }

            if (minPosition != i) {
                int temp = data[i];
                data[i] = data[minPosition];
                data[minPosition] = temp;
            }

            if (maxPosition != data.length - 1 - i) {
                int temp = data[data.length - 1 - i];
                data[data.length - 1 - i] = data[maxPosition];
                data[maxPosition] = temp;
            }

            for (int k = 0; k < data.length; k++) {
                System.out.print(data[k] + " ");
            }
            System.out.println();
        }
    }

}
