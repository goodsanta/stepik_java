package one;

import java.util.*;

public class CollectionsTest<T> {

    /**
     * Method for calculating symmetric difference of two sets.
     * @param set1 first set
     * @param set2 second set
     * @param <T>
     * @return new set
     */
    public static <T> Set<T> symmetricDifference(Set<? extends T> set1, Set<? extends T> set2) {
        Set<T> set3 = new HashSet<>();
        try {
            for (T element : set1) {
                if (!(set2.contains(element))) {
                    set3.add(element);
                }
            }
            for (T element : set2) {
                if (!(set1.contains(element))) {
                    set3.add(element);
                }
            }
        }
        catch (ClassCastException | NullPointerException e) {
            throw e;
        }
        return set3;
    }

    /**
     * Напишите программу, которая прочитает из <code>System.in</code> последовательность целых чисел,<br>
     * разделенных пробелами, затем удалит из них все числа, стоящие на четных позициях,<br>
     * и затем выведет получившуюся последовательность в обратном порядке в <code>System.out</code>.
     * @param
     */
    public static void deleteEven() {
        List<Integer> list = new ArrayList<>();
        try (Scanner scanner = new Scanner(System.in)) {
            while (scanner.hasNextInt()) {
                list.add(scanner.nextInt());
            }
        }
        for (int i = 0; i < list.size(); i++) {
            list.remove(i);
        }
        Collections.reverse(list);
        for (Integer element : list) {
            System.out.print(element + " ");
        }
    }

    public static void main(String[] args) {
//        System.out.println(symmetricDifference(setOne, setTwo));
        deleteEven();
    }
}
