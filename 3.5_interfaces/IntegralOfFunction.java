import java.util.function.DoubleUnaryOperator;

public class IntegralOfFunction {

    /**
     * Реализуйте метод, выполняющий численное интегрирование заданной функции<br>
     * на заданном интервале по формуле левых прямоугольников.<br>
     *
     * Функция задана объектом, реализующим интерфейс <code>java.util.function.DoubleUnaryOperator</code>. <br>
     * Его метод <code>applyAsDouble()</code> принимает значение аргумента и возвращает значение функции в заданной точке.<br>
     *
     * Интервал интегрирования задается его конечными точками <code>a</code> и <code>b</code>, причем <code>a <= b</code>.<br>
     * Для получения достаточно точного результата используйте шаг сетки не больше <code>1.0e-6</code>.<br>
     * @param f function to be integrated
     * @param a starting point of interval
     * @param b ending point of interval
     * @return
     */
    public static double integrate (DoubleUnaryOperator f, double a, double b){
        double h = 1.0e-7; //step of integration
        double result = 0;
        for (; a <= b - h; a += h){
            result += f.applyAsDouble(a); //value of function in present point
        }
        return result *= h;
    }

    public static void main(String[] args) {
        System.out.println(integrate(x -> Math.sin(x)/x, 1 , 5));
    }
}
