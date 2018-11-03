package Operations;

public class MatrixIntegerCalculator extends MatrixCalculator<Integer> {

    public MatrixIntegerCalculator(String filename, int test_size, OperationName operationName) {
        super(filename, test_size, operationName);
        switch
                (operationName) {
            case ADD:
                this.operation = (pair) -> {
                    int line = pair.getKey();
                    int column = pair.getValue();
                    return matrix1.getElement(line, column) + matrix2.getElement(line, column);
                };
                break;
            case MULTIPLICATION:
                this.operation = (pair) -> {
                    int line = pair.getKey();
                    int column = pair.getValue();
                    int S = 0;
                    int columns = matrix1.getColumns();
                    for (int i = 0; i < columns; S += matrix1.getElement(line, i) * matrix2.getElement(i, column), i++)
                        ;
                    return S;
                };
                break;
            case CIRCLE_POINT:
                this.operation = (pair) -> {
                    int line = pair.getKey();
                    int column = pair.getValue();
                    return 1/(1/matrix1.getElement(line,column)+matrix2.getElement(line,column));
                };
        }
    }

}
