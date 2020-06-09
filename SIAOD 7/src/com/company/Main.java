package com.company;

import java.util.*;
import java.io.FileReader;
import java.io.BufferedReader;

import javax.swing.JFrame;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

public class Main extends JFrame {

    //Вывод поля и пути
    public Main(Integer[][] matrix, int matrixHeight, int matrixWidth) {
        super("Ортогонально-диагональный лучевой алгоритм");

        mxGraph graph = new mxGraph();
        Object defaultParent = graph.getDefaultParent();
        graph.getModel().beginUpdate();

        Object v [][] = new Object[matrixHeight][matrixWidth];

        for (int i = 0 ; i < matrixHeight; i++){
            for (int j = 0; j < matrixWidth; j++){
                switch (matrix[i][j]) {
                    case (0):
                        v[i][j] = graph.insertVertex(defaultParent, null, "", j * 30, i * 30, 30, 30, "fillColor=white");
                        break;
                    case (1):
                        v[i][j] = graph.insertVertex(defaultParent, null, "", j * 30, i * 30, 30, 30, "fillColor=red");
                        break;
                    case (3):
                        v[i][j] = graph.insertVertex(defaultParent, null, "End", j * 30, i * 30, 30, 30, "fillColor=lightblue");
                        break;
                    case (4):
                        v[i][j] = graph.insertVertex(defaultParent, null, "", j * 30, i * 30, 30, 30, "fillColor=yellow");
                        break;
                }
            }
        }

        v[start_y][start_x] = graph.insertVertex(defaultParent, null, "Start", start_x * 30, start_y * 30, 30, 30, "fillColor=lightgreen");

        graph.getModel().endUpdate();
        mxGraphComponent graphComponent = new mxGraphComponent(graph);
        getContentPane().add(graphComponent);

        graph.setCellsEditable(false);
        graph.setCellsMovable(false);
        graph.setCellsResizable(false);
        graph.setCellsSelectable(false);
        graph.setEnabled(false);
        graphComponent.setConnectable(false);
    }

    public static int start_x;
    public static int start_y;

    //Функция для нахождения пути, лучевой ортогональный поиск
    public static void Map(Integer[][] matrix, int matrixHeight, int matrixWidth){
        int point_x = 0, point_y = 0, point_x_end = 0, point_y_end = 0;

//Находим начальную и конечную точку
        for (int i = 0; i < matrixHeight; i++) {
            for (int j = 0; j < matrixWidth; j++) {
                if (matrix[i][j] == 2){
                    point_x = j;
                    point_y = i;
                    start_x = j;
                    start_y = i;
                }
                if (matrix[i][j] == 3){
                    point_x_end = j;
                    point_y_end = i;
                }
            }
        }

        double distL = 0, distR = 0, distB = 0, distH = 0, distHL = 0, distHR = 0, distBL = 0, distBR = 0;
        while (matrix[point_y][point_x] != matrix[point_y_end][point_x_end]) {

//Если все соседние клетки заблокированы, путь не найден
            if (
                    (matrix[point_y][point_x + 1] == 1 || matrix[point_y][point_x + 1] == 4) &&
                            (matrix[point_y - 1][point_x] == 1 || matrix[point_y - 1][point_x] == 4) &&
                            (matrix[point_y][point_x - 1] == 1 || matrix[point_y][point_x - 1] == 4) &&
                            (matrix[point_y + 1][point_x] == 1 || matrix[point_y + 1][point_x] == 4) &&

                            (matrix[point_y - 1][point_x + 1] == 1 || matrix[point_y - 1][point_x + 1] == 4) &&
                            (matrix[point_y - 1][point_x - 1] == 1 || matrix[point_y - 1][point_x - 1] == 4) &&
                            (matrix[point_y + 1][point_x - 1] == 1 || matrix[point_y + 1][point_x - 1] == 4) &&
                            (matrix[point_y + 1][point_x + 1] == 1 || matrix[point_y + 1][point_x + 1] == 4)){
                System.out.println("Путь не найден");
                matrix[point_y][point_x] = 4;
                return;
            }

//Если соседняя клетка открыта, находит расстояние от неё до конечной клетки пути
            if (matrix[point_y][point_x + 1] != 1 && matrix[point_y][point_x + 1] != 4) {
                distR = Math.sqrt(Math.pow(Math.abs(point_x_end - (point_x + 1)),2) + Math.pow(Math.abs(point_y_end-point_y),2));
            }
            else{
                distR = Integer.MAX_VALUE;
            }
            if (matrix[point_y - 1][point_x] != 1 && matrix[point_y - 1][point_x] != 4) {
                distH = Math.sqrt(Math.pow(Math.abs(point_x_end - point_x),2) + Math.pow(Math.abs(point_y_end-(point_y-1)),2));
            }
            else{
                distH = Integer.MAX_VALUE;;
            }
            if (matrix[point_y][point_x - 1] != 1 && matrix[point_y][point_x - 1] != 4) {
                distL = Math.sqrt(Math.pow(Math.abs(point_x_end - (point_x - 1)),2) + Math.pow(Math.abs(point_y_end-point_y),2));
            }
            else{
                distL = Integer.MAX_VALUE;;
            }
            if (matrix[point_y + 1][point_x] != 1 && matrix[point_y + 1][point_x] != 4) {

                distB = Math.sqrt(Math.pow(Math.abs(point_x_end - point_x),2) + Math.pow(Math.abs(point_y_end-(point_y+1)),2));
            }
            else{
                distB = Integer.MAX_VALUE;;
            }

            if (matrix[point_y - 1][point_x - 1] != 1 && matrix[point_y - 1][point_x - 1] != 4) {
                distHL = Math.sqrt(Math.pow(Math.abs(point_x_end - (point_x-1)),2) + Math.pow(Math.abs(point_y_end-(point_y-1)),2));
            }
            else{
                distHL = Integer.MAX_VALUE;;
            }
            if (matrix[point_y - 1][point_x + 1] != 1 && matrix[point_y - 1][point_x + 1] != 4) {
                distHR = Math.sqrt(Math.pow(Math.abs(point_x_end - (point_x+1)),2) + Math.pow(Math.abs(point_y_end-(point_y-1)),2));
            }
            else{
                distHR = Integer.MAX_VALUE;;
            }
            if (matrix[point_y + 1][point_x - 1] != 1 && matrix[point_y + 1][point_x - 1] != 4) {
                distBL = Math.sqrt(Math.pow(Math.abs(point_x_end - (point_x - 1)),2) + Math.pow(Math.abs(point_y_end-(point_y+1)),2));
            }
            else{
                distBL = Integer.MAX_VALUE;;
            }
            if (matrix[point_y + 1][point_x + 1] != 1 && matrix[point_y + 1][point_x + 1] != 4) {
                distBR = Math.sqrt(Math.pow(Math.abs(point_x_end - (point_x + 1)),2) + Math.pow(Math.abs(point_y_end - (point_y + 1)),2));
            }
            else{
                distBR = Integer.MAX_VALUE;;
            }

//Сравнивает полученные расстояния от соседних клеток до конечной клетки пути, и выбирает наименьшее
            if (distR <= distL && distR <= distB && distR <= distH && distR <= distHL && distR <= distHR && distR <= distBL && distR <= distBR){
                matrix[point_y][point_x] = 4;
                point_x += 1;
            }
            else {
                if (distH <= distL && distH <= distB && distH <= distR && distH <= distHL && distH <= distHR && distH <= distBL && distH <= distBR) {
                    matrix[point_y][point_x] = 4;
                    point_y -= 1;
                } else {
                    if (distL <= distR && distL <= distB && distL <= distH && distL <= distHL && distL <= distHR && distL <= distBL && distL <= distBR) {
                        matrix[point_y][point_x] = 4;
                        point_x -= 1;
                    } else {
                        if (distB <= distL && distB <= distR && distR <= distH && distB <= distHL && distB <= distHR && distB <= distBL && distB <= distBR) {
                            matrix[point_y][point_x] = 4;
                            point_y += 1;
                        }
                        else {

                            if (distHL <= distL && distHL <= distR && distHL <= distH && distHL <= distB && distHL <= distHR && distHL <= distBL && distHL <= distBR) {
                                matrix[point_y][point_x] = 4;
                                point_x -= 1;
                                point_y -= 1;
                            } else {
                                if (distHR <= distL && distHR <= distR && distHR <= distH && distHR <= distB && distHR <= distHL && distHR <= distBL && distHR <= distBR) {
                                    matrix[point_y][point_x] = 4;
                                    point_x += 1;
                                    point_y -= 1;
                                } else {
                                    if (distBL <= distL && distBL<= distR && distBL <= distH && distBL <= distB && distBL <= distHL && distBL <= distHL && distBL <= distBR) {
                                        matrix[point_y][point_x] = 4;
                                        point_x -= 1;
                                        point_y += 1;
                                    }
                                    else{
                                        if(distBR <= distL && distBR<= distR && distBR <= distH && distBR <= distB && distBR <= distHL && distBR <= distHL && distBR <= distBL){
                                            matrix[point_y][point_x] = 4;
                                            point_x += 1;
                                            point_y += 1;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        System.out.println("Путь построен!");
    }

    //Загрузка матрицы состояния поля, вызов функции нахождения пути, установка времени, вызов GUI
    public static void main(String[] args) throws Exception {
        long startTime = 0 , finishTime = 0, time = 0;

        BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\User\\Desktop\\TEST.txt"));

        List<String> lines = new ArrayList<>();
        while (br.ready()) {
            lines.add(br.readLine());
        }
        int matrixWidth = lines.get(0).split(" ").length;
        int matrixHeight = lines.size();

        Integer[][] matrix = new Integer[matrixHeight][matrixWidth];

        for (int i = 0; i < matrixHeight; i++) {
            for (int j = 0; j < matrixWidth; j++) {
                String[] line = lines.get(i).split(" ");
                matrix[i][j] = Integer.parseInt(line[j]);
            }
        }

        startTime = System.nanoTime();
        Map(matrix, matrixHeight, matrixWidth);
        finishTime = System.nanoTime();
        time = finishTime - startTime;

        System.out.println("Время: " + time);

        Main frame = new Main(matrix, matrixHeight, matrixWidth);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(matrixWidth * 50,

                matrixHeight * 50);
        frame.setVisible(true);
    }
}