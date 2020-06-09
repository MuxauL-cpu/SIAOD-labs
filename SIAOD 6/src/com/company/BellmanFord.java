package com.company;
public class BellmanFord {

    private int nodesNumber;
    int[] distances;
    int[][] matrix;

    public BellmanFord(int[][] matrix)
    {
        nodesNumber = matrix.length;
        this.matrix = matrix;
        distances = new int[nodesNumber];
        for (int i = 0; i < nodesNumber; i++)
            distances[i] = 1000;
        distances[0] = 0;
    }

    public int[] execute()
    {
        for (int i = 0; i < nodesNumber - 1; i++)
        {
            for (int j = 0; j < nodesNumber; j++)
            {
                for (int k = 0; k < matrix.length; k++)
                {
                    if (matrix[j][k] != 0 && distances[j] + matrix[j][k]  < distances[k])
                        distances[k] = distances[j] + matrix[j][k];
                }
            }
        }
        return distances;
    }

}
