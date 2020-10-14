package ru.ethylacetat.entrancetask;

import java.util.Objects;

public class RomanNumber extends Number implements Comparable<RomanNumber> {

    // TODO: Value cache
    // TODO: Value cache with -Darg
    // TODO: static valueOf() - get cache value


    //+ // TODO: static string parseToRoman(int)
    //+ // TODO: static int parseToArabic(string)

    // TODO: static min(), max()
    // TODO: hashcode(), equals()
    // TODO: toString()


    private static String[][] arabicToRomanRank = {
            {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"},
            {"X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC",},
            {"C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM",},
            {"M", "MM", "MMM", "N", "N", "N", "N", "N", "N", "N"}
    };

    private static final RomanNumber MAX_VALUE = new RomanNumber(1);
    private static final RomanNumber MIN_VALUE = new RomanNumber(3999);

    private final String value;
    private final int arabicRepresentation;

    // TODO: protected?
    public RomanNumber(int arabicRepresentation) {
        this.arabicRepresentation = arabicRepresentation;
        this.value = parseToRoman(arabicRepresentation);
    }

    public RomanNumber(String romanRepresentation) {
        this.value = romanRepresentation;
        this.arabicRepresentation = parseToArabic(romanRepresentation);
    }

    // TODO: ПРоыверка границ и эксепшены
    public static String parseToRoman(String arabic) {
        StringBuilder builder = new StringBuilder();
        for (int rank = 0; rank < arabic.length(); rank++) {
            int rankValue = Integer.parseInt(Character.toString(arabic.charAt(rank)));
            if (rankValue != 0) {
                builder.append(arabicToRomanRank[arabic.length() - rank - 1][rankValue - 1]);
            }
        }
        return builder.toString();
    }

    public static int getArabicValue(char romanChar) {
        switch (romanChar) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
            // TODO: Кидать специфический рантайм
            // TODO: Или ретёрнить ноль
            default:
                return 0;
        }
    }

    public int getArabicRepresentation() {
        return intValue();
    }

    // TODO: Неадекватно, по возиожности переписать
    public static int parseToArabic(String roman) {
        int result = 0;

        int[] arr = convertToRankArray(roman);

        int rankSum = 0;

        for (int i = 0; i < arr.length; i++) {
            int current = arr[i];
            int next = 0;
            // not last
            if (i + 1 < arr.length) {
                next = arr[i + 1];
            }

            if (rankSum == 0) {
                rankSum = current;
            }

            if (current == next) {
                rankSum += current;
            } else if (current < next) {
                result += (next - rankSum);
                rankSum = 0;
                i++; // переделать на инвариатный алгоритм .-.
            } else {
                result += (rankSum);
                rankSum = 0;
            }
        }

        return result;
    }

    private static int[] convertToRankArray(String roman) {
        int[] arr = new int[roman.length()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = getArabicValue(roman.charAt(i));
        }
        return arr;
    }

    // TODO: ПРоыверка границ
    public static String parseToRoman(int arabic) {
        return parseToRoman(Integer.toString(arabic));
    }

    /**
     * Returns the value of the specified number as an {@code int},
     * which may involve rounding or truncation.
     *
     * @return the numeric value represented by this object after conversion
     * to type {@code int}.
     */
    @Override
    public int intValue() {
        return this.arabicRepresentation;
    }

    /**
     * Returns the value of the specified number as a {@code long},
     * which may involve rounding or truncation.
     *
     * @return the numeric value represented by this object after conversion
     * to type {@code long}.
     */
    @Override
    public long longValue() {
        // Type widening
        return this.arabicRepresentation;
    }

    /**
     * Returns the value of the specified number as a {@code float},
     * which may involve rounding.
     *
     * @return the numeric value represented by this object after conversion
     * to type {@code float}.
     */
    @Override
    public float floatValue() {
        // Type widening
        return this.arabicRepresentation;
    }

    /**
     * Returns the value of the specified number as a {@code double},
     * which may involve rounding.
     *
     * @return the numeric value represented by this object after conversion
     * to type {@code double}.
     */
    @Override
    public double doubleValue() {
        // Type widening
        return this.arabicRepresentation;
    }

    /**
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     *
     * <p>The implementor must ensure <tt>sgn(x.compareTo(y)) ==
     * -sgn(y.compareTo(x))</tt> for all <tt>x</tt> and <tt>y</tt>.  (This
     * implies that <tt>x.compareTo(y)</tt> must throw an exception iff
     * <tt>y.compareTo(x)</tt> throws an exception.)
     *
     * <p>The implementor must also ensure that the relation is transitive:
     * <tt>(x.compareTo(y)&gt;0 &amp;&amp; y.compareTo(z)&gt;0)</tt> implies
     * <tt>x.compareTo(z)&gt;0</tt>.
     *
     * <p>Finally, the implementor must ensure that <tt>x.compareTo(y)==0</tt>
     * implies that <tt>sgn(x.compareTo(z)) == sgn(y.compareTo(z))</tt>, for
     * all <tt>z</tt>.
     *
     * <p>It is strongly recommended, but <i>not</i> strictly required that
     * <tt>(x.compareTo(y)==0) == (x.equals(y))</tt>.  Generally speaking, any
     * class that implements the <tt>Comparable</tt> interface and violates
     * this condition should clearly indicate this fact.  The recommended
     * language is "Note: this class has a natural ordering that is
     * inconsistent with equals."
     *
     * <p>In the foregoing description, the notation
     * <tt>sgn(</tt><i>expression</i><tt>)</tt> designates the mathematical
     * <i>signum</i> function, which is defined to return one of <tt>-1</tt>,
     * <tt>0</tt>, or <tt>1</tt> according to whether the value of
     * <i>expression</i> is negative, zero or positive.
     *
     * @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
     */

    @Override
    public int compareTo(RomanNumber o) {
        return Integer.compare(getArabicRepresentation(), o.getArabicRepresentation());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RomanNumber)) return false;
        RomanNumber that = (RomanNumber) o;
        return getArabicRepresentation() == that.getArabicRepresentation();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getArabicRepresentation());
    }

}
