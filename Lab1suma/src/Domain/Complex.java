package Domain;

public class Complex {

    private double real;
    private double imaginar;

    public Complex(Complex c) {
        this.real = c.real;
        this.imaginar = c.imaginar;
    }

    public Complex(double real, double imaginar) {
        this.real = real;
        this.imaginar = imaginar;
    }

    public Complex(String complex) {
        int plusIndex = complex.lastIndexOf("+");
        int minusIndex = complex.lastIndexOf("-");
        int signIndex = plusIndex > 0 ? plusIndex : minusIndex;
        this.real = Double.parseDouble(complex.substring(0, signIndex));
        this.imaginar = Double.parseDouble(complex.substring(signIndex, complex.length() - 1));
    }

    public Complex add(Complex c) {
        return new Complex(this.real + c.real, this.imaginar + c.imaginar);
    }

    public Complex multiple(Complex c) {
        return new Complex(this.real * c.real - this.imaginar * c.imaginar,
                this.real * c.imaginar + this.imaginar * c.real);
    }

    public Complex inverse() {
        double denominator = this.real * this.real + this.imaginar * this.imaginar;
        return new Complex(this.real / denominator, -this.imaginar / denominator);
    }

    public static Complex inverse(Complex c) {
        double denominator = c.real * c.real + c.imaginar * c.imaginar;
        return new Complex(c.real / denominator, -c.imaginar / denominator);
    }

    @Override
    public String toString() {
        return imaginar >= 0 ? this.real + "+" + this.imaginar + "i" : "" + this.real + this.imaginar + "i";
    }

    public static void main(String[] args) {

        Complex ccc = new Complex("-2+7i");
        Complex ccc2 = new Complex("0+1i");
        System.out.println(ccc.add(ccc2));
        System.out.println(ccc.multiple(ccc2));

    }

}
