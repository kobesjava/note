package com.example.myapplication.sort;

import org.junit.Test;

//希尔排序 间隔的插入排序
//稳定
//O(n^1.3)
public class SortShellTest {

    int[] data = {4, 5, 2, 8, 7, 6, 9, 1, 3, 0, 21, 23, 28, 32, 54, 31, 76, 53, 89, 77, 65, 2, 12, 32};

    @Test
    public void test() {
        for (int gap = 4; gap > 0; gap >>= 1) {
            for (int i = gap; i < data.length; i++) {
                for (int j = i; j - gap >= 0; j -= gap) {
                    if (data[j - gap] > data[j]) {
                        int temp = data[j];
                        data[j] = data[j - gap];
                        data[j - gap] = temp;
                    }
                }
            }
        }
        for (int i = 0; i < data.length; i++) {
            System.out.print(data[i] + " ");
        }
    }

    //优化
    @Test
    public void test1() {

        //Knuth序列 比二分序列快
        int gap = 1;
        while (gap <= data.length / 3) {
            gap = 3 * gap + 1;
        }
        System.out.println(gap);

        for (; gap > 0; gap = ( gap - 1 ) / 3) {
            for (int i = gap; i < data.length; i++) {
                for (int j = i; j - gap >= 0; j = j - gap) {
                    if (data[j - gap] > data[j]) {
                        int temp = data[j];
                        data[j] = data[j - gap];
                        data[j - gap] = temp;
                    }
                }
            }
        }

        for (int i = 0; i < data.length; i++) {
            System.out.print(data[i] + " ");
        }
    }

}
