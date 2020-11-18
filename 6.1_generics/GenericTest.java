package one;

import java.util.Objects;

public class GenericTest {

    /**
     * Реализуйте generic-класс <code>Pair</code>, похожий на <code>Optional</code>,<br>
     * но содержащий пару элементов разных типов и не запрещающий элементам принимать значение <code>null</code>.
     * @param <K>
     * @param <V>
     */
    static class Pair<K, V> {
        private final K key;
        private final V value;

        private Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getFirst() {
            return this.key;
        }

        public V getSecond() {
            return this.value;
        }

        public static <K, V> Pair<K, V> of(K key, V value) {
            return new Pair<>(key, value);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Pair)) {
                return false;
            }
            Pair<?, ?> other = (Pair<?, ?>) obj;
            return Objects.equals(this.key, other.key) & Objects.equals(this.value, other.value);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(this.key) + Objects.hashCode(this.value);
        }
    }

    public static void main(String[] args) {
        Pair<Integer, String> pair = Pair.of(1, "hello");
        System.out.println(pair.getFirst());
        System.out.println(pair.getSecond());
        Pair<Integer, String> pairTwo = Pair.of(1, "hello");
        System.out.println(pairTwo.getFirst());
        System.out.println(pairTwo.getSecond());
        System.out.println(pair.hashCode());
        System.out.println(pairTwo.hashCode());
        System.out.println(pair.equals(pairTwo));

    }

}