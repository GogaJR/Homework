package org.inkscape.shapes;

public class Triangle extends Line {
    private int x3;
    private int y3;

    public Triangle(int x1, int y1, int x2, int y2, int x3, int y3) {
        super(x1, y1, x2, y2);
        this.x3 = x3;
        this.y3 = y3;
    }

    @Override
    public int[][] draw(int[][] matrix) {
        matrix = super.drawTriangleLine(matrix, x1, y1, x2, y2);
        matrix = super.drawTriangleLine(matrix, x1, y1, x3, y3);
        matrix = super.drawTriangleLine(matrix, x2, y2, x3, y3);

        return matrix;
    }
}
