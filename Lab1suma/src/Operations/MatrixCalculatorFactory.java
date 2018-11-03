package Operations;

import Inputs.MatrixGeneratorUtil;

import java.util.Objects;

import static Inputs.Constants.COMPLEX_CLASS;
import static com.sun.org.apache.xalan.internal.xsltc.compiler.Constants.DOUBLE_CLASS;
import static com.sun.org.apache.xalan.internal.xsltc.compiler.Constants.INTEGER_CLASS;

public final class MatrixCalculatorFactory {

    public static MatrixCalculator getMatrixCalculator(String filename, int test_size, OperationName operationName) {
        String classname = MatrixGeneratorUtil.getElementsType(filename);
        switch (Objects.requireNonNull(classname)) {
            case INTEGER_CLASS:
                return new MatrixIntegerCalculator(filename, test_size, operationName);
            case DOUBLE_CLASS:
                return new MatrixDoubleCalculator(filename, test_size, operationName);
            case COMPLEX_CLASS:
                return new MatrixComplexCalculator(filename, test_size, operationName);
        }
        return null;
    }

}
