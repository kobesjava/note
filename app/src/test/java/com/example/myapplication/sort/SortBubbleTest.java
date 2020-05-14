package com.example.myapplication.sort;

import org.junit.Test;

//冒泡排序
//稳定
//O(n^2)
//最好的O(n)
public class SortBubbleTest {

    //int[] data = {4, 5, 2, 8, 7, 6, 9, 1, 3, 0};
    //int[] data = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    int[] data = {4, 3, 2, 1, 5, 6, 7, 8, 9};


    @Test
    public void test() {
        for (int i = data.length - 1; i >= 0; i--) {
            for (int j = 0; j < i - 1; j++) {
                if (data[j] > data[j + 1]) {
                    int temp = data[j + 1];
                    data[j + 1] = data[j];
                    data[j] = temp;
                }
            }
            for (int k = 0; k < data.length; k++) {
                System.out.print(data[k] + " ");
            }
            System.out.println();
        }
    }

    //优化
    @Test
    public void test1() {
        boolean hasSwap = false;
        for (int i = 0; i < data.length; i++) {
            System.out.println("执行第" + i + "次");
            for (int j = 0; j < data.length - i - 1; j++) {
                if (data[j] > data[j + 1]) {
                    hasSwap = true;
                    int temp = data[j + 1];
                    data[j + 1] = data[j];
                    data[j] = temp;
                }
            }
            if (!hasSwap) {
                break;
            }
        }
        for (int k = 0; k < data.length; k++) {
            System.out.print(data[k] + " ");
        }
        System.out.println();
    }


    //优化2
    @Test
    public void test2() {
        int lastSwapPosition = -1;
        for (int i = 0; i < data.length; i++) {
            System.out.println("执行第" + i + "次");
            int last = lastSwapPosition;
            if (last == -1) {
                last = data.length - i - 1;
            }
            lastSwapPosition = -1;
            for (int j = 0; j < last; j++) {
                if (data[j] > data[j + 1]) {
                    lastSwapPosition = j + 1;
                    int temp = data[j + 1];
                    data[j + 1] = data[j];
                    data[j] = temp;
                }
            }
            if (lastSwapPosition == -1) {
                break;
            }
        }
        for (int k = 0; k < data.length; k++) {
            System.out.print(data[k] + " ");
        }
        System.out.println();
    }

}
