package com.company;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static int input() {
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        return num;
    }

    public static int output(int x) {return x;}

    public static void quickSort(int[] arr, int low, int high, int count) {
        if (arr == null || arr.length == 0)
            return;

        if (low >= high)
            return;

        int middle = low + (high - low) / 2;
        int pivot = arr[middle];

        int i = low, j = high;
        while (i <= j) {
            i++;
        }

        while (arr[j] > pivot) {
            j--;
        }

        if (i <= j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;
            i--;
            count++;
        }
    }

    public static void main(String[] args) {

        System.out.println("Введите количество элементов");
        Scanner in = new Scanner(System.in);
        int ch = in.nextInt();
        int []arr;
        arr = new int[ch];
        System.out.println("Введите элементы массива");

        for (int i = 0; i < arr.length; i++) {
            arr[i] = input();
        }

        int n = arr.length;
        long start = System.nanoTime();
        int count = 0;
        int low = 0;
        int high = arr.length - 1;
        quickSort(arr,0, arr.length - 1,count);
        long finish = System.nanoTime();
        long time = finish - start;
        System.out.println("Time: " + time);
    }
}
