package sample;

import java.util.concurrent.ThreadLocalRandom;


public class MyRSA {

    private static final int MAX_RANGE = 64;
    private static final int ALPHABET_SIZE = 27;

    private static final ThreadLocalRandom localRandom = ThreadLocalRandom.current();

    private String plainText;

    private long n;
    private long phi;
    private long k; //min_bound_power
    private long l; //max_bound_power
    private long e; //(n,e) public key

    private long d; // private key


    public MyRSA(int k, int l, String plainText) {
        this.plainText = plainText;
        this.k = k;
        this.l = l;
    }

    public MyRSA(int k, int l, int n, int e, String plainText) {
        this.k = k;
        this.l = l;
        this.n = n;
        this.e = e;
        long p = getP(n);
        this.phi = (p - 1) * (n / p - 1);
        this.plainText = plainText;
    }

    public void computeAlgorithm() {
        n = generateN();
        System.out.println("n is " + n);
        e = generateE();
        System.out.println("e is" + e);
    }

    /**
     * -1 if the condition isn't respected
     *
     * @param number
     * @return
     */
    private long getP(long number) {
        long sqrt = (long) Math.sqrt(number);
        sqrt -= (1 - sqrt % 2);
        for (long i = sqrt; i >= 3; i -= 2) {
            if (isPrime(i) && (i != number / i) && isPrime(number / i)) {
                return i;
            }
        }
        return -1;
    }

    private long generateN() {

        long p = -1;
        long n = 0;
        long min_value = (long) Math.pow(ALPHABET_SIZE, k);
        long max_value = (long) Math.pow(ALPHABET_SIZE, l);
        do {
            n = localRandom.nextLong(min_value, max_value);
            p = getP(n);
        }
        while (p == -1);
        System.out.println("p" + p);
        System.out.println("q" + n / p);

        phi = (p - 1) * (n / p - 1);

        return n;
    }

    private boolean isPrime(long number) {
        long sqrt = (long) Math.sqrt(number);
        for (int i = 2; i <= sqrt; ++i) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    private long generateE() {
        long e = 0;
        do {
            e = localRandom.nextLong(phi);
        } while (!areCoprimes(phi, e));
        return e;
    }

    private boolean areCoprimes(long nr1, long nr2) {

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

        return nr1 == 1;
    }

    public static String toString(int[] encription) {
        int length = encription.length;
        StringBuilder stringBuilder = new StringBuilder();
        for (int result : encription) {
            if (result == 0) {
                stringBuilder.append(' ');
            } else {
                stringBuilder.append(Character.toChars('A' + result - 1));
            }
        }
        return stringBuilder.toString();
    }

    public static int[] toNumerical(String plainText) {
        int length = plainText.length();
        int[] numerical = new int[length];
        plainText = plainText.toLowerCase();
        for (int i = 0; i < length; i++) {
            char currentCharacter = plainText.charAt(i);
            if (currentCharacter == ' ') {
                numerical[i] = 0;
            } else {
                numerical[i] = currentCharacter - 'a' + 1;
            }
        }
        return numerical;
    }

}
