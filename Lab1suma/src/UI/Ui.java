package UI;

import Operations.MatrixCalculator;
import Operations.MatrixCalculatorFactory;
import Operations.OperationName;

import java.util.Objects;
import java.util.Scanner;

public class Ui {

    private static final Scanner scanner = new Scanner(System.in);
    private MatrixCalculator matrixCalculator;

    public void showUi() {
        boolean loop = true;
        while (loop) {
            printCommands();
            switch (scanner.next()) {
                case "1":
                    handleOperation(OperationName.ADD);
                    break;
                case "2":
                    handleOperation(OperationName.MULTIPLICATION);
                    break;
                case "3":
                    handleOperation(OperationName.CIRCLE_POINT);
                case "X":
                    loop = false;
                    break;
                default:
                    System.out.println("Invalid comand, retry!");
            }
        }
    }

    private void handleOperation(OperationName operationName) {
        System.out.println("Give filename");
        String fileName = scanner.next();

        System.out.println("Give test size");
        int test_size = scanner.nextInt();

        matrixCalculator = MatrixCalculatorFactory.getMatrixCalculator(fileName, test_size, operationName);
        try {
            Objects.requireNonNull(matrixCalculator).startThreads();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void printCommands() {
        System.out.println("Menu:");
        System.out.println("1:Sum of 2 matrix");
        System.out.println("2:Multiplication of 2 matrix");
        System.out.println("3.Circle point operation");
        System.out.println("X.exit");
    }

}
