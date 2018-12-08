package org.inkscape.shapes;

public class Line extends Point {
    protected int x2;
    protected int y2;

    public Line(int x1, int y1, int x2, int y2) {
        super(x1, y1);
        this.x2 = x2;
        this.y2 = y2;
    }

    @Override
    public int[][] draw(int[][] matrix) {
        if(y1 == y2 && x1 != x2) {
            for(int i=0; i<matrix.length; i++) {
                for(int j=0; j<matrix[0].length; j++){
                    if(i >= x1 && i <= x2 && j == y1) {
                        matrix[i][j] = 1;
                    }
                }
            }
        }
        else if(x1 == x2 && y1 != y2) {
            for(int i=0; i<matrix.length; i++) {
                for(int j=0; j<matrix[0].length; j++){
                    if(j >= y1 && j <= y2 && i == x1) {
                        matrix[i][j] = 1;
                    }
                }
            }
        }
        else {
            int dx = Math.abs(x2 - x1);
            int dy = Math.abs(y2 - y1);

            if (dy == dx) {
                if(x1 > x2 && y1 > y2) {
                    while(x2 <= x1 && y2 <= y1) {
                        matrix[x2][y2] = 1;
                        x2++;
                        y2++;
                    }
                }
                else if(x1 > x2 && y1 < y2) {
                    while(x2 <= x1 && y1 <= y2) {
                        matrix[x2][y2] = 1;
                        x2++;
                        y2--;
                    }
                }
                else if(x1 < x2 && y1 > y2) {
                    while(x1 <= x2 && y2 <= y1) {
                        matrix[x1][y1] = 1;
                        x1++;
                        y1--;
                    }
                }
                else if(x1 < x2 && y1 < y2) {
                    while(x1 <= x2 && y1 <= y2) {
                        matrix[x1][y1] = 1;
                        x1++;
                        y1++;
                    }
                }
            }
            else {
                int slope = dy>dx ? dy/dx : dx/dy;

                if(x2 > x1 && y2 > y1) {
                    matrix[x1][y1] = 1;

                    while(y1 < y2 && x1 < x2) {
                        y1++;

                        for(int i=0; i<slope; i++) {
                            x1++;
                            matrix[x1][y1] = 1;
                        }
                    }

                    while(x1 < x2) {
                        x1++;
                        matrix[x1][y1] = 1;
                    }

                    while(y1 < y2) {
                        y1++;
                        matrix[x1][y1] = 1;
                    }
                }
                else if(x2 > x1 && y2 < y1) {
                    matrix[x1][y1] = 1;

                    while(x2 > x1 && y2 < y1) {
                        y1--;

                        for(int i=0; i<slope; i++) {
                            x1++;
                            matrix[x1][y1] = 1;
                        }
                    }

                    while(x1 < x2) {
                        x1++;
                        matrix[x1][y1] = 1;
                    }

                    while(y1 > y2) {
                        y1--;
                        matrix[x1][y1] = 1;
                    }
                }
                else if(x2 < x1 && y2 > y1) {
                    matrix[x1][y1] = 1;

                    while(x2 < x1 && y1 < y2) {
                        y1++;

                        for(int i=0; i<slope; i++) {
                            x1--;
                            matrix[x1][y1] = 1;
                        }
                    }

                    while(x1 > x2) {
                        x1--;
                        matrix[x1][y1] = 1;
                    }

                    while(y1 < y2) {
                        y1++;
                        matrix[x1][y1] = 1;
                    }
                }
                else if(x2 < x1 && y2 < y1) {
                    matrix[x1][y1] = 1;

                    while(x2 < x1 && y1 > y2) {
                        y1--;

                        for(int i=0; i<slope; i++) {
                            x1--;
                            matrix[x1][y1] = 1;
                        }
                    }

                    while(x1 > x2) {
                        x1--;
                        matrix[x1][y1] = 1;
                    }

                    while(y1 > y2) {
                        y1--;
                        matrix[x1][y1] = 1;
                    }
                }
            }
        }

        return matrix;
    }

    public int[][] drawTriangleLine(int matrix[][], int x1, int y1, int x2, int y2) {
        Line line = new Line(x1, y1, x2, y2);
        line.draw(matrix);

        return matrix;
    }
}
