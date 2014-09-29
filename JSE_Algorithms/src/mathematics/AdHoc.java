package mathematics;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class AdHoc {

    public BigInteger mcd(BigInteger num, BigInteger den) {
        BigInteger n = num.abs();
        BigInteger d = den.abs();
        if (d == BigInteger.ZERO) {
            return n;
        }
        BigInteger r = BigInteger.ZERO;
        while (d != BigInteger.ZERO) {
            r = n.mod(d);
            n = d;
            d = r;
        }
        return n;
    }

    public String DoubleParseFraction(double Double) {
        String string_double = Double + "";
        int integer = string_double.indexOf('.');
        if (integer == -1) {
            return string_double + "/1";
        } else {
            int size_char = string_double.length() - (integer + 1);
            string_double = string_double.replace(".", "");
            BigInteger den = BigInteger.ONE;
            BigInteger num = new BigInteger(string_double);
            while (size_char-- > 0) {
                den = den.multiply(BigInteger.TEN);
            }
            if (den.compareTo(num) >= 0) {
                BigDecimal decimal_a = new BigDecimal(num);
                BigDecimal decimal_b = new BigDecimal(den);
                BigDecimal divide = decimal_b.divide(decimal_a, 0, RoundingMode.HALF_UP);
                return "1/" + divide;
            } else {
                BigInteger mcd = mcd(num, den);
                return (num.divide(mcd) + "/" + den.divide(mcd));
            }

        }
    }

    public BigInteger[] build_catalanNumbers(int n) {
        BigInteger[] factorial = build_factorial(2 * n + 1);
        BigInteger[] catalan = new BigInteger[n + 1];
        catalan[0] = catalan[1] = BigInteger.ONE;
        for (int i = 2; i < catalan.length; i++) {
            catalan[i] = factorial[2 * i].divide(factorial[i + 1].multiply(factorial[i]));
        }

        return catalan;
    }

    public BigInteger[] build_factorial(int n) {
        BigInteger[] fact = new BigInteger[n + 1];
        fact[1] = BigInteger.ONE;
        for (int i = 2; i < fact.length; i++) {
            fact[i] = fact[i - 1].multiply(BigInteger.valueOf(i));
        }
        return fact;
    }

    public BigInteger[] build_Tiling() {
        BigInteger bigInteger[] = new BigInteger[251];
        bigInteger[0] = bigInteger[1] = BigInteger.ONE;
        bigInteger[2] = BigInteger.valueOf(3);
        for (int i = 3; i < bigInteger.length; i++) {
            bigInteger[i] = bigInteger[i - 1].add(bigInteger[i - 2].multiply(BigInteger.valueOf(2)));
        }
        return bigInteger;
    }

    public BigInteger[] build_TriTiling() {
        BigInteger bigInteger[] = new BigInteger[31];
        bigInteger[0] = BigInteger.ONE;
        bigInteger[2] = BigInteger.valueOf(3);
        for (int i = 4; i < bigInteger.length; i += 2) {
            bigInteger[i] = bigInteger[i - 2].multiply(BigInteger.valueOf(4)).subtract(bigInteger[i - 4]);
        }
        return bigInteger;
    }

    private long[] build_hamming_numbers_x7(int n) {
        long x2 = 2, x3 = 3, x5 = 5, x7 = 7;
        long[] hamming = new long[n + 1];
        hamming[1] = 1;
        int i = 1, j = 1, k = 1, l = 1;
        for (int index = 2; index <= n; index++) {
            hamming[index] = Math.min(Math.min(x2, x3), Math.min(x5, x7));
            if (hamming[index] == x2) {
                x2 = 2 * hamming[++i];
            }
            if (hamming[index] == x3) {
                x3 = 3 * hamming[++j];
            }
            if (hamming[index] == x5) {
                x5 = 5 * hamming[++k];
            }
            if (hamming[index] == x7) {
                x7 = 7 * hamming[++l];
            }
        }
        return hamming;
    }
}
