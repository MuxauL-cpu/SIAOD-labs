package com.company;

public class BubbleSort {
    int[] mas;

    public BubbleSort(int[] mas) {this.mas = mas;}

    public void swap (int []mas, int index) {
        int tmp = mas[index - 1];
        mas[index - 1] = mas[index];
        mas[index] = tmp;
    }

    public int []bubbleSort() {
        int []resMas = mas;
        for (int i = 1; i < mas.length; i++) {
            for (int j = resMas.length - 1; j >= 1; j--) {
                if(resMas[j - 1] > resMas[j]) {
                    swap(resMas, j);
                }
            }
        }
        return resMas;
    }
}
