package one;

public class Main {

    /**
     * sqrt method with checked exception.
     * @param x
     * @return
     * @throws IllegalArgumentException
     */
    public static double sqrt(double x) throws IllegalArgumentException {
        if (x < 0) {
            throw new IllegalArgumentException("Expected non-negative number, got " + x);
        }
        return Math.sqrt(x);
    }


    /**
     * Метод <code>getCallerClassAndMethodName()</code> должен возвращать имя класса и метода,<br>
     * откуда вызван метод, вызвавший данный утилитный метод.<br>
     * Или <code>null</code> (нулевую ссылку, а не строку "null"), если метод,<br>
     * вызвавший <code>getCallerClassAndMethodName()</code> является точкой входа в программу, т.е. его никто не вызывал.
     * @return full class name and method name from where this method was called
     */
    public static String getCallerClassAndMethodName() {
        Throwable t = new Throwable();
        StackTraceElement[] el = t.getStackTrace();
        StringBuilder sb = new StringBuilder();
        //check if method called from entry point
        if (el.length <= 2) {
            return null;
        }
        //if not, take name of method from where this method was called
        else {
            sb.append(el[2].getClassName());
            sb.append("#");
            sb.append(el[2].getMethodName());
            return sb.toString();
        }
    }

    public static void anotherMethod() {
        System.out.println(getCallerClassAndMethodName());
    }

    public static void main(String[] args) throws Exception {
        System.out.println(sqrt(9));
        //the following method call return null pointer.
        System.out.println(getCallerClassAndMethodName());
        //this return class name and method name
        anotherMethod();
    }
}