package util;



import java.math.BigInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Fraction {
    // 分子
    private BigInteger numerator;

    // 分母
    private BigInteger denominator;


    public Fraction(BigInteger numerator, BigInteger denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
        simplify();  // 构造后直接化简分数
    }


    public Fraction(String number) {
        BigInteger integerPart,numerator = null,denominator = BigInteger.ONE;
        if (number.matches("(\\d+)’(\\d+)/(\\d+)")) {
            String pattern = "(\\d+)’(\\d+)/(\\d+)";
            Pattern r = Pattern.compile(pattern);
            Matcher m = r.matcher(number);
            if (m.find()) {
                integerPart = new BigInteger(m.group(1));
                numerator = new BigInteger(m.group(2));
                denominator = new BigInteger(m.group(3));
                numerator = numerator.add(integerPart.multiply(denominator));
            }
        }else if (number.matches("(\\d+)/(\\d+)")){
            String pattern = "(\\d+)/(\\d+)";
            Pattern r = Pattern.compile(pattern);
            Matcher m = r.matcher(number);
            if (m.find()) {
                numerator = new BigInteger(m.group(1));
                denominator = new BigInteger(m.group(2));
            }
        }else {
            numerator = new BigInteger(number);
        }
        this.numerator = numerator;
        this.denominator = denominator;
    }

    // 将分数化简为最简分数
    private void simplify() {
        BigInteger gcd = numerator.gcd(denominator);
        numerator = numerator.divide(gcd);
        denominator = denominator.divide(gcd);
        if (denominator.compareTo(BigInteger.ZERO) < 0) {
            numerator = numerator.negate();
            denominator = denominator.negate();
        }
    }


    // 加法
    public Fraction add(Fraction other) {
        BigInteger newNumerator = numerator.multiply(other.denominator).add(denominator.multiply(other.numerator));
        BigInteger newDenominator = denominator.multiply(other.denominator);
        return new Fraction(newNumerator, newDenominator);
    }

    // 减法
    public Fraction subtract(Fraction other) {
        BigInteger newNumerator = numerator.multiply(other.denominator).subtract(denominator.multiply(other.numerator));
        BigInteger newDenominator = denominator.multiply(other.denominator);
        return new Fraction(newNumerator, newDenominator);
    }

    // 乘法
    public Fraction multiply(Fraction other) {
        BigInteger newNumerator = numerator.multiply(other.numerator);
        BigInteger newDenominator = denominator.multiply(other.denominator);
        return new Fraction(newNumerator, newDenominator);
    }

    // 除法
    public Fraction divide(Fraction other) {
        BigInteger newNumerator = numerator.multiply(other.denominator);
        BigInteger newDenominator = denominator.multiply(other.numerator);
        return new Fraction(newNumerator, newDenominator);
    }

    // 将分数转换为字符串
    @Override
    public String toString() {
        if (denominator.equals(BigInteger.ONE)) {
            return numerator.toString();  // 整数直接返回分子
        } else {
            return numerator.toString() + "/" + denominator.toString();  // 分数返回分子/分母
        }
    }

}