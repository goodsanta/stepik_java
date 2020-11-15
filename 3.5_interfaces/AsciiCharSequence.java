import java.util.Arrays;

/**
 * Напишите класс <code>AsciiCharSequence</code>, <br>
 * реализующий компактное хранение последовательности ASCII-символов <br>
 * (их коды влезают в один байт) в массиве байт. По сравнению с классом <code>String</code>, <br>
 * хранящим каждый символ как <code>char</code>, <code>AsciiCharSequence</code> будет занимать в два раза меньше памяти.<br>
 *
 * <ul>Класс <code>AsciiCharSequence</code> должен:
 *
 *     <li>реализовывать интерфейс <code>java.lang.CharSequence</code></li>
 *     <li>иметь конструктор, принимающий массив байт</li>
 *     <li>определять методы <code>length()</code>, <code>charAt()</code>, <code>subSequence()</code> и <code>toString()</code></li>
 * </ul>
 * Сигнатуры методов и ожидания по их поведению смотрите в описании интерфейса <code>java.lang.CharSequence</code> (JavaDoc или исходники).
 */
public class AsciiCharSequence implements CharSequence {
    private byte[] bytes;

    public AsciiCharSequence(byte[] bytes) {
        this.bytes = bytes;
    }

    @Override
    public int length() {
        return bytes.length;
    }

    @Override
    public String toString() {
        return new String(bytes);
    }

    @Override
    public char charAt(int index) {
        return (char) bytes[index];
    }

    @Override
    public AsciiCharSequence subSequence(int start, int end) {
        AsciiCharSequence subsequence = new AsciiCharSequence(new byte[end - start]);
        for (int i = 0; start < end; start++) {
            subsequence.bytes[i] = this.bytes[start];
            i++;
        }
        return subsequence;
    }

    public static void main(String[] args) {
        AsciiCharSequence h = new AsciiCharSequence(new byte[] {0x4d, 0x61, 0x78, 127});
        System.out.println(h.length());
        System.out.println(h.charAt(0));
        AsciiCharSequence g = h.subSequence(1, 2);
        System.out.println(g.length());
        System.out.println(g.charAt(0));
        System.out.println(h.toString());
    }
}
