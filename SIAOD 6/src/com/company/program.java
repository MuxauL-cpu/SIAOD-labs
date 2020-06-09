package com.company;
import javax.swing.*;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class program {


    public static void main(String args[])
    {
        int adjacency_matrix[][];
        int number_of_vertices;
        Scanner scan = new Scanner(System.in);

        try
        {
            System.out.println("Number of nodes:");
            number_of_vertices = scan.nextInt();
            adjacency_matrix = new int[number_of_vertices][number_of_vertices];

            System.out.println("Weights:");
            for (int i = 0; i < number_of_vertices; i++)
            {
                for (int j = 0; j < number_of_vertices; j++)
                {
                    adjacency_matrix[i][j] = scan.nextInt();
                    if (i == j)
                    {
                        adjacency_matrix[i][j] = 0;
                        continue;
                    }
                }
            }
            Graph frame = new Graph(adjacency_matrix);

            long start = System.nanoTime();
            BellmanFord bellmanFord = new BellmanFord(adjacency_matrix);

            int[] result = bellmanFord.execute();


            long end = System.nanoTime();
            long duration = end - start;
            System.out.println("Calculation time: " + (duration / 1000000L) + " milliseconds");

            System.out.println("All shortest paths from start vertex: " + Arrays.toString(result));

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1000, 1000);
            frame.setVisible(true);

        } catch (InputMismatchException inputMismatch)
        {
            System.out.println("Wrong Input Format");
        }
        scan.close();
    }
}
