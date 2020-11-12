public class Main {

    /**
     * Реализуйте метод, который возвращает букву, стоящую в таблице UNICODE после символа "\" (обратный слэш) на расстоянии <code>a</code>.
     * @param a
     * @return
     */
    public static char charExpression(int a){
        return (char) ('\\' + a);
    }

    /**
     * Реализуйте метод, проверяющий, является ли заданное число по абсолютной величине степенью двойки.
     * @param value
     * @return
     */
    public static boolean isPowerOfTwo (int value){
        return Integer.bitCount(Math.abs(value)) == 1;
    }

    public static void main(String[] args) {
        System.out.println(charExpression(4));
        System.out.println(isPowerOfTwo(-2));
    }
}
