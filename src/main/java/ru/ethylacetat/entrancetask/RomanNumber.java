package ru.ethylacetat.entrancetask;

import java.util.Objects;

// Serializable
public class RomanNumber extends Number implements Comparable<RomanNumber> {

    private static final String[][] arabicToRomanRank = {
            {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"},
            {"X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC",},
            {"C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM",},
            {"M", "MM", "MMM", "N", "N", "N", "N", "N", "N", "N"}
    };

    private static final int MAX_ARABIC_REPRESENTATION = 3999;
    private static final int MIN_ARABIC_REPRESENTATION = 1;

    public static final RomanNumber MAX_VALUE = new RomanNumber(MAX_ARABIC_REPRESENTATION);
    public static final RomanNumber MIN_VALUE = new RomanNumber(MIN_ARABIC_REPRESENTATION);

    private final String value;
    private final int arabicRepresentation;

    // TODO: protected?
    // TODO: ArithmeticException
    public RomanNumber(int arabicRepresentation) {
        this.arabicRepresentation = arabicRepresentation;
        this.value = parseToRoman(arabicRepresentation);
    }

    public RomanNumber(String romanRepresentation) {
        this.value = romanRepresentation;
        this.arabicRepresentation = parseToArabic(romanRepresentation);
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
            default:
                return 0;
        }
    }

    public int getArabicRepresentation() {
        return intValue();
    }

    public static String parseToRoman(int arabic) {
        if (arabic < MIN_ARABIC_REPRESENTATION || arabic > MAX_ARABIC_REPRESENTATION) {
            throw new NumberFormatException("Roman mast be in range [1, 3999] but is " + arabic);
        }
        return parseToRoman(Integer.toString(arabic));
    }

    private static String parseToRoman(String arabic) {
        StringBuilder builder = new StringBuilder();
        for (int rank = 0; rank < arabic.length(); rank++) {
            int rankValue = Integer.parseInt(Character.toString(arabic.charAt(rank)));
            if (rankValue != 0) {
                builder.append(arabicToRomanRank[arabic.length() - rank - 1][rankValue - 1]);
            }
        }
        return builder.toString();
    }

    // TODO: Неадекватно, по возиожности переписать
    // TODO: Проверять формат на неправильный порядок римских символов
    public static int parseToArabic(String roman) {

        if (!roman.matches("[IVXLCDM]+")) {
            throw new NumberFormatException("The wrong roman number format: " + roman);
        }

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

    public String romanValue() {
        return this.value;
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
    public String toString() {
        return this.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getArabicRepresentation());
    }

}
