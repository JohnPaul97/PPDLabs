package Operations;

import Domain.Complex;

public class MatrixComplexCalculator extends MatrixCalculator<Complex> {

    public MatrixComplexCalculator(String filename, int test_size, OperationName operationName) {
        super(filename, test_size, operationName);
        switch (operationName) {
            case ADD:
                this.operation = pair -> {
                    int line = pair.getKey();
                    int column = pair.getValue();
                    return matrix1.getElement(line, column).add(matrix2.getElement(line, column));
                };
                break;
            case MULTIPLICATION:
                this.operation = pair -> {
                    int line = pair.getKey();
                    int column = pair.getValue();
                    int columns = matrix1.getColumns();
                    Complex S = new Complex(0, 0);
                    for (int i = 0; i < columns; S = new Complex(S.add(matrix1.getElement(line, i).multiple(matrix2.getElement(i, column)))), i++)
                        ;
                    return S;
                };
            case CIRCLE_POINT:
                this.operation = (pair) -> {
                    int line = pair.getKey();
                    int column = pair.getValue();
                    Complex inverse3 = matrix1.getElement(line, column).inverse().add(matrix2.getElement(line, column).inverse());
                    return Complex.inverse(inverse3);
                };
        }
    }

}
