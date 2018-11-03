package Inputs;

import Domain.Matrix;
import javafx.util.Pair;

import java.io.*;
import java.lang.reflect.InvocationTargetException;

import static Inputs.Constants.*;

/**
 * util class that have methods for writing matrixes to files or storing them in memory from files
 */
public final class MatrixGeneratorUtil {

    /**
     * @param matrix to be written
     */
    public static <T> void generateFileFromMatrix(Matrix<T> matrix) {
        int lines = matrix.getLines();
        int columns = matrix.getColumns();
        try (PrintWriter pw = new PrintWriter(new FileWriter(SOLUTION + lines + SEPARATOR + columns))) {
            pw.println(lines + ELEMENTS_SEPARATOR + columns);
            for (int i = 0; i < lines; i++) {
                for (int j = 0; j < columns; j++) {
                    pw.print(matrix.getElement(i, j) + ELEMENTS_SEPARATOR);
                }
                pw.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param fileName
     * @return a pair of matrixes
     */
    public static <T> Pair<Matrix<T>, Matrix<T>> generateMatrixesFromFile(String fileName) {

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String className = br.readLine();
            return new Pair<>(generateSimpleMatrix(br, className), generateSimpleMatrix(br, className));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getElementsType(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            return br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param br
     * @return a matrix that had already been written to a file
     * @throws IOException
     */
    private static <T> Matrix<T> generateSimpleMatrix(BufferedReader br, String className) throws IOException {

        String[] values = br.readLine().split(ELEMENTS_SEPARATOR);
        int lines = Integer.parseInt(values[0]);
        int columns = Integer.parseInt(values[1]);
        Matrix<T> matrixResult = new Matrix<>(lines, columns);

        for (int i = 0; i < lines; i++) {
            values = br.readLine().split(ELEMENTS_SEPARATOR);
            for (int j = 0; j < columns; j++) {
                try {
                    matrixResult.setElement(i, j, (T) Class.forName(className).getConstructor(String.class).newInstance(values[j]));
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        return matrixResult;
    }

}
