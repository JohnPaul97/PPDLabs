package sample;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    private static boolean isPrime(long number) {
        if (number == 1 || number == 0) {
            return false;
        }
        long sqrt = (long) Math.sqrt(number);
        for (int i = 2; i <= sqrt; ++i) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    private static long areCoprimes(long nr1, long nr2) {

        if (nr2 > nr1) {
            long aux = nr1;
            nr1 = nr2;
            nr2 = aux;
        }

        long r = 0;

        do {
            r = nr1 % nr2;
            nr1 = nr2;
            nr2 = r;
        }
        while (nr2 != 0);

        return nr1;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
//        primaryStage.setTitle("Hello World");
//        primaryStage.setScene(new Scene(root, 300, 275));
//        primaryStage.show();
    }


    public static void main(String[] args) {

        new MyRSA(2, 3, "bla").computeAlgorithm();
//        launch(args);

    }
}
