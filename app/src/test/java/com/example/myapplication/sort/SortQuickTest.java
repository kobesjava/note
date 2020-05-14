package com.example.myapplication.sort;

import org.junit.Test;

//快速排序
//时间复杂度O(N*logN) 最差O(N*N)
//空间复杂度O(logN)
public class SortQuickTest {

    //int[] data = {4, 2, 8, 7, 6, 9, 1, 3, 5, 10, 6};
    int[] data = {4, 6};

    @Test
    public void test() {
        sort(0, data.length - 1);
    }

    private void sort(int left, int right) {
        if (left >= right) return;
        int mid = pp(left, right);
        sort(0, mid - 1);
        sort(mid + 1, right);
    }

    private int pp(int left, int right) {
        int pivo = right;
        int pivoVal = data[pivo];
        right = right - 1;
        while (left < right) {
            while (data[left] <= pivoVal && left <= right) {
                left++;
            }
            while (data[right] > pivoVal && right > left) {
                right--;
            }

            if (left < right) {
                int tempLeft = data[left];
                data[left] = data[right];
                data[right] = tempLeft;
            }
        }

        if (data[left] > pivoVal) {
            int tempLeft = data[left];
            data[left] = data[pivo];
            data[pivo] = tempLeft;
        }

        for (int i : data) {
            System.out.print(i + " ");
        }
        System.out.println();

        System.out.print(left + " " + right);
        System.out.println();

        return left;
    }

}
