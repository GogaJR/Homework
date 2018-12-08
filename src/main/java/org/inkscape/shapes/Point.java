package org.inkscape.shapes;

import java.util.LinkedList;
import java.util.Queue;

public class Point {
    protected int x1;
    protected int y1;

    public Point(int x1, int y1) {
        this.x1 = x1;
        this.y1 = y1;
    }

    public int[][] draw(int[][] matrix) {
        matrix[x1][y1] = 1;

        return matrix;
    }

    public int[][] fill(int[][] matrix) {
        if(matrix[x1][y1] == 1) {
            return matrix;
        }

        Queue<Point> pointQueue = new LinkedList<>();
        pointQueue.add(new Point(x1 , y1));

        do {
            Point point = pointQueue.poll();

            int x = point.x1;
            int y = point.y1;
            matrix[x][y] = 1;

            if(x != matrix.length-1) {
                if(matrix[x+1][y] != 1) {
                    if(!pointQueue.contains(new Point(x+1, y))) {
                        pointQueue.add(new Point(x+1, y));
                    }
                }
            }

            if(x != 0) {
                if(matrix[x-1][y] != 1) {
                    if(!pointQueue.contains(new Point(x-1, y))) {
                        pointQueue.add(new Point(x-1, y));
                    }
                }
            }

            if(y != matrix[0].length-1) {
                if(matrix[x][y+1] != 1) {
                    if(!pointQueue.contains(new Point(x, y+1))) {
                        pointQueue.add(new Point(x, y+1));
                    }
                }
            }

            if(y != 0) {
                if(matrix[x][y-1] != 1) {
                    if(!pointQueue.contains(new Point(x, y-1))) {
                        pointQueue.add(new Point(x, y-1));
                    }
                }
            }
        }while (!pointQueue.isEmpty());

        return matrix;
    }


    @Override
    public boolean equals(Object object) {
        Point point = (Point) object;

        if(point.x1 == this.x1 && point.y1 == this.y1) {
            return true;
        }

        return false;
    }
}
