package com.example.myapplication.sort;

import org.junit.Test;

//归并排序
//O(N*logN)
//空间复杂度O(N)
public class SortMergeTest {

    int[] data = {4, 5, 2, 8, 7, 6, 9, 1, 3, 0, 21, 23, 28, 32, 54, 31, 76, 53, 89, 77, 65, 2, 12, 32};

    @Test
    public void test() {
        sort(data, 0, data.length - 1);
        for (int i = 0; i < data.length; i++) {
            System.out.print(data[i] + " ");
        }
        System.out.println();
    }

    public void sort(int[] arr, int left, int right) {
        if (left == right) return;
        sort(arr, left, left + ( right - left ) / 2);
        sort(arr, left + ( right - left ) / 2 + 1, right);
        merge(arr, left, left + ( right - left ) / 2 + 1, right);
    }

    public void merge(int[] arr, int leftStart, int rightStart, int rightEnd) {
        int[] temp = new int[rightEnd - leftStart + 1];
        int k = 0;
        int i = leftStart;
        int j = rightStart;
        while (i < rightStart && j <= rightEnd) {
            temp[k++] = arr[i] >= arr[j] ? arr[j++] : arr[i++];
        }
        while (i <= rightStart - 1) {
            temp[k++] = arr[i++];
        }
        while (j <= rightEnd) {
            temp[k++] = arr[j++];
        }
        for (int m = 0; m < temp.length; m++) {
            arr[leftStart + m] = temp[m];
        }
    }

}
