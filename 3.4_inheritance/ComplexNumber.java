/**
 * Дан класс <code>ComplexNumber</code>.<br>
 * Переопределите в нем методы <code>equals()</code> и <code>hashCode()</code> так,<br>
 * чтобы <code>equals()</code> сравнивал экземпляры <code>ComplexNumber</code> по содержимому полей <code>re</code> и <code>im</code>,<br>
 * а <code>hashCode()</code> был бы согласованным с реализацией <code>equals()</code>.<br>
 *
 * Реализация <code>hashCode()</code>, возвращающая константу или не учитывающая дробную часть <code>re</code> и <code>im</code>, засчитана не будет
 */
public final class ComplexNumber {
    private final double re;
    private final double im;

    public ComplexNumber(double re, double im) {
        this.re = re;
        this.im = im;
    }

    public double getRe() {
        return re;
    }

    public double getIm() {
        return im;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ComplexNumber that = (ComplexNumber) o;
        return (Double.compare(this.re, that.re) == 0 && Double.compare(this.im, that.im) == 0);
    }

    @Override
    public int hashCode() {
        Object reThat = (Object) this.re;
        Object imThat = (Object) this.im;
        return reThat.hashCode() + imThat.hashCode();
    }

    public static void main(String[] args) {
        ComplexNumber a = new ComplexNumber(1,0);
        ComplexNumber b = new ComplexNumber(1,0);
        System.out.println(b.hashCode());
        System.out.println(a.hashCode());
        System.out.println(a.equals(b));
    }
}