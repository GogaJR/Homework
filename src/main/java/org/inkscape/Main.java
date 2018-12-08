package org.inkscape;

import java.util.Scanner;
import java.io.*;
import org.inkscape.shapes.*;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static Point point;
    private static Line line;
    private static Triangle triangle;

    private static int inputNumber(String coordinateName, int border) {
        int number = -1;

        do {
            System.out.print(coordinateName);
            if(scanner.hasNextInt()) {
                number = scanner.nextInt();
            }
            else {
                scanner.next();
                System.out.println("Enter an integer!");
                continue;
            }

            if(number<0 || number>border) {
                System.out.println("Enter an integer from 0 to " + border);
            }
        }while(number<0 || number>border);

        return number;
    }

    public static void main(String[] args) {
        int matrix[][]= new int[1024][768];

        while(true) {
            System.out.println("(E) Export as an image");
            System.out.println("(C) Create a shape");
            System.out.println("(F) Fill at point");
            System.out.println("(Q) Quit\n");

            System.out.print("Enter the command: ");
            char input = scanner.next().toUpperCase().charAt(0);

            if(input == 'C') {
                boolean flag = true;

                while(flag) {
                    System.out.println("\n(P) Point");
                    System.out.println("(L) Line");
                    System.out.println("(T) Triangle\n");
                    System.out.print("Enter the shape type: ");
                    input = scanner.next().toUpperCase().charAt(0);

                    if(input == 'P') {
                        System.out.println("\nEnter the coordinates: ");

                        int x = inputNumber("x: ", matrix.length-1);
                        int y = inputNumber("y: ", matrix[0].length-1);

                        point = new Point(x, y);
                        matrix = point.draw(matrix);
                        flag = false;
                    } else if(input == 'L') {
                        System.out.println("\nEnter the coordinates: ");

                        int x1 = inputNumber("x1: ", matrix.length-1);
                        int y1 = inputNumber("y1: ", matrix[0].length-1);

                        int x2 = inputNumber("x2: ", matrix.length-1);
                        int y2 = inputNumber("y2: ", matrix[0].length-1);

                        line = new Line(x1, y1, x2, y2);
                        matrix = line.draw(matrix);
                        flag = false;
                    } else if(input == 'T') {
                        System.out.println("\nEnter the coordinates: ");

                        int x1 = inputNumber("x1: ", matrix.length-1);
                        int y1 = inputNumber("y1: ", matrix[0].length-1);

                        int x2 = inputNumber("x2: ", matrix.length-1);
                        int y2 = inputNumber("y2: ", matrix[0].length-1);

                        int x3 = inputNumber("x3: ", matrix.length-1);
                        int y3 = inputNumber("y3: ", matrix[0].length-1);

                        triangle = new Triangle(x1, y1, x2, y2, x3, y3);
                        matrix = triangle.draw(matrix);
                        flag = false;
                    }
                    else {
                        System.out.println("Enter the valid command!\n");
                        continue;
                    }
                }
            } else if(input == 'F') {
                System.out.println("\nEnter the coordinates: ");

                int x = inputNumber("x: ", matrix.length-1);
                int y = inputNumber("y: ", matrix[0].length-1);

                point = new Point(x, y);
                matrix = point.fill(matrix);
            } else if(input == 'E') {
                FileOutputStream fos = null;

                try {
                    fos = new FileOutputStream("C://Users//gogag//Desktop//Shapes.pbm");
                }catch(FileNotFoundException e) {
                    System.out.println("File Not Found.");
                }

                try {
                    fos.write("P1".getBytes());
                    fos.write("\n".getBytes());
                    fos.write(String.valueOf(matrix.length).getBytes());
                    fos.write(" ".getBytes());
                    fos.write(String.valueOf(matrix[0].length).getBytes());
                    fos.write("\n".getBytes());

                    for(int i=0; i<matrix.length; i++) {
                        for(int j=0; j<matrix[0].length; j++) {
                            fos.write(String.valueOf(matrix[i][j]).getBytes());
                            fos.write(" ".getBytes());
                        }

                        fos.write("\n".getBytes());
                    }
                }catch(IOException e) {
                    System.out.println("I/O Exception.");
                }
            } else if(input == 'Q') {
                return;
            } else {
                System.out.println("Enter the valid command!\n");
            }
        }
    }
}
