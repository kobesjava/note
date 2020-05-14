package com.example.myapplication.sort;

import org.junit.Test;

import java.util.Arrays;

//基数排序 先按照个位数排 再按照十位数排 再按照百位数排
//多关键字排序 其中用到了计数排序 LSD(从最小位开始排) MSD(从最大位开始排 递归的再排序每个桶)
//可用于字符串排序
//时间复杂度O(N*K)
//空间复杂度O(N)
public class SortRadixTest {

    int[] data = {421, 240, 115, 532, 305, 430, 124};

    @Test
    public void test() {
        int[] newData = new int[data.length];
        int[] countArr = new int[10];
        for (int index = 0; index < 3; index++) {
            int division = (int) Math.pow(10, index);
            for (int val : data) {
                int num = val / division % 10;
                countArr[num]++;
            }

            for (int i = 1; i < countArr.length; i++) {
                countArr[i] = countArr[i - 1] + countArr[i];
            }

            for (int i = data.length - 1; i >= 0; i--) {
                int num = data[i] / division % 10;
                newData[--countArr[num]] = data[i];
            }

            System.arraycopy(newData, 0, data, 0, newData.length);
            Arrays.fill(countArr, 0);
        }
        System.out.println(Arrays.toString(data));
    }

}
