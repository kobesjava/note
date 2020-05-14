package com.example.myapplication.sort;

import org.junit.Test;

//插入排序
//稳定
//O(n^2)
//最好的O(n)
public class SortInsertTest {

    int[] data = {4, 5, 2, 8, 7, 6, 9, 1, 3, 0};
    //int[] data = {3, 4, 2};
    //int[] data = {2, 4, 5, 8, 7};

    @Test
    public void test() {
        for (int i = 1; i < data.length; i++) {
            for (int j = i; j > 0; j--) {
                if (data[j] < data[j - 1]) {
                    int temp = data[j];
                    data[j] = data[j - 1];
                    data[j - 1] = temp;
                } else if (data[j] > data[j - 1]) {
                    break;
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
        int swapPosition;
        int swapVal;
        for (int i = 1; i < data.length; i++) {
            swapPosition = i;
            swapVal = data[i];
            for (int j = i; j - 1 >= 0; j--) {
                if (swapVal < data[j - 1]) {
                    data[j] = data[j - 1];
                    swapPosition = j - 1;
                } else if (data[swapPosition] > data[j - 1]) {
                    break;
                }
            }
            if (swapPosition != i) {
                data[swapPosition] = swapVal;
            }
            for (int k = 0; k < data.length; k++) {
                System.out.print(data[k] + " ");
            }
            System.out.println();
        }
    }

    //二分插入
    @Test
    public void test2() {
        for (int i = 1; i < data.length; i++) {
            int insertP = selectInsertPosition(0, i - 1, i);
            if (insertP != i) {
                int temp = data[i];
                for (int j = i; j > insertP; j--) {
                    data[j] = data[j - 1];
                }
                data[insertP] = temp;
            }
            for (int k = 0; k < data.length; k++) {
                System.out.print(data[k] + " ");
            }
            System.out.println();
        }
        System.out.println();

    }

    public int selectInsertPosition(int start, int end, int select) {
        if (start == end) {
            if (data[end] > data[select]) {
                return end;
            } else {
                return select;
            }
        }
        if (data[start + ( end - start ) / 2] > data[select]) {
            return selectInsertPosition(start, start + ( end - start ) / 2, select);
        } else {
            return selectInsertPosition(start + ( end - start ) / 2 + 1, end, select);
        }
    }
}
