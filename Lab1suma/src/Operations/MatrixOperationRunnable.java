package Operations;

import Domain.Matrix;
import javafx.util.Pair;

import java.util.function.Function;

public class MatrixOperationRunnable<T> implements Runnable {

    protected Matrix<T> matrix1;
    protected Matrix<T> matrix2;
    protected Matrix<T> matrix3;
    protected Function<Pair<Integer,Integer>, T> operation;
    protected int start_index;
    protected int end_index;

    public MatrixOperationRunnable(Matrix<T> matrix1, Matrix<T> matrix2, Matrix<T> matrix3, Function<Pair<Integer, Integer>, T> operation,
                                   int start_index, int end_index) {
        this.matrix1 = matrix1;
        this.matrix2 = matrix2;
        this.matrix3 = matrix3;
        this.operation = operation;
        this.start_index = start_index;
        this.end_index = end_index;
    }

    @Override
    public void run() {
        int columns = matrix2.getColumns();
        for (int i = start_index; i < end_index; i++) {
            int line = i / columns;
            int column = i % columns;
            matrix3.setElement(line, column, operation.apply(new Pair<>(line,column)));
        }
    }

}

