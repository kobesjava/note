package com.example.myapplication.sort;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;

//计数排序 桶排序的一种 非比较排序
//适合数据量大 范围小
//时间复杂度 N+K
//空间复杂度 N+K
public class SortCountTest {

    int[] data = {4, 5, 2, 8, 4, 6, 9, 5, 1, 3};

    @Test
    public void test() {

        int[] countArr = new int[10];

        for (int val : data) {
            countArr[val]++;
        }

//        for (int index = 0, j = 0; index < data.length; index++) {
//            while (countArr[j] <= 0) {
//                j++;
//            }
//            countArr[j]--;
//            data[index] = j;
//        }

        for (int i = 0, j = 0; i < countArr.length; i++) {
            while (countArr[i] > 0) {
                countArr[i]--;
                data[j++] = i;
            }
        }

        System.out.println(Arrays.toString(data));
    }

    //稳定排序
    @Test
    public void test1() {
        int[] countArr = new int[10];

        for (int val : data) {
            countArr[val]++;
        }
        System.out.println(Arrays.toString(countArr));

        for (int i = 1; i < countArr.length; i++) {
            countArr[i] = countArr[i - 1] + countArr[i];
        }
        System.out.println(Arrays.toString(countArr));

        int[] newData = new int[data.length];
        for (int i = data.length - 1; i >= 0; i--) {
            newData[--countArr[data[i]]] = data[i];
        }
        System.out.println(Arrays.toString(newData));
    }

    //稳定排序
    @Test
    public void test3() {
        Person[] data = new Person[10];
        data[0] = new Person(4, "first");
        data[1] = new Person(5, "first");
        data[2] = new Person(2, "first");
        data[3] = new Person(8, "first");
        data[4] = new Person(4, "second");
        data[5] = new Person(6, "first");
        data[6] = new Person(9, "first");
        data[7] = new Person(5, "secod");
        data[8] = new Person(1, "first");
        data[9] = new Person(3, "first");

        int[] countArr = new int[10];

        for (Person person : data) {
            countArr[person.age]++;
        }
        System.out.println(Arrays.toString(countArr));

        for (int i = 1; i < countArr.length; i++) {
            countArr[i] = countArr[i - 1] + countArr[i];
        }
        System.out.println(Arrays.toString(countArr));

        Person[] newData = new Person[data.length];
        for (int i = data.length - 1; i >= 0; i--) {
            newData[--countArr[data[i].age]] = data[i];
        }
        for (Person person : newData) {
            System.out.print(person.age + "$" + person.name + " ");
        }
    }

    //稳定排序
    @Test
    public void test4() {
        Person[] data = new Person[10];
        data[0] = new Person(4, "first");
        data[1] = new Person(5, "first");
        data[2] = new Person(2, "first");
        data[3] = new Person(8, "first");
        data[4] = new Person(4, "second");
        data[5] = new Person(6, "first");
        data[6] = new Person(9, "first");
        data[7] = new Person(5, "secod");
        data[8] = new Person(1, "first");
        data[9] = new Person(3, "first");

        LinkedList<Person>[] countArr = new LinkedList[10];

        for (Person val : data) {
            if (countArr[val.age] == null) {
                countArr[val.age] = new LinkedList<>();
            }
            countArr[val.age].add(val);
        }

        for (int i = 0, j = 0; i < countArr.length; i++) {
            while (countArr[i] != null && countArr[i].size() > 0) {
                data[j++] = countArr[i].removeFirst();
            }
        }

        for (Person person : data) {
            System.out.print(person.age + "$" + person.name + " ");
        }
    }

    static class Person {
        int age;
        String name;

        public Person(int age, String name) {
            this.age = age;
            this.name = name;
        }
    }

}
