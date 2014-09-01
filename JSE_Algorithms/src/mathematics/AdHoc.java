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

}
