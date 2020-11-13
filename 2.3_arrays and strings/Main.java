public class Main {

    /**
     * Реализуйте метод, проверяющий, является ли заданная строка палиндромом.
     * @param text
     * @return
     */
    public static boolean isPalindrome(String text) {
        //delete all symbols except numbers and characters
        text = text.replaceAll("[^0-9a-zA-Z]", "");
        text = text.toLowerCase();
        StringBuilder sb = new StringBuilder(text);
        sb = sb.reverse();
        String text3 = sb.toString();
        return text.equals(text3);
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome("Madam, I'm Adam!"));
    }
}