package Domain;

public class Matrix<T> {

    private T[][] values;
    private int lines;
    private int columns;

    public Matrix(int lines, int columns) {
        this.lines = lines;
        this.columns = columns;
        this.values = (T[][]) new Object[lines][columns];
    }

    public int getLines() {
        return lines;
    }

    public int getColumns() {
        return columns;
    }

    public T getElement(int line, int column) {
        return values[line][column];
    }

    public void setElement(int line, int column, T value) {
        values[line][column] = value;
    }

}
