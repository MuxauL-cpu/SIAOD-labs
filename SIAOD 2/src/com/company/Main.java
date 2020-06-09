package com.company;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;

public class Main {

    //Метод для ввода данных
    public static int inputInt() {
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        return num;
    }

    //Метод для добавления числа
    public static int [] add(int [] arr, int addInt) {
        int [] buf = new int[arr.length+1];
        for (int i=0; i<arr.length; i++)
        {
            buf[i] = arr[i];
        }
        buf[arr.length] = addInt;
        return buf;
    }

    //Метод для удаления числа
    public static int [] delete(int [] arr, int delInt) {
        int [] buf = new int[arr.length - 1];
        arr[delInt] = 2000000000;
        Arrays.sort(arr);
        for (int i=0; i<arr.length - 1; i++)
        {
            buf[i] = arr[i];
        }
        return buf;
    }

    //Метод вывода числового массива
    public static void show(int [] arr) {
        System.out.println("Данный массив:\r\n" + Arrays.toString(arr) + "\r\n");
    }

    public static int binarySearch(int[] sortedArray, int key, int l, int r) {
        if(r >= l)
        {
            int mid = l+(r-l)/2;
            if(sortedArray[mid] == key)
                return mid;
            if(sortedArray[mid] > key)
            {
                return binarySearch(sortedArray,key,l,mid-1);
            }
            return binarySearch(sortedArray,key,mid+1,r);
        }
        return -1;
    }

    public static void main(String[] args) {
        long start;
        long finish;
        long SF;

        int [] array;
        int key = 10;

        System.out.println("Введите длину генерируемого массива:");
        array = new int[inputInt()];
        System.out.println();

        for (int i=0; i<array.length; i++)
        {
            array[i]=(int) Math.round(Math.random() * 100);
        }
        Arrays.sort(array);

        int l = 0;
        int r = array.length - 1;

        do
        {
            do
            {
                System.out.println("|1. Добавить | |2. Бинарный поиск | |3. Удалить элемент | |4. Вывести массив | |5. Поиск Java | |0. Выход | ");
                key = inputInt();

            } while ((key != 1) && (key != 2) && (key != 3) && (key != 4) && (key != 5) && (key != 0));

            switch (key) {

                case  (1):
                    System.out.println("Введите число для добавления:");
                    int [] addBuf = add(array, inputInt());
                    array = new int[addBuf.length];
                    array = addBuf;
                    Arrays.sort(array);
                    System.out.println("Число добавлено\r\n");
                    break;

                case (2):
                    System.out.println("Введите число для поиска:");
                    int whatFind = inputInt();
                    start = System.nanoTime();
                    int index = binarySearch(array, whatFind, l, r);
                    finish = System.nanoTime();
                    SF = finish - start;
                    if (index != -1) {
                        System.out.println("Число " + whatFind + " находится в ячейке " + index);
                        System.out.println("Время поиска: " + SF + "\r\n");
                    }
                    else{
                        System.out.println("Данного числа нет в масииве\r\n");
                    }
                    break;

                case (3):
                    System.out.println("Введите число для удаления:");
                    int indexDel = binarySearch(array, inputInt(), l, r);
                    if (indexDel == -1) {
                        System.out.println("Данного числа нет в масииве\r\n");
                        break;
                    }
                    int [] delBuf = delete(array, indexDel);
                    array = new int[delBuf.length];
                    array = delBuf;
                    Arrays.sort(array);
                    System.out.println("Элемент массива удалён\r\n");
                    break;

                case (4):
                    show(array);
                    break;

                case (5):
                    System.out.println("Введите число для поиска:");
                    int whatFindJava = inputInt();
                    start = System.nanoTime();
                    int indexJava = Arrays.binarySearch(array, whatFindJava);
                    finish = System.nanoTime();
                    SF = finish - start;
                    if (indexJava != -1) {
                        System.out.println("Число " + whatFindJava + " находится в ячейке " + indexJava);
                        System.out.println("Время поиска: " + SF + "\r\n");
                    }
                    else{
                        System.out.println("Данного числа нет в масииве\r\n");
                    }
                    break;
            }

        } while (key != 0);
    }
}