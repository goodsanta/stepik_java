import java.math.BigInteger;
import java.util.Arrays;

public class Main {

    /**
     * Calculates factorial of given <code>value</code>.
     * @param value
     * @return factorial
     */
    public static BigInteger factorial(int value) {
        BigInteger bigIntegerValue = BigInteger.valueOf(1);
        for (int count = 1;count <= value;count++){
            bigIntegerValue = bigIntegerValue.multiply(BigInteger.valueOf(count));
        }
        return bigIntegerValue;
    }

    /**
     * Реализуйте метод, сливающий два отсортированных по неубыванию массива чисел
     * в один отсортированный в том же порядке массив. Массивы могут быть любой длины, в том числе нулевой.
     *
     * Предполагается, что вы реализуете алгоритм слияния, имеющий линейную сложность:
     * он будет идти по двум исходным массивам и сразу формировать отсортированный результирующий массив.
     * Так, чтобы сортировка полученного массива при помощи <code>Arrays.sort()</code> уже не требовалась.
     * @param a1 first sorted array
     * @param a2 second sorted array
     * @return array merged from a1 and a2, sorted
     */
    public static int[] mergeArrays(int[] a1, int[] a2){
        int[] a3 = new int[a1.length + a2.length];
        int i = 0;
        int j = 0;
        int k = 0;
        Arrays.sort(a1);
        Arrays.sort(a2);
        while (i < a1.length && j < a2.length) {
            if (a1[i] < a2[j]) {
                System.arraycopy(a1,i,a3,k,1);
                //a3[k] = a1[i];
                i++;
                k++;
            } else {
                System.arraycopy(a2,j,a3,k,1);
                //a3[k] = a2[j];
                j++;
                k++;
            }
        }
        //merge remaining elements
        while (i < a1.length) {
            System.arraycopy(a1,i,a3,k,1);
            //a3[k] = a1[i];
            i++;
            k++;
        }
        while (j < a2.length){
            System.arraycopy(a2,j,a3,k,1);
            //a3[k] = a2[j];
            j++;
            k++;
        }
        return a3;
    }

    /**
     * Вам дан список ролей и сценарий пьесы в виде массива строчек.<br>
     *
     * Каждая строчка сценария пьесы дана в следующем виде:<br>
     * Роль: текст<br>
     *
     * Текст может содержать любые символы.<br>
     *
     * Напишите метод, который будет группировать строчки по ролям, пронумеровывать их<br>
     * и возвращать результат в виде готового текста (см. пример). Каждая группа распечатывается в следующем виде:<br>
     *
     * Роль:<br>
     * i) текст<br>
     * j) текст2<br>
     * ...<br>
     * ==перевод строки==<br>
     *
     * i и j -- номера строк в сценарии. Индексация строчек начинается с единицы,<br>
     * выводить группы следует в соответствии с порядком ролей. Переводы строк между группами обязательны,<br>
     * переводы строк в конце текста не учитываются.<br>
     *
     * Заметим, что вам предстоит обработка огромной пьесы в 50 000 строк для 10 ролей – соответственно,<br>
     * неправильная сборка результирующей строчки может выйти за ограничение по времени.<br>
     *
     * <ul>Обратите внимание еще на несколько нюансов:
     *
     *     <li>имя персонажа может встречаться в строке более одного раза, в том числе с двоеточием;</li>
     *     <li>название одной роли может быть префиксом названия другой роли (например, "Лука" и "Лука Лукич");</li>
     *     <li>роль, у которой нет реплик, тоже должна присутствовать в выходном файле;</li>
     *     <li>в качестве перевода строки надо использовать символ '\n' (перевод строки в стиле UNIX);</li>
     *     <li>будьте внимательны, не добавляйте лишних пробелов в конце строк.</li>
     * </ul>
     * @param roles array of roles in scenario
     * @param textLines array of speeches
     * @return scenario with speeches numbered and grouped by roles
     */
    private static String printTextPerRole(String[] roles, String[] textLines){
        StringBuilder finalString = new StringBuilder();
        int i = 0;
        int j = 0;
        int k = 1; //number of speech
        while (j < roles.length) {
            finalString.append(roles[j]);
            finalString.append(":");
            finalString.append('\n');
            while (i < textLines.length) {
                //search array of speeches for needed role
                if (textLines[i].startsWith(roles[j] + ":")) {
                    //if found copy speech and delete role name
                    StringBuilder sbTextLine = new StringBuilder(textLines[i]);
                    sbTextLine.delete(0, roles[j].length() + 2);
                    finalString.append(k);
                    finalString.append(") ");
                    finalString.append(sbTextLine);
                    finalString.append('\n');
                    k++;
                    i++;
                }
                //else search for next speech
                else {
                    i++;
                    k++;
                }
            }
            //go for next role
            finalString.append('\n');
            j++;
            i = 0;
            k = 1;//number of speech starts with one again
        }
        return finalString.toString();
    }

    public static void main(String[] args) {
        System.out.println(factorial(3));
        System.out.println(Arrays.toString(mergeArrays(new int[]{3, 1}, new int[]{0, 0, 0, 5, 7, 6, 2, 1})));
        System.out.println(printTextPerRole (new String[] {"Аммос Федорович", "Городничий", "Артемий Филиппович", "Лука Лукич"},
                new String[] {"Городничий: Я пригласил вас, господа, с тем, чтобы сообщить вам пренеприятное известие: к нам едет ревизор.",
                        "Аммос Федорович: Как ревизор?",
                        "Артемий Филиппович: Как ревизор?",
                        "Городничий: Ревизор из Петербурга, инкогнито. И еще с секретным предписаньем.",
                        "Аммос Федорович: Вот те на!",
                        "Артемий Филиппович: Вот не было заботы, так подай!",
                        "Лука Лукич: Господи боже! еще и с секретным предписаньем!"}));
    }
}