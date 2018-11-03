package Operations;

import Domain.Matrix;
import Inputs.MatrixGeneratorUtil;
import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.function.Function;

public abstract class MatrixCalculator<T> {

    protected Matrix<T> matrix1;
    protected Matrix<T> matrix2;
    protected int test_size;
    protected Function<Pair<Integer, Integer>, T> operation;
    protected OperationName operationName;
    private int no_threads;

    public MatrixCalculator(String filename, int test_size, OperationName operationName) {
        Pair<Matrix<T>, Matrix<T>> matrixes = MatrixGeneratorUtil.generateMatrixesFromFile(filename);
        this.matrix1 = Objects.requireNonNull(matrixes).getKey();
        this.matrix2 = matrixes.getValue();
        this.test_size = test_size;
        this.operationName = operationName;
    }

    public void startThreads() throws InterruptedException {
        Matrix<T> matrix3 = new Matrix<>(matrix1.getLines(), matrix2.getColumns());
        try (BufferedReader br = new BufferedReader(new FileReader("threadsConfig.txt"))) {
            String number;
            while ((number = br.readLine()) != null) {
                this.no_threads = Integer.parseInt(number);
                long average_time = 0L;
                for (int i = 0; i < this.test_size; i++) {
                    long startTime = System.nanoTime();
                    matrix3 = calculateMatrixUsingThreads();
                    average_time += (System.nanoTime() - startTime);
                }
                System.out.println(no_threads + " threads in " + average_time / test_size + " nanoseconds");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        MatrixGeneratorUtil.generateFileFromMatrix(matrix3);
    }

    private Matrix<T> calculateMatrixUsingThreads() throws InterruptedException {
        Thread[] threads = new Thread[no_threads];
        int start = 0;
        int lines = matrix1.getLines(), columns = matrix2.getColumns();
        int increasing_size = lines * columns / no_threads;
        int remain = (lines * columns) % no_threads;
        int end;
        Matrix<T> matrix3 = new Matrix<>(lines, columns);

        for (int i = 0; i < no_threads; i++) {
            end = start + increasing_size;
            if (remain > 0) {
                end++;
                remain--;
            }
            MatrixOperationRunnable<T> matrixOperationRunnable = null;
            matrixOperationRunnable = new MatrixOperationRunnable<>(matrix1, matrix2, matrix3, operation, start, end);
            threads[i] = new Thread(matrixOperationRunnable);
            threads[i].start();
            start = end;
        }
        for (int i = 0; i < no_threads; i++) {
            threads[i].join();
        }
        return matrix3;
    }

}
