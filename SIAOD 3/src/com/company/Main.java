package com.company;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    // Ввод целых чисел
    public static int InputInt() {
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        return num;
    }

    // Ввод строки и подстроки
    public static String inputStr() {
        Scanner in = new Scanner(System.in);
        String arr = in.nextLine();
        return arr;
    }

    // Вывод строки и подстроки
    public static void show(String arr, String uArr) {
        System.out.println("Строка: " + arr);
        System.out.println("Подстрока: " + uArr);
    }

    public static int getFirstEntry(String arr, String uArr) {
        int arrLen = arr.length();
        int uArrLen = uArr.length();

        if (uArrLen > arrLen) {
            return -1;
        }

        // Каждый элемент таблицы это пара (символ и число)
        HashMap<Character, Integer> offSetTable = new HashMap<Character, Integer>();

        for (int i = 0; i <= 255; i++) {
            offSetTable.put((char) i, uArrLen);
        }

        // Определяем смещения
        for (int i = 0; i < uArrLen - 1; i++) {
            offSetTable.put(uArr.charAt(i), uArrLen - i - 1);
        }

        int i = uArrLen - 1;
        int j = i;
        int k = i;

        while (j >= 0 && i <= arrLen - 1) {
            j = uArrLen - 1;
            k = i;

            // Если совпадает - смещается
            while (j >= 0 && arr.charAt(k) == uArr.charAt(j)) {
                System.out.println("Совпало под индексом: " + k + " " + arr.charAt(k));
                k--;
                j--;
            }
            i += offSetTable.get(arr.charAt(i));
        }
        if (k >= arrLen - uArrLen) {
            return -1;
        } else {
            return k + 1;
        }
    }

    // Стандартный метод поиска с использованием indexOf
    public static int[] JavaSearch(String uArr, String arr) {
        int [] indx = new int [100];
        int count = 0;
        int index = 0;

        for (int i = 0; i < arr.length(); i++) {
            index = arr.indexOf(uArr, i);
            if (index > -1) {
                indx[count] = index;
                i = index;
                count++;
            }
        }
        return Arrays.copyOfRange(indx, 0, count);
    }

    public static void main(String[] args) {
        String Array = null;
        String underArray = null;
        int key = 10;

        long start;
        long finish;
        long result;

        do {
            do {
                System.out.println("|1. Ввести строку и подстроку| |2. Поиск методом Бойера-Мура | " +
                        "|3. Показать строку и подстроку | |4. Поиск стандартным методом | |5. Выйти |");
                key = InputInt();
            } while ((key != 1) && (key != 2) && (key != 3) && (key != 4) && (key != 5));

            switch (key) {

                case(1):
                    System.out.println("Необходимо ввести строки и подстроки. Внимание, поиск чувствителен к регистру!");

                    System.out.println("Введите строку: ");
                    Array = inputStr();

                    System.out.println("Введите подстроку: ");
                    underArray = inputStr();

                    System.out.println("Строка и подстрока добавлена\r\n");
                    break;

                case(2):
                    start = System.nanoTime();

                    getFirstEntry(Array, underArray);

                    finish = System.nanoTime();
                    result = finish - start;

                    System.out.println("Время поиска " + result + "\r\n");
                    break;

                case(3):
                    show(Array, underArray);
                    break;

                case(4):
                    start = System.nanoTime();

                    System.out.println("Кол-во совпадений " + JavaSearch(underArray, Array).length + " в следующих индексах " +
                            Arrays.toString(JavaSearch(underArray, Array)));

                    finish = System.nanoTime();
                    result = finish - start;

                    System.out.println("Время поиска " + result + "\r\n");
                    break;
            }
        } while (key != 5);
    }
}
