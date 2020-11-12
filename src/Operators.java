import java.math.*;

public class Operators {

    /**
     * Реализуйте метод, возвращающий true, если среди четырех его аргументов ровно два истинны (любые). Во всех остальных случаях метод должен возвращать false.
     */
    public static boolean trueOrFalse (boolean q1, boolean q2, boolean q3, boolean q4) {
        boolean q5 = (!q1&!q2&q3&q4)|(!q1&q2&!q3&q4)|(!q1&q2&q3&!q4)|(q1&!q2&!q3&q4)|(q1&!q2&q3&!q4)|(q1&q2&!q3&!q4);
        return q5;
    }

    /**
     * В Григорианском календаре год является високосным в двух случаях: либо он кратен 4, но при этом не кратен 100, либо кратен 400.
     *
     * Реализуйте метод, вычисляющий количество високосных лет с начала нашей эры (первого года) до заданного года включительно.
     */
    public static int yearCount (int year) {
        return ((year/4) - (year/100) + (year/400));
    }

    /**
     * Реализуйте метод, возвращающий ответ на вопрос: правда ли, что a + b = c?
     * Допустимая погрешность – 0.0001 (1E-4)
     */
    public static boolean doubleExpression (double a, double b, double c) {
        boolean e;
        e = Math.abs(a+b-c)<0.0001;
        return e;
    }

    /**
     * Реализуйте метод flipBit, изменяющий значение одного бита заданного целого числа на противоположное.
     * @param value     any number
     * @param bitIndex  index of the bit to flip, 1 <= bitIndex <= 32
     * @return new value with one bit flipped
     */
    public static int flipBit (int value, int bitIndex) {
        int mask = 1; // because mask == 0 return the same param value
        mask = mask << bitIndex-1;
        value = value ^ mask;
        return value;
    }

    public static void main(String[] args) {
        System.out.println(trueOrFalse(false,false,true,true));
        System.out.println(yearCount(2020));
        System.out.println(doubleExpression(0.1,0.2,0.3));
        System.out.println(flipBit(3,1));
    }
}
