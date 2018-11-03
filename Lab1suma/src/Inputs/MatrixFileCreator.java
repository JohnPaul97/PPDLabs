package Inputs;

import Domain.Complex;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import static Inputs.Constants.*;
import static com.sun.org.apache.xalan.internal.xsltc.compiler.Constants.DOUBLE_CLASS;
import static com.sun.org.apache.xalan.internal.xsltc.compiler.Constants.INTEGER_CLASS;

public final class MatrixFileCreator {

    private static final Random generator = new Random();

    public static void generateMatrixAndWriteToFile(String classname, int lines1, int columns1,
                                                    int lines2, int columns2) {
        String elementType = classname.substring(classname.lastIndexOf(".") + 1);
        String fileName = MATRIX_PREFIX + elementType + SEPARATOR + lines1 + SEPARATOR + columns1
                + SEPARATOR + lines2 + SEPARATOR + columns2;
        try (PrintWriter pw = new PrintWriter(new FileWriter(fileName))) {
            pw.println(classname);
            generateMatrix(classname, lines1, columns1, pw);
            generateMatrix(classname, lines2, columns2, pw);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void generateMatrix(String clasname, int lines, int columns, PrintWriter pw) {
        pw.println(lines + " " + columns);
        for (int i = 0; i < lines; i++) {
            for (int j = 0; j < columns; j++) {
                switch (clasname) {
                    case INTEGER_CLASS:
                        pw.print(generateRandomInteger()+" ");
                        break;
                    case DOUBLE_CLASS:
                        pw.print(generateRandomDouble()+" ");
                        break;
                    case COMPLEX_CLASS:
                        pw.print(generateRandomComplex().toString()+" ");
                        break;
                }
            }
            pw.println();
        }
    }

    private static Double generateRandomDouble() {
        return generator.nextDouble() * 5;
    }

    private static Integer generateRandomInteger() {
        return generator.nextInt(9)+1;
    }

    private static Complex generateRandomComplex() {
        return new Complex(generateRandomInteger(), generateRandomInteger());
    }

    public static void main(String[] args) {
//        generateMatrixAndWriteToFile(Constants.INTEGER_CLASS, 10, 10, 10, 10);
//        generateMatrixAndWriteToFile(Constants.INTEGER_CLASS, 5, 5, 5, 5);
//        generateMatrixAndWriteToFile(Constants.INTEGER_CLASS, 3, 7, 7, 11);
//        generateMatrixAndWriteToFile(Constants.INTEGER_CLASS, 2, 4, 4, 3);
//        generateMatrixAndWriteToFile(Constants.INTEGER_CLASS, 1000, 1000, 1000, 1000);
//        generateMatrixAndWriteToFile(Constants.INTEGER_CLASS, 1000, 500, 500, 1000);

//        generateMatrixAndWriteToFile(Constants.DOUBLE_CLASS, 10, 10, 10, 10);
//        generateMatrixAndWriteToFile(Constants.DOUBLE_CLASS, 5, 5, 5, 5);
//        generateMatrixAndWriteToFile(Constants.DOUBLE_CLASS, 3, 7, 7, 11);
        generateMatrixAndWriteToFile(Constants.DOUBLE_CLASS, 2, 4, 4, 3);
//        generateMatrixAndWriteToFile(Constants.DOUBLE_CLASS, 1000, 1000, 1000, 1000);
//        generateMatrixAndWriteToFile(Constants.DOUBLE_CLASS, 1000, 500, 500, 1000);
//
//        generateMatrixAndWriteToFile(COMPLEX_CLASS,2,2,2,2);
//        generateMatrixAndWriteToFile(COMPLEX_CLASS,100,100,100,100);
    }

}
